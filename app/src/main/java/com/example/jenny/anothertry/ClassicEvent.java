package com.example.jenny.anothertry;


import android.graphics.Color;

import com.alamkanak.weekview.WeekViewEvent;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;

public class ClassicEvent {

    private static final String TAG = "ClassicEventDebug";
    private WeekViewEvent event;
    private String eventDate;
    private int eventID;    //events in future array: first 2 digits are the day index, last 2 digits are event index;
    private String eventName;
    private Calendar eventStartTime;
    private Calendar eventEndTime;
    private String eventLocation;
    //private List<Contacts> eventInvitees;
    //private Alert eventAlert;
    private int eventColor;
   // private List<Photo> eventPhotos;
    private URL eventURL;
   // private Notes eventNotes;
    //private Repeat eventRepeats;
    private boolean eventAttendanceMandatory;
    //private List<Assignment> eventAssignments;
    private boolean eventSavedState;

    public ClassicEvent(int id, String name, Calendar start, Calendar end) {
        eventName = name;
        eventStartTime = start;
        eventEndTime = end;
        event = new WeekViewEvent(eventID, name, start, end);
        String str = eventStartTime.getTime().toString() + "," + eventEndTime.getTime().toString();
        eventDate = str.substring(4, 10) + " " + str.substring(24,28);
        int startIndex = str.indexOf(",");
        eventDate += "," + str.substring(startIndex+4, startIndex+11) + str.substring(startIndex+24,startIndex+29);
    }

    public ClassicEvent(int id, String name, String location, Calendar start, Calendar end, int col) {
        eventID = id;
        eventName = name;
        eventStartTime = start;
        eventEndTime = end;
        eventLocation = location;
     //   eventColor = col;
        event = new WeekViewEvent(id,name,location,start,end);
      //  event.setColor(getResources().getColor(R.color.col));
        eventDate = event.toString();
    }

    public void setEventID(int id){
        eventID = id;
    }

    public void setEventIdEventIndex(int eventIndex){
        int dayIndex = (int)(eventID/100) * 100;
        eventID = dayIndex + eventIndex;
    }

    public void setEventIdDayIndex(int dayIndex){
        int eventIndex = eventID % 100;
        eventID = dayIndex*100+eventIndex;
    }

    //accessors
    public Calendar getStartTime(){
        return eventStartTime;
    }

    public Calendar getEndTime(){
        return eventEndTime;
    }

    public void setEventStartTime(Calendar startTime){
        eventStartTime = startTime;
    }

    public void setEventEndTime(Calendar endTime){
        eventEndTime = endTime;
    }

    public String getEventDate(){
        return eventDate;
    }

    public int getEventID(){
        return eventID;
    }

    public String getEventName(){
        return eventName.substring(0,10);
    }

    public void setEventSavedState(boolean b){
        eventSavedState = b;
    }

    public boolean getEventSavedState(){
        return eventSavedState;
    }
    public String toString(){
        String str = "";
        str = eventName + ", "+ eventStartTime.getTime().toString() + ", "+eventEndTime.getTime().toString();
        return str;
    }
}
