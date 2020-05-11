package android.example.workouttracker;

import android.util.Log;

import java.util.ArrayList;

public class Storage {

    public static ArrayList<Routine> main=new ArrayList<>(); //"Main" arraylist

    public static ArrayList<Routine> getMain(){
        return main;
    }


    //Functions to add/delete/update exercises in each day
    public static Day addExercise(Day day, Exercise exercise){ //Working
        day.add(exercise);
        return day;
    }
    public static void deleteExercise(int routineIndex,int dayIndex, int deleteIndex){ //Working
        main.get(routineIndex).getAtIndex(dayIndex).removeAtIndex(deleteIndex);
    }
    public static Day updateExercise(Day day, Exercise exercise,int index){ //Working
        day.set(exercise,index);
        return day;
    }

    //Functions to add/delete days in routine (Dont need update, can just alter directly using update exercise)
    public static Routine addDay(Routine routine, Day day){//Working
        routine.add(day);
        return routine;
    }

    public static Routine updateDay(Routine routine, Day day ,int index){//Working
        routine.set(day,index);
        return routine;
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
    public static void updateRoutine(Routine newRoutine, int index){
        main.set(index,newRoutine);
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
