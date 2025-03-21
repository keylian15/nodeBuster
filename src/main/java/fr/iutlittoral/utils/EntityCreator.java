package fr.iutlittoral.utils;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;

import fr.iutlittoral.components.AlphaDecay;
import fr.iutlittoral.components.Bullet;
import fr.iutlittoral.components.CircleShape;
import fr.iutlittoral.components.LimitedLifespan;
import fr.iutlittoral.components.Position;
import fr.iutlittoral.components.Shade;
import javafx.scene.paint.Color;

/**
 * Helper class to create entities with a list of components
 */
public class EntityCreator {

    private Engine world;

    public EntityCreator(Engine world) {
        this.world = world;
    }
    
    public Entity create(Component ... components) {
        Entity entity = new Entity();

        for (Component component : components) {
            entity.add(component);
        }

        world.addEntity(entity);

        return entity;
    }

    public void createBullet(double x, double y) {
        double radius = 2;
            Entity bulletEntity = new Entity();
            bulletEntity.add(new Bullet(x, y, radius));
            bulletEntity.add(new LimitedLifespan(.001f));
            
            world.addEntity(bulletEntity);

            Entity circleEntity = new Entity();
            circleEntity.add(new Position(x - radius, y - radius));
            circleEntity.add(new CircleShape(radius));
            circleEntity.add(new LimitedLifespan(.7));
            circleEntity.add(new CircleShape(4));
            circleEntity.add(new Shade(Color.RED));
            circleEntity.add(new AlphaDecay());
            world.addEntity(circleEntity);
    }
}
