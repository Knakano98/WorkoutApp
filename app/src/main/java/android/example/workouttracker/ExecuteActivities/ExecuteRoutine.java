package android.example.workouttracker.ExecuteActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.example.workouttracker.InputActivities.RoutineEditCreate;
import android.example.workouttracker.Objects.Day;
import android.example.workouttracker.Objects.Routine;
import android.example.workouttracker.R;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.example.workouttracker.Storage.main;

public class ExecuteRoutine extends AppCompatActivity {

    Routine routine=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setTitle("Executing: RoutineName");
        setContentView(R.layout.activity_execute_routine);

        routine=getIntent().getParcelableExtra("routine");

        containerLinLayout=findViewById(R.id.dayContainer);

        routine.logRoutine();

        createListView(routine);
    }


    int dayIndex=-420;
    private LinearLayout containerLinLayout;
    public void createListView(Routine routine){
        containerLinLayout.removeAllViews();

        for(int i=0;i<routine.routineSize();i++){
            Day day=routine.getAtIndex(i);

            //Create new linLayout container for day
            final LinearLayout dynamicLinLayout=new LinearLayout(ExecuteRoutine.this);
            TextView dayName= new TextView(ExecuteRoutine.this);
            dayName.append(day.getName());
            dynamicLinLayout.addView(dayName);

            //Create edit button
            final Button executeDay=new Button(ExecuteRoutine.this);
            executeDay.append("Execute");
            dynamicLinLayout.addView(executeDay);

            executeDay.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dayIndex = ((ViewGroup) dynamicLinLayout.getParent()).indexOfChild(dynamicLinLayout);
                    startDayExecuteActivity();
                }
            });


            containerLinLayout.addView(dynamicLinLayout); //Add newly generated day to container linear layout
        }
    }

    private void startDayExecuteActivity(){
        Intent intent= new Intent(this, ExecuteDay.class);



        Day executedDay=routine.getAtIndex(dayIndex);

        intent.putExtra("day",executedDay);


        int ROUTINE_EXECUTE=1;

        //Should this be startActivityForResult?
        startActivityForResult(intent,ROUTINE_EXECUTE); //Start routine input activity
    }




}
