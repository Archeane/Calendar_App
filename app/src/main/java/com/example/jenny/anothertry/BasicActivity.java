package com.example.jenny.anothertry;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A basic example of how to use week view library.
 * Created by Raquib-ul-Alam Kanak on 1/3/2014.
 * Website: http://alamkanak.github.io
 */
public class BasicActivity extends BaseActivity  {

    private final String TAG = "AddingEventDebug";

    private WeekViewEvent event;

    private ArrayList<WeekViewEvent> eventsArray;

    @Override
    public List<? extends WeekViewEvent> onMonthChange(final int newYear, final int newMonth) {

        //Setting events

        eventsArray = new ArrayList<WeekViewEvent>();
  /*      for(int i = 0; i < 3; i++) {
            startTime = Calendar.getInstance();
            startTime.add(Calendar.DATE, i);
            startTime.set(Calendar.HOUR_OF_DAY, 0);
            startTime.set(Calendar.MINUTE, 0);
            endTime = (Calendar) startTime.clone();
            endTime.add(Calendar.HOUR_OF_DAY, 8);
            event = new WeekViewEvent(1, getEventTitle(startTime) + "Sleep", startTime, endTime);
            event.setColor(getResources().getColor(R.color.event_color_04));
            eventsArray.add(event);
        }

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 0);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.MINUTE, 53);
        event = new WeekViewEvent(2, getEventTitle(startTime) + "Physics", startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_02));
        eventsArray.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 10);
        startTime.set(Calendar.MINUTE, 0);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 1);
        event = new WeekViewEvent(10, getEventTitle(startTime) + "Chemistry", startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_03));
        eventsArray.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 14);
        startTime.set(Calendar.MINUTE, 30);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 2);
        event = new WeekViewEvent(11, getEventTitle(startTime)+"Math", startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_02));
        eventsArray.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 20);
        startTime.set(Calendar.MINUTE, 0);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 2);
        event = new WeekViewEvent(13, getEventTitle(startTime)+"Gym", startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_03));
        eventsArray.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 18);
        startTime.set(Calendar.MINUTE, 0);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 1);
        event = new WeekViewEvent(14, getEventTitle(startTime) + "club", startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_04));
        eventsArray.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 1);
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_01));
        eventsArray.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, startTime.getActualMaximum(Calendar.DAY_OF_MONTH));
        startTime.set(Calendar.HOUR_OF_DAY, 15);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_02));
        eventsArray.add(event);

        while(eventArrayEmpty) {
            setEvent();
        }
*/
            return eventsArray;
    }

}
