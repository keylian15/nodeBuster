package fr.iutlittoral.components;

import com.badlogic.ashley.core.Component;

public class Bullet implements Component {
    public double x;
    public double y;
    public double radius;

    public Bullet(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
}
