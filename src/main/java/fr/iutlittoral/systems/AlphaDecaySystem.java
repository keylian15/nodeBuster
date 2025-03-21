package fr.iutlittoral.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import fr.iutlittoral.components.AlphaDecay;
import fr.iutlittoral.components.Shade;
import fr.iutlittoral.components.LimitedLifespan;
import javafx.scene.paint.Color;


/**
 * A system that fades rendered components over time
 * targeted entities :
 *  - AlphaDecay Component
 *  - LimitedLifespan Component
 *  - Shade Component
 */
public class AlphaDecaySystem extends IteratingSystem implements EntityListener {
    ComponentMapper<AlphaDecay> alphas = ComponentMapper.getFor(AlphaDecay.class);
    ComponentMapper<LimitedLifespan> lifespans = ComponentMapper.getFor(LimitedLifespan.class);
    ComponentMapper<Shade> tints = ComponentMapper.getFor(Shade.class);

    public AlphaDecaySystem() {
        super(Family.all(AlphaDecay.class, LimitedLifespan.class, Shade.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        // Alpha alpha = alphas.get(entity);
        LimitedLifespan lifespan = lifespans.get(entity);
        double decay = 1 - ((double)lifespan.elapsedLifespan / (double)lifespan.totalLifespan);
        // alpha.value = decay;
        Shade shades = tints.get(entity);
        Color color = shades.color;
        shades.currentColor = color.deriveColor(1., 1., 1., decay);
    }

    @Override
    public void entityAdded(Entity entity) {
        // System.out.println("Entity added " + entity);
    }

    @Override
    public void entityRemoved(Entity entity) {
        // System.out.println("Entity removed " + entity);
        
    }
}
