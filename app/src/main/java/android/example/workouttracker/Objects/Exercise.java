package android.example.workouttracker.Objects;

import android.os.Parcel;
import android.os.Parcelable;

public class Exercise implements Parcelable {
    private String exerciseName;
    private int reps;
    private int weight;
    private int sets;
    private int restTime;
    //All these have to be accessible/editable outside so all need getters/setters

    public Exercise(String name,int reps, int weight, int sets, int restTime){ //Constructor
        this.exerciseName=name;
        this.reps=reps;
        this.weight=weight;
        this.sets=sets;
        this.restTime=restTime;
    }

    //getters
    public String getName(){
        return exerciseName;
    }
    public int getReps(){
        return reps;
    }
    public int getWeight(){
        return weight;
    }
    public int getSets(){
        return sets;
    }
    public int getRestTime(){return restTime;}

    //setters
    public void setName(String name){
        this.exerciseName=name;
    }
    public void setReps(int reps){
        this.reps=reps;
    }
    public void setWeight(int weight){
        this.weight=weight;
    }
    public void setSets(int sets){
        this.sets=sets;
    }
    public void setRestTime(int restTime){this.restTime=restTime;}

    protected Exercise(Parcel in) {
        exerciseName = in.readString();
        reps = in.readInt();
        weight = in.readInt();
        sets = in.readInt();
        restTime = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(exerciseName);
        dest.writeInt(reps);
        dest.writeInt(weight);
        dest.writeInt(sets);
        dest.writeInt(restTime);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Exercise> CREATOR = new Parcelable.Creator<Exercise>() {
        @Override
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        @Override
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };
}