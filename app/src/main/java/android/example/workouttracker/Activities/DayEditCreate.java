package android.example.workouttracker.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.example.workouttracker.Objects.Day;
import android.example.workouttracker.Objects.Exercise;
import android.example.workouttracker.Objects.Routine;
import android.example.workouttracker.R;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.example.workouttracker.Storage.addExercise;
import static android.example.workouttracker.Storage.updateExercise;

public class DayEditCreate extends AppCompatActivity {
    Day newDay= new Day("defaultName"); //New day to be added to routine
    int index; //Index tracks

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_edit_create);
        this.setTitle("Day Input");

        containerLinLayout=findViewById(R.id.exerciseContainer); //Lin layout container for dynamically created exercise views

        //Get info sent from RoutineEditCreate
        Bundle bundle=getIntent().getExtras();

        //If index in bundle is -1, means new day is being created
        index=bundle.getInt("index");

        if(index!=-1){ //If not -1, is being edited. Get day from routine/main to edit
            //Get routine being created/edited
            Routine newRoutine=bundle.getParcelable("newRoutine");
            //Sets newDay to exiting day begin edited
            EditText input=findViewById(R.id.dayName);

            newDay=newRoutine.getAtIndex(index);
            input.setText(newDay.getName());

        }

        createListView(); //Display Day list
    }

    //Listener for finish button
    public void finish(View view){
        //Gets name for day and sets it
        EditText input=findViewById(R.id.dayName);
        String routineName=input.getText().toString();
        newDay.setName(routineName);

        //Put extras into return intent
        Intent returnIntent=new Intent();
        returnIntent.putExtra("day",newDay);
        returnIntent.putExtra("index",index);

        setResult(Activity.RESULT_OK,returnIntent);
        finish();

    }

    int editIndex=-420; //editIndex tracks index of exercise being edited

    //Listener for opening ExerciseInput
    public void createExercise(View view){
        Intent intent= new Intent(this, ExerciseInput.class);
        Bundle bundle=new Bundle();

        if(view.getId()==R.id.createExercise){ //Sends -1 as index if being called from create
            bundle.putInt("index",-1);
        }
        else{ //Else sends the index of exercise being edited;
            bundle.putInt("index",editIndex);
            intent.putExtra("newDay",newDay);
        }

        intent.putExtras(bundle);

        int EXERCISE_INPUT=1;
        startActivityForResult(intent,EXERCISE_INPUT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if (resultCode == RESULT_OK) {
            //Get exercise from ExerciseInput
            Bundle bundle=data.getExtras();
            Exercise exercise=data.getParcelableExtra("exercise");

            if(bundle.getInt("index")==-1){ //If new day being created, add day to routine
                newDay=addExercise(newDay,exercise);
            }
            else{//Else, routine is being edited, update day at index
                newDay=updateExercise(newDay,exercise,editIndex);
            }

            createListView(); //Refresh display
        }
    }

    private LinearLayout containerLinLayout;//Contains container for Exercises

    public void createListView(){
        containerLinLayout.removeAllViews();

        //Iterate through every exercise in newDay and display
        for(int i=0;i<newDay.daySize();i++){
            Exercise exercise=newDay.getAtIndex(i);

            //Create new Linear Layout
            final LinearLayout dynamicLinLayout=new LinearLayout(DayEditCreate.this);

            //Get name of exercise and set TextView
            TextView exerciseName= new TextView(DayEditCreate.this);
            exerciseName.append(exercise.getName());
            //Add to container
            dynamicLinLayout.addView(exerciseName);

            //Create editExercise button, set text and add to container
            final Button editExercise=new Button(DayEditCreate.this);
            editExercise.append("Edit");
            dynamicLinLayout.addView(editExercise);

            //Listener for edit button being created
            editExercise.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //Gets index of Exercise being edited
                    editIndex = ((ViewGroup) dynamicLinLayout.getParent()).indexOfChild(dynamicLinLayout);
                    createExercise(editExercise); //Call createExercise from an edit button
                }
            });

            //Create deleteExercise button, set text and add to container
            final Button deleteExercise=new Button(DayEditCreate.this);
            deleteExercise.append("Delete");
            dynamicLinLayout.addView(deleteExercise);

            //Listener for delete button
            deleteExercise.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int indexOfMyView = ((ViewGroup) dynamicLinLayout.getParent()).indexOfChild(dynamicLinLayout);//Get index of day being deleted
                    newDay.removeAtIndex(indexOfMyView);//Delete appropriate day
                    createListView(); //Refresh routine display
                }
            });

            containerLinLayout.addView(dynamicLinLayout); //Add newly generated day to container linear layout
        }
    }
}
