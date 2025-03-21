package fr.iutlittoral;

import fr.iutlittoral.components.Spawner;
import fr.iutlittoral.components.Target;
import fr.iutlittoral.components.spawntypes.MovingBoxSpawnType;
import fr.iutlittoral.components.spawntypes.SimpleBoxSpawnType;
import fr.iutlittoral.events.TargetDestroyed;
import fr.iutlittoral.systems.*;
import fr.iutlittoral.systems.spawners.MovingBoxSpawnerSystem;
import fr.iutlittoral.systems.spawners.SimpleBoxSpawnerSystem;
import fr.iutlittoral.utils.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Signal;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        // Taille de la fenetre :
        final int x = 1600;
        final int y = 900;

        /* Standard JavaFX stage creation */
        var canvas = new Canvas(x, y);
        canvas.getGraphicsContext2D().fillRect(0, 0, x, y);
        var scene = new Scene(new StackPane(canvas), x, y);
        stage.setScene(scene);
        stage.show();

        /* Ashley engine initialization */
        Engine world = new Engine();

        /* Helper objects initialization */
        Font font = new Font("Vera.ttf", 25);
        // Keyboard keyboard = new Keyboard(scene);
        Mouse mouse = new Mouse(canvas);
        EntityCreator creator = new EntityCreator(world);

        /* Adds a target spawner */
        // Changement dans la durée entre les spwans parce que sinon spawn par lot.
        creator.create(
                new Spawner(1.5, 0, 0, 1550, 850),
                new SimpleBoxSpawnType());
        creator.create(new Spawner(1.5, 0, 0, 1550, 850),
                new MovingBoxSpawnType());

        // Création du score
        Score score = new Score();

        // Création de explosion
        ExplosionListener explosion = new ExplosionListener(world);

        /* System registration */
        world.addSystem(new SimpleBoxSpawnerSystem(Color.AQUA));
        // Ajout des cibles mouvantes.
        world.addSystem(new MovingBoxSpawnerSystem(Color.RED));

        BulletCollisionSystem bulletCollisionSystem = new BulletCollisionSystem();
        world.addSystem(bulletCollisionSystem);
        world.addSystem(new LimitedLifespanSystem());
        world.addSystem(new VelocitySystem());

        // Récupération du signal
        Signal<TargetDestroyed> targetDestroyedSignal = bulletCollisionSystem.getTargetDestroyedSignal();
        // Score en tant que listener de targetDestroyedSignal
        targetDestroyedSignal.add(score);
        // Explosion en tant que listener
        targetDestroyedSignal.add(explosion);

        AlphaDecaySystem alphaSystem = new AlphaDecaySystem();
        world.addEntityListener(Family.all(Target.class).get(), alphaSystem);
        world.addSystem(alphaSystem);
        world.addSystem(new BoxShapeRenderer(canvas));
        world.addSystem(new CircleShapeRenderer(canvas));

        GameLoopTimer timer = new GameLoopTimer() {
            @Override
            public void tick(float secondsSinceLastFrame) {
                if (mouse.isJustPressed(MouseButton.PRIMARY)) {
                    creator.createBullet(mouse.getX(), mouse.getY());
                    mouse.resetJustPressed();
                }
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.save();
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, x, y);
                gc.setFill(Color.WHITE);
                gc.setFont(font);
                gc.fillText("Score " + score.getScore(), 10, 35);
                gc.restore();
                world.update(secondsSinceLastFrame);
            }
        };

        timer.start();
    }

    public static void main(String[] args) {
        launch();
    }
}