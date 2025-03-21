package fr.iutlittoral.components;

import com.badlogic.ashley.core.Component;

import javafx.scene.paint.Color;

/**
 * A component representing the color of an Entity meant to be rendered on the screen
 */
public class Shade implements Component {
    public Color color;
    public Color currentColor;

    public Shade(Color color ) {
        this.color = color;
        this.currentColor = color;
    }
}
