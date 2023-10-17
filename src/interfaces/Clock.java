package interfaces;

import javafx.scene.control.Button;

import javafx.animation.Timeline;



public interface Clock {
  Timeline createTimer(Button start,Button reset);
  void playSound();
  String formatTime(int minutes, int seconds);
 
}