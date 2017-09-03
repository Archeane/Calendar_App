package com.example.jenny.anothertry;

import java.util.ArrayList;
import java.util.List;

import static com.example.jenny.anothertry.BaseActivity.convertStringToDate;

/**
 * Created by Jenny on 8/6/2017.
 */

public class TesterEvents {

    private List<ClassicEvent> events;
    private List<ClassicEvent> events2;
    private List<ClassicEvent> events3;


    public TesterEvents(){
        events = new ArrayList<ClassicEvent>();
        java.util.Calendar startTime = java.util.Calendar.getInstance();
        startTime.setTime(convertStringToDate("20170811050000"));
    //    startTime.set(2017,8,10,5,0);
        java.util.Calendar endTime = (java.util.Calendar)startTime.clone();
        endTime.add(java.util.Calendar.HOUR_OF_DAY, 1);
        ClassicEvent event1 = new ClassicEvent(1, "event 1", startTime, endTime);

        java.util.Calendar startTime2 = java.util.Calendar.getInstance();
        startTime2.setTime(convertStringToDate("20170811080000"));
      //  startTime2.set(2017,8,10,8,0);
        java.util.Calendar endTime2 = (java.util.Calendar)startTime2.clone();
        endTime2.add(java.util.Calendar.HOUR_OF_DAY, 1);
        endTime2.add(java.util.Calendar.MINUTE, 45);
        ClassicEvent event2 = new ClassicEvent(2, "event 2", startTime2, endTime2);

        java.util.Calendar startTime3 = java.util.Calendar.getInstance();
        startTime3.setTime(convertStringToDate("20170811171500"));
      //  startTime3.set(2017,8,9,17,15);
        java.util.Calendar endTime3 = (java.util.Calendar)startTime3.clone();
        endTime3.add(java.util.Calendar.MINUTE, 30);
        ClassicEvent event3 = new ClassicEvent(3, "event 3", startTime3, endTime3);

        java.util.Calendar startTime4 = java.util.Calendar.getInstance();
        startTime4.setTime(convertStringToDate("20170811190000"));
     //   startTime4.set(2017,8,9,19,0);
        java.util.Calendar endTime4 = (java.util.Calendar)startTime4.clone();
        endTime4.add(java.util.Calendar.HOUR_OF_DAY, 1);
        endTime4.add(java.util.Calendar.MINUTE, 30);
        ClassicEvent event4 = new ClassicEvent(4, "event 4", startTime4, endTime4);

        events.add(event1);
        events.add(event2);
        events.add(event3);
        events.add(event4);

        events2 = new ArrayList<ClassicEvent>();
        java.util.Calendar startTime5 = java.util.Calendar.getInstance();
        startTime5.setTime(convertStringToDate("20170813370000"));
        java.util.Calendar endTime5 = (java.util.Calendar)startTime5.clone();
        endTime5.add(java.util.Calendar.HOUR_OF_DAY, 1);
        ClassicEvent event5 = new ClassicEvent(5, "event 5", startTime5, endTime5);

        java.util.Calendar startTime6 = java.util.Calendar.getInstance();
        startTime6.setTime(convertStringToDate("201708130100000"));
        java.util.Calendar endTime6 = (java.util.Calendar)startTime6.clone();
        endTime6.add(java.util.Calendar.HOUR_OF_DAY, 1);
        endTime6.add(java.util.Calendar.MINUTE, 25);
        ClassicEvent event6 = new ClassicEvent(6, "event 6", startTime6, endTime6);

        java.util.Calendar startTime8 = java.util.Calendar.getInstance();
        startTime8.setTime(convertStringToDate("20170813131500"));
        java.util.Calendar endTime8 = (java.util.Calendar)startTime8.clone();
        endTime8.add(java.util.Calendar.MINUTE, 40);
        ClassicEvent event8 = new ClassicEvent(8, "event 8", startTime8, endTime8);

        java.util.Calendar startTime7 = java.util.Calendar.getInstance();
        startTime7.setTime(convertStringToDate("20170813190000"));
        //startTime4.set(Calendar.HOUR_OF_DAY, 18);
        java.util.Calendar endTime7 = (java.util.Calendar)startTime7.clone();
        endTime7.add(java.util.Calendar.HOUR_OF_DAY, 2);
        endTime7.add(java.util.Calendar.MINUTE, 30);
        ClassicEvent event7 = new ClassicEvent(7, "event 7", startTime7, endTime7);

        events2.add(event5);
        events2.add(event6);
        events2.add(event7);
        events.add(event8);

        events3 = new ArrayList<ClassicEvent>();
        java.util.Calendar startTime9 = java.util.Calendar.getInstance();
        startTime9.setTime(convertStringToDate("20170814080000"));
        java.util.Calendar endTime9 = (java.util.Calendar)startTime9.clone();
        endTime9.add(java.util.Calendar.HOUR_OF_DAY, 1);
        ClassicEvent event9 = new ClassicEvent(9, "event 9", startTime9, endTime9);

        java.util.Calendar startTime10 = java.util.Calendar.getInstance();
        startTime10.setTime(convertStringToDate("20170814100000"));
        java.util.Calendar endTime10 = (java.util.Calendar)startTime10.clone();
        endTime10.add(java.util.Calendar.HOUR_OF_DAY, 1);
        endTime10.add(java.util.Calendar.MINUTE, 20);
        ClassicEvent event10 = new ClassicEvent(10, "event 10", startTime10, endTime10);

        java.util.Calendar startTime11 = java.util.Calendar.getInstance();
        startTime11.setTime(convertStringToDate("20170814141500"));
        java.util.Calendar endTime11 = (java.util.Calendar)startTime11.clone();
        endTime11.add(java.util.Calendar.MINUTE, 45);
        ClassicEvent event11 = new ClassicEvent(11, "event 11", startTime11, endTime11);

        java.util.Calendar startTime12 = java.util.Calendar.getInstance();
        startTime12.setTime(convertStringToDate("20170814190000"));
        //startTime4.set(Calendar.HOUR_OF_DAY, 18);
        java.util.Calendar endTime12 = (java.util.Calendar)startTime12.clone();
        endTime12.add(java.util.Calendar.HOUR_OF_DAY, 1);
        endTime12.add(java.util.Calendar.MINUTE, 30);
        ClassicEvent event12 = new ClassicEvent(12, "event 12", startTime12, endTime12);

        events3.add(event9);
        events3.add(event10);
        events3.add(event12);
        events3.add(event11);
    }

    public List<ClassicEvent> getEvents(){
        return events;
    }

    public List<ClassicEvent> getEvents2(){
        return events2;
    }

    public List<ClassicEvent> getEvents3(){return events3;}
}
