package com.example.jenny.anothertry;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FutureEvents {

    private


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
}
