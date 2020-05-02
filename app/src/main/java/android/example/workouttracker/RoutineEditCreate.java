package android.example.workouttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class RoutineEditCreate extends AppCompatActivity {
    Routine testRoutine=new Routine("defaultRoutineName");
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_edit_create);
        this.setTitle("Routine Input");

        Bundle bundle=getIntent().getExtras();

        //If index in bundle is -1, means new routine is being created
        if(bundle.getInt("index")==-1){
            Log.v("debug","ROUTINE EDIT CREATE: CREATE ");
            index=bundle.getInt("index");
        }
        else{ //Else, the index represents the index of routine being edited
            Log.v("debug","ROUTINE EDIT CREATE: EDIT "+ bundle.getInt("index"));
            index=bundle.getInt("index");
        }
    }

    public void finish(View view){ //Listener for finish button
        //Gets name for routine
        EditText input=(EditText)findViewById(R.id.routineName);
        String routineName=input.getText().toString();
        testRoutine.setName(routineName);

        //Put extras into return intent
        Intent returnIntent=new Intent();
        returnIntent.putExtra("routine",testRoutine);
        returnIntent.putExtra("index",index);

        setResult(Activity.RESULT_OK,returnIntent);
        finish();

    }


}
