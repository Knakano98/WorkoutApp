package android.example.workouttracker;

import android.util.Log;

import java.util.ArrayList;

public class Storage {

    public static ArrayList<Routine> main=new ArrayList<>(); //"Main" arraylist

    public static ArrayList<Routine> getMain(){
        return main;
    }

    public void updateMain(ArrayList<Routine> newMain){
        this.main=newMain;
    }

    //Functions to add/delete/update exercises in each day
    public static void addExercise(int routineIndex,int dayIndex,Exercise exercise){ //Working
        main.get(routineIndex).getAtIndex(dayIndex).add(exercise);
    }
    public static void deleteExercise(int routineIndex,int dayIndex, int deleteIndex){ //Working
        main.get(routineIndex).getAtIndex(dayIndex).removeAtIndex(deleteIndex);
    }
    public static void updateExercise(int routineIndex, int dayIndex, int updateIndex, Exercise newExercise){ //Working
        main.get(routineIndex).getAtIndex(dayIndex).setExerciseAtIndex(updateIndex, newExercise);
    }

    //Functions to add/delete days in routine (Dont need update, can just alter directly using update exercise)
    public static void addDay(int routineIndex,String name){//Working
        if(name==null){
            name="defaultName";
        }

        Day day=new Day(name);
        main.get(routineIndex).add(day);
    }
    public static void deleteDay(int routineIndex, int deleteIndex){ //Working
        main.get(routineIndex).removeAtIndex(deleteIndex);
    }

    //Routine functions
    public static void addRoutine(Routine routine){
        main.add(routine);
    }
    public static void deleteRoutine(int routineIndex){
        main.remove(routineIndex);
    }

    //Debugging
    public static void testMain(){ //Testing function to display contents of main
        Log.v("debug","MAIN: ");
        for(Routine routine:main ){
            Log.v("debug","    RoutineName: " + routine.getName());
            routine.logRoutine();
        }

    }
}
