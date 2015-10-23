package com.group5.core.screens;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.group5.core.EndlessRunner;
import com.group5.core.controllers.Director;
import com.group5.core.util.ScoreContainer;
import com.group5.core.util.ScoreItem;
import com.group5.core.world.Player;
import com.group5.core.world.WorldManager;
import com.group5.core.world.WorldObject;

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
     * Skin of the HUD.
     */
    private Skin hudSkin;

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
     * This variable contains the final score, to ensure it doesn't get changed.
     */
    private int finalScore;

    /**
     * Random number generator.
     */
    private Random random = new Random();

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
        worldManager.setPlayer(player);

        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth / 2.f + player.getX(), camera.viewportHeight / 2.f + player.getY(), 0);
        camera.update();
        Gdx.input.setInputProcessor(worldManager.getInputProcessor());
    }

    /**
     * Returns the screen's SpriteBatch.
     *
     * @return the screen's SpriteBatch
     */
    public SpriteBatch getSpriteBatch() {
        return batch;
    }

    /**
     * Returns the screen's Stage.
     *
     * @return the screen's Stage
     */
    public Stage getStage() {
        return stage;
    }

    @Override
    public void show() {
        createHUDSkin();
        createHUD();
    }

    @Override
    public void resume() {

    }

    @Override
    public void render(final float delta) {
        if (worldManager.getPlayer().isDead()) {
            Gdx.gl.glClearColor(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1);
        } else {
            Gdx.gl.glClearColor(0, 0, 0, 0);
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        worldManager.update(delta, score);

        camera.position.set(camera.viewportWidth / 2.f + worldManager.getPlayer().getX() * 50.f - 100.f,
                camera.viewportHeight / 2.f, 0);
        camera.update();
        batch.setProjectionMatrix(
                camera.combined.scale(1 / WorldManager.PHYSICS_SCALE_FACTOR, 1 / WorldManager.PHYSICS_SCALE_FACTOR, 1));
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
            finalScore = Math.round(score);
            Gdx.input.setInputProcessor(stage);
        }
        score = score + delta * worldManager.getPlayer().getPhysicsStrategy().getBody().getLinearVelocity().len();
        if (gameOverMenuActive) {
            scoreLabel.setText(" " + Integer.toString(finalScore));
        } else {
            scoreLabel.setText(" " + Integer.toString(Math.round(score)));
        }

        batch.end();
        stage.act();
        stage.draw();

        // Enable if you want to see physics outlined
        //physicsRenderer.render(this.worldManager.getPhysicsWorld(), camera.combined);
    }

    /**
     * Create skin for the HUD of the game.
     */
    private void createHUDSkin() {
        hudSkin = new Skin();
        hudSkin.add("bar", ((EndlessRunner) Gdx.app.getApplicationListener()).getTextureCache().load("bar.png"));
        BitmapFont font = new BitmapFont();
        hudSkin.add("default", font);

        Label.LabelStyle background = new Label.LabelStyle();
        background.background = hudSkin.newDrawable("bar");
        background.font = hudSkin.getFont("default");
        hudSkin.add("background", background);

        Label.LabelStyle text = new Label.LabelStyle();
        text.font = hudSkin.getFont("default");
        text.fontColor = Color.BLACK;
        hudSkin.add("default", text);
    }

    /**
     * Create the HUD for the screen.
     */
    private void createHUD() {
        Table hud = new Table();
        hud.setFillParent(true);
        hud.add().expandY();

        //Create highscore table
        int highestScore = 0;
        ArrayList<ScoreItem> scoreList = ScoreContainer.getList();
        for (ScoreItem item : scoreList) {
            if (item.getRank() == 1) {
                highestScore = item.getScore();
            }
        }
        Stack highStack = new Stack();
        Label highscore = new Label(" Highscore", hudSkin);
        Label numHighscore = new Label(" " + Integer.toString(highestScore), hudSkin);
        Table hs = new Table();
        hs.defaults().width(100f);
        hs.add(highscore);
        hs.row();
        hs.add(numHighscore);
        highStack.add(new Label("", hudSkin, "background"));
        highStack.add(hs);
        hud.add(highStack).top().expandX();

        hud.add();

        //Create score table
        Stack scoreStack = new Stack();
        Label scoreL = new Label(" Score", hudSkin);
        scoreLabel = new Label(" " + Integer.toString(Math.round(score)), hudSkin);
        Table scoreCell = new Table();
        scoreCell.defaults().width(100f);
        scoreCell.add(scoreL);
        scoreCell.row();
        scoreCell.add(scoreLabel);
        scoreStack.add(new Label("", hudSkin, "background"));
        scoreStack.add(scoreCell);
        hud.add(scoreStack).top().expandX();

        stage.addActor(hud);
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
