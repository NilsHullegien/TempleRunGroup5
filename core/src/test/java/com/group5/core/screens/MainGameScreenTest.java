package com.group5.core.screens;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainGameScreenTest {
    private MainGameScreen mainScreen;
    
    @Before
    public void setup() {
        mainScreen = Mockito.mock(MainGameScreen.class);
    }
    
    @Test
    public void renderTest() {
        mainScreen.render(0);
    }
}
