import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class WorkoutManager {

    private static final String url = "jdbc:sqlite:workouts.db";

    // Метод для создания таблицы workouts
    public synchronized  void createWorkoutsTable() {
        String sql = "CREATE TABLE IF NOT EXISTS workouts (\n"
                + "id integer PRIMARY KEY,\n"
                + "type text NOT NULL,\n"
                + "duration integer\n"
                + ");";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:workouts.db");
             Statement stmt = conn.createStatement()) {
            // Создаем таблицу workouts
            stmt.execute(sql);
            System.out.println("Таблица workouts была успешно создана.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public synchronized  List<Workout> getWorkouts() {
        List<Workout> workouts = new ArrayList<>();
        String query = "SELECT id, type, duration FROM workouts";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String type = rs.getString("type");
                int duration = rs.getInt("duration");
                Workout workout = new Workout(id, type, duration);
                workouts.add(workout);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workouts;
    }

    public synchronized  void saveWorkout(Workout workout) {
        String query = "INSERT INTO workouts (type, duration) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, workout.getType());
            stmt.setInt(2, workout.getDuration());
            stmt.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
