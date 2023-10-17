package screens;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.util.Duration;
import java.awt.Toolkit;

import interfaces.Clock;


public class PomodoroScreen extends VBox implements Clock {
  private int minutes;
  private int initalMinutes;
  private int seconds=0;
  private boolean isRunning=false;
  private Timeline timer;
  private Label timerLabel;
  private Stage stage;

  public PomodoroScreen (int minutes, Stage stage){
    this.minutes = minutes ;
    this.initalMinutes = minutes;
    this.stage = stage;
    stage.setAlwaysOnTop(true);
    createScreen();
  }

  
  private void createScreen (){
  this.setStyle("-fx-background-color: #f0f0f0;");
  this.setAlignment(Pos.CENTER);

  Label titleLabel = new Label("Concentração");
  titleLabel.setFont(Font.font("System", 30));
  titleLabel.setStyle("-fx-font-weight: bold");
  StackPane titlePane = new StackPane(titleLabel);

  timerLabel = new Label(formatTime(minutes, seconds));
  timerLabel.setFont(Font.font("System", 22));
  timerLabel.setStyle("-fx-font-weight: bold");

  Circle circle = new Circle(75);
  circle.setStyle("-fx-fill: lightgray;");
  StackPane timerPane = new StackPane(circle, timerLabel);

  Button startButton = new Button("Start");
  Button resetButton = new Button("Reset");

  startButton.setOnAction(event -> {
    if (!isRunning) {
        isRunning = true;
        startButton.setText("Pause");
        timer = createTimer(startButton,resetButton);
        timer.play();
    } else {
        isRunning = false;
        startButton.setText("Resume");
        timer.pause();
    }
  });

  resetButton.setOnAction(event -> {
      isRunning = false;
      startButton.setText("Start");
      if (timer != null) {
          timer.stop();
      }
      if(minutes==0 && seconds ==0){
   
        RestScreen rest = new RestScreen(25,stage);
        Scene restScene = new Scene(rest, 300, 350);
        stage.setScene(restScene);
      }
      minutes = this.initalMinutes;
      seconds = 0;
      timerLabel.setText(formatTime(minutes, seconds));
  });

  HBox buttonBox = new HBox(20, startButton, resetButton);
  buttonBox.setAlignment(Pos.CENTER);

  StackPane stackPane = new StackPane();
  stackPane.setPadding(new Insets(30, 0, 30, 0));
  stackPane.getChildren().add(timerPane);

  this.getChildren().addAll(titlePane,stackPane, buttonBox);
  }

  public Timeline createTimer(Button start,Button reset) {
    KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
      if (minutes == 0 && seconds == 0) {
        try {
            playSound();
            timer.pause();
          } catch (Exception e) {
            System.out.println(e);
          }finally{
            start.setDisable(true);
            reset.setText("Vamos descansar!");
          }
      } else {
          if (seconds == 0) {
              minutes--;
              seconds = 59;
          } else {
              seconds--;
          }
      }
      timerLabel.setText(formatTime(minutes, seconds));
     });
    Timeline timeline = new Timeline(keyFrame);
    timeline.setCycleCount(Timeline.INDEFINITE); 
    return timeline;
  }

  
  public void playSound() {
    Toolkit.getDefaultToolkit().beep();
  }

  
  public String formatTime(int minutes, int seconds) {
    return String.format("%02d:%02d", minutes, seconds);
  }
 
}