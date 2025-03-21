package fr.iutlittoral.components;

import com.badlogic.ashley.core.Component;

/**
 * A component representing the (x, y) position of an Entity
 */
public class Position implements Component {
    
    public double x;
    public double y;
    
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Position " + x + " ; " + y;
    }
}
