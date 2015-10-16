package com.group5.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.group5.core.controllers.Director;
import com.group5.core.world.Player;
import com.group5.core.world.WorldManager;
import com.group5.core.world.WorldObject;

import java.util.Iterator;

/**
 * Main game screen.
 */
public class MainGameScreen implements Screen {

    /**
     * A spritebatch to draw things with.
     */
    private SpriteBatch batch;
    /**
     * The worldManager that contains all game objects.
     */
    private WorldManager worldManager;
    /**
     * The camera the worldManager is viewed with.
     */
    private OrthographicCamera camera;
    /**
     * Stage of this screen.
     */
    private Stage stage = new Stage();

    /**
     * The game over screen widget.
     */
    private GameOverWidget gameOverWidget;

    /**
     * Skin of the labels.
     */
    private Skin labelSkin;
    /**
     * Box2D shape renderer for debugging.
     */
    private Box2DDebugRenderer physicsRenderer;
    /**
     * Boolean to check if the game over menu is active.
     */
    private boolean gameOverMenuActive = false;
    /**
     * Score of the game.
     */
    private float score;
    /**
     * Label that indicates the score.
     */
    private Label scoreLabel;

    /**
     * Constructs a new main game screen that plays the actual game.
     *
     * @param b the SpriteBatch to draw textures with
     */
    public MainGameScreen(final SpriteBatch b) {
        this.stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        gameOverWidget = new GameOverWidget(this);
        this.batch = b;
        this.score = 0;
        this.worldManager = new WorldManager();
        Player player = new Player(worldManager.getPhysicsWorld(), new Vector2(2, 7), new Vector2(2, 2));
        Director director = new Director(7, 2, player.getPosition(), worldManager.getPhysicsWorld(), new Vector2(200, 0));
        this.worldManager.setPlayer(player);
        this.worldManager.setDirector(director);
        this.physicsRenderer = new Box2DDebugRenderer();
        worldManager.setPlayer(player);

        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth / 2.f + player.getX(),
                camera.viewportHeight / 2.f + player.getY(), 0);
        camera.update();
        Gdx.input.setInputProcessor(worldManager.getInputProcessor());
    }

    /**
     * Returns the screen's SpriteBatch.
     * @return the screen's SpriteBatch
     */
    public SpriteBatch getSpriteBatch() {
        return batch;
    }

    /**
     * Returns the screen's Stage.
     * @return the screen's Stage
     */
    public Stage getStage() {
        return stage;
    }

    @Override
    public void show() {
        createDefaultLabelSkin();
        createScoreLabel();
    }

    @Override
    public void resume() {

    }

    @Override
    public void render(final float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        worldManager.update(delta);

        camera.position.set(camera.viewportWidth / 2.f
                        + worldManager.getPlayer().getX() * 50.f - 100.f,
                camera.viewportHeight / 2.f, 0);
        camera.update();

        batch.setProjectionMatrix(
                camera.combined.scale(1 / WorldManager.PHYSICS_SCALE_FACTOR,
                        1 / WorldManager.PHYSICS_SCALE_FACTOR,
                        1));
        batch.begin();
        Iterator<WorldObject> it = worldManager.getDirector().getObjects(true);
        worldManager.getPlayer().doRender(batch);
        while (it.hasNext()) {
            it.next().doRender(batch);
        }
        if (!gameOverMenuActive && !(worldManager.getGameStatus())) {
            gameOverMenuActive = true;
            gameOverWidget.setScore(Math.round(score));
            gameOverWidget.show();
            scoreLabel.setVisible(false);
            Gdx.input.setInputProcessor(stage);
        }
        score = score + delta * worldManager.getPlayer().getSpeed().len();

        scoreLabel.setText("Score: " + Math.round(score));
        batch.end();
        stage.act();
        stage.draw();

        // Enable if you want to see physics outlined
        physicsRenderer.render(this.worldManager.getPhysicsWorld(),
                camera.combined.scale(50.f, 50.f, 1.f));
    }

    /**
     * Create a label which will indicate the score.
     */
    private void createScoreLabel() {
        scoreLabel = new Label("Score: " + score, labelSkin);
        scoreLabel.setPosition(5, 0);
        stage.addActor(scoreLabel);
    }

    /**
     * Create the skin for a default label.
     */
    private void createDefaultLabelSkin() {
        // create font
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("menufont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 16;
        BitmapFont font = generator.generateFont(param);
        generator.dispose();
        labelSkin = new Skin();
        labelSkin.add("default", font);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = labelSkin.getFont("default");
        labelStyle.fontColor = Color.RED;
        labelSkin.add("default", labelStyle);
    }

    @Override
    public void pause() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void resize(final int width, final int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {

    }

}
