package android.example.workouttracker.ExecuteActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.example.workouttracker.Objects.Exercise;
import android.example.workouttracker.R;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExecuteExercise extends AppCompatActivity {
    Exercise exercise=null;
    private LinearLayout containerLinLayout;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_execute_exercise);

        String dayName=getIntent().getStringExtra("dayName");
        String routineName=getIntent().getStringExtra("routineName");

        containerLinLayout=findViewById(R.id.setsContainer);

        exercise=getIntent().getParcelableExtra("exercise");
        this.setTitle("Executing: " +routineName + ", " +dayName  + ", " + exercise.getName());

        TextView exerciseName=(TextView) findViewById(R.id.exerciseName);
        exerciseName.append(exercise.getName());


        generateSets();
    }


    private void generateSets(){
        for(int i=0;i<exercise.getSets();i++){

            final LinearLayout dynamicLinLayout=new LinearLayout(ExecuteExercise.this);
            TextView setName= new TextView(ExecuteExercise.this);
            setName.append("Set "+ (i+1) +": ");

            dynamicLinLayout.addView(setName);

            EditText reps=new EditText(ExecuteExercise.this);
            reps.setHint("Reps");

            dynamicLinLayout.addView(reps);

            EditText weight=new EditText(ExecuteExercise.this);
            weight.setHint("weight");
            dynamicLinLayout.addView(weight);

            containerLinLayout.addView(dynamicLinLayout);

        }



    }
    //Name of exercise, 
    //Set 1: [defined rep goal]
    //Set 2... cont
    //etc




}
