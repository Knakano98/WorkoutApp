package android.example.workouttracker.StatStorage;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Set;

public class ExerciseStat implements Parcelable {
    private ArrayList<SetStat> sets=new ArrayList<>();
    private String date;
    private String name;

    public ExerciseStat(){

    }

    public void setDate(String date){
        this.date=date;
    }

    public String getDate(){
        return date;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public void addSet(SetStat set){
        sets.add(set);
    }

    public ArrayList<SetStat> getSets(){
        return sets;
    }


    public void logExerciseStat(){
        Log.v("debug","NAME: " + this.name);
        Log.v("debug","TIME: " + this.date);
        for(int i=0;i<sets.size();i++){
            Log.v("debug","Set "+(i+1));
            Log.v("debug", "    Reps  : "+ sets.get(i).getReps());
            Log.v("debug", "    Weight: "+ sets.get(i).getWeight());
        }
    }

    protected ExerciseStat(Parcel in) {
        if (in.readByte() == 0x01) {
            sets = new ArrayList<SetStat>();
            in.readList(sets, SetStat.class.getClassLoader());
        } else {
            sets = null;
        }
        date = in.readString();
        name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (sets == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(sets);
        }
        dest.writeString(date);
        dest.writeString(name);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ExerciseStat> CREATOR = new Parcelable.Creator<ExerciseStat>() {
        @Override
        public ExerciseStat createFromParcel(Parcel in) {
            return new ExerciseStat(in);
        }

        @Override
        public ExerciseStat[] newArray(int size) {
            return new ExerciseStat[size];
        }
    };
}