package fr.iutlittoral.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import fr.iutlittoral.components.Position;
import fr.iutlittoral.components.Velocity;

/**
 * Velocity System
 * Updates the position of Entities that have a velocity Component
 * Targeted Entities:
 *  - Velocity Component
 *  - Position Component
 */
public class VelocitySystem extends IteratingSystem {

    ComponentMapper<Velocity> velocities = ComponentMapper.getFor(Velocity.class);
    ComponentMapper<Position> positions = ComponentMapper.getFor(Position.class);

    public VelocitySystem() {
        super(Family.all(Velocity.class, Position.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Position position = positions.get(entity);
        Velocity velocity = velocities.get(entity);

        position.x += velocity.vector.x * deltaTime;
        position.y += velocity.vector.y * deltaTime;
    }
    
}
