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

import static android.example.workouttracker.Storage.addDay;
import static android.example.workouttracker.Storage.addRoutine;
import static android.example.workouttracker.Storage.deleteRoutine;
import static android.example.workouttracker.Storage.main;
import static android.example.workouttracker.Storage.testMain;
import static android.example.workouttracker.Storage.updateDay;
import static android.example.workouttracker.Storage.updateRoutine;

public class RoutineEditCreate extends AppCompatActivity {
    Routine newRoutine=new Routine("defaultRoutineName"); //Routine being created/edited
    int index; //Keeps track of index of routine being edited (-1= newly created)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_edit_create);
        this.setTitle("Routine Input");

        //Gets container for days
        containerLinLayout=findViewById(R.id.dayContainer);

        //Get bundle from SelectEdit
        Bundle bundle=getIntent().getExtras();
        //If index in bundle is -1, means new routine is being created

        index=bundle.getInt("index");

        if(bundle.getInt("index")!=-1){
            //Sets new routine to routine being edited
            EditText input=(EditText)findViewById(R.id.routineName);
            newRoutine=main.get(index);
            input.setText(newRoutine.getName());
        }




        //Display days in routine
        createListView();

        //Debugging
        testMain();
    }

    //Listener for finish button (Routine)
    public void finish(View view){
        //Gets name for routine
        EditText input=(EditText)findViewById(R.id.routineName);
        String routineName=input.getText().toString();
        newRoutine.setName(routineName);

        //Put extras into return intent
        Intent returnIntent=new Intent();
        returnIntent.putExtra("routine",newRoutine);
        returnIntent.putExtra("index",index);

        setResult(Activity.RESULT_OK,returnIntent);
        finish();

    }

    int editIndex=-420; //Stores index for day being edited
    private LinearLayout containerLinLayout; //Container linear layout to represent new day

    //Displays list of days in routine
    public void createListView(){
        containerLinLayout.removeAllViews();

        for(int i=0;i<newRoutine.routineSize();i++){
            Day day=newRoutine.getAtIndex(i);

            //Create new linLayout container for day
            final LinearLayout dynamicLinLayout=new LinearLayout(RoutineEditCreate.this);
            TextView dayName= new TextView(RoutineEditCreate.this);
            dayName.append(day.getName());
            dynamicLinLayout.addView(dayName);

            //Create edit button
            final Button editDay=new Button(RoutineEditCreate.this);
            editDay.append("Edit");
            dynamicLinLayout.addView(editDay);

            editDay.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) { //Gets index of day being edited
                    editIndex = ((ViewGroup) dynamicLinLayout.getParent()).indexOfChild(dynamicLinLayout);
                    createDay(editDay); //Call createDay from an edit button
                }
            });

            //Create delete button
            final Button deleteDay=new Button(RoutineEditCreate.this);
            final int deleteButtonID=View.generateViewId();
            deleteDay.setId(deleteButtonID); //What is this for? Unneeded?
            deleteDay.append("Delete");
            dynamicLinLayout.addView(deleteDay);

            deleteDay.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int indexOfMyView = ((ViewGroup) dynamicLinLayout.getParent()).indexOfChild(dynamicLinLayout);//Get index of day being deleted
                    newRoutine.removeAtIndex(indexOfMyView);//Delete appropriate day

                    createListView(); //Refresh routine display
                }
            });
            containerLinLayout.addView(dynamicLinLayout); //Add newly generated day to container linear layout
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if (resultCode == RESULT_OK) {
            //Get day from dayEditCreate
            Bundle bundle=data.getExtras();
            Day day=data.getParcelableExtra("day");

            //Log.v("debug","EDIT TEST"+ day.getName() + bundle.getInt("index"));

            if(bundle.getInt("index")==-1){ //If new day being created, add day to routine
                newRoutine=addDay(newRoutine,day);
            }
            else{//Else, routine is being edited, update day at index
                newRoutine=updateDay(newRoutine,day,editIndex);
            }

            //Log.v("debug",newRoutine.getAtIndex(0).getName());

            createListView(); //Refresh display
        }
    }

    public void createDay(View view){
        Intent intent= new Intent(this,DayEditCreate.class);

        Bundle bundle=new Bundle();
        bundle.putInt("routineIndex",index);

        if(view.getId()==R.id.createDay){//If new activity is accessed from createDay, -1 is passed to DayEditCreate to signal it is a new Day
            Log.v("debug","CREATEDAY");
            bundle.putInt("index",-1);
            intent.putExtras(bundle);
        }

        else{ //Else, the index of the routine being edited is passed
            Log.v("debug","EDITDAY");
            bundle.putInt("index",editIndex);
            intent.putExtras(bundle);
            intent.putExtra("newRoutine", newRoutine);
        }

        int DAY_INPUT=1;
        startActivityForResult(intent,DAY_INPUT); //Start day input activity
    }


}
