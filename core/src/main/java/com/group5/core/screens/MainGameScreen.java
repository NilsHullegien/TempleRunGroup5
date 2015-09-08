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
import com.group5.core.world.World;
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
     * The world that contains all game objects.
     */
    private World world;

    /**
     * The camera the world is viewed with.
     */
    private OrthographicCamera camera;

    /**
     * The main game player.
     */
    private Player player;

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
     * Constructs a new main game screen that plays the actual game.
     * @param b the SpriteBatch to draw textures with
     */
    public MainGameScreen(final SpriteBatch b) {
        Player player = new Player(100, 100);
        this.batch = b;
        this.world = new World();
        this.world.setPlayer(player);

        world.setPlayer(player);
        world.add(new FloorTile(0, 0));

        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth / 2.f + player.getX(),
                camera.viewportHeight / 2.f + player.getY(), 0);
        camera.update();
    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);

        createDefaultButtonSkin();
        createDefaultLabelSkin();
        gameOverScreenSetup();
    }

    @Override
    public void resume() {

    }

    @Override
    public void render(final float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.update(delta);

        camera.position.set(camera.viewportWidth / 2.f + world.getPlayer().getX() - 100.f,
                camera.viewportHeight / 2.f, 0);
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (WorldObject obj : world.getObjects()) {
            batch.draw(obj.getTexture(), obj.getX(), obj.getY());
        }
        
        if (!(world.getGameStatus())){
            stage.getActors().get(0).setVisible(true);
        }
        batch.end();
        stage.act();
        stage.draw();
    }

    /**
     * Set up the screen that pops up when it's game over.
     */
    private void gameOverScreenSetup() {

        //the screen consists of one table containing one label and two buttons.
        Table t = new Table();
        t.setPosition(0, 0);

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

        t.setWidth(200.f);
        t.setFillParent(true);
        t.setColor(Color.RED);
        t.setVisible(true);
        t.add(topText);
        t.row();
        Table t2 = new Table();
        t2.add(restartButton).width(100.f);
        t2.add(menuButton).width(100.f);
        t.add(t2);
        t.setVisible(false);
        stage.addActor(t);
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
        Pixmap pixmap = new Pixmap((int) Gdx.graphics.getWidth() / 5, (int)Gdx.graphics.getHeight() / 8, Pixmap.Format.RGB888);
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
        Pixmap pixmap = new Pixmap((int) Gdx.graphics.getWidth() / 5, (int)Gdx.graphics.getHeight() / 8, Pixmap.Format.RGB888);
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
