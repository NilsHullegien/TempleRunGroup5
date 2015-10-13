package com.group5.core.screens;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.group5.core.EndlessRunner;
import com.group5.core.util.ScoreContainer;
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

    /**
     * Score of the game.
     */
    private float score;

    /**
     * Label that indicates the score.
     */
    private Label scoreLabel;

    /**
     * Table that contains the game over screen.
     */
    private Table gameOverTable;

    /**
     * Label that indicates the final score.
     */
    private Label gameOverScore;

    /**
     * This label is only visible when the user gets a new high score in this session.
     */
    private Label highScoreLabel;

    /**
     * Textfield that gives a name that is added to the score in the high score screen.
     */
    private TextField highScoreField;

    /**
     * String used in the description of the score.
     */
    private String name = "";

    /**
     * The date formatter used for printing log timestamps.
     */
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM HH:mm:ss");


    /**
     * This variable contains the final score, to ensure it doesn't get changed.
     */
    private int finalScore;

    /**
     * Constructs a new main game screen that plays the actual game.
     *
     * @param b
     *            the SpriteBatch to draw textures with
     */
    public MainGameScreen(final SpriteBatch b) {
        this.stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        this.batch = b;
        this.score = 0;
        this.worldManager = new WorldManager();
        Player player = new Player(worldManager.getPhysicsWorld(), new Vector2(
                2, 10), new Vector2(2, 2));
        this.worldManager.setPlayer(player);

        this.physicsRenderer = new Box2DDebugRenderer();

        worldManager.setPlayer(player);
        worldManager.add(new FloorTile(worldManager.getPhysicsWorld(),
                new Vector2(0, 0)));

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

        camera.position.set(camera.viewportWidth / 2.f
                        + worldManager.getPlayer().getX() * 50.f - 100.f,
                camera.viewportHeight / 2.f, 0);
        camera.update();

        batch.setProjectionMatrix(
                camera.combined.scale(1 / WorldManager.PHYSICS_SCALE_FACTOR,
                        1 / WorldManager.PHYSICS_SCALE_FACTOR,
                        1));
        batch.begin();
        for (WorldObject obj : worldManager.getObjects()) {
            obj.doRender(batch);
        }

        if (!gameOverMenuActive && !(worldManager.getGameStatus())) {
            gameOverMenuActive = true;
            gameOverTable.setVisible(true);
            gameOverScore.setText(" Score:"
                    + Integer.toString(Math.round(score)));
            scoreLabel.setVisible(false);
            if (ScoreContainer.isHighScore(Math.round(score))) {
                highScoreLabel.setVisible(true);
            }
            finalScore = Math.round(score);
            Gdx.input.setInputProcessor(stage);
        }
        score = score + delta * worldManager.getPlayer().getSpeed().len();

        scoreLabel.setText(Integer.toString(Math.round(score)));
        batch.end();
        stage.act();
        stage.draw();

        // Enable if you want to see physics outlined
        physicsRenderer.render(this.worldManager.getPhysicsWorld(),
                camera.combined.scale(50.f, 50.f, 1.f));
    }

    /**
     * Set up the screen that pops up when it's game over.
     */
    private void gameOverScreenSetup() {

        // the screen consists of one table containing one label and two
        // buttons.
        gameOverTable = new Table();

        Label topText = new Label(" Game over!", labelSkin);
        gameOverScore = new Label("", labelSkin);
        highScoreLabel = new Label(" New Highscore!", labelSkin);
        highScoreLabel.setVisible(false);
        topText.setPosition(0, 0);
        TextButton restartButton = new TextButton("New game", buttonSkin); // Use
                                                                           // the
                                                                           // initialized
                                                                           // buttonSkin

        restartButton.setPosition(0, 0);
        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x,
                    final float y) {
                ScoreContainer.addScore(finalScore, name, dateFormat.format(new Date()));
                ((Game) Gdx.app.getApplicationListener())
                        .setScreen(new MainGameScreen(batch));
            }
        });

        TextButton menuButton = new TextButton("Menu", buttonSkin);
        menuButton.setPosition(0, 0);
        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x,
                    final float y) {
                ScoreContainer.addScore(finalScore, name, dateFormat.format(new Date()));
                EndlessRunner.get().create();
            }
        });

        //Create white texture
        Pixmap pixmap = new Pixmap(10, 10, Pixmap.Format.RGB565);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        buttonSkin.add("background", new Texture(pixmap));

        //Text field that gets the input for the name shown in the highScoreScreen.
        TextFieldStyle txtStyle = new TextFieldStyle();
        txtStyle.font = new BitmapFont();
        txtStyle.fontColor = Color.BLACK;
        txtStyle.background = buttonSkin.getDrawable("background");

        highScoreField = new TextField(" ", txtStyle);
        highScoreField.setMessageText(" Enter name here");
        highScoreField.setTextFieldListener(new TextFieldListener() {

            @Override
            public void keyTyped(final TextField textField, final char c) {
                //Using the substring to remove the first space.
                //This space is added for better visuals.
                name = textField.getText().substring(1);
            }
        });

        gameOverTable.setFillParent(true);
        gameOverTable.setColor(Color.BLUE);
        gameOverTable.setVisible(false);
        gameOverTable.add(highScoreLabel).expandX().width(200.f);
        gameOverTable.row();
        gameOverTable.add(topText).width(200.f);
        gameOverTable.row();
        gameOverTable.add(gameOverScore).width(200.f);
        gameOverTable.row();
        gameOverTable.add(highScoreField).width(200.f).height(30.f).expandX();
        gameOverTable.row();
        Table t2 = new Table();
        t2.add(restartButton).width(100.f);
        t2.add(menuButton).width(100.f);
        gameOverTable.add(t2);
        //t.setVisible(false);
        stage.addActor(gameOverTable);
    }

    /**
     * Create a label which will indicate the score.
     */
    private void createScoreLabel() {
        scoreLabel = new Label(Float.toString(score), labelSkin);
        stage.addActor(scoreLabel);

    }

    /**
     * Create the skin for a default label.
     */
    private void createDefaultLabelSkin() {

        // create font
        BitmapFont font = new BitmapFont();
        labelSkin = new Skin();
        labelSkin.add("default", font);

        // create texture
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth() / 5,
                Gdx.graphics.getHeight() / 8, Pixmap.Format.RGB888);
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
        // create font
        BitmapFont font = new BitmapFont();
        buttonSkin = new Skin();
        buttonSkin.add("default", font);

        // create texture
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth() / 5,
                Gdx.graphics.getHeight() / 8, Pixmap.Format.RGB888);
        pixmap.setColor(Color.GRAY);
        pixmap.fill();
        buttonSkin.add("background", new Texture(pixmap));

        // create button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = buttonSkin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = buttonSkin.newDrawable("background",
                Color.DARK_GRAY);

        textButtonStyle.over = buttonSkin.newDrawable("background",
                Color.LIGHT_GRAY);
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
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {

    }

}
