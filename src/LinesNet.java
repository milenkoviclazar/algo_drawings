import processing.core.PApplet;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LinesNet extends PApplet {
    static public void main(String args[]) {
        PApplet.main(new String[] { "LinesNet" });
    }
    public void settings() {
        size(1630, 630);
    }
    public void setup() {

        background(255);
        noStroke();
        noLoop();
    }

    public void draw() {

        int iterations = 2000;
        while (iterations-- > 0)
        {
            stroke(random(255), random(255), random(255));
            if (random(1) < 0.5)
            {
                line(random(width), 0, random(width), height);
            }
            else
            {
                line(0, random(height), width, random(height));
            }
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyyhhmmss");
        String formattedDate = sdf.format(date);
        save(String.format("/Users/milenkoviclazar/sandbox/generative_art/lines%s.jpg", formattedDate));
    }

}
