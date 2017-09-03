package com.example.jenny.anothertry;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Utilities {

    public Utilities(){}

    public int binarySearchStartTime(ClassicEvent[] a, int start, int end, Date target) {
        int middle = (start + end) / 2;
        if(end < start) {
            return -1;
        }
        if(target.compareTo(a[middle].getStartTime().getTime()) == 0) {
            return middle;
        } else if(target.compareTo(a[middle].getStartTime().getTime()) < 0) {
            return binarySearchStartTime(a, start, middle - 1, target);
        } else {
            return binarySearchStartTime(a, middle + 1, end, target);
        }
    }

    public int binarySearchEndTime(List<ClassicEvent> a, int start, int end, Date target) {
        int middle = (start + end) / 2;
        if(end < start) {
            return -1;
        }
        if(target.compareTo(a.get(middle).getEndTime().getTime()) == 0) {
            return middle;
        } else if(target.compareTo(a.get(middle).getEndTime().getTime()) < 0) {
            return binarySearchEndTime(a, start, middle - 1, target);
        } else {
            return binarySearchEndTime(a, middle + 1, end, target);
        }
    }

    public Date addDateToDate(Date a, Date b){
        long sum = a.getTime() + b.getTime();
        return new Date(sum);
    }

    public Date addDaysToDate(Date a, int days){
        long b = a.getTime();
        long sum = b + Math.abs(days*24*60*60*1000);
        return new Date(sum);
    }

    public int daysBetweenTwoDates(Date eariler, Date later){
        long result = TimeUnit.DAYS.convert(later.getTime() - eariler.getTime(), TimeUnit.MILLISECONDS);
        if(result < 0){
            return (int)result -1;
        }
        return (int)result +1;
    }

    public int convertDateToDay(Date d){
        return (int)d.getTime()/(1000*60*60*24);
    }

    public ClassicEvent findEventFromEventsArray(Date eStartTime, List<ClassicEvent> e){
        for(int i = 0; i < e.size(); i++){
            if(eStartTime.equals(e.get(i).getStartTime().getTime())){
                return e.get(i);
            }
        }
        return null;
    }

    public int correspondingMonthFromString(String month){
        if(month.substring(0,3).toLowerCase().equals("jan")){
            return 1;
        }
        if(month.substring(0,3).toLowerCase().equals("feb")){
            return 2;
        }
        if(month.substring(0,3).toLowerCase().equals("mar")){
            return 3;
        }
        if(month.substring(0,3).toLowerCase().equals("apr")){
            return 4;
        }
        if(month.substring(0,3).toLowerCase().equals("may")){
            return 5;
        }
        if(month.substring(0,3).toLowerCase().equals("jun")){
            return 6;
        }
        if(month.substring(0,3).toLowerCase().equals("jul")){
            return 7;
        }
        if(month.substring(0,3).toLowerCase().equals("aug")){
            return 8;
        }
        if(month.substring(0,3).toLowerCase().equals("sep")){
            return 9;
        }
        if(month.substring(0,3).toLowerCase().equals("oct")){
            return 10;
        }
        if(month.substring(0,3).toLowerCase().equals("nov")){
            return 11;
        }
        if(month.substring(0,3).toLowerCase().equals("dec")){
            return 12;
        }
        return -1;
    }
}
