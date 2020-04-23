package android.example.workouttracker;

import android.util.Log;

import java.util.ArrayList;

public class Day {
    private String name;
    private ArrayList<Exercise> day;
    //Needs get, add, remove at index,set

    public Day(String name){
        this.day=new ArrayList<Exercise>();
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public Exercise getAtIndex(int index){
        return day.get(index);
    }

    public void add(Exercise exercise){
        day.add(exercise);
    }

    public void removeAtIndex(int index){
        day.remove(index);
    }

    public void setExerciseAtIndex(int index, Exercise exercise){
        day.set(index,exercise);
    }

    public void logDay(){
        for(Exercise exercise: day){
            Log.v("debug","            ExerciseName: " + exercise.getName());
            Log.v("debug","            Reps: " + exercise.getReps());
            Log.v("debug","            Sets: " + exercise.getSets());
            Log.v("debug","            Weight: " + exercise.getWeight());

        }
    }

}
