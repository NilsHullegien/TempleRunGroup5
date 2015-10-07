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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.group5.core.EndlessRunner;

public class HighScoreScreen implements Screen{

    /**
     * The background sprite of the menu.
     */
    private Sprite backgroundSprite;
    
    private Skin skin;
    
    private SpriteBatch batch;
    
    private Stage stage = new Stage();
    
    private Table table = new Table();
    
    public HighScoreScreen(final SpriteBatch b) {
        this.batch = b;
    }
    
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        createDefaultSkin();
        loadBackground();
        buttonSetup();
        //createTable();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //draw the background
        batch.begin();
        //stage.setDebugAll(true);
        backgroundSprite.draw(batch);
        batch.end();
        //draw the buttons
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
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
    
    /**
     * Creates a simple skin for the buttons.
     */
    private void createDefaultSkin() {
        //create font
        BitmapFont font = new BitmapFont();
        skin = new Skin();
        skin.add("default", font);

        //create texture
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 8, Pixmap.Format.RGB565);
        pixmap.setColor(Color.RED);
        pixmap.fill();
        skin.add("background", new Texture(pixmap));

        //create button style
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
        backgroundSprite = new Sprite(EndlessRunner.get().getTextureCache().load("background.png"));
    }
    
    /**
     * Sets up all the buttons for the Main Menu.
     */
    private void buttonSetup() {
        TextButton returnButton = new TextButton("Return to Main menu", skin); // Use the initialized skin
        returnButton.setPosition(0, Gdx.graphics.getHeight() / 10.f);
        returnButton.setColor(Color.RED);
        returnButton.setWidth(Gdx.graphics.getWidth());

        returnButton.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x, final float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu(batch));
            }
        });

        loadBackground();

        stage.addActor(returnButton);
    }
    
    /**
     * Creates the table in which the high scores will be displayed.
     */
    private void createTable() {
        skin.add("default", new Texture("background.png"));
        Label nameLabel = new Label("Name: ", skin);
        TextField nameText = new TextField("", skin);
        Label addressLabel = new Label("Address: ", skin);
        TextField addressText = new TextField("", skin);
        table.add(nameLabel);
        table.add(nameText).width(100F);
        table.row();
        table.add(addressLabel);
        table.add(addressText).width(100F);
        
        //MUST BE AT THE END FOR RENDERING.
        table.debug();
    }
}
