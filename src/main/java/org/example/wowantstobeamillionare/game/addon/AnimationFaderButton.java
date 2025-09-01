package org.example.wowantstobeamillionare.game.addon;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class AnimationFaderButton {

    public static void fadeButtonAnnimation(Button source) {
        TranslateTransition slideOut = new TranslateTransition(Duration.millis(800), source);
        slideOut.setToX(200); // Slide 200 pixels to the right
        FadeTransition fadeOut = new FadeTransition(Duration.millis(800), source);
        fadeOut.setToValue(0); // Fade to transparent
        // Combine both animations
        ParallelTransition slideAndFade = new ParallelTransition(slideOut, fadeOut);
        // Disable the button after animation completes
        slideAndFade.setOnFinished(e -> source.setDisable(true));
        // Start the animation
        slideAndFade.play();
    }
}
