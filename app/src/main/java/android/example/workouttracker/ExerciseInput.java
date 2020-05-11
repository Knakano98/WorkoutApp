package android.example.workouttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ExerciseInput extends AppCompatActivity {
    Exercise newExercise= new Exercise("defaultName",0,0,0,0);
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_edit_create);
        this.setTitle("Exercise Input");

        //Get info sent from DayEditCreate
        Bundle bundle=getIntent().getExtras();
        index=bundle.getInt("index");
    }


    public void finish(View view){
        Intent returnIntent=new Intent();
        EditText nameInput=findViewById(R.id.nameInput);
        String name=nameInput.getText().toString();
        newExercise.setName(name);

        //TODO: Need to add cond if input is empty
        EditText setsInput=findViewById(R.id.setsInput);
        int sets=Integer.parseInt(setsInput.getText().toString());
        newExercise.setSets(sets);

        EditText repsInput=findViewById(R.id.repsInput);
        int reps=Integer.parseInt(repsInput.getText().toString());
        newExercise.setReps(reps);

        EditText restTimeInput=findViewById(R.id.restTimeInput);
        int time=Integer.parseInt(restTimeInput.getText().toString());
        newExercise.setRestTime(time);



        returnIntent.putExtra("exercise",newExercise);
        returnIntent.putExtra("index",index);

        setResult(Activity.RESULT_OK,returnIntent);
        finish();

    }


    //Todo:
    //Get delete/edit exercise working
    //

}
