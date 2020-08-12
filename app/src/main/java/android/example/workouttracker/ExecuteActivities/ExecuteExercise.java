package android.example.workouttracker.ExecuteActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.example.workouttracker.Objects.Exercise;
import android.example.workouttracker.R;
import android.example.workouttracker.StatStorage.ExerciseStat;
import android.example.workouttracker.StatStorage.SetStat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ExecuteExercise extends AppCompatActivity {
    Exercise exercise=null;
    private LinearLayout containerLinLayout;
    private ArrayList<Integer> repInputList=new ArrayList<>();
    private ArrayList<Integer> weightInputList=new ArrayList<>();


    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_execute_exercise);

        String dayName=getIntent().getStringExtra("dayName");
        String routineName=getIntent().getStringExtra("routineName");

        containerLinLayout=findViewById(R.id.setsContainer);

        exercise=getIntent().getParcelableExtra("exercise");
        this.setTitle("Executing: " +routineName + ", " +dayName  + ", " + exercise.getName());

        //On create, need to check if exerciseStat has been created for this session
        //If so, load numbers from there to editTexts and if finish is pressed, edit the exercistStat
        //Else, create new
        //Need to find someway to "link" exerciseStat and exercise





        TextView exerciseName=(TextView) findViewById(R.id.exerciseName);
        exerciseName.append(exercise.getName());


        //Why did i do this? Chang to function+ listner in xml
        final Button finishExercise=(Button) findViewById(R.id.finishExercise);
        finishExercise.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Create exerciseStat object

                ExerciseStat newExerciseStat=new ExerciseStat();

                for(int i=0;i<exercise.getSets();i++){
                    //Iterate through all info in sets
                    EditText repInput=(EditText) findViewById(repInputList.get(i));
                    //Log.v("debug","Rep set " + (i+1) +" :"+ repInput.getText().toString() );

                    EditText weightInput=(EditText) findViewById(weightInputList.get(i));
                    //Log.v("debug","Weight set " + (i+1) +" :"+  weightInput.getText().toString() );

                    //Create new setstat object for every set with relevent info
                    SetStat newSet=new SetStat(Integer.parseInt(repInput.getText().toString()),Integer.parseInt(weightInput.getText().toString()) );

                    //Add to exercise stat
                    newExerciseStat.addSet(newSet);
                }


                newExerciseStat.setName(exercise.getName());
                newExerciseStat.setDate(java.time.LocalDate.now().toString());

                //newExerciseStat.logExerciseStat();

                Intent returnIntent=new Intent();
                returnIntent.putExtra("exerciseStat",newExerciseStat);

                setResult(Activity.RESULT_OK,returnIntent);
                finish();
                //return exercisestat object

            }
        });


        generateSets();

        if(getIntent().hasExtra("existingExerciseStat")){
            Log.v("debug","EXISTING");
            ExerciseStat existingExerciseStat=getIntent().getParcelableExtra("existingExerciseStat");



            for(int i=0;i<repInputList.size();i++){
                EditText reps=(EditText) findViewById(repInputList.get(i));
                reps.setText(existingExerciseStat.getSets().get(i).getReps()+"");

                EditText weight=(EditText) findViewById(weightInputList.get(i));
                weight.setText(existingExerciseStat.getSets().get(i).getWeight()+"");
            }


        }

    }


    private void generateSets(){
        for(int i=0;i<exercise.getSets();i++){

            final LinearLayout dynamicLinLayout=new LinearLayout(ExecuteExercise.this);
            TextView setName= new TextView(ExecuteExercise.this);
            setName.append("Set "+ (i+1) +": ");

            dynamicLinLayout.addView(setName);

            EditText reps=new EditText(ExecuteExercise.this);

            //Create ID for input editTexts and store in arraylist for access outside.
            int repsID=View.generateViewId();
            repInputList.add(repsID);
            reps.setId(repsID);

            reps.setHint("Reps");
            dynamicLinLayout.addView(reps);

            EditText weight=new EditText(ExecuteExercise.this);

            //Create ID for input editTexts and store in arraylist for access outside.
            int weightID=View.generateViewId();
            weightInputList.add(weightID);
            weight.setId(weightID);

            weight.setHint("weight");
            dynamicLinLayout.addView(weight);

            containerLinLayout.addView(dynamicLinLayout);

        }
    }


}
