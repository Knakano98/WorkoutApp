package android.example.workouttracker;

import android.util.Log;

import java.util.ArrayList;

public class Routine {
    private String name;
    private ArrayList<Day> routine;

    public Routine(String name){
        this.name=name;
        this.routine=new ArrayList<Day>();
    }

    public String getName(){
        return name;
    }

    public Day getAtIndex(int index){
        return routine.get(index);
    }

    public void add(Day day){
        routine.add(day);
    }

    public void removeAtIndex(int index){
        routine.remove(index);
    }

    public void logRoutine(){
        for(Day day: routine){
            Log.v("debug","        DayName :" + day.getName());
            day.logDay();
        }
    }

}
