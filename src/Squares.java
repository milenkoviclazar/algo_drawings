import processing.core.PApplet;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Squares extends PApplet {
    static public void main(String args[]) {
        PApplet.main(new String[]{"Squares"});
    }

    public void settings() {
        size(1024, 1024);
    }

    public void setup() {

        background(255);
        noStroke();
        noLoop();
    }

    void draw_squares(int x, int y, int size, int lvl, int mask) {
        if (lvl == 0) {
            return;
        }
        if (random(1) < 0.3) {
            fill(random(100), 40, 100);
        } else {
            fill(0, 0, 100);
        }
        rect(x - size, y - size, 2 * size, 2 * size);
        int newsize = size / 2;
        if (mask == 1) {
            draw_squares(x + newsize, y - newsize, newsize, lvl - 1, 0);
            draw_squares(x - newsize, y + newsize, newsize, lvl - 1, 1);
            draw_squares(x - newsize, y - newsize, newsize, lvl - 1, 1);
        } else if (mask == 2) {
            draw_squares(x + newsize, y - newsize, newsize, lvl - 1, 2);
            draw_squares(x - newsize, y + newsize, newsize, lvl - 1, 0);
            draw_squares(x - newsize, y - newsize, newsize, lvl - 1, 2);
        } else if (mask == 3) {
            draw_squares(x + newsize, y - newsize, newsize, lvl - 1, 2);
            draw_squares(x - newsize, y + newsize, newsize, lvl - 1, 1);
            draw_squares(x - newsize, y - newsize, newsize, lvl - 1, 3);
        }
    }

    public void draw() {
        int size = 3 * width / 8;
        print(size);
        stroke(0, 0, 0);
        colorMode(HSB, 100, 100, 100);
        draw_squares(width / 2, height / 2, size, 8, 3);

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyyhhmmss");
        String formattedDate = sdf.format(date);
        save(String.format("/Users/milenkoviclazar/sandbox/generative_art/squares%s.jpg", formattedDate));
        exit();
    }

}
