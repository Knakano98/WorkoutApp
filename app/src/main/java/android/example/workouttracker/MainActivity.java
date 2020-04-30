package android.example.workouttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    //Functions for button on main activity
    public void execute(View view){
        //Open new activity here
        Intent intent=new Intent(this,Execute.class);
        startActivity(intent);
        Log.v("debug","EXECUTE");
    }
    public void selectEdit(View view){
        Intent intent=new Intent(this,SelectEdit.class);
        startActivity(intent);
        Log.v("debug","SlEDIT");
    }
    public void stats(View view){
        Intent intent=new Intent(this,Stats.class);
        startActivity(intent);
        Log.v("debug","STATS");
    }
    public void history(View view){
        Intent intent=new Intent(this,History.class);
        startActivity(intent);
        Log.v("debug","HISTORY");
    }




}
