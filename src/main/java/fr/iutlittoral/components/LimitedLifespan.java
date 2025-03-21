package fr.iutlittoral.components;

import com.badlogic.ashley.core.Component;

/**
 * A component representing that an entity is supposed to leave the world after a certain period of time
 */
public class LimitedLifespan implements Component {
    public double totalLifespan;
    public double elapsedLifespan;

    public LimitedLifespan(double lifespan) {
        this.totalLifespan = lifespan;
        this.elapsedLifespan = 0;
    }
}
