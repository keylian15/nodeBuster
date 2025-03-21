package fr.iutlittoral.utils;

import java.util.HashMap;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * A mouse manager
 */
public class Mouse {
    private HashMap<MouseButton, Boolean> pressed = new HashMap<>();
    private HashMap<MouseButton, Boolean> justPressed = new HashMap<>();
    private HashMap<MouseButton, Boolean> released = new HashMap<>();

    private double x;
    private double y;

    public Mouse(Node node) {
        for (MouseButton button : MouseButton.values()) {
            pressed.put(button, false);
            justPressed.put(button, false);
            released.put(button, false);
        }

        node.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
            MouseButton button = event.getButton();
            this.justPressed.put(button, true);
            this.pressed.put(button, true);
            this.x = event.getX();
            this.y = event.getY();
        });

        node.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
            MouseButton button = event.getButton();
            this.justPressed.put(button, false);
            this.pressed.put(button, false);
        });
    }

    public void resetJustPressed() {
        for (MouseButton button : justPressed.keySet())
            justPressed.put(button, false);
    }

    public boolean isPressed(MouseButton button) {
        return this.pressed.get(button);
    }

    public boolean isJustPressed(MouseButton button) {
        return this.justPressed.get(button);
    }

    public boolean isReleased(MouseButton button) {
        return this.released.get(button);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
