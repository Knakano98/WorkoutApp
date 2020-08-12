package android.example.workouttracker.StatStorage;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class DayStat implements Parcelable {
    //Create new one for each session
    //Save into folder after

    private String date;
    private String name;
    private ArrayList<ExerciseStat> exerciseStatArrayList=new ArrayList<>();

    public void setDate(String date){
        this.date=date;
    }

    public String getDate(){
        return date;
    }

    public DayStat(){

    }

    public void addExerciseStat(ExerciseStat exerciseStat){
        exerciseStatArrayList.add(exerciseStat);
    }

    public ArrayList<ExerciseStat> getExerciseStatArrayList() {
        return exerciseStatArrayList;
    }

    protected DayStat(Parcel in) {
        date = in.readString();
        name = in.readString();
        if (in.readByte() == 0x01) {
            exerciseStatArrayList = new ArrayList<ExerciseStat>();
            in.readList(exerciseStatArrayList, ExerciseStat.class.getClassLoader());
        } else {
            exerciseStatArrayList = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(name);
        if (exerciseStatArrayList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(exerciseStatArrayList);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<DayStat> CREATOR = new Parcelable.Creator<DayStat>() {
        @Override
        public DayStat createFromParcel(Parcel in) {
            return new DayStat(in);
        }

        @Override
        public DayStat[] newArray(int size) {
            return new DayStat[size];
        }
    };
}