package tr1fker.laboratorywork8;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class HouseSimulationController {

    @FXML
    private Pane rootPane;

    @FXML
    private Rectangle window;

    @FXML
    private Button startButton;

    private boolean lightOn;

    @FXML
    public void initialize() {
        this.lightOn = false;
        startButton.setOnAction(event -> toggleLightAndSmoke());
    }

    private void toggleLightAndSmoke() {
        lightOn = !lightOn;
        if (lightOn) {
            window.setFill(Color.YELLOW);
            startInfiniteSmoke();
            startButton.setText("Stop");
        } else {
            window.setFill(Color.GRAY);
            stopInfiniteSmoke();
            startButton.setText("Start");
        }
    }

    private Timeline smokeTimeline;
    private void startInfiniteSmoke() {
        smokeTimeline = new Timeline(new KeyFrame(Duration.millis(200), event -> {
            Circle smoke = createSmokeBall();
            rootPane.getChildren().add(smoke);
            animateSmoke(smoke);
        }));

        smokeTimeline.setCycleCount(Timeline.INDEFINITE);
        smokeTimeline.play();
    }

    private void stopInfiniteSmoke() {
        if (smokeTimeline != null) {
            smokeTimeline.stop();
        }
    }

    private Circle createSmokeBall() {
        Circle smoke = new Circle(10, Color.GRAY);
        smoke.setCenterX(200 + (Math.random() * 15 - 7.5));
        smoke.setCenterY(60);
        return smoke;
    }

    private void animateSmoke(Circle smoke) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(50), event -> {
                    smoke.setCenterY(smoke.getCenterY() - 2);
                    smoke.setCenterX(smoke.getCenterX() + Math.random() - 0.5);
                    if (smoke.getCenterY() < 0) {
                        rootPane.getChildren().remove(smoke);
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
