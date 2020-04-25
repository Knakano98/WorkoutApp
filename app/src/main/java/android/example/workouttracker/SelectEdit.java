package android.example.workouttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class SelectEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_edit);
        this.setTitle("Select Routine");

    }

    public void createRoutine(View view){
        Intent intent= new Intent(this,RoutineEditCreate.class);

        //If new activity is accessed from createRoutine, -1 is passed to signal it is a new routine
        //Else, the index of the routine being edited is passed
        if(view.getId()==R.id.createRoutine){
            Log.v("debug","CREATEROUTINE");
            Bundle bundle=new Bundle();
            bundle.putInt("index",-1);
            intent.putExtras(bundle);


        }
        else{
            Log.v("debug","EDITROUTINE");
            Bundle bundle=new Bundle();
            bundle.putInt("index",0);
            intent.putExtras(bundle);
        }


        startActivity(intent);

        //Need to figure out a way for code to run only after the input activity finishes
        //Log.v("debug","ROUTINE EDIT FINISHED");
        //Confirm is pressed in new activity, and needs to come back to here

        //Needs to dynamically generate/delete layout box with routine name/edit/delete in this activity
        //Have "confirm" button in the new activity that leads back to here, and then generate layout box/ update main here
        //Edit just edits but also edits layout box

        //TODO HERE:
        //1. figure out way for code to wait until input activity finishes
        //2. Make it so "finish" button adds dummy routine info to main
        //3. Find way for xml list to sync up to main

    }

    //Create routine button leads to routine creation activity, can create/edit routine be same activity?
        //Pass index into routine creation activity, so -1 if new, and index num if edit
    //Routine creation activity contains input for name, and create days(with eventually days also displayed)
    //Create day leads to same thing except with exercises

}
