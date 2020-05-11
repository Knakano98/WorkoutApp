package android.example.workouttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.example.workouttracker.Storage.addExercise;
import static android.example.workouttracker.Storage.main;
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



        if(bundle.getInt("index")==-1){
            //Log.v("debug","DAY EDIT CREATE: CREATE ");
            index=bundle.getInt("index");
        }
        else{ //Else, the index represents the index of day being edited
            index=bundle.getInt("index");
            int routineIndex=bundle.getInt("routineIndex");

            Routine newRoutine=bundle.getParcelable("newRoutine");

            if(routineIndex==-1){ //Shows currently in routine creation
                newDay=newRoutine.getAtIndex(index);
            }
            else{//Else routine is already saved in main
                newDay=main.get(routineIndex).getAtIndex(index);
            }
        }

        createListView();
    }

    public void finish(View view){ //Listener for finish button
        //Gets name for day
        EditText input=(EditText)findViewById(R.id.dayName);
        String routineName=input.getText().toString();
        newDay.setName(routineName);

        //Put extras into return intent
        Intent returnIntent=new Intent();
        returnIntent.putExtra("day",newDay);
        returnIntent.putExtra("index",index);

        setResult(Activity.RESULT_OK,returnIntent);
        finish();

    }

    int editIndex=-420;
    public void createExercise(View view){
        Intent intent= new Intent(this,ExerciseInput.class);
        Bundle bundle=new Bundle();

        if(view.getId()==R.id.createExercise){
            Log.v("debug","createExercise");
            bundle.putInt("index",-1);
        }
        else{
            Log.v("debug","editExercise");
            bundle.putInt("index",editIndex);
        }

        intent.putExtras(bundle);

        int EXERCISE_INPUT=1;
        startActivityForResult(intent,EXERCISE_INPUT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if (resultCode == RESULT_OK) {
            //Get day from dayEditCreate
            Bundle bundle=data.getExtras();
            Exercise exercise=data.getParcelableExtra("exercise");


            Log.v("debug",exercise.getName());
            Log.v("debug",Integer.toString(bundle.getInt("index")));


            if(bundle.getInt("index")==-1){ //If new day being created, add day to routine
                newDay=addExercise(newDay,exercise);
                Log.v("debug","CREATEEXERCISE" + newDay.getAtIndex(0).getName());
            }
            else{//Else, routine is being edited, update day at index

                newDay=updateExercise(newDay,exercise,editIndex);
            }

            createListView(); //Refresh display
        }
    }

    private LinearLayout containerLinLayout;
    public void createListView(){
        containerLinLayout.removeAllViews();
        for(int i=0;i<newDay.daySize();i++){
            Exercise exercise=newDay.getAtIndex(i);

            final LinearLayout dynamicLinLayout=new LinearLayout(DayEditCreate.this);
            TextView exerciseName= new TextView(DayEditCreate.this);
            exerciseName.append(exercise.getName());
            dynamicLinLayout.addView(exerciseName);

            final Button editExercise=new Button(DayEditCreate.this);
            editExercise.append("Edit");
            dynamicLinLayout.addView(editExercise);

            editExercise.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) { //Gets index of Exercise being edited
                    editIndex = ((ViewGroup) dynamicLinLayout.getParent()).indexOfChild(dynamicLinLayout);
                    createExercise(editExercise); //Call createExercise from an edit button
                }
            });

            final Button deleteExercise=new Button(DayEditCreate.this);
            deleteExercise.append("Delete");
            dynamicLinLayout.addView(deleteExercise);

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
