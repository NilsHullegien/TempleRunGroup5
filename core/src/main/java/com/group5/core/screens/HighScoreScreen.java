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
import com.group5.core.util.ScoreContainer;
import com.group5.core.util.ScoreItem;

import java.util.ArrayList;

/**
 * Creates a High Score screen.
 *
 * @author Nils
 */
public class HighScoreScreen implements Screen {

    /**
     * The background sprite of the menu.
     */
    private Sprite backgroundSprite;

    /**
     * Skin for return to main menu button.
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
     * Skin used for table.
     */
    private Skin lightSkin = new Skin();

    /**
     * Skin used for table.
     */
    private Skin darkSkin = new Skin();

    /**
     * Skin used for the top of the table.
     */
    private Skin topSkin = new Skin();


    /**
     * Constructor.
     *
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
        createLightSkin();
        createDarkSkin();
        createTopSkin();
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

    /**
     * Create the light skin used in the table.
     */
    private void createLightSkin() {

        // create font
        BitmapFont font = new BitmapFont();
        lightSkin = new Skin();
        lightSkin.add("default", font);

        // create texture
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth() / 5,
                Gdx.graphics.getHeight() / 8, Pixmap.Format.RGB888);
        pixmap.setColor(Color.LIGHT_GRAY);
        pixmap.fill();
        lightSkin.add("background", new Texture(pixmap));

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.background = lightSkin.newDrawable("background", Color.GRAY);
        labelStyle.font = lightSkin.getFont("default");
        labelStyle.fontColor = Color.WHITE;
        lightSkin.add("default", labelStyle);
    }

    /**
     * Create dark skin used in the table.
     */
    private void createDarkSkin() {
        // create font
        BitmapFont font = new BitmapFont();
        darkSkin = new Skin();
        darkSkin.add("default", font);

        // create texture
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth() / 5,
                Gdx.graphics.getHeight() / 8, Pixmap.Format.RGB888);
        pixmap.setColor(Color.DARK_GRAY);
        pixmap.fill();
        darkSkin.add("background", new Texture(pixmap));

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.background = darkSkin.newDrawable("background", Color.GRAY);
        labelStyle.font = darkSkin.getFont("default");
        labelStyle.fontColor = Color.WHITE;
        darkSkin.add("default", labelStyle);
    }

    /**
     * Create dark skin used in the table.
     */
    private void createTopSkin() {
        // create font
        BitmapFont font = new BitmapFont();
        topSkin = new Skin();
        topSkin.add("default", font);

        // create texture
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth() / 5,
                Gdx.graphics.getHeight() / 8, Pixmap.Format.RGB888);
        pixmap.setColor(Color.RED);
        pixmap.fill();
        topSkin.add("background", new Texture(pixmap));

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.background = topSkin.newDrawable("background", Color.GRAY);
        labelStyle.font = topSkin.getFont("default");
        labelStyle.fontColor = Color.WHITE;
        topSkin.add("default", labelStyle);
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

        Label rank = new Label("   RANK", topSkin);
        Label score = new Label("   SCORE", topSkin);
        Label name = new Label("   NAME", topSkin);
        Label date = new Label("   DATE", topSkin);
        rank.setPosition(0, 0);
        score.setPosition(0, 1);
        name.setPosition(0, 2);

        table.setFillParent(true);
        table.setColor(Color.BLUE);
        table.add(rank).expandX().width(75.f);
        table.add(score).expandX().width(100.f);
        table.add(name).expandX().width(325.f);
        table.add(date).expandX().width(150.f);
        table.row();
        //This should add all the scores to the table.
        for (int i = 1; i <= (scoreList.size()); i++) {
            for (int j = 0; j <= (scoreList.size() - 1); j++) {
                if (scoreList.get(j).getRank() == i) {
                    if (i % 2 == 0) {
                        table.add(new Label("   " + Integer.toString(scoreList.get(j).getRank()), lightSkin)).expandX().width(75.f);
                        table.add(new Label("   " + Integer.toString(scoreList.get(j).getScore()), lightSkin)).expandX().width(100.f);
                        table.add(new Label("   " + scoreList.get(j).getName(), lightSkin)).expandX().width(325.f);
                        table.add(new Label("   " + scoreList.get(j).getDate(), lightSkin)).expandX().width(150.f);
                        table.row();
                    } else {
                        table.add(new Label("   " + Integer.toString(scoreList.get(j).getRank()), darkSkin)).expandX().width(75.f);
                        table.add(new Label("   " + Integer.toString(scoreList.get(j).getScore()), darkSkin)).expandX().width(100.f);
                        table.add(new Label("   " + scoreList.get(j).getName(), darkSkin)).expandX().width(325.f);
                        table.add(new Label("   " + scoreList.get(j).getDate(), darkSkin)).expandX().width(150.f);
                        table.row();
                    }
                }
            }
        }
        table.row();
        stage.addActor(table);
    }
}
