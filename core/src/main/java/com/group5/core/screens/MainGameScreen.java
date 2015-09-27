package com.group5.core.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.group5.core.EndlessRunner;
import com.group5.core.world.FloorTile;
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
     * Skin of the buttons.
     */
    private Skin buttonSkin;

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
    
    private float score;
    
    private Label scoreLabel;
    
    private Table gameOverTable;

    /**
     * Constructs a new main game screen that plays the actual game.
     *
     * @param b the SpriteBatch to draw textures with
     */
    public MainGameScreen(final SpriteBatch b) {
        this.batch = b;
        this.score = 0;
        this.worldManager = new WorldManager();
        Player player = new Player(worldManager.getPhysicsWorld(), new Vector2(2, 10), new Vector2(2, 2));
        this.worldManager.setPlayer(player);

        this.physicsRenderer = new Box2DDebugRenderer();

        worldManager.setPlayer(player);
        worldManager.add(new FloorTile(worldManager.getPhysicsWorld(), new Vector2(0, 0)));

        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth / 2.f + player.getX(),
                camera.viewportHeight / 2.f + player.getY(), 0);
        camera.update();
        Gdx.input.setInputProcessor(worldManager.getInputProcessor());
    }

    @Override
    public void show() {
        createDefaultButtonSkin();
        createDefaultLabelSkin();
        createScoreLabel();
        gameOverScreenSetup();
    }

    @Override
    public void resume() {

    }

    @Override
    public void render(final float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        worldManager.update(delta);

        camera.position.set(camera.viewportWidth / 2.f + worldManager.getPlayer().getX() * 50.f - 100.f,
                camera.viewportHeight / 2.f, 0);
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (WorldObject obj : worldManager.getObjects()) {
            obj.doRender(batch);
        }

        if (!gameOverMenuActive && !(worldManager.getGameStatus())) {
            gameOverMenuActive = true;
            gameOverTable.setVisible(true);
            Gdx.input.setInputProcessor(stage);
        }
        score = score + delta;

        scoreLabel.setText(Integer.toString(Math.round(score)));
        batch.end();
        stage.act();
        stage.draw();

        // Enable if you want to see physics outlined
        physicsRenderer.render(this.worldManager.getPhysicsWorld(), camera.combined.scale(50.f, 50.f, 1.f));
    }

    /**
     * Set up the screen that pops up when it's game over.
     */
    private void gameOverScreenSetup() {

        //the screen consists of one table containing one label and two buttons.
        gameOverTable = new Table();

        Label topText = new Label("Game over!", labelSkin);
        topText.setPosition(0, 0);
        TextButton restartButton = new TextButton("New game", buttonSkin); // Use the initialized buttonSkin

        restartButton.setPosition(0, 0);
        restartButton.addListener(new ClickListener() {
            public void clicked(final InputEvent event, final float x, final float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainGameScreen(batch));
            }
        });

        TextButton menuButton = new TextButton("Menu", buttonSkin);
        menuButton.setPosition(0, 0);
        menuButton.addListener(new ClickListener() {
            public void clicked(final InputEvent event, final float x, final float y) {
                EndlessRunner.get().create();
            }
        });
        gameOverTable.setFillParent(true);
        gameOverTable.setColor(Color.BLUE);
        gameOverTable.setVisible(false);
        gameOverTable.add(topText).expandX().width(200.f);
        gameOverTable.row();
        Table t2 = new Table();
        t2.add(restartButton).width(100.f);
        t2.add(menuButton).width(100.f);
        gameOverTable.add(t2);
        //t.setVisible(false);
        stage.addActor(gameOverTable);
    }

    private void createScoreLabel() {
        scoreLabel = new Label(Float.toString(score), labelSkin);
        stage.addActor(scoreLabel);
        
    }
    
    /**
     * Create the skin for a default label.
     */
    private void createDefaultLabelSkin() {

        //create font
        BitmapFont font = new BitmapFont();
        labelSkin = new Skin();
        labelSkin.add("default", font);

        //create texture
        Pixmap pixmap = new Pixmap((int) Gdx.graphics.getWidth() / 5
                , (int) Gdx.graphics.getHeight() / 8, Pixmap.Format.RGB888);
        pixmap.setColor(Color.GRAY);
        pixmap.fill();
        labelSkin.add("background", new Texture(pixmap));

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.background = labelSkin.newDrawable("background", Color.GRAY);
        labelStyle.font = labelSkin.getFont("default");
        labelStyle.fontColor = Color.RED;
        labelSkin.add("default", labelStyle);
    }

    /**
     * Create the skin for the buttons in the game over menu.
     */
    private void createDefaultButtonSkin() {
        //create font
        BitmapFont font = new BitmapFont();
        buttonSkin = new Skin();
        buttonSkin.add("default", font);

        //create texture
        Pixmap pixmap = new Pixmap((int) Gdx.graphics.getWidth() / 5
                , (int) Gdx.graphics.getHeight() / 8, Pixmap.Format.RGB888);
        pixmap.setColor(Color.GRAY);
        pixmap.fill();
        buttonSkin.add("background", new Texture(pixmap));

        //create button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = buttonSkin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = buttonSkin.newDrawable("background", Color.DARK_GRAY);

        textButtonStyle.over = buttonSkin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = buttonSkin.getFont("default");
        buttonSkin.add("default", textButtonStyle);
    }

    @Override
    public void pause() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void resize(final int width, final int height) {

    }

    @Override
    public void dispose() {

    }

}
