//package com.group5.core;
//
//import static org.junit.Assert.assertTrue;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mockito;
//
//import com.group5.core.util.Logger;
//import com.group5.core.util.TextureCache;
//
//public class EndlessRunnerTest {
//
//    private EndlessRunner er;
//    private TextureCache texCache;
//    
//    @Before
//    public void setup() {
//        texCache = Mockito.mock(TextureCache.class);
//        er = Mockito.mock(EndlessRunner.class);
//        er.create();
//        Mockito.when(er.getTextureCache()).thenReturn(texCache);
//    }
//    
//    @Test
//    public void loadConfigurationTest() {
//        er.loadConfiguration();
//    }
//    
//    @Test
//    public void getTextureCacheTest() {
//        assertTrue(er.getTextureCache().equals(texCache));
//    }
//}
