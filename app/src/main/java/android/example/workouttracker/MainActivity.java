package android.example.workouttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Testing
//        Exercise test1=new Exercise("exercise1",1,2,3);
//        Exercise test2=new Exercise("exercise2",1,2,3);
//        Exercise test3=new Exercise("exercise3",1,2,3);
//
//        addRoutine("routine1");
//        addRoutine("routine2");
//        addRoutine("routine3");
//
//        addDay(0,"day1");
//        addDay(1,"day2");
//        addDay(2,"day3");
//
//        addDay(0,"day1.2");
//        addExercise(0,1,test1);
//        addExercise(0,1,test1);
//
//        addExercise(0,0,test1);
//        addExercise(1,0,test2);
//        addExercise(2,0,test3);
//
//        test1=new Exercise("update",4,5,6);
//
//        updateExercise(1,0,0,test1);
//
//        testMain();
//
//        deleteExercise(0,0,0);
//        deleteExercise(1,0,0);
//        deleteExercise(2,0,0);
//
//        deleteDay(0,0);
//        deleteDay(1,0);
//        deleteDay(2,0);
//
//        deleteRoutine(0);
//        deleteRoutine(0);
//        deleteRoutine(0);
//
//        testMain();


        //Might be a good idea to create a predefined test run

    }

    ArrayList<Routine> main=new ArrayList<>(); //"Main" arraylist


    //Main arrayList contains Workouts (Note: Workout/routine interchangable)
    //Workout each contains "Days"
    //Each "Day" contains Exercise
    //Exercise object contains name,reps,weights

    //NOTE: Keep in mind index changes when deleting, since it gets removed entirely and rest gets pushed "up"


    //Functions for button on main activity
    public void execute(View view){
        //Open new activity here
        Intent intent=new Intent(this,Execute.class);
        startActivity(intent);
        Log.v("debug","EXECUTE");
    }
    public void selectEdit(View view){
        Intent intent=new Intent(this,SelectEdit.class);
        startActivity(intent);
        Log.v("debug","SlEDIT");
    }
    public void stats(View view){
        Intent intent=new Intent(this,Stats.class);
        startActivity(intent);
        Log.v("debug","STATS");
    }
    public void history(View view){
        Intent intent=new Intent(this,History.class);
        startActivity(intent);
        Log.v("debug","HISTORY");
    }

    //Functions to add/delete/update exercises in each day
    public void addExercise(int routineIndex,int dayIndex,Exercise exercise){ //Working
        main.get(routineIndex).getAtIndex(dayIndex).add(exercise);
    }
    public void deleteExercise(int routineIndex,int dayIndex, int deleteIndex){ //Working
        main.get(routineIndex).getAtIndex(dayIndex).removeAtIndex(deleteIndex);
    }
    public void updateExercise(int routineIndex, int dayIndex, int updateIndex, Exercise newExercise){ //Working
        main.get(routineIndex).getAtIndex(dayIndex).setExerciseAtIndex(updateIndex, newExercise);
    }

    //Functions to add/delete days in routine (Dont need update, can just alter directly using update exercise)
    public void addDay(int routineIndex,String name){//Working
        if(name==null){
            name="defaultName";
        }

        Day day=new Day(name);
        main.get(routineIndex).add(day);
    }
    public void deleteDay(int routineIndex, int deleteIndex){ //Working
        main.get(routineIndex).removeAtIndex(deleteIndex);
    }

    //Routine functions
    public void addRoutine(String name){
        if(name==null){
            name="defaultName";
        }

        Routine routine=new Routine(name);
        main.add(routine);
    }
    public void deleteRoutine(int routineIndex){
        main.remove(routineIndex);
    }

    //Debugging
    public void testMain(){ //Testing function to display contents of main
        Log.v("debug","MAIN: ");
        for(Routine routine:main ){
            Log.v("debug","    RoutineName: " + routine.getName());
            routine.logRoutine();
        }

    }


}
