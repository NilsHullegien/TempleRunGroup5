package com.group5.core.screens;

import java.util.ArrayList;

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
import com.group5.core.util.ScoreContainer;
import com.group5.core.util.ScoreItem;

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
    private Skin buttonSkin;

    /**
     * The list containing the scores.
     */
    private ArrayList<ScoreItem> scoreList;

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
        createLabelSkin();
        createButtonSkin();
        buttonSetup();
        tableSetup();
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
        buttonSkin.dispose();
    }

    /**
     * Creates a simple skin for the buttons.
     */
    private void createButtonSkin() {
        // create font
        BitmapFont font = new BitmapFont();
        buttonSkin = new Skin();
        buttonSkin.add("default", font);

        // create texture
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth() / 5,
                Gdx.graphics.getHeight() / 8, Pixmap.Format.RGB565);
        pixmap.setColor(Color.GRAY);
        pixmap.fill();
        buttonSkin.add("background", new Texture(pixmap));

        // create button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = buttonSkin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = buttonSkin.newDrawable("background", Color.DARK_GRAY);

        textButtonStyle.over = buttonSkin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = buttonSkin.getFont("default");
        buttonSkin.add("default", textButtonStyle);
    }

    /**
     * Loads the HighScoreScreen background.
     */
    private void loadBackground() {
        backgroundSprite = new Sprite(EndlessRunner.get().getTextureCache()
                .load("background.png"));
    }

    /**
     * Sets up all the buttons for the HighScore Screen.
     */
    private void buttonSetup() {
        TextButton returnButton = new TextButton("Return to Main menu", buttonSkin);
        returnButton.setPosition(0, Gdx.graphics.getHeight() / 10.f);
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

    //////////////////////////////////
    ////////TESTING///////////////////
    //////////////////////////////////

    /**
     * Create the skin for a default label.
     */
    private void createLabelSkin() {

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
     * Creates the table in which the high scores are shown.
     */
    private void tableSetup() {
        scoreList = ScoreContainer.getList();
        // the screen consists of one table containing one label and two
        // buttons.
        table = new Table();
        table.reset();
        table.clear();
        table.left().top();

        Label rank = new Label("  RANK", labelSkin);
        Label score = new Label("  SCORE", labelSkin);
        Label name = new Label("  NAME", labelSkin);
        rank.setPosition(0, 0);
        score.setPosition(0, 1);
        name.setPosition(0, 2);

        table.setFillParent(true);
        table.setColor(Color.BLUE);
        table.add(rank).expandX().width(50.f);
        table.add(score).expandX().width(100.f);
        table.add(name).expandX().width(200.f);
        table.row();
        for (int i = 0; i <= (scoreList.size() - 1); i++) {
            table.add(new Label(Integer.toString(scoreList.get(i).getRank()), labelSkin)).expandX().width(50.f);
            table.add(new Label(Integer.toString(scoreList.get(i).getScore()), labelSkin)).expandX().width(100.f);
            table.add(new Label(scoreList.get(i).getName(), labelSkin)).expandX().width(200.f);
            table.row();
        }
        table.row();
        stage.addActor(table);
    }
}
