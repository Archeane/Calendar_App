package com.example.jenny.anothertry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.jenny.anothertry.BaseActivity.convertStringToDate;

public class FreeTime {

    private final String TAG = "FreeTimeDebug";

    //private List<List<Calendar>> freeTimes = new ArrayList<List<Calendar>>();
    private Calendar[][] freeTimes;
    private long commonFreeTime;
    private List<Date> commonFreeTimeArr;
    private int commonTimeOverlapCount;
    private Calendar commonFreeTimeStart;
    private Calendar commonFreeTimeEnd;
    private int minimumThreshold;

    private Utilities utility;
    //TODO: fix this stupid constructor that keeps setting the start time of the last event of the day to 00:00 of next day
    //TODO: this constructor only works for a list of events with the same date. The events also have to have the same date as the date on computer. To fix this set freeTimeStart & end to the date on the event
    public FreeTime(List<ClassicEvent> e, int threshold, String currentDate) {
        utility = new Utilities();
        freeTimes = new Calendar[e.size()+1][2];
        minimumThreshold = threshold;
        Calendar freeTimeStart = Calendar.getInstance();
        freeTimeStart.setTime(convertStringToDate("currentDate"));
        Calendar freeTimeEnd = (Calendar)freeTimeStart.clone();
        for (int i = -1; i < e.size(); i++) {
            if (i == -1) {
                Date one = e.get(0).getStartTime().getTime();
                if (!(one.getHours() == 0 && one.getMinutes() == 0)) {    //if first event of today starts after 00:00
                    String eventDate = e.get(0).getEventDate();
                   /* int eventYear = Integer.parseInt(eventDate.substring(7,11));
                    int eventMonth = utility.correspondingMonthFromString(eventDate.substring(0,4));
                    int eventDay = Integer.parseInt(eventDate.substring(4, 6));

                    Date ftStart = freeTimeStart.getTime();
                   */
                    freeTimeStart.set(Calendar.HOUR_OF_DAY,0);
                    freeTimeStart.set(Calendar.MINUTE,0);
                    freeTimeStart.set(Calendar.SECOND,0);
                    Date ftStart = freeTimeStart.getTime();
                    freeTimeEnd = e.get(0).getStartTime();
                    Date ftend = freeTimeEnd.getTime();
                    long difference = freeTimeEnd.getTimeInMillis() - freeTimeStart.getTimeInMillis();
                    if (difference >= threshold * 60 * 1000) { //threshold is in minutes
                        freeTimes[i + 1][0] = freeTimeStart;
                        freeTimes[i + 1][1] = freeTimeEnd;
                    }
                }
            } else if (i == e.size() - 1) {     //at the last event of day and event ends within today
                freeTimeStart = e.get(i).getEndTime();
                Calendar endOfDay = Calendar.getInstance();
                endOfDay.setTime(convertStringToDate(currentDate));
                endOfDay.set(Calendar.HOUR_OF_DAY,24);
                endOfDay.set(Calendar.MINUTE,0);
                endOfDay.set(Calendar.SECOND,0);
                long difference = endOfDay.getTimeInMillis() - freeTimeStart.getTimeInMillis();
                if (difference >= threshold * 60 * 1000) { //threshold is in minutes
                    freeTimes[i + 1][0] = freeTimeStart;
                    freeTimes[i + 1][1] = endOfDay;
                }
            } else {      //at neither the start nor end of todays events
                freeTimeStart = e.get(i).getEndTime();
                freeTimeEnd = e.get(i + 1).getStartTime();
                long difference = freeTimeEnd.getTimeInMillis() - freeTimeStart.getTimeInMillis();
                if (difference >= threshold * 60 * 1000) {      //threshold is in minutes
                    freeTimes[i + 1][0] = freeTimeStart;
                    freeTimes[i + 1][1] = freeTimeEnd;
                }
            }
        }
    }

    //when user clicks a free time and wants to see whose free;
    public boolean compareFreeTime(Calendar timeClicked, FreeTime another, int threshold){
        commonFreeTimeArr = new ArrayList<Date>();
        Calendar freeTimeStart = getFreeTimeFromEmptyViewClick(timeClicked)[0]; //Todo: debug this method. Trying to get comparefreeTime to work for multiple free time overlaps
        Date start = freeTimeStart.getTime();
        Calendar freeTimeEnd = getFreeTimeFromEmptyViewClick(timeClicked)[1];
        Date end = freeTimeEnd.getTime();
        commonTimeOverlapCount = 0;
        for(int i = 0; i < another.getSize();i++){
            Calendar anotherEndTime = another.getEndTimeAt(i);
            Date d = anotherEndTime.getTime();
            if (anotherEndTime.compareTo(freeTimeStart) >= 0){ //when another person's free time ends after my start time -> free time overlaps
                Calendar anotherStartTime = another.getStartTimeAt(i);
                Date a = anotherStartTime.getTime();
                if(anotherStartTime.compareTo(freeTimeEnd) < 0){ // when anthoer person's free time starts before the end time of my free time block
                    long startTimeDifference = freeTimeStart.getTimeInMillis() - anotherStartTime.getTimeInMillis();
                    long endTimeDifference = freeTimeEnd.getTimeInMillis() - anotherEndTime.getTimeInMillis();
                    if(startTimeDifference <= 0 && endTimeDifference <= 0){
                        long commonTime = freeTimeEnd.getTimeInMillis() - freeTimeStart.getTimeInMillis();
                        if(commonTime >= threshold * 60 * 1000){
                            commonFreeTime += commonTime;
                            commonTimeOverlapCount++;
                            commonFreeTimeArr.add(freeTimeStart.getTime());
                            commonFreeTimeArr.add(freeTimeEnd.getTime());
                        }
                    }else if(startTimeDifference <= 0 && endTimeDifference >= 0){
                        long commonTime = anotherEndTime.getTimeInMillis() - freeTimeStart.getTimeInMillis();
                        if(commonTime >= threshold * 60 * 1000){
                            commonFreeTime += commonTime;
                            commonTimeOverlapCount++;
                            commonFreeTimeArr.add(freeTimeStart.getTime());
                            commonFreeTimeArr.add(anotherEndTime.getTime());
                        }
                    }else if(startTimeDifference >= 0 && endTimeDifference >= 0){
                        long commonTime = anotherEndTime.getTimeInMillis() - anotherStartTime.getTimeInMillis() ;
                        if(commonTime >= threshold * 60 * 1000){
                            commonFreeTime += commonTime;
                            commonTimeOverlapCount++;
                            commonFreeTimeArr.add(anotherStartTime.getTime());
                            commonFreeTimeArr.add(anotherEndTime.getTime());
                        }
                    }else if(startTimeDifference <= 0 && endTimeDifference <= 0){
                        long commonTime = freeTimeEnd.getTimeInMillis() - anotherStartTime.getTimeInMillis() ;
                        if(commonTime >= threshold * 60 * 1000){
                            commonFreeTime += commonTime;
                            commonTimeOverlapCount++;
                            commonFreeTimeArr.add(anotherStartTime.getTime());
                            commonFreeTimeArr.add(freeTimeEnd.getTime());
                        }
                    }
                }
            }
        }
        if(commonTimeOverlapCount > 0){
            return true;
        }
        return false;
    }

    //when user wants to see whose free during a certain time period for a threshold time
    public boolean compareFreeTime(Calendar freeTimeStart, Calendar freeTimeEnd, FreeTime another, int threshold){
        commonTimeOverlapCount = 0;
        for(int i = 0; i < another.getSize();i++){
            Calendar anotherEndTime = another.getEndTimeAt(i);
            Date d = anotherEndTime.getTime();
            if (anotherEndTime.compareTo(freeTimeStart) >= 0){ //when another person's free time ends after my start time -> free time overlaps
                Calendar anotherStartTime = another.getStartTimeAt(i);
                Date a = anotherStartTime.getTime();
                if(anotherStartTime.compareTo(freeTimeEnd) < 0){ // when anthoer person's free time starts before the end time of my free time block
                    long startTimeDifference = freeTimeStart.getTimeInMillis() - anotherStartTime.getTimeInMillis();
                    long endTimeDifference = freeTimeEnd.getTimeInMillis() - anotherEndTime.getTimeInMillis();
                    if(startTimeDifference <= 0 && endTimeDifference <= 0){
                        long commonTime = freeTimeEnd.getTimeInMillis() - freeTimeStart.getTimeInMillis();
                        if(commonTime >= threshold * 60 * 1000){
                            commonFreeTime += commonTime;
                            commonTimeOverlapCount++;
                        }
                    }else if(startTimeDifference <= 0 && endTimeDifference >= 0){
                        long commonTime = anotherEndTime.getTimeInMillis() - freeTimeStart.getTimeInMillis();
                        if(commonTime >= threshold * 60 * 1000){
                            commonFreeTime += commonTime;
                            commonTimeOverlapCount++;
                        }
                    }else if(startTimeDifference >= 0 && endTimeDifference >= 0){
                        long commonTime = anotherEndTime.getTimeInMillis() - anotherStartTime.getTimeInMillis() ;
                        if(commonTime >= threshold * 60 * 1000){
                            commonFreeTime += commonTime;
                            commonTimeOverlapCount++;
                        }
                    }else if(startTimeDifference <= 0 && endTimeDifference <= 0){
                        long commonTime = freeTimeEnd.getTimeInMillis() - anotherStartTime.getTimeInMillis() ;
                        if(commonTime >= threshold * 60 * 1000){
                            commonFreeTime += commonTime;
                            commonTimeOverlapCount++;
                        }
                    }
                }
            }
        }
        if(commonTimeOverlapCount > 0){
            return true;
        }
        return false;
    }

    //when user wants to make plans with another individual; returns the available free times

    //gets the start of the free time block clicked. Ex: if free time starts at 17:00 and ends at 19:00, method will return arr of 17:00 & 19:00 if time clicked is between those two times
    public Calendar[] getFreeTimeFromEmptyViewClick(Calendar timeClicked){
        Calendar[] arr = new Calendar[2];
        try {
            for (int i = 0; i < freeTimes.length; i++) {
                if (timeClicked.compareTo(getStartTimeAt(i)) > 0 &&   //if timeclicked is after startTime
                        timeClicked.compareTo(getEndTimeAt(i)) < 0) { //if timeclicked is before endTime
                    arr[0] = getStartTimeAt(i);
                    arr[1] = getEndTimeAt(i);
                    return arr;
                }
            }
            throw new NullPointerException("time clicked is not a free time or is not found in free time array for today");
        }catch(NullPointerException e){
            boolean ohnotheresabug = true;
        }
        return arr;
    }

    public Calendar getStartTimeAt(int pos){
        return freeTimes[pos][0];
    }

    public Calendar getEndTimeAt(int pos){
        return freeTimes[pos][1];
    }

    public int getSize(){
        return freeTimes.length;
    }

    public Calendar[][] getFreeTimes(){
        return freeTimes;
    }

    public long getCommonFreeTime(){
        return commonFreeTime/60000;
    }

    public int getCommonTimeOverlapCount(){return commonTimeOverlapCount;}

    public void deleteFreeTime(Calendar startTime, Calendar endTime, int timeBlock){

    }

    /*
    public String toString(){
        String str = "";
        for(int i = 0; i < freeTimes.length;i++){
            for(int j = 0; j < freeTimes[i].length; j++){
                Calendar test = freeTimes[i][j];
                String str2 = test.getTime().toString();
                str += str2;
            }
        }
        return str;
    }
/*
    public void changeFreeTimeFromEvent(WeekViewEvent event){
        long eStartTime = event.getStartTime().getTimeInMillis();
        long eEndTime = event.getEndTime().getTimeInMillis();

        //optimize
        for(int i = 0; i < events.size()-1; i++){
            if(events.get(i).get())
        }
    }
*/


}
