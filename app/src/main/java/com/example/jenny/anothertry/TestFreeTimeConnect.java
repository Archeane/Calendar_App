package com.example.jenny.anothertry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.jenny.anothertry.BaseActivity.convertStringToDate;

public class TestFreeTimeConnect {

    private ArrayList<ClassicEvent> friendEvents;
    private FreeTime friendFreeTime;
    private String[][] whatisgoingon;

    public TestFreeTimeConnect(){
        friendEvents = new ArrayList<ClassicEvent>();
        Calendar startTime = Calendar.getInstance();
        startTime.setTime(convertStringToDate("20170810100000"));
        Calendar endTime = (Calendar)startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 2);
        ClassicEvent event1 = new ClassicEvent(1, "event 1", startTime, endTime);

        Calendar startTime2 = Calendar.getInstance();
        startTime2.setTime(convertStringToDate("20170810140000"));
        Calendar endTime2 = (Calendar)startTime2.clone();
        endTime2.add(Calendar.HOUR_OF_DAY, 1);
        endTime2.add(Calendar.MINUTE, 45);
        ClassicEvent event2 = new ClassicEvent(2, "event 2", startTime2, endTime2);

        Calendar startTime3 = Calendar.getInstance();
        startTime3.setTime(convertStringToDate("20170810170000"));
        Calendar endTime3 = (Calendar)startTime3.clone();
        endTime3.add(Calendar.MINUTE, 30);
        ClassicEvent event3 = new ClassicEvent(3, "event 3", startTime3, endTime3);

        Calendar startTime4 = Calendar.getInstance();
        startTime4.setTime(convertStringToDate("20170810180000"));
        //startTime4.set(Calendar.HOUR_OF_DAY, 18);
        Calendar endTime4 = (Calendar)startTime4.clone();
        endTime4.add(Calendar.HOUR_OF_DAY, 1);
        endTime4.add(Calendar.MINUTE, 30);
        ClassicEvent event4 = new ClassicEvent(4, "event 4", startTime4, endTime4);

        friendEvents.add(event1);
        friendEvents.add(event2);
        friendEvents.add(event3);
        friendEvents.add(event4);

        friendFreeTime = new FreeTime(friendEvents,10, "20170810000000");

        whatisgoingon = new String[friendFreeTime.getFreeTimes().length][2];
        for(int i = 0; i < friendFreeTime.getFreeTimes().length; i++){
            for(int j = 0; j < friendFreeTime.getFreeTimes()[i].length; j++){
                Calendar a = friendFreeTime.getFreeTimes()[i][j];
                Date d = friendFreeTime.getFreeTimes()[i][j].getTime();
                String str = d.toString();
                whatisgoingon[i][j] = str.substring(4,19);
            }
        }

    }

    public FreeTime getFriendFreeTime(){
        return friendFreeTime;
    }

    public String[][] getWhatIsGoingOn(){
        return whatisgoingon;
    }
}
