package fr.iutlittoral.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.utils.ImmutableArray;

import fr.iutlittoral.components.Bullet;
import fr.iutlittoral.components.BoxCollider;
import fr.iutlittoral.components.Position;
import fr.iutlittoral.components.Target;
import fr.iutlittoral.events.TargetDestroyed;


/**
 * Bullet System
 * A system that detects collisions between bullets and collidable entities
 * Targeted Entities:
 *  - "bullets" that have:
 *    - Bullet Component
 *    - Position Component
 *  - "targets" that have:
 *    - BoxCollider Component
 *    - Position Component
 *    - Target Component
 */
public class BulletCollisionSystem extends EntitySystem {


    private Signal<TargetDestroyed> targetDestroyedSignal;

    private ComponentMapper<Bullet> bulletMapper = ComponentMapper.getFor(Bullet.class);
    private ComponentMapper<Position> positionMapper = ComponentMapper.getFor(Position.class);
    private ComponentMapper<BoxCollider> colliderMapper = ComponentMapper.getFor(BoxCollider.class);

    ImmutableArray<Entity> bulletEntities;
    ImmutableArray<Entity> targetEntities;

    @Override
    public void addedToEngine(Engine engine) {
        this.bulletEntities = engine.getEntitiesFor(Family.all(Bullet.class).get());
        this.targetEntities = engine.getEntitiesFor(Family.all(Position.class, Target.class, BoxCollider.class).get());
    }

    public BulletCollisionSystem() {
        this.targetDestroyedSignal = new Signal<TargetDestroyed>();
    }

    public Signal<TargetDestroyed> getTargetDestroyedSignal() {
        return this.targetDestroyedSignal;
    }

    @Override
    public void update(float deltaTime) {
        this.bulletEntities.forEach(
            bulletEntity -> {
                Bullet bullet = bulletMapper.get(bulletEntity);
                this.targetEntities.forEach(targetEntity -> {
                    BoxCollider collider = colliderMapper.get(targetEntity);
                    Position position = positionMapper.get(targetEntity);

                    if (bullet.x >= position.x && bullet.x < position.x + collider.width && bullet.y >= position.y && bullet.y < position.y + collider.height) {
                        int score = targetEntity.getComponent(Target.class).value;
                        targetDestroyedSignal.dispatch(new TargetDestroyed(score, bullet.x, bullet.y));
                        getEngine().removeEntity(targetEntity);
                    }
                });
            }
        );
    }    
}
