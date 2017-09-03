package com.example.jenny.anothertry;

import java.util.Date;
import java.util.List;

import EventsArray.EventProcessor;

public class TestEventProcesserNdFreeTime {

    private static final String TAG = "processor&freeTime";

    public static void main(String[] args){
        Utilities utility = new Utilities();
        TesterEvents testerEvents = new TesterEvents();
        List<ClassicEvent> e1 = testerEvents.getEvents();
        List<ClassicEvent> e2 = testerEvents.getEvents2();
        List<ClassicEvent> e3 = testerEvents.getEvents3();
        Date todaysDate = new Date();
        todaysDate.setDate(1);
        todaysDate.setHours(0);
        todaysDate.setMinutes(0);
        todaysDate.setSeconds(0);
        Date futureDate = new Date();
        futureDate.setDate(13);
        futureDate.setHours(0);
        futureDate.setMinutes(0);
        futureDate.setSeconds(0);


        EventProcessor processor = new EventProcessor(todaysDate);
        processor.initilizeHistoryEvents(100);
        processor.initializeFutureEvents(100,30, futureDate, e3);
        processor.addEventsToFuture(e1);
        processor.addEventsToFuture(e2);
        List<ClassicEvent>[] futureEvents = processor.getFutureEventsArray();

        processor.initializePresentMonthlyEvents(todaysDate,14,30);
        processor.updatePresentMonthlyEvents(14);  //first event date from presentmonthly must be equal to that of future date
        ClassicEvent[][] presentMonthlyEvents = processor.getPresentMonthlyEvents();
        List<ClassicEvent> historyEvents = processor.getHistroyEvents();

        //FreeTime fTimeOne = new FreeTime();
        //FreeTime fTimeTwo = new FreeTime();
    }
}
