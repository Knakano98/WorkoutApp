package android.example.workouttracker.ExecuteActivities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.example.workouttracker.InputActivities.DayEditCreate;
import android.example.workouttracker.Objects.Day;
import android.example.workouttracker.Objects.Exercise;
import android.example.workouttracker.Objects.Routine;
import android.example.workouttracker.StatStorage.DayStat;
import android.example.workouttracker.StatStorage.ExerciseStat;
import android.os.Bundle;
import android.example.workouttracker.R;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

import static android.example.workouttracker.Storage.addExercise;
import static android.example.workouttracker.Storage.updateExercise;

public class ExecuteDay extends AppCompatActivity {

    HashMap<String,ExerciseStat> exerciseStatHashMap= new HashMap<>();


    Day day=null;
    String routineName="";
    DayStat newDayStat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_execute_day);





        //Create Stat object here for day

        //Maybe use hashmap to map exercise to exercisestat
        //initially have exercisestat all be null, then fill in as it goes
        //if not null and being opened, then can edit that one


        newDayStat= new DayStat();


        day=getIntent().getParcelableExtra("day");
        routineName=getIntent().getStringExtra("routineName");

        this.setTitle("Executing: " +routineName + ", " +day.getName() );

        containerLinLayout=findViewById(R.id.exerciseContainer);


        //Initialize hashmap with <exercise, null>
        for(int i=0;i<day.daySize();i++){
            exerciseStatHashMap.put(day.getAtIndex(i).getName(),null);
        }


        day.logDay();

        createListView();
    }

    private LinearLayout containerLinLayout;

    int exerciseIndex=-420;

    public void createListView(){
        containerLinLayout.removeAllViews();

        //Iterate through every exercise in newDay and display
        for(int i=0;i<day.daySize();i++){
            Exercise exercise=day.getAtIndex(i);

            //Create new Linear Layout
            final LinearLayout dynamicLinLayout=new LinearLayout(ExecuteDay.this);

            //Get name of exercise and set TextView
            TextView exerciseName= new TextView(ExecuteDay.this);
            exerciseName.append(exercise.getName());
            //Add to container
            dynamicLinLayout.addView(exerciseName);

            //Create executeExercise button, set text and add to container
            final Button executeExercise=new Button(ExecuteDay.this);
            executeExercise.append("Do Exercise");
            dynamicLinLayout.addView(executeExercise);

            //Listener for edit button being created
            executeExercise.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    exerciseIndex = ((ViewGroup) dynamicLinLayout.getParent()).indexOfChild(dynamicLinLayout);
                    startDayExecuteActivity();
                }
            });


            containerLinLayout.addView(dynamicLinLayout); //Add newly generated day to container linear layout
        }
    }

    private void startDayExecuteActivity(){
        Intent intent= new Intent(this, ExecuteExercise.class);



        Exercise executedExercise=day.getAtIndex(exerciseIndex);
        intent.putExtra("routineName",routineName);
        intent.putExtra("dayName", day.getName());
        intent.putExtra("exercise",executedExercise);


        //If not null, means previous executed stat is being edited, pass existing exerciseStat
        if(exerciseStatHashMap.get(executedExercise.getName())!=null){
            intent.putExtra("existingExerciseStat", exerciseStatHashMap.get(executedExercise.getName()));
        }



        int ROUTINE_EXECUTE=1;

        //Should this be startActivityForResult?
        startActivityForResult(intent,ROUTINE_EXECUTE); //Start routine input activity
    }

    //On activity result not triggering from executeEXercise?
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);



        if (resultCode == RESULT_OK) {

            ExerciseStat exerciseStat=data.getParcelableExtra("exerciseStat");
            //Log.v("debug",exerciseStat.getName());
            newDayStat.addExerciseStat(exerciseStat);
            exerciseStatHashMap.replace(exerciseStat.getName(),exerciseStat);
            exerciseStat.logExerciseStat();

        }
    }

    //Exit warning
    @Override
    public void onBackPressed(){
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit Warning");
        builder.setMessage("Warning: Exercise info input will not be saved without finishing, are you sure?");

        // add a button
        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
               finish();
            }
        });
        builder.setNegativeButton("no",null);

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }


}
