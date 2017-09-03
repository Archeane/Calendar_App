package com.example.jenny.anothertry;

import android.content.Intent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alamkanak.weekview.WeekViewEvent;

import static com.example.jenny.anothertry.BaseActivity.mWeekView;

public class AddingEvent extends AppCompatActivity{
    private String TAG = "AddingEventDebug";

    private EditText eName, eStartTime, eEndTime, eDate;
    private Button btn;

    private String eventName, eventDate;
    private Calendar eventStartTime, eventEndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "entered oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_event);
        Log.d(TAG, "activity set");

        eName = (EditText)findViewById(R.id.eventName);
        eStartTime = (EditText)findViewById(R.id.eventStartTime);
        eEndTime = (EditText)findViewById(R.id.eventEndTime);
        eDate = (EditText)findViewById(R.id.eventDate);
        btn = (Button)findViewById(R.id.addEvent);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, eStartTime.getText().toString());

                int eStart = Integer.parseInt(eStartTime.getText().toString());
                int eEnd = Integer.parseInt(eEndTime.getText().toString());

                eventName = eName.getText().toString();
                eventDate = eDate.getText().toString();

                Calendar startTime = Calendar.getInstance();
                Calendar endTime = (Calendar)startTime.clone();
                startTime.set(Calendar.HOUR_OF_DAY, eStart);
                endTime.set(Calendar.HOUR_OF_DAY, eEnd);

                WeekViewEvent newEvent = new WeekViewEvent(999, eventName, startTime, endTime);
            //    eventsArray.add(newEvent);
           //     Log.d(TAG, eventsArray.get(eventsArray.size()-1).getName());
                try {
                    mWeekView.notifyDatasetChanged();
                }catch(Exception e){
                    Log.d(TAG,"exception thrown");
                }

            }
        });

    }

    public String getEventName(){
        return eventName;
    }

    public Calendar getEventStartTime(){
        return eventStartTime;
    }

    public Calendar getEventEndTime(){
        return eventEndTime;
    }

}
