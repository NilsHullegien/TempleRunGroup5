package com.group5.core.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.group5.core.EndlessRunner;
import com.group5.core.util.ScoreReader;

/**
 * The main menu screen of the game.
 */
public class MainMenu implements Screen {

    /**
     * The background sprite of the menu.
     */
    private Sprite backgroundSprite;

    /**
     * The sprite batch to draw stuff with.
     */
    private SpriteBatch batch;

    /**
     * The GUI skin.
     */
    private Skin skin;

    /**
     * The GUI stage.
     */
    private Stage stage = new Stage();

    /**
     * Constructs a new main menu.
     *
     * @param b the SpriteBatch to draw things with
     */
    public MainMenu(final SpriteBatch b) {
        this.batch = b;
    }

    /**
     * Loads the MainMenu background.
     */
    private void loadBackground() {
        backgroundSprite = new Sprite(EndlessRunner.get().getTextureCache()
                .load("background.png"));
    }

    /**
     * Sets up all the buttons for the Main Menu.
     */
    private void setUp() {
        Table table = new Table();
        table.setFillParent(true);

        Table buttons = new Table();

        TextButton btn = new TextButton("New game", skin);
        btn.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x,
                                final float y) {
                ((Game) Gdx.app.getApplicationListener())
                        .setScreen(new MainGameScreen(batch));
            }
        });
        buttons.add(btn).height(50).expandX();
        buttons.row().padTop(20);


        btn = new TextButton("Highscores", skin);
        btn.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x,
                                final float y) {
                ((Game) Gdx.app.getApplicationListener())
                        .setScreen(new HighScoreScreen(batch));
            }
        });
        buttons.add(btn).height(50).expandX();
        buttons.row().padTop(20);

        btn = new TextButton("Exit game", skin);
        btn.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x,
                                final float y) {
                Gdx.app.exit();
            }
        });
        buttons.add(btn).height(50).expandX();

        table.add(buttons).expandY().width(100);

        stage.addActor(table);
    }

    @Override
    public void show() {
        stage.setViewport(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);
        createDefaultSkin();
        setUp();
        loadBackground();
        ScoreReader.read("scores.properties");
    }

    /**
     * Creates a simple skin for the buttons.
     */
    private void createDefaultSkin() {
        // create font
        BitmapFont font = new BitmapFont();
        skin = new Skin();
        skin.add("default", font);

        // create texture
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth() / 5,
                Gdx.graphics.getHeight() / 8, Pixmap.Format.RGB888);
        pixmap.setColor(Color.GRAY);
        pixmap.fill();
        skin.add("background", new Texture(pixmap));

        // create button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);

        textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);
    }

    @Override
    public void render(final float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // draw the background
        batch.begin();
        backgroundSprite.draw(batch);
        batch.end();
        // draw the buttons
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(final int width, final int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

}
