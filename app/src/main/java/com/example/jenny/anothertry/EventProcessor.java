package com.example.jenny.anothertry;

import android.annotation.TargetApi;
import android.util.Log;

import com.example.jenny.anothertry.ClassicEvent;
import com.example.jenny.anothertry.FreeTime;
import com.example.jenny.anothertry.Utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EventProcessor{

    private static final String TAG = "debug process events";

    private List<ClassicEvent> histroyEvents;
    private Date firstHistoryEventDate;
    private boolean historyEventsInitialized = false;

    private ClassicEvent[][] presentMonthlyEvents;
    private List<ClassicEvent> presentMonthlyEventsQuickAccess;
    private Date firstMonthEventDate;
    private long firstMonthEventDateInMillis;
    private Date lastMonthEventDate;
    private long lastMonthEventDateInMillis;
    private long lengthOfTimeBlockInMili;
    private int numOfTimeBlock;
    private boolean presentMonthlyEventsInitialized = false;

    private ClassicEvent[] presentYearEvents;

    private List<ClassicEvent>[] futureEvents;
    private int[] eventIndexes;
    private Date firstFutureEventDate;
    private boolean futureEventsInitialized = false;

    private FreeTime[] presentMonthFreeTime;
    private FreeTime[] presentYearFreeTime;

    private Utilities utility;
    private Date todaysDate;
    private int eventCounter;

    @TargetApi(11)
    public EventProcessor(Date date){
        utility = new Utilities();
        todaysDate = date;
        todaysDate.setHours(0);
    }

    //constructing array

    /*******************************************************************
     **************PRESENT MONTH EVENTS*********************************
     *******************************************************************/

    /************SETTER METHODS***************************/
    public void initializePresentMonthlyEvents(Date startOfMonth, int numOfDays, int lengthOfBlocksInMins){
        firstMonthEventDate = startOfMonth;
        firstMonthEventDateInMillis = startOfMonth.getTime();
        lastMonthEventDateInMillis = (utility.addDaysToDate(firstMonthEventDate, numOfDays)).getTime();
        numOfTimeBlock = 24*60/lengthOfBlocksInMins;
        lengthOfTimeBlockInMili = lengthOfBlocksInMins * 60*1000;
        presentMonthlyEvents = new ClassicEvent[numOfDays][numOfTimeBlock];
        presentMonthlyEventsQuickAccess = new ArrayList<ClassicEvent>();
        presentMonthlyEventsInitialized = true;
    }
    public void updatePresentMonthlyEvents(){
        try {
            if(todaysDate.equals(lastMonthEventDate) && lastMonthEventDate.equals(firstFutureEventDate)) {
                int dayDifferences = utility.daysBetweenTwoDates(firstMonthEventDate,lastMonthEventDate);
                firstMonthEventDate = firstFutureEventDate;
                Date tempFirstFutureDate = new Date();
                tempFirstFutureDate = utility.addDaysToDate(firstFutureEventDate,dayDifferences);
                addEventsToHistoryEvents(presentMonthlyEventsQuickAccess);

                for(int currentDay = 0; currentDay < dayDifferences; currentDay++){
                    eventIndexes[currentDay]
                }
                updateFutureEvents(dayDifferences); //move events between firstfuturedate and tempfirstfuturedate to presentMonthlyEvents





                for (int day = 0; day < numOfDays; day++) {
                    try {
                        if (!(futureEvents[day].equals(null))) { //if there are events in futureEvents today. Since events in futureEvents are sorted the index to put the events in will be the same in present & future
                            for (int eventNum = 0; eventNum < futureEvents[day].size(); eventNum++) {
                                ClassicEvent e = futureEvents[day].get(eventNum);
                                int[] blockIndex = getEventBlockIndexFromEvent(e, day);
                                presentMonthlyEventsQuickAccess.add(e);
                                for (int i = blockIndex[0]; i <= blockIndex[1]; i++) {
                                    presentMonthlyEvents[day][i] = e;
                                }
                            }
                        } else {
                            throw new NullPointerException("futureEvents at days is equal to null");
                        }
                    } catch (NullPointerException n) {
                        boolean theresabug = true;
                    }
                }

                addEventsToHistoryEvents(presentMonthlyEventsQuickAccess);
                presentMonthlyEventsInitialized = true;
            }
            else{
                throw new IllegalStateException("today is not the day to update yet");
            }
        }catch(IllegalStateException n ){
            Log.d(TAG, "")
        }
    }

    public void addEventToMonthly(ClassicEvent e){
        try{
            long start = e.getStartTime().getTimeInMillis();
            long end = e.getEndTime().getTimeInMillis();
            if(start >= firstMonthEventDateInMillis && end <= (lastMonthEventDateInMillis + 1000*60*60*24)){
                int dayIndex = utility.daysBetweenTwoDates(firstFutureEventDate, e.getStartTime().getTime());
                int[] blockIndex = getEventBlockIndexFromEvent(e, dayIndex);
                for (int i = blockIndex[0]; i <= blockIndex[1]; i++) {
                    presentMonthlyEvents[dayIndex][i] = e;
                }
            }else{
                throw new NullPointerException("event date exceeds time frame of present month time, event was added to future events");
            }
        }catch(NullPointerException n){
            addEventToFuture(e);
        }
    }
/*
    public void deleteEventsFromMonthly(int eventID){
        int dayIndex = (int)eventID/100;
        for(int i = 20; i < presentMonthlyEvents[dayIndex].length; i++){
            presentMonthlyEvents[dayIndex][i] = null;
        }
    }
*/
    /**************GETTER METHODS*************************/
    public ClassicEvent[][] getPresentMonthlyEvents(){
        return presentMonthlyEvents;
    }
    public int getMinDiffFromBlockToEventStartTime(ClassicEvent e, int dayIndex, int eventBlockIndex, int numOfBlocks){
        int blockLengthInMins = 24*60/numOfBlocks;
        long eventTime = e.getStartTime().getTimeInMillis();
        long dayTime = (getDateFromMonthlyIndex(dayIndex).getTime());
        int blockNum = (int)((eventTime-dayTime)/blockLengthInMins);
        int blockTime = blockLengthInMins * blockNum;
        return (int)eventTime/(1000*60) - blockTime;
    }  //calculates the difference between closetest block time and start time of event
    public int getMinDiffFromBlockToEventEndTime(ClassicEvent e, int dayIndex, int eventBlockIndex, int numOfBlocks){
        int blockLengthInMins = 24*60/numOfBlocks;
        long eventTime = e.getEndTime().getTimeInMillis();
        long dayTime = (getDateFromMonthlyIndex(dayIndex).getTime());
        int blockNum = (int)((eventTime-dayTime)/blockLengthInMins) + 1;
        int blockTime = blockLengthInMins * blockNum;
        return Math.abs((int)eventTime/(1000*60) - blockTime);
    }  //calculates the difference between closetest block time and end time of event

    public int[] getEventBlockIndexFromEvent(ClassicEvent e, int dayIndex){
        int[] blockNum = new int[2];
        long eventStartTime = e.getStartTime().getTimeInMillis();
        long eventEndTime = e.getEndTime().getTimeInMillis();
        long dayStartTime = firstFutureEventDate.getTime() + dayIndex*24*60*60*1000;
        Date test = new Date(dayStartTime);
        for(int i = 0; i < numOfTimeBlock-1; i++){
            long blockStartTime = dayStartTime + i*lengthOfTimeBlockInMili;
            if(eventStartTime < blockStartTime){
                blockNum[0] = i;
                long blockEndTime = dayStartTime + (i+1)*lengthOfTimeBlockInMili;
                if(eventEndTime > blockEndTime){
                    blockNum[1] = i+1;
                    return blockNum;
                }else if(eventEndTime == blockEndTime){
                    blockNum[1] = i+1;
                    return blockNum;
                }
            }
        }
        long blockStartTime = dayStartTime+(numOfTimeBlock-1)*lengthOfTimeBlockInMili;
        if(eventStartTime >= blockStartTime){
            blockNum[0] = numOfTimeBlock-1;
            blockNum[1] = numOfTimeBlock-1;
        }
        return blockNum;
    }

    public Date getDateFromMonthlyIndex(int dayIndex){
        long diff = dayIndex*1000*60*60*24;
        Date d = new Date((firstMonthEventDate.getTime()+diff)/1000*60*60*24);
        return d;
    }   //return date of presentMonthlyEvents[dayIndex] dayIndex = day of the month

    /*******************************************************************
     **************FUTURE EVENTS***************************************
     *******************************************************************/

    /************SETTER METHODS**************************/

    public void initializeFutureEvents(int dayMaximum, int numOfDaysToInit, Date firstFutureEventDate, List<ClassicEvent> e){
        this.firstFutureEventDate = firstFutureEventDate;
        futureEvents = (ArrayList<ClassicEvent>[])new ArrayList[dayMaximum];
        for(int i = 0; i < numOfDaysToInit; i++){
            futureEvents[i] = new ArrayList<ClassicEvent>();
        }
        addEventsToFuture(e);
        futureEventsInitialized = true;
    }

    public void updateFutureEvents(int numOfDaysToRemove){

        for(int i = 0; i < eventIndexes.length; i++){
            futureEvents[eventIndexes[i]-numOfDaysToRemove] = new ArrayList<ClassicEvent>();
            futureEvents[eventIndexes[i]-numOfDaysToRemove] = futureEvents[eventIndexes[i]];
        }
    }//loop through an array of event indexes -> move the events at i to i-numOfDaysToRemove

    //TODO: add special case for when event start time is the same & delete duplicate events, overnight events
    public void addEventToFuture(ClassicEvent e){
        Date eventDate = e.getStartTime().getTime();
        int dayIndex = utility.daysBetweenTwoDates(firstFutureEventDate,eventDate);
        if(dayIndex < 0) {
            if (presentMonthlyEventsInitialized == true) {
                if (firstEventOfMonth.compareTo(eventDate) < 0) {
                    addEventToMonthly(e);
                    return;
                }
            }else if (historyEventsInitialized == true) {
//            Log.d(TAG, "the event time is past first date of monthly events. Will be added to history events");
                addEventToHistoryEvents(e);
                return;
            } else {
                initilizeHistoryEvents(100);
                addEventToHistoryEvents(e);
                return;
            }
        }

        int eventIndex = 0;
        try {
            if(!(futureEvents[dayIndex].equals(null))) {
                eventIndex = getEventIndexFromDate(eventDate, futureEvents[dayIndex]);
                futureEvents[dayIndex].add(eventIndex, e);
                int newID = dayIndex*100 + eventIndex;
                futureEvents[dayIndex].get(eventIndex).setEventID(newID);
            }else{
                throw new NullPointerException("the events list at dayIndex was not instanitated");
            }
        }catch(NullPointerException n){
            futureEvents[dayIndex] = new ArrayList<ClassicEvent>();
            eventIndex = getEventIndexFromDate(eventDate, futureEvents[dayIndex]);
            futureEvents[dayIndex].add(eventIndex, e);
            int newID = dayIndex*100 + eventIndex;
            futureEvents[dayIndex].get(eventIndex).setEventID(newID);
        }

    }
    public void addEventsToFuture(List<ClassicEvent> e){
        for(int i = 0; i < e.size(); i++){
            addEventToFuture(e.get(i));
        }
    }

    public void deleteEventFromFuture(int eventID){
        try {
            int dayIndex = (int)(eventID/100);
            int eventIndex = eventID%100;
            if(!(futureEvents[dayIndex].equals(null))){
                if(!(futureEvents[dayIndex].get(eventIndex).equals(null))){
                    if(futureEvents[dayIndex].get(eventIndex).getEventID() == eventID){
                        futureEvents[dayIndex].remove(eventIndex);
                    }else{
                        throw new NullPointerException("the event ID passed in does not match the event id accessed");
                    }
                }else{
                    throw new NullPointerException("There does not exist a event in the day specified");
                }
            }else{
                throw new NullPointerException("There is no events on this day");
            }
        }catch(NullPointerException e){
            Log.d(TAG, "the eventID does not point to an existing event");
        }
    }


    /***********GETTER METHODS**************************/
    public List<ClassicEvent>[] getFutureEventsArray(){
        return futureEvents;
    }
    public List<ClassicEvent> getFutureEvents(Date eventsDate){
         return futureEvents[utility.daysBetweenTwoDates(firstFutureEventDate, eventsDate)];
    }
    public ClassicEvent getFutureEvent(int eventID){
        return futureEvents[eventID/100].get(eventID%100);
    }
    public ClassicEvent getFutureEvent(Date eventStartTime){
        int dayIndex = utility.daysBetweenTwoDates(firstFutureEventDate, eventStartTime);
        return utility.findEventFromEventsArray(eventStartTime, futureEvents[dayIndex]);
    }

    /***********HISTORY EVENTS*************************/
    public void initilizeHistoryEvents(int eventMaximum){
        histroyEvents = new ArrayList<ClassicEvent>();
        if(histroyEvents.size() > eventMaximum){
            for(int i = 0; i < histroyEvents.size()-eventMaximum+histroyEvents.size()/10; i++){
                if(!(histroyEvents.get(i).getEventSavedState())){
                    histroyEvents.remove(i);
                }
            }
        }
        historyEventsInitialized = true;
    }

    public List<ClassicEvent> getHistroyEvents(){
        return histroyEvents;
    }
    public void addEventToHistoryEvents(ClassicEvent e){
        if (histroyEvents.size() > 0) {
            Calendar eventStartTime = e.getStartTime();
            if (eventStartTime.compareTo(histroyEvents.get(histroyEvents.size() / 2).getStartTime()) < 0) {
                if (eventStartTime.compareTo(histroyEvents.get(histroyEvents.size() / 4).getStartTime()) < 0) {
                    for (int i = 0; i < histroyEvents.size() / 4; i++) {
                        if (eventStartTime.compareTo(histroyEvents.get(i).getStartTime()) > 0) {
                            histroyEvents.add(i, e);
                            return;
                        }
                    }
                    histroyEvents.add(0, e);
                    firstHistoryEventDate = eventStartTime.getTime();
                } else {
                    for (int i = histroyEvents.size() / 4; i < histroyEvents.size() / 2; i++) {
                        if (eventStartTime.compareTo(histroyEvents.get(i).getStartTime()) > 0) {
                            histroyEvents.add(i, e);
                            return;
                        }
                    }
                }
            } else {
                if (eventStartTime.compareTo(histroyEvents.get(histroyEvents.size() * 3 / 4).getStartTime()) < 0) {
                    for (int i = histroyEvents.size() / 2; i < histroyEvents.size() * 3 / 4; i++) {
                        if (eventStartTime.compareTo(histroyEvents.get(i).getStartTime()) > 0) {
                            histroyEvents.add(i, e);
                            return;
                        }
                    }
                } else {
                    for (int i = histroyEvents.size() * 3 / 4; i < histroyEvents.size(); i++) {
                        if (eventStartTime.compareTo(histroyEvents.get(i).getStartTime()) < 0) {
                            histroyEvents.add(i, e);
                            return;
                        }
                    }
                }
            }
        } else {
            histroyEvents.add(e);
        }
    }
    public void addEventsToHistoryEvents(List<ClassicEvent> e){
        for(int j = 0; j < e.size(); j++) {
            if (histroyEvents.size() > 0) {
                Calendar eventStartTime = e.get(j).getStartTime();
                if (eventStartTime.compareTo(histroyEvents.get(histroyEvents.size() / 2).getStartTime()) < 0) {
                    if (eventStartTime.compareTo(histroyEvents.get(histroyEvents.size() / 4).getStartTime()) < 0) {
                        for (int i = 0; i < histroyEvents.size() / 4; i++) {
                            if (eventStartTime.compareTo(histroyEvents.get(i).getStartTime()) > 0) {
                                histroyEvents.add(i, e.get(i));
                                return;
                            }
                        }
                        histroyEvents.add(0, e.get(j));
                        firstHistoryEventDate = eventStartTime.getTime();
                    } else {
                        for (int i = histroyEvents.size() / 4; i < histroyEvents.size() / 2; i++) {
                            if (eventStartTime.compareTo(histroyEvents.get(i).getStartTime()) > 0) {
                                histroyEvents.add(i, e.get(i));
                                return;
                            }
                        }
                    }
                } else {
                    if (eventStartTime.compareTo(histroyEvents.get(histroyEvents.size() * 3 / 4).getStartTime()) < 0) {
                        for (int i = histroyEvents.size() / 2; i < histroyEvents.size() * 3 / 4; i++) {
                            if (eventStartTime.compareTo(histroyEvents.get(i).getStartTime()) > 0) {
                                histroyEvents.add(i, e.get(i));
                                return;
                            }
                        }
                    } else {
                        for (int i = histroyEvents.size() * 3 / 4; i < histroyEvents.size(); i++) {
                            if (eventStartTime.compareTo(histroyEvents.get(i).getStartTime()) < 0) {
                                histroyEvents.add(i, e.get(i));
                                return;
                            }
                        }
                    }
                }
            } else {
                histroyEvents.add(e.get(j));
            }
        }
    }

    /*******************************************************************
     **************Utility Methods**************************************
     *******************************************************************/

    public int getEventIndexFromDate(Date startTime, List<ClassicEvent> e){
        if(e.size() == 0){
            return 0;
        }
        for (int i = 0; i < e.size(); i++) {
            if (startTime.compareTo(e.get(i).getStartTime().getTime()) < 0) {  //startTime is ealier than eventstartTime at i
                if(i == 0){
                    return 0;
                }
                return i;
            }
        }
        return e.size();
    } //loop thru list of events to find the index of an evnet

}
