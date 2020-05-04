package android.example.workouttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.example.workouttracker.Storage.addRoutine;
import static android.example.workouttracker.Storage.deleteRoutine;
import static android.example.workouttracker.Storage.main;
import static android.example.workouttracker.Storage.testMain;
import static android.example.workouttracker.Storage.updateRoutine;

public class SelectEdit extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_edit);
        this.setTitle("Select Routine");

        containerLinLayout=findViewById(R.id.routineContainer); //Gets container Linear Layout in Scrollview that stores dynamically created routines
        createListView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if (resultCode == RESULT_OK) {
            Bundle bundle=data.getExtras();
            Routine routine=data.getParcelableExtra("routine");
            Log.v("debug","EDIT TEST"+ routine.getName() + bundle.getInt("index"));

            //Change so if edit, don't add to routine, instead edit
            if(bundle.getInt("index")==-1){ //If new routine being created, add routine to main
                addRoutine(routine);
            }
            else{//Else, routine is being edited, update routine at index
                updateRoutine(routine,bundle.getInt("index"));
            }

            testMain();
            createListView();
        }


    }

    public void createRoutine(View view){
        Intent intent= new Intent(this,RoutineEditCreate.class);

        if(view.getId()==R.id.createRoutine){//If new activity is accessed from createRoutine, -1 is passed to signal it is a new routine
            Log.v("debug","CREATEROUTINE");
            Bundle bundle=new Bundle();
            bundle.putInt("index",-1);
            intent.putExtras(bundle);

        }
        else{ //Else, the index of the routine being edited is passed
            Log.v("debug","EDITROUTINE");
            Bundle bundle=new Bundle();
            bundle.putInt("index",editIndex);
            intent.putExtras(bundle);
        }

        int ROUTINE_INPUT=1;
        startActivityForResult(intent,ROUTINE_INPUT); //Start routine input activity
    }


    int editIndex=-420; //Stores index for routine being edited
    private LinearLayout containerLinLayout; //Container linear layout to represent new routine
    public void createListView(){
        containerLinLayout.removeAllViews();

        for(Routine routine: main){
            //Generate new LinLayout container for routine
            final LinearLayout dynamicLinLayout=new LinearLayout(SelectEdit.this);
            TextView routineName= new TextView(SelectEdit.this);
            routineName.append(routine.getName());
            dynamicLinLayout.addView(routineName);

            //Create edit button
            final Button editRoutine=new Button(SelectEdit.this);
            editRoutine.append("Edit");
            dynamicLinLayout.addView(editRoutine);

            editRoutine.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) { //Gets index of routine being edited
                    editIndex = ((ViewGroup) dynamicLinLayout.getParent()).indexOfChild(dynamicLinLayout);
                    createRoutine(editRoutine); //Call createRoutine from an edit button
                }
            });

            //Create delete button and generate ID
            final Button deleteRoutine=new Button(SelectEdit.this);
            final int deleteButtonID=View.generateViewId();
            deleteRoutine.setId(deleteButtonID);

            //Delete button listener
            deleteRoutine.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int indexOfMyView = ((ViewGroup) dynamicLinLayout.getParent()).indexOfChild(dynamicLinLayout);//Get index of routine being deleted
                    deleteRoutine(indexOfMyView); //Delete appropriate routine
                    createListView(); //Refresh routine display
                }
            });

            //Set button text/ add to container lin layout
            deleteRoutine.append("Delete");
            dynamicLinLayout.addView(deleteRoutine);

            containerLinLayout.addView(dynamicLinLayout); //Add newly generated routine to container linear layout
        }
    }

}
