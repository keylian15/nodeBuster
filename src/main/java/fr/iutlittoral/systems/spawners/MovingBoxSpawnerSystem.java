package fr.iutlittoral.systems.spawners;

import java.util.Random;

import com.badlogic.ashley.core.Entity;

import fr.iutlittoral.components.*;
import fr.iutlittoral.components.spawntypes.MovingBoxSpawnType;
import javafx.scene.paint.Color;

public class MovingBoxSpawnerSystem extends AbstractSpawnerSystem {
    private Color color;

    public MovingBoxSpawnerSystem(Color color) {
        super(MovingBoxSpawnType.class);
        this.color = color;
    }

    @Override
    public void spawn(double x, double y) {
        Entity entity = new Entity();
        entity.add(new Position(x, y));
        entity.add(new BoxShape(40, 40));
        entity.add(new Shade(color));
        entity.add(new LimitedLifespan(30));
        entity.add(new BoxCollider(40, 40));
        entity.add(new Target(5));
        entity.add(new AlphaDecay());

        entity.add(new Velocity(randomVelocity(), randomVelocity()));

        getEngine().addEntity(entity);
    }

    private float randomVelocity(){
        Random random = new Random();
        int etape =random.nextInt(5) ;
        float velocity = 100 + etape *50;
        if(random.nextBoolean()) velocity = -velocity; 
        return velocity;
    }
}
