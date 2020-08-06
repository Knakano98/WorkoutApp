package android.example.workouttracker.ExecuteActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.example.workouttracker.InputActivities.DayEditCreate;
import android.example.workouttracker.Objects.Day;
import android.example.workouttracker.Objects.Exercise;
import android.example.workouttracker.Objects.Routine;
import android.os.Bundle;
import android.example.workouttracker.R;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExecuteDay extends AppCompatActivity {

    Day day=null;
    String routineName="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_execute_day);

        //Create Stat object here for day

        day=getIntent().getParcelableExtra("day");
        routineName=getIntent().getStringExtra("routineName");

        this.setTitle("Executing: " +routineName + ", " +day.getName() );

        containerLinLayout=findViewById(R.id.exerciseContainer);


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


        int ROUTINE_EXECUTE=1;

        //Should this be startActivityForResult?
        startActivityForResult(intent,ROUTINE_EXECUTE); //Start routine input activity
    }


}
