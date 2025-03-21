package fr.iutlittoral.components;

import com.badlogic.ashley.core.Component;

import fr.iutlittoral.utils.Vector2;

/**
 * A component representing that an entity is moving at a certain speed.
 */
public class Velocity implements Component {
    
    public Vector2 vector;

    public Velocity(double dx, double dy) {
        this.vector = new Vector2(dx, dy);
    }
}
