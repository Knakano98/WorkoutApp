package android.example.workouttracker.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.example.workouttracker.Objects.Day;
import android.example.workouttracker.Objects.Exercise;
import android.example.workouttracker.R;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ExerciseInput extends AppCompatActivity {
    Exercise newExercise= new Exercise("defaultName",0,0,0,0);
    int index; //Index of exercise being edited (created=-1)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_edit_create);
        this.setTitle("Exercise Input");


        //Get info sent from DayEditCreate
        Bundle bundle=getIntent().getExtras();
        index=bundle.getInt("index");

        if(index!=-1){ //If not -1, is being edited. Get day from routine/main to edit
            //Get routine being created/edited
            Day newDay=bundle.getParcelable("newDay");
            //Sets newDay to exiting day begin edited
            newExercise=newDay.getAtIndex(index);

            EditText setsInput=findViewById(R.id.setsInput);
            EditText repsInput=findViewById(R.id.repsInput);
            EditText restTimeInput=findViewById(R.id.restTimeInput);
            EditText nameInput=findViewById(R.id.nameInput);

            nameInput.setText(newExercise.getName());
            setsInput.setText(String.valueOf(newExercise.getSets()));
            repsInput.setText(String.valueOf(newExercise.getReps()));
            restTimeInput.setText(String.valueOf(newExercise.getRestTime()));

        }
    }


    public void finish(View view){
        //Create return intent
        Intent returnIntent=new Intent();

        EditText setsInput=findViewById(R.id.setsInput);
        EditText repsInput=findViewById(R.id.repsInput);
        EditText restTimeInput=findViewById(R.id.restTimeInput);
        EditText nameInput=findViewById(R.id.nameInput);

        //Get name,rep,sets,resttime from editTexts
        String name=nameInput.getText().toString();
        newExercise.setName(name);

        //TODO: Need to add cond if input is empty

        int sets=Integer.parseInt(setsInput.getText().toString());
        newExercise.setSets(sets);


        int reps=Integer.parseInt(repsInput.getText().toString());
        newExercise.setReps(reps);


        int time=Integer.parseInt(restTimeInput.getText().toString());
        newExercise.setRestTime(time);


        returnIntent.putExtra("exercise",newExercise);
        returnIntent.putExtra("index",index);

        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}
