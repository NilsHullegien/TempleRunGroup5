package com.group5.core;

public abstract class Screen {
    
    static int screenX;
    static int screenY;
    
    public void registerScreenSize(int x, int y) {
        screenX = x;
        screenY = y;
}
    
     public static int getscreenX() {
         return screenX;
     }
     
     public static int getscreenY() {
         return screenY;
     }
}
