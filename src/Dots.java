import processing.core.PApplet;
import sun.awt.geom.Curve;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Dots extends PApplet {
    static public void main(String args[]) {
        PApplet.main(new String[] { "Dots" });
    }
    public void settings() {
        size(1630, 630);
//        size(851, 315);
    }
    public void setup() {

        background(255);
        noStroke();
        noLoop();
    }

//    int dist = 40;
    int dist = 10;
    int startX = 500;
    int endX = 600;
//    int startX = 2000;
//    int endX = 2400;

    public void draw() {

        fill(0);
        stroke(0);

        //line(startX, 0, startX, height);
        //line(endX, 0, endX, height);
        int x = dist / 2;
        int xPrev;
        while (x <= width) {
            double phi = 0;
            if (startX <= x && x <= endX) {
                phi = x - startX;
                phi /= endX - startX;
                phi *= 2 * PI;
            }
            double fract = cos((float)phi) / 2 + 0.5;
            //double fract = 1;
            int y = dist / 2;
            while (y <= height) {
                double rnd = random(1);
                if (rnd < 0.1) {
                    //fill(0, 255, 0);
                } else if (rnd < 0.2) {
                    //fill(255, 0, 0);
                } else if (rnd < 0.3) {
                    //fill(0, 0, 255);
                } else {
                    fill(0);
                }
                float clr = 255 - (float) fract * 255;
                fill(clr);
                stroke(clr);
                ellipse(x, y, dist / 2 * (float)fract, dist / 2);
                y += dist;
            }
            xPrev = x;
            x += dist * (float) fract;
            if (x - xPrev <= 0) x++;
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyyhhmmss");
        String formattedDate = sdf.format(date);
        save(String.format("/Users/milenkoviclazar/sandbox/generative_art/output/dots%s.jpg", formattedDate));
    }

}
