package com.example.jenny.anothertry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.jenny.anothertry.BaseActivity.convertStringToDate;

public class TestFreeTime {

    private static final String TAG = "testFreeTime";
    private FreeTime freeTime;
    private String[][] whatisgoingon;

    public TestFreeTime(){
        TesterEvents e = new TesterEvents();

        freeTime = new FreeTime(e.getEvents(),10, "20170810000000");

        whatisgoingon = new String[freeTime.getFreeTimes().length][2];
        for(int i = 0; i < freeTime.getFreeTimes().length; i++){
            for(int j = 0; j < freeTime.getFreeTimes()[i].length; j++){
                Calendar a = freeTime.getFreeTimes()[i][j];
                Date d = freeTime.getFreeTimes()[i][j].getTime();
                String str = d.toString();
                whatisgoingon[i][j] = str.substring(4,19);
            }
        }

    }

    public static void main(String[] args){
        Calendar timeClick = Calendar.getInstance();
        timeClick.setTime(convertStringToDate("20170810170000"));
        Calendar timeClic2 = Calendar.getInstance();
        timeClic2.setTime(convertStringToDate("20170810175000"));

        TestFreeTime temp = new TestFreeTime();

        TestFreeTimeConnect temp2 = new TestFreeTimeConnect();
        FreeTime pleaseWork = temp2.getFriendFreeTime();
        String[][] testFreeTime2 = temp2.getWhatIsGoingOn();

        boolean yes = false;
        yes = temp.construct().compareFreeTime(timeClick, timeClic2, pleaseWork,30);
        long commonTime = temp.construct().getCommonFreeTime();
        int count = temp.construct().getCommonTimeOverlapCount();
    }

    public FreeTime construct(){
        return freeTime;
    }



}
