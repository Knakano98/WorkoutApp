package android.example.workouttracker.ExecuteActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.example.workouttracker.InputActivities.RoutineEditCreate;
import android.example.workouttracker.InputActivities.SelectEdit;
import android.example.workouttracker.Objects.Routine;
import android.example.workouttracker.R;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.example.workouttracker.Storage.*;

public class Execute extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_execute);
        this.setTitle("Execute a Routine");
        containerLinLayout=findViewById(R.id.routineContainer); //Gets container Linear Layout in Scrollview that stores dynamically created routines
        createListView();

        //testMain();
    }

    int executeIndex=-420;

    private LinearLayout containerLinLayout;
    private void createListView(){
        containerLinLayout.removeAllViews();

        for(Routine routine: main){
            //Generate new LinLayout container for routine
            final LinearLayout dynamicLinLayout=new LinearLayout(Execute.this);
            TextView routineName= new TextView(Execute.this);
            routineName.append(routine.getName());
            dynamicLinLayout.addView(routineName);

            //Create Execute button
            final Button executeRoutine=new Button(Execute.this);
            executeRoutine.append("Execute");
            dynamicLinLayout.addView(executeRoutine);

            //Execute button listener
            executeRoutine.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //Get index, get appropriate routine from main that matches index, send to next activity
                    executeIndex = ((ViewGroup) dynamicLinLayout.getParent()).indexOfChild(dynamicLinLayout);
                    startExecuteActivity();
                }
            });
  containerLinLayout.addView(dynamicLinLayout); //Add newly generated routine to container linear layout
        }
    }

    private void startExecuteActivity(){
        Intent intent= new Intent(this, ExecuteRoutine.class);



        Routine executedRoutine=main.get(executeIndex);

        intent.putExtra("routine",executedRoutine);


        int ROUTINE_EXECUTE=1;

        //Should this be startActivityForResult?
        startActivityForResult(intent,ROUTINE_EXECUTE); //Start routine input activity
    }



}
