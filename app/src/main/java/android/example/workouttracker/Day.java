package android.example.workouttracker;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

public class Day implements Parcelable {
    private String name;
    private ArrayList<Exercise> day;
    //Needs get, add, remove at index,set

    public Day(String name){
        this.day=new ArrayList<Exercise>();
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public int daySize(){
        return day.size();
    }

    public Exercise getAtIndex(int index){
        return day.get(index);
    }

    public void add(Exercise exercise){
        day.add(exercise);
    }

    public void set(Exercise exercise,int index){
        day.set(index,exercise);
    }

    public void removeAtIndex(int index){
        day.remove(index);
    }

    public void logDay(){
        for(Exercise exercise: day){
            Log.v("debug","            ExerciseName: " + exercise.getName());
            Log.v("debug","            Reps: " + exercise.getReps());
            Log.v("debug","            Sets: " + exercise.getSets());
            Log.v("debug","            Weight: " + exercise.getWeight());
            Log.v("debug","            Time: " + exercise.getRestTime());

        }
    }


    //Parsable stuff
    protected Day(Parcel in) {
        name = in.readString();
        if (in.readByte() == 0x01) {
            day = new ArrayList<Exercise>();
            in.readList(day, Exercise.class.getClassLoader());
        } else {
            day = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        if (day == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(day);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Day> CREATOR = new Parcelable.Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel in) {
            return new Day(in);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];
        }
    };
}