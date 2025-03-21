package fr.iutlittoral.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import fr.iutlittoral.components.CircleShape;
import fr.iutlittoral.components.Shade;
import fr.iutlittoral.components.Position;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * A System rendering entities that have the shape of a square
 * Targeted entities :
 *  - CircleShape Component
 *  - Position Component
 */
public class CircleShapeRenderer extends IteratingSystem {

    private ComponentMapper<CircleShape> circles = ComponentMapper.getFor(CircleShape.class);
    private ComponentMapper<Position> positions = ComponentMapper.getFor(Position.class);
    private ComponentMapper<Shade> shades = ComponentMapper.getFor(Shade.class);
    private Canvas canvas;

    public CircleShapeRenderer(Canvas canvas) {
        super(Family.all(CircleShape.class, Position.class).get());
        this.canvas = canvas;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Position position = positions.get(entity);
        CircleShape circle = circles.get(entity);
        Shade shade = shades.get(entity);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.save();
        gc.setFill(shade.currentColor);
        gc.fillOval(position.x, position.y, circle.radius, circle.radius);
        gc.restore();
    }
}
