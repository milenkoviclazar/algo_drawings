import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Slocka extends PApplet {
    static public void main(String args[]) {
        PApplet.main(new String[]{"Slocka"});
    }

    public void settings() {
        size(800, 600, P3D);
    }

    public void setup() {
        loop();
    }

    int sizeX = 8 * 3;
    int sizeY = 6 * 3;
    int sizeZ = 8 * 3;

    int[][][] matrix = new int[][][]{
            {
                    {2, 0, 2, 0, 2, 0, 2, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 2, 0, 2, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 2, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
            },
            {
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 2, 0, 2, 0, 2, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 2, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 2},
            }
    };


    public void draw() {
        background(0);
        lights();


        float fov = PI / 2.0f;
        float cameraZ = (float) (height / 2.0) / tan(fov / 2.0f);
        perspective(fov, (float) width / height, cameraZ / 2.50f, cameraZ * 2.0f);

        rotateX(-(float) 30 / 100);
        rotateY((float) 30 / 100);
        translate(width / 2, height / 2, 0);
        if (mousePressed) {
            rotateX(-(float) mouseY / 100);
            rotateY(-(float) mouseX / 100);
        }
        for (int z = 0; z < 9; z++) {
            translate(0, 0, sizeZ);
            pushMatrix();
            for (int y = 0; y < matrix[z % 2].length; y++) {
                translate(0, sizeY, 0);
                pushMatrix();
                for (int x = 0; x < matrix[z % 2][y].length; x++) {
                    translate(sizeX, 0, 0);
                    if (matrix[z % 2][y][x] == 2) {
                        box(sizeX, sizeY, sizeZ);
                    }
                }
                popMatrix();
            }
            popMatrix();
        }
    }

}
