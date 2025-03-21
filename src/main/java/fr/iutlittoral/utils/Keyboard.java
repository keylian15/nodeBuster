package fr.iutlittoral.utils;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * A keyboard manager
 */
public class Keyboard  {
    private boolean changed = false;

    private boolean keyPressed[] = new boolean[255];

    public Keyboard(Scene scene) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            int keyCode = event.getCode().ordinal();
            this.setPressed(keyCode, true);
        });

        scene.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            int keyCode = event.getCode().ordinal();
            this.setPressed(keyCode, false);
        });
    }

    public void reset() {changed = false;}

    public boolean hasChanged() {return changed;}

    public void setPressed(int keyCode, boolean isPressed) {
        if (keyCode > 255) return;
        if (isPressed != keyPressed[keyCode]) {
            keyPressed[keyCode] = isPressed;
            changed = true;
        }
    }

    public void setPressed(KeyCode keyCode, boolean isPressed) {
        this.setPressed(keyCode.ordinal(), isPressed);
    }

    public boolean isKeyPressed(int keyCode) {
        return keyPressed[keyCode];
    }

    public boolean isKeyPressed(KeyCode keyCode) {
        return keyPressed[keyCode.ordinal()];
    }
}
