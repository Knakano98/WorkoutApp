package android.example.workouttracker.Objects;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

public class Routine implements Parcelable{
    private String routineName;
    private ArrayList<Day> routine;

    public Routine(String name){
        this.routineName=name;
        this.routine=new ArrayList<Day>();
    }

    public void setName(String name){
        this.routineName=name;
    }

    public int routineSize(){
        return routine.size();
    }

    public String getName(){
        return routineName;
    }

    public Day getAtIndex(int index){
        return routine.get(index);
    }

    public void set(Day day, int index){
        routine.set(index,day);
    }

    public void add(Day day){
        routine.add(day);
    }

    public void removeAtIndex(int index){
        routine.remove(index);
    }

    public void logRoutine(){
        for(Day day: routine){
            Log.v("debug","        DayName :" + day.getName());
            day.logDay();
        }
    }

    //Parcelable Implementation
    protected Routine(Parcel in) {
        routineName = in.readString();
        if (in.readByte() == 0x01) {
            routine = new ArrayList<Day>();
            in.readList(routine, Day.class.getClassLoader());
        } else {
            routine = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(routineName);
        if (routine == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(routine);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Routine> CREATOR = new Parcelable.Creator<Routine>() {
        @Override
        public Routine createFromParcel(Parcel in) {
            return new Routine(in);
        }

        @Override
        public Routine[] newArray(int size) {
            return new Routine[size];
        }
    };
}
