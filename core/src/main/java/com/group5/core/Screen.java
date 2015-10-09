package com.group5.core;

/**
 * Simple class that makes dynamic screen size available.
 * @author Levi
 *
 */
public abstract class Screen {
    /**
     * horizontal screen size.
     */
    private static int screenX;
    /**
     * vertical screen size.
     */
    private static int screenY;
    /**
     * set the Screensize.
     * @param x horizontal screen size.
     * @param y vertical screen size.
     */
    public static void registerScreenSize(final int x, final int y) {
        screenX = x;
        screenY = y;
}
    /**
     * get horizontal screen size.
     * @return horizontal screen size.
     */
     public static int getscreenX() {
         return screenX;
     }
     /**
      * get vertical screen size.
      * @return vertical screen size.
      */
     public static int getscreenY() {
         return screenY;
     }
}
