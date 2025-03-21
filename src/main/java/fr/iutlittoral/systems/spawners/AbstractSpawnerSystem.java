package fr.iutlittoral.systems.spawners;

import java.util.Random;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;

import fr.iutlittoral.components.Spawner;

/**
 * An abstract class of all spawning systems
 */
public abstract class AbstractSpawnerSystem extends IteratingSystem {

    private Random rng;
    private ComponentMapper<Spawner> spawners = ComponentMapper.getFor(Spawner.class);

    /**
     * 
     * @param spawnTypeClass the component type this spawner system is taking care of. This system will spawn
     * entities from spawners that have this type of component
     */
    public AbstractSpawnerSystem(Class<? extends Component> spawnTypeClass) {
        super(Family.all(Spawner.class, spawnTypeClass).get());
        this.rng = new Random();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Spawner spawner = spawners.get(entity);

        spawner.currentTime += deltaTime;

        if (spawner.currentTime >= spawner.timeBetweenSpawns) {
            spawner.currentTime -= spawner.timeBetweenSpawns;
            double x = rng.nextDouble(spawner.minX, spawner.maxX);
            double y = rng.nextDouble(spawner.minY, spawner.maxY);
            this.spawn(x, y);
        }
        
    }

    public abstract void spawn(double x, double y);
}
