package android.example.workouttracker.StatStorage;

import java.util.ArrayList;

public class DayStat {
    //Create new one for each session
    //Save into folder after

    private String date;
    private String name;
    private ArrayList<ExerciseStat> exerciseStatArrayList=new ArrayList<>();

    public void setDate(String date){
        this.date=date;
    }

    public String getDate(){
        return date;
    }

    public void addExerciseStat(ExerciseStat exerciseStat){
        exerciseStatArrayList.add(exerciseStat);
    }

    public ArrayList<ExerciseStat> getExerciseStatArrayList() {
        return exerciseStatArrayList;
    }
}
