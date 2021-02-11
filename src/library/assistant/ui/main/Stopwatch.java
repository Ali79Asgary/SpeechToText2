package library.assistant.ui.main;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Stopwatch {

    Label lblTimer;
    Timeline timeline;
    int minutes = 0, seconds = 0, milliSeconds = 0;

    public Stopwatch(Label lblTimer) { this.lblTimer = lblTimer; }

    public void starter(){
        lblTimer.setText("00:00:000");
        timeline = new Timeline(new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                change(lblTimer);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(false);
    }

    public void change(Label lblTimer) {
        if (milliSeconds == 1000){
            seconds++;
            milliSeconds = 0;
        }
        if (seconds == 60){
            minutes++;
            seconds = 0;
        }
        lblTimer.setText((((minutes/10) == 0) ? "0" : "") + minutes + ":"
                + (((seconds/10) == 0) ? "0" : "") + seconds + ":"
                + (((milliSeconds/10) == 0) ? "00" : (((milliSeconds/100) == 0) ? "0" : "")) + milliSeconds++);
    }

    public void play() { timeline.play(); }

    public void pause() { timeline.pause(); }

    public void reset(Label lblTimer) {
        minutes = 0;
        seconds = 0;
        milliSeconds = 0;
        pause();
        lblTimer.setText("00:00:000");
    }
}
