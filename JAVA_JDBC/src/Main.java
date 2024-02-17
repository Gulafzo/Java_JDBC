import java.util.List;

//приложения для фитнеса, здорового питания
public class Main {
    public static void main(String[] args) {
        WorkoutManager workoutManager = new WorkoutManager();

        // Создаем таблицу workouts
        workoutManager.createWorkoutsTable();

        Workout workout1 = new Workout(1, "Running", 30);
        String type = workout1.getType();
        int duration = workout1.getDuration();

        System.out.println("Workout type: " + type);
        System.out.println("Duration: " + duration + " minutes");

        new Thread(() -> {
            workoutManager.saveWorkout(new Workout(1, "Running", 40));
        }).start();

        new Thread(() -> {
            List<Workout> workouts = workoutManager.getWorkouts();
            for (Workout workout : workouts) {
                System.out.println(workout.getType() + ": " + workout.getDuration() + " mins");
            }
        }).start();

        synchronized (workoutManager) {
            workoutManager.createWorkoutsTable();
            Workout workout2 = new Workout(8, "Running", 20);
            workoutManager.saveWorkout(workout2);
            List<Workout> workouts = workoutManager.getWorkouts();
            for (Workout workout : workouts) {
                System.out.println(workout.getType() + ": " + workout.getDuration() + " mins");
            }
        }
    }
}