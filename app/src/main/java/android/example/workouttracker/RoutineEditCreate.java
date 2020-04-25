package android.example.workouttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class RoutineEditCreate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_edit_create);
        this.setTitle("Routine Input");

        Bundle bundle=getIntent().getExtras();

        if(bundle.getInt("index")==-1){
            Log.v("debug","ROUTINE EDIT CREATE: CREATE ");
        }
        else{
            Log.v("debug","ROUTINE EDIT CREATE: EDIT ");
        }
    }

    public void finish(View view){
        finish();

    }


}