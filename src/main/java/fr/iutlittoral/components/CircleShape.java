package fr.iutlittoral.components;

import com.badlogic.ashley.core.Component;

/**
 * A component representing that an entity is supposed to be rendered on the screen as a circle.
 */
public class CircleShape implements Component {
    public double radius;

    public CircleShape(double radius) {
        this.radius = radius;
    }
}
