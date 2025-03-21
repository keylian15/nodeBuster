package fr.iutlittoral.systems;

import java.util.Random;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;

import fr.iutlittoral.components.AlphaDecay;
import fr.iutlittoral.components.BoxCollider;
import fr.iutlittoral.components.BoxShape;
import fr.iutlittoral.components.LimitedLifespan;
import fr.iutlittoral.components.Position;
import fr.iutlittoral.components.Shade;
import fr.iutlittoral.components.Velocity;
import fr.iutlittoral.events.TargetDestroyed;
import javafx.scene.paint.Color;

public class ExplosionListener implements Listener<TargetDestroyed> {

    private Engine engine;

    public ExplosionListener(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void receive(Signal<TargetDestroyed> signal, TargetDestroyed event) {
        double x = event.x;
        double y = event.y;

        for (double angle = 0; angle < 360; angle += 2) {
            // Convertir l'angle en radians
            double radians = Math.toRadians(angle);

            // Générer un rayon aléatoire pour donner un effet d'explosion irrégulière
            double radius = 150 * Math.random();

            double dx = radius * Math.cos(radians);
            double dy = radius * Math.sin(radians);

            Entity entity = new Entity();
            entity.add(new Position(x, y));
            entity.add(new BoxShape(2, 2));
            entity.add(new Shade(randomColor()));
            entity.add(new LimitedLifespan(randomLifespan()));
            entity.add(new BoxCollider(2, 2));
            entity.add(new AlphaDecay());

            entity.add(new Velocity(dx, dy));

            engine.addEntity(entity);
        }
    }

    private static double randomLifespan() {
        Random random = new Random();
        double lifespan = random.nextDouble(1) + 0.3;
        return lifespan;
    }

    public static Color randomColor() {
        Random random = new Random();
        int key = random.nextInt(0, 5);
        Color color= Color.rgb(255, 0, 0);;
        switch (key) {
            case 0:
                color = Color.rgb(255, 50, 0);
                break;
            case 1:
                color = Color.rgb(255, 100, 0);
                break;
            case 2:
                color = Color.rgb(255, 130, 0);
                break;
            case 3:
                color = Color.rgb(255, 150, 0);
                break;
            case 4:
                color = Color.rgb(255, 165, 0);
                break;
            case 5:
                color = Color.rgb(255, 180, 0);
                break;
        }
        return color;
    }

}
