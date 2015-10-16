package com.group5.core.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.group5.core.EndlessRunner;
import com.group5.core.util.ScoreContainer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Game over menu component for use in Screen classes.
 */
public class GameOverWidget {
    /**
     * The date formatter used for printing log timestamps.
     */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM HH:mm:ss");

    /**
     * Whether or not the widget is visible.
     */
    private boolean visible;

    /**
     * The parent to which the widget belongs.
     */
    private MainGameScreen parent;

    /**
     * The stage to which the widget belongs.
     */
    private Stage stage;

    /**
     * The skin with which the UI is drawn.
     */
    private Skin skin;

    /**
     * The score that is displayed on the widget.
     */
    private int score;

    /**
     * The name of the player that is stored to the high scores.
     */
    private String name;

    /**
     * The wrapping table of the widget.
     */
    private Table wrapTable;

    /**
     * The score text label of the widget.
     */
    private Label scoreLabel;

    /**
     * The high score label of the widget.
     */
    private Label isHighScoreLabel;

    /**
     * Creates a new game over screen component that will be drawn on the given stage.
     *
     * @param p the parent screen.
     */
    public GameOverWidget(final MainGameScreen p) {
        visible = false;
        parent = p;
        stage = p.getStage();

        score = 0;
        name = "Player";

        createSkin();
        setUpMenu();
    }

    /**
     * Updates the score that is displayed by the widget.
     * @param s the current score
     */
    public void setScore(final int s) {
        score = s;
        scoreLabel.setText("Score: " + score);
        if (ScoreContainer.isHighScore(score)) {
            isHighScoreLabel.setVisible(true);
        }
    }

    /**
     * Adds the widget to the stage.
     */
    public void show() {
        if (!visible) {
            stage.addActor(wrapTable);
            visible = true;
        }
    }

    /**
     * Hides the widget from the stage.
     */
    public void hide() {
        if (visible) {
            wrapTable.remove();
            visible = false;
        }
    }

    /**
     * Initializes the UI skin used by the widget.
     */
    private void createSkin() {
        // create font
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("menufont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 16;
        BitmapFont font = generator.generateFont(param);
        generator.dispose();

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
        textButtonStyle.down = skin.newDrawable("background",
                Color.DARK_GRAY);

        textButtonStyle.over = skin.newDrawable("background",
                Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = skin.getFont("default");
        labelStyle.fontColor = Color.RED;
        skin.add("default", labelStyle);
    }

    /**
     * Creates the menu element represented by the widget.
     */
    private void setUpMenu() {
        wrapTable = new Table();
        wrapTable.setFillParent(true);

        // the screen consists of one table containing one label and two
        // buttons.
        Table menuTable = new Table();
        menuTable.setSkin(skin);
        menuTable.setBackground(skin.newDrawable("background", Color.GRAY));

        Label title = new Label("Game over!", skin);
        scoreLabel = new Label("", skin);
        isHighScoreLabel = new Label("New Highscore!", skin);
        isHighScoreLabel.setVisible(false);

        TextButton restartButton = new TextButton("New game", skin);
        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x,
                                final float y) {
                ScoreContainer.addScore(score, name, DATE_FORMAT.format(new Date()));
                ((Game) Gdx.app.getApplicationListener())
                        .setScreen(new MainGameScreen(parent.getSpriteBatch()));
            }
        });

        TextButton menuButton = new TextButton("Menu", skin);
        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x,
                                final float y) {
                ScoreContainer.addScore(score, name, DATE_FORMAT.format(new Date()));
                EndlessRunner.get().create();
            }
        });

        //Create white texture
        Pixmap pixmap = new Pixmap(10, 10, Pixmap.Format.RGB565);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("background", new Texture(pixmap));

        //Text field that gets the input for the name shown in the highScoreScreen.
        TextField.TextFieldStyle txtStyle = new TextField.TextFieldStyle();
        txtStyle.font = skin.getFont("default");
        txtStyle.fontColor = Color.BLACK;
        txtStyle.background = skin.getDrawable("background");
        txtStyle.background.setLeftWidth(txtStyle.background.getLeftWidth() + 10);

        TextField highScoreField = new TextField("", txtStyle);
        highScoreField.setMessageText("Name:");
        highScoreField.setTextFieldListener(new TextField.TextFieldListener() {

            @Override
            public void keyTyped(final TextField textField, final char c) {
                //Using the substring to remove the first space.
                //This space is added for better visuals.
                name = textField.getText();
            }
        });

        menuTable.add(isHighScoreLabel).left().padLeft(10).expandX();
        menuTable.row();
        menuTable.add(title).left().padLeft(10).expandX();
        menuTable.row();
        menuTable.add(scoreLabel).left().padLeft(10).expandX();
        menuTable.row();
        menuTable.add(highScoreField).fillX().pad(10).height(30.f);
        menuTable.row();

        Table t2 = new Table();
        t2.add(restartButton).width(100.f);
        t2.add(menuButton).width(100.f);
        menuTable.add(t2);

        wrapTable.add(menuTable).center().width(200).height(180);
    }

}
