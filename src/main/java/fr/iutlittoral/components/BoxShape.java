package fr.iutlittoral.components;

import com.badlogic.ashley.core.Component;

/**
 * A component representing that an entity is supposed to be rendered on the screen as a square.
 */
public class BoxShape implements Component {
    public double width;
    public double height;

    public BoxShape(double width, double height) {
        this.width = width;
        this.height = height;
    }
}
