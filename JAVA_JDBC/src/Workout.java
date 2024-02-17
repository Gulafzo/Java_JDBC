public class Workout {

    private int id;
    private String type;
    private int duration;

    public Workout(int id, String type, int duration) {
        this.id = id;
        this.type = type;
        this.duration = duration;
    }


    public String getType() {
        return type;
    }

    public int getDuration() {
        return duration;
    }

    public int getId() {
        return id;
    }
}
