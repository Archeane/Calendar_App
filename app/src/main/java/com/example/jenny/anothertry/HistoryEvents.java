package com.example.jenny.anothertry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HistoryEvents {

    private List<ClassicEvent> historyEvents;
    private Date firstHistoryEventDate;
    private int capacity;
    private int size;

    public HistoryEvents(int c){
        capacity = c;
        size = 0;
        historyEvents = new ArrayList<ClassicEvent>();
    }

    public List<ClassicEvent> getHistroyEvents(){
        return historyEvents;
    }

    public void addEventToHistoryEvents(ClassicEvent e){
        Calendar eventStartTime = e.getStartTime();
        if (size > 0) {
            for(int j = size-1; j >= 0; j--){
                if(historyEvents.get(j).getStartTime().compareTo(eventStartTime) < 0){
                    historyEvents.add(j, e);
                    size++;
                    assignEventId(j);
                    return;
                }
            }
        }
        historyEvents.add(0, e);
        firstHistoryEventDate = eventStartTime.getTime();
        size++;
    }

    public void addEventsToHistoryEvents(List<ClassicEvent> e){
        for(int i = 0; i < e.size(); i++){
            addEventToHistoryEvents(e.get(i));
        }
    }

    public void assignEventId(int pos){
        historyEvents.get(pos).setEventID(pos);
    }
}
