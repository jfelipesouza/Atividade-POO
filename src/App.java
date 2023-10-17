import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.layout.HBox;
import screens.PomodoroScreen;
import screens.ToDoScreen;
import javafx.geometry.Pos;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        
        stage.setTitle("Atividade avaliativa");

        Button pomodoroButton = new Button("Pomodoro");
        pomodoroButton.setFont(Font.font("System", 30));
        pomodoroButton.setStyle("-fx-font-weight: bold; ");
        pomodoroButton.setStyle("-fx-background-color: transparent; -fx-background-insets: 0;");

        Button toDoButton = new Button("To-Do");
        toDoButton.setFont(Font.font("System", 30));
        toDoButton.setStyle("-fx-font-weight: bold;");
        toDoButton.setStyle("-fx-background-color: transparent; -fx-background-insets: 0;");

        pomodoroButton.setOnAction(event -> {
            PomodoroScreen pomodoroScreen = new PomodoroScreen(1, stage);
            Scene pomodoroScene = new Scene(pomodoroScreen, 300, 350);
            stage.setTitle("Pomodoro");
            Image icon = new Image("file:./assets/tomate.png");
            stage.getIcons().add(icon);

            stage.setScene(pomodoroScene);
        });

        toDoButton.setOnAction(event -> {
            ToDoScreen todoScreen = new ToDoScreen();

            Scene toDoScene = new Scene(todoScreen.getUI(), 400, 450);
            Image paperIcon = new Image("file:./assets/paper.png");
            stage.setTitle("To-Do");
            stage.getIcons().add(paperIcon);
            stage.setScene(toDoScene);
            
        });

        HBox buttonsHBox = new HBox(20, pomodoroButton, toDoButton);
        buttonsHBox.setAlignment(Pos.CENTER);

        Scene initialScene = new Scene(buttonsHBox, 600, 300);
        stage.setScene(initialScene);
        stage.setAlwaysOnTop(true);
        stage.show();
    }
}