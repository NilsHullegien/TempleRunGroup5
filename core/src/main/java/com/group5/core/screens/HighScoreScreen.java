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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.group5.core.EndlessRunner;

/**
 * Creates a High Score screen.
 * @author Nils
 *
 */
public class HighScoreScreen implements Screen {

    /**
     * The background sprite of the menu.
     */
    private Sprite backgroundSprite;

    /**
     * Skin.
     */
    private Skin skin;

    /**
     * Batch.
     */
    private SpriteBatch batch;

    /**
     * Stage.
     */
    private Stage stage = new Stage();

    /**
     * Table.
     */
    private Table table = new Table();

    /**
     * Another skin.
     */
    private Skin labelSkin = new Skin();

    /**
     * Button Skin.
     */
    private Skin buttonSkin = new Skin();

    /**
     * Label that indicates the final score.
     */
    private Label gameOverScore;

    /**
     * Constructor.
     * @param b spriteBatch
     */
    public HighScoreScreen(final SpriteBatch b) {
        this.batch = b;
    }

    /**
     * Show method.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        createDefaultSkin();
        buttonSetup();
        // gameOverScreenSetup();
    }

    /**
     * Render method.
     */
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

    /**
     * Unused method.
     */
    @Override
    public void resize(final int width, final int height) {
    }

    /**
     * Unused method.
     */
    @Override
    public void pause() {
    }

    /**
     * Unused method.
     */
    @Override
    public void resume() {
    }

    /**
     * Disposes the HighScoreScreen.
     */
    @Override
    public void hide() {
        dispose();
    }

    /**
     * Dispose stage and skin.
     */
    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
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
                Gdx.graphics.getHeight() / 8, Pixmap.Format.RGB565);
        pixmap.setColor(Color.RED);
        pixmap.fill();
        skin.add("background", new Texture(pixmap));

        // create button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.YELLOW);
        textButtonStyle.down = skin.newDrawable("background", Color.RED);

        textButtonStyle.over = skin.newDrawable("background", Color.RED);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);
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
    private void buttonSetup() {
        TextButton returnButton = new TextButton("Return to Main menu", skin); // Use
                                                                               // the
                                                                               // initialized
                                                                               // skin
        returnButton.setPosition(0, Gdx.graphics.getHeight() / 10.f);
        returnButton.setColor(Color.RED);
        returnButton.setWidth(Gdx.graphics.getWidth());

        returnButton.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x,
                    final float y) {
                ((Game) Gdx.app.getApplicationListener())
                        .setScreen(new MainMenu(batch));
            }
        });
        loadBackground();
        stage.addActor(returnButton);
    }

    /**
     * Set up the screen that pops up when it's game over.
     */
    private void gameOverScreenSetup() {

        // the screen consists of one table containing one label and two
        // buttons.
        table = new Table();

        Label topText = new Label("Game over!", labelSkin);
        gameOverScore = new Label("", labelSkin);
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
                EndlessRunner.get().create();
            }
        });
        table.setFillParent(true);
        table.setColor(Color.BLUE);
        table.setVisible(false);
        table.add(topText).expandX().width(200.f);
        table.row();
        table.add(gameOverScore).width(200.f);
        table.row();
        Table t2 = new Table();
        t2.add(restartButton).width(100.f);
        t2.add(menuButton).width(100.f);
        table.add(t2);
        // t.setVisible(false);
        table.setColor(Color.RED);
        stage.addActor(table);
    }
}
