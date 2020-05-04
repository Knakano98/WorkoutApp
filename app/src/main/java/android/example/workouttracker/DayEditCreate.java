package android.example.workouttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class DayEditCreate extends AppCompatActivity {
    Day newDay= new Day("defaultName");
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_edit_create);
        this.setTitle("Day Input");

        //Get info sent from RoutineEditCreate
        Bundle bundle=getIntent().getExtras();

        //If index in bundle is -1, means new day is being created
        if(bundle.getInt("index")==-1){
            //Log.v("debug","DAY EDIT CREATE: CREATE ");
            index=bundle.getInt("index");
        }
        else{ //Else, the index represents the index of day being edited
            //Log.v("debug","DAY EDIT CREATE: EDIT "+ bundle.getInt("index"));
            index=bundle.getInt("index");
        }
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
}
