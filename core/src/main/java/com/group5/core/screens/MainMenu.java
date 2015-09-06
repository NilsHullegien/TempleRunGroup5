package com.group5.core.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.group5.core.screens.RunGame;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MainMenu implements Screen {


	Skin skin;
	private Stage stage = new Stage();
	
	@Override
	public void show() {
        Gdx.input.setInputProcessor(stage);
        createDefaultSkin();
        TextButton newGameButton = new TextButton("New game", skin); // Use the initialized skin
        newGameButton.setPosition(Gdx.graphics.getWidth()/5*1, Gdx.graphics.getHeight()/8*1);
        
        TextButton highscoreButton = new TextButton("Highscore", skin); // Use the initialized skin
        highscoreButton.setPosition(Gdx.graphics.getWidth()/5*2, Gdx.graphics.getHeight()/8*1);
        
        TextButton quitButton = new TextButton("Quit game", skin); // Use the initialized skin
        quitButton.setPosition(Gdx.graphics.getWidth()/5*3 , Gdx.graphics.getHeight()/8*1);
        
        stage.addActor(newGameButton);
        stage.addActor(highscoreButton);
        stage.addActor(quitButton);
        
	}
	
	private void createDefaultSkin(){
		//create font
		BitmapFont font = new BitmapFont();
		skin = new Skin();
		skin.add("default", font);
		
		//create texture
		Pixmap pixmap = new Pixmap((int)Gdx.graphics.getWidth()/5, (int)Gdx.graphics.getHeight()/8,Pixmap.Format.RGB888);
		pixmap.setColor(Color.GRAY);
		pixmap.fill();
		skin.add("background",new Texture(pixmap));
		
		//create button style
		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
		textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
		textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);
	}

	@Override
	public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
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
