package fr.iutlittoral.components;

import com.badlogic.ashley.core.Component;

/**
 * A spawner component. This Entity creates new entities from time to time in the game world.
 */
public class Spawner implements Component {

    public double timeBetweenSpawns;
    public double currentTime;
    public double minX;
    public double minY;
    public double maxX;
    public double maxY;

    /**
     * 
     * @param timeBetweenSpawns delay between two spawnings
     * @param minX x coordinate of the left side of the zone to spawn into
     * @param minY y coordinate of the top side of the zone to spawn into
     * @param maxX x coordinate of the right side of the zone to spawn into
     * @param maxY y coordinate of the bottom side of the zone to spawn into
     */
    public Spawner(double timeBetweenSpawns, double minX, double minY, double maxX, double maxY) {
        this.timeBetweenSpawns = timeBetweenSpawns;
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
        this.currentTime = 0;
    }
}
