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

        Exercise test=new Exercise("test",1,2,3);
        main.add(routine);
        main.get(0).add(day);

//        addExercise(0,0,test);
//
//        Log.v("debug",main.get(0).get(0).get(0).getName());
//        Log.v("debug",Integer.toString(main.get(0).get(0).get(0).getReps()));
//        Log.v("debug",Integer.toString(main.get(0).get(0).get(0).getWeight()));
//        Log.v("debug",Integer.toString(main.get(0).get(0).get(0).getSets()));
//



    }

    ArrayList<ArrayList<ArrayList<Exercise>>> main=new ArrayList<>(); //"Main" arraylist, needs to be updated everytime something happens'
    ArrayList<ArrayList<Exercise>> routine=new ArrayList<>();
    ArrayList<Exercise> day=new ArrayList<>(); //Both need to be dynamically created, need functions for creating/deleting/updating


    //Main arrayList contains Workouts (Note: Workout/routine interchangable)
    //Workout each contains "Days"
    //Each "Day" contains Exercise
    //Exercise object contains name,reps,weights

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
    public void addExercise(int routineIndex,int dayIndex,Exercise exercise){
        main.get(routineIndex).get(dayIndex).add(exercise);
    }
    public void deleteExercise(int routineIndex,int dayIndex, int deleteIndex){
        main.get(routineIndex).get(dayIndex).remove(deleteIndex);
    }
    public void updateExercise(int routineIndex, int dayIndex, int updateIndex, Exercise newExercise){
        main.get(routineIndex).get(dayIndex).set(updateIndex, newExercise);
    }


}
