package fr.iutlittoral.components;

import com.badlogic.ashley.core.Component;

/**
 * A component representing that an entity can be hit, in a zone of a certain size.
 */
public class BoxCollider implements Component {
    public double width;
    public double height;

    public BoxCollider(double width, double height) {
        this.width = width;
        this.height = height;
    }
}
