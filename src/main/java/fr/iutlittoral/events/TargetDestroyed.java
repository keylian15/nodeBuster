package fr.iutlittoral.events;

public class TargetDestroyed {
    public final int score;
    public double x;
    public double y;

    public TargetDestroyed(int score, double x, double y) {
        this.score = score;
        this.x = x;
        this.y = y;
    }
}
