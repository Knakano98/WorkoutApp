package android.example.workouttracker.StatStorage;

import android.os.Parcel;
import android.os.Parcelable;

public class SetStat implements Parcelable {
    private int reps;
    private int weight;

    public SetStat(int reps,int weight){
        this.reps=reps;
        this.weight=weight;
    }

    public int getReps(){
        return reps;
    }

    public void setReps(int reps){
        this.reps=reps;
    }

    public int getWeight(){
        return weight;
    }

    public void setWeight(int weight){
        this.weight=weight;
    }



    protected SetStat(Parcel in) {
        reps = in.readInt();
        weight = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(reps);
        dest.writeInt(weight);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<SetStat> CREATOR = new Parcelable.Creator<SetStat>() {
        @Override
        public SetStat createFromParcel(Parcel in) {
            return new SetStat(in);
        }

        @Override
        public SetStat[] newArray(int size) {
            return new SetStat[size];
        }
    };
}