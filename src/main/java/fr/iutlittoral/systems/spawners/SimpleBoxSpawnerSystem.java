package fr.iutlittoral.systems.spawners;

import com.badlogic.ashley.core.Entity;

import fr.iutlittoral.components.*;
import fr.iutlittoral.components.spawntypes.SimpleBoxSpawnType;
import javafx.scene.paint.Color;

/**
 * A spawner system that spawn simple boxes.
 * Targeting entities :
 * - Spawner Component
 * - SimpleBox Component
 */
public class SimpleBoxSpawnerSystem extends AbstractSpawnerSystem {

    private Color color;

    public SimpleBoxSpawnerSystem(Color color) {
        super(SimpleBoxSpawnType.class);
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
        entity.add(new Target(1));
        entity.add(new AlphaDecay());
                
        getEngine().addEntity(entity);
    }
}
