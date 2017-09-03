package com.example.jenny.anothertry;

import android.annotation.TargetApi;

import java.util.Date;
import java.util.Calendar;

import EventsArray.EventProcessor;

import static com.example.jenny.anothertry.BaseActivity.convertStringToDate;

@TargetApi(11)
public class EventProcessorTester {

    private static final String TAG = "eventProcessorTester";

    public static void main(String[] args){
        Date d = new Date();
        d.setDate(1);
        d.setHours(0);
        d.setMinutes(0);
        d.setSeconds(0);

        EventProcessor tester = new EventProcessor(d);
        TesterEvents e = new TesterEvents();

        Calendar test = Calendar.getInstance();
        test.setTime(convertStringToDate("20170804000000"));

//        int index = tester.getEventIndexFromDate(test.getTime(), e.getEvents());
        Calendar test2 = Calendar.getInstance();
        test2.setTime(convertStringToDate("20170806050000"));
/*
        int index2 = tester.getEventIndexFromDate(test2.getTime(), e.getEvents());
        Calendar test3 = Calendar.getInstance();
        test3.setTime(convertStringToDate("20170806180000"));
        int index3 = tester.getEventIndexFromDate(test3.getTime(), e.getEvents());
        Calendar test4 = Calendar.getInstance();
        test4.setTime(convertStringToDate("20170806220000"));
        int index4 = tester.getEventIndexFromDate(test4.getTime(), e.getEvents());
*/

        tester.initializeFutureEvents(100, 10, d, e.getEvents());
        for(int i = 0; i < e.getEvents2().size(); i++){
            tester.addEventToFuture(e.getEvents2().get(i));
            tester.addEventToFuture(e.getEvents3().get(i));
        }


    //    tester.deleteEventFromFuture(202);
//        List<ClassicEvent> tempoary = tester.getFutureEvents(test2.getTime());
//        ClassicEvent temp2 = tester.getFutureEvent(203);

        tester.initilizeHistoryEvents(100);
        tester.initializePresentMonthlyEvents(d, 30, 15);
        tester.updatePresentMonthlyEvents(30);

        java.util.Calendar startTime13 = java.util.Calendar.getInstance();
        startTime13.setTime(convertStringToDate("20170813141500"));
        java.util.Calendar endTime13 = (java.util.Calendar)startTime13.clone();
        endTime13.add(java.util.Calendar.MINUTE, 45);
        ClassicEvent event13 = new ClassicEvent(11, "event 11", startTime13, endTime13);
        tester.addEventToMonthly(event13);

//        tester.deleteEventsFromMonthly(500);
    }

}
