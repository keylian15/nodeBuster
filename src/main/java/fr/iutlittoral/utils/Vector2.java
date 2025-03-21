package fr.iutlittoral.utils;

/**
 * A generic class representing a 2D vector
 */
public class Vector2 {
    public double x;
    public double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void normalize() {
        double amplitude = this.amplitude();
        if (amplitude == 0) return;
        this.x /= amplitude;
        this.y /= amplitude;
    }

    public void times(double factor) {
        this.x *= factor;
        this.y *= factor;
    }

    public void add(Vector2 that) {
        this.x += that.x;
        this.y += that.y;
    }

    public double amplitude() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public double amplitude2() {
        return this.x * this.x + this.y * this.y;
    }

    @Override
    public String toString() {
        
        return "(" + x + ";" + y + ")";
    }
}
