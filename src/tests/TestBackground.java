package tests;

import biuoop.DrawSurface;
import biuoop.GUI;

public class TestBackground {
    public static void main(String[] args) {
       // Sprite bg = new SpaceBackGround(11);
        GUI gui = new GUI("TEST", 800, 600);
        DrawSurface d = gui.getDrawSurface();
      //  bg.drawOn(d);
        gui.show(d);
    }
}
