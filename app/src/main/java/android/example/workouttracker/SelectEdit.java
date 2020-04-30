package android.example.workouttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;



import static android.example.workouttracker.Storage.addRoutine;
import static android.example.workouttracker.Storage.main;
import static android.example.workouttracker.Storage.testMain;


public class SelectEdit extends AppCompatActivity {

    private LinearLayout containerLinLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_edit);
        this.setTitle("Select Routine");
        containerLinLayout=findViewById(R.id.routineContainer);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        Routine routine=data.getParcelableExtra("routine");
        Log.v("debug",routine.getName());

        addRoutine(routine);
        testMain();

        createListView();
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

        int ROUTINE_INPUT=1;
        startActivityForResult(intent,ROUTINE_INPUT);


        //Needs to dynamically generate/delete layout box with routine name/edit/delete in this activity
        //Have "confirm" button in the new activity that leads back to here, and then generate layout box/ update main here
        //Edit just edits but also edits layout box

        //TODO HERE:
        //3. Find way for xml list to sync up to main

    }





    public void createListView(){
        containerLinLayout.removeAllViews();
        for(Routine routine: main){

            //Generate new LinLayout container for routine
            LinearLayout dynamicLinLayout=new LinearLayout(SelectEdit.this);
            TextView routineName= new TextView(SelectEdit.this);
            routineName.append(routine.getName());
            dynamicLinLayout.addView(routineName);

            Button editRoutine=new Button(SelectEdit.this);
            editRoutine.append("Edit");
            dynamicLinLayout.addView(editRoutine);

            Button deleteRoutine=new Button(SelectEdit.this);
            deleteRoutine.append("Delete");
            dynamicLinLayout.addView(deleteRoutine);

            containerLinLayout.addView(dynamicLinLayout);

            //Need to generate ids for dynamically created stuff to get edit/delete working
            //1. Delete
            //2. Edit
            //3. Create 
        }
    }

    //Create routine button leads to routine creation activity, can create/edit routine be same activity?
        //Pass index into routine creation activity, so -1 if new, and index num if edit
    //Routine creation activity contains input for name, and create days(with eventually days also displayed)
    //Create day leads to same thing except with exercises


}
