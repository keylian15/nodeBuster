package fr.iutlittoral.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import fr.iutlittoral.components.BoxShape;
import fr.iutlittoral.components.Shade;
import fr.iutlittoral.components.Position;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * A System rendering entities that have the shape of a square
 * Targeted entities :
 *  - BoxShape Component
 *  - Position Component
 *  - Shade Component
 */
public class BoxShapeRenderer extends IteratingSystem {

    private ComponentMapper<BoxShape> boxes = ComponentMapper.getFor(BoxShape.class);
    private ComponentMapper<Position> positions = ComponentMapper.getFor(Position.class);
    private ComponentMapper<Shade> colors = ComponentMapper.getFor(Shade.class);
    private Canvas canvas;

    public BoxShapeRenderer(Canvas canvas) {
        super(Family.all(BoxShape.class, Position.class, Shade.class).get());
        this.canvas = canvas;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Position position = positions.get(entity);
        BoxShape box = boxes.get(entity);
        Shade shade = colors.get(entity);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.save();
        gc.setFill(shade.currentColor);
        gc.fillRect(position.x, position.y, box.width, box.height);
        gc.restore();
    }
}
