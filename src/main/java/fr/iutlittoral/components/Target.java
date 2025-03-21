package fr.iutlittoral.components;

import com.badlogic.ashley.core.Component;

/**
 * A component representing a target that can be shot
 */
public class Target implements Component {
    public int value;

    public Target() {
        this.value = 1;
    }

    public Target(int value) {
        this.value = value;
    }
}
