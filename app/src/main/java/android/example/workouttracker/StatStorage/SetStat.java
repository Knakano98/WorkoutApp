package android.example.workouttracker.StatStorage;

public class SetStat {
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


}
