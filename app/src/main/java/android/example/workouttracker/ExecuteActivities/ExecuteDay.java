package android.example.workouttracker.ExecuteActivities;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_execute_day);

        this.setTitle("Executing: RoutineName, DayName");

        day=getIntent().getParcelableExtra("day");

        containerLinLayout=findViewById(R.id.exerciseContainer);


        day.logDay();

        createListView();
    }

    private LinearLayout containerLinLayout;

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
                    //Gets index of Exercise being edited

                }
            });


            containerLinLayout.addView(dynamicLinLayout); //Add newly generated day to container linear layout
        }
    }

}
