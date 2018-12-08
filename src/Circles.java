import processing.core.PApplet;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Circles extends PApplet {
    static public void main(String args[]) {
        PApplet.main(new String[]{"Circles"});
    }

    public void settings() {
//        size(800, 600);
        size(3260, 1260);
//        size(6520 * 2, 2520 * 2);
    }

    public void setup() {
        noLoop();
    }

    public void draw() {
        float opacity = 0.34f;
        colorMode(RGB, 100);
        background(100);
        float size = 1.2f;
        int exponent = 1;
        System.out.println(opacity * 255);
        while (size < min(width, height) / 3) {
            if (random(1) > 0.5) {
                stroke(78, 139, 59, opacity * 255);
                fill(78, 139, 59, opacity * 255);
            } else {
                stroke(34, 128, 178, opacity * 255);
                fill(34, 128, 178, opacity * 255);
            }
            float x = size / 2 + random(width - size);
            float y = size / 2 + random(height - size);
            ellipse(x, y, size, size);
            size = exp(++exponent / 10);
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyyhhmmss");
        String formattedDate = sdf.format(date);
        save(String.format("/Users/milenkoviclazar/sandbox/generative_art/output/circles_%s.jpg", formattedDate));
    }
}
