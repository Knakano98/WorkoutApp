package android.example.workouttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import static java.sql.DriverManager.println;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void execute(View view){
        //Open new activity here
       Log.v("debug","EXECUTE");

    }

    public void selectEdit(View view){
        Log.v("debug","SlEDIT");
    }

    public void stats(View view){
        Log.v("debug","STATS");
    }

    public void history(View view){
        Log.v("debug","HISTORY");
    }
}
