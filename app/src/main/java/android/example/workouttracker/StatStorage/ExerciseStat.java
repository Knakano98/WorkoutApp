package android.example.workouttracker.StatStorage;

import android.util.Log;

import java.util.ArrayList;
import java.util.Set;

public class ExerciseStat {
    private ArrayList<SetStat> sets=new ArrayList<>();
    private String date;
    private String name;

    public void setDate(String date){
        this.date=date;
    }

    public String getDate(){
        return date;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public void addSet(SetStat set){
        sets.add(set);
    }

    public ArrayList<SetStat> getSets(){
        return sets;
    }


    public void logExerciseStat(){
        for(int i=0;i<sets.size();i++){
            Log.v("debug","Set "+(i+1));
            Log.v("debug", "    Reps  : "+ sets.get(i).getReps());
            Log.v("debug", "    Weight: "+ sets.get(i).getWeight());
        }
    }
}
