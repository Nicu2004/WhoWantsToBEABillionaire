package org.example.wowantstobeamillionare.controllers.player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
public class gradientClass {
    // Add this to your PlayerGameScene controller class



        @FXML
        private Rectangle backgroundRect; // Add this to your FXML background rectangle

        private Timeline gradientAnimation;

        @FXML
        public void initialize() {
            // Your existing initialization code...

            // Start the live gradient animation
            startLiveGradient();
        }

        private void startLiveGradient() {
            // Create animated gradient with shifting colors
            gradientAnimation = new Timeline(
                    new KeyFrame(Duration.ZERO, e -> updateGradient(0)),
                    new KeyFrame(Duration.seconds(0.1), e -> updateGradient(0.1)),
                    new KeyFrame(Duration.seconds(0.2), e -> updateGradient(0.2)),
                    new KeyFrame(Duration.seconds(0.3), e -> updateGradient(0.3)),
                    new KeyFrame(Duration.seconds(0.4), e -> updateGradient(0.4)),
                    new KeyFrame(Duration.seconds(0.5), e -> updateGradient(0.5)),
                    new KeyFrame(Duration.seconds(0.6), e -> updateGradient(0.6)),
                    new KeyFrame(Duration.seconds(0.7), e -> updateGradient(0.7)),
                    new KeyFrame(Duration.seconds(0.8), e -> updateGradient(0.8)),
                    new KeyFrame(Duration.seconds(0.9), e -> updateGradient(0.9)),
                    new KeyFrame(Duration.seconds(1.0), e -> updateGradient(1.0))
            );

            gradientAnimation.setCycleCount(Timeline.INDEFINITE);
            gradientAnimation.setAutoReverse(true);
            gradientAnimation.play();
        }

        private void updateGradient(double progress) {
            // Create shifting colors based on time
            Color color1 = Color.web(interpolateColor("#16213e", "#533483", progress));
            Color color2 = Color.web(interpolateColor("#0f3460", "#16213e", progress));
            Color color3 = Color.web(interpolateColor("#533483", "#0f3460", progress));

            // Create the animated gradient
            LinearGradient gradient = new LinearGradient(
                    0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                    new Stop(0, color1),
                    new Stop(0.5, color2),
                    new Stop(1, color3)
            );

            // Apply to background - you'll need to find your background rectangle
            // This assumes you have a background rectangle in your scene
            if (backgroundRect != null) {
                backgroundRect.setFill(gradient);
            }
        }

        // Helper method to interpolate between hex colors
        private String interpolateColor(String color1, String color2, double factor) {
            // Remove the # if present
            color1 = color1.replace("#", "");
            color2 = color2.replace("#", "");

            // Parse RGB components
            int r1 = Integer.parseInt(color1.substring(0, 2), 16);
            int g1 = Integer.parseInt(color1.substring(2, 4), 16);
            int b1 = Integer.parseInt(color1.substring(4, 6), 16);

            int r2 = Integer.parseInt(color2.substring(0, 2), 16);
            int g2 = Integer.parseInt(color2.substring(2, 4), 16);
            int b2 = Integer.parseInt(color2.substring(4, 6), 16);

            // Interpolate
            int r = (int) (r1 + factor * (r2 - r1));
            int g = (int) (g1 + factor * (g2 - g1));
            int b = (int) (b1 + factor * (b2 - b1));

            // Clamp values
            r = Math.max(0, Math.min(255, r));
            g = Math.max(0, Math.min(255, g));
            b = Math.max(0, Math.min(255, b));

            return String.format("#%02x%02x%02x", r, g, b);
        }

        // Alternative: CSS-based live gradient (add this to your scene's stylesheet)
        public void applyCSSGradient() {
            // You can also use CSS animations - add this to a .css file:
        /*
        .live-gradient {
            -fx-background-color: linear-gradient(
                from 0% 0% to 100% 100%,
                #16213e 0%,
                #0f3460 50%,
                #533483 100%
            );
            -fx-background-radius: 10px;
        }

        @keyframes gradient-shift {
            0% {
                -fx-background-color: linear-gradient(
                    from 0% 0% to 100% 100%,
                    #16213e 0%,
                    #0f3460 50%,
                    #533483 100%
                );
            }
            25% {
                -fx-background-color: linear-gradient(
                    from 0% 0% to 100% 100%,
                    #533483 0%,
                    #16213e 50%,
                    #0f3460 100%
                );
            }
            50% {
                -fx-background-color: linear-gradient(
                    from 0% 0% to 100% 100%,
                    #0f3460 0%,
                    #533483 50%,
                    #16213e 100%
                );
            }
            75% {
                -fx-background-color: linear-gradient(
                    from 0% 0% to 100% 100%,
                    #16213e 0%,
                    #0f3460 50%,
                    #533483 100%
                );
            }
            100% {
                -fx-background-color: linear-gradient(
                    from 0% 0% to 100% 100%,
                    #533483 0%,
                    #16213e 50%,
                    #0f3460 100%
                );
            }
        }

        .live-gradient {
            -fx-animation: gradient-shift 3s infinite ease-in-out;
        }
        */
        }

        // Don't forget to stop the animation when the scene closes
        public void stopGradientAnimation() {
            if (gradientAnimation != null) {
                gradientAnimation.stop();
            }
        }
    }
