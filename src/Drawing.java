import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;
import java.awt.geom.Point2D;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;


public class Drawing extends PApplet {
    static public void main(String args[]) {
        PApplet.main(new String[]{"Drawing"});
    }

    private enum State {
        HORIZONTAL,
        VERTICAL,
        LINES
    }

    public void settings() {
        size(1200, 1200);
//        size(800, 600);
    }

    float x;
    float y;
    float direction;
    final float step = 40;
    float startX;
    float startY;
    float handPrecision = 4000;
    float startDirection;
    int totalNumberOfLines = 10;

    public void setup() {
        frameRate(1000000000);
        background(0);
        stroke(255);
        visited = new HashSet<>();
    }

    float handNoise() {
        return randomGaussian() / handPrecision;
    }

    State currentState = State.HORIZONTAL;

    public void draw() {
        if (currentState == State.HORIZONTAL) {
            moveHorizontal();
        } else if (currentState == State.VERTICAL){
            moveVertical();
        } else if (currentState == State.LINES) {
            moveLine();
        }
        checkForTransition();
        direction += handNoise();
        x += cos(direction);
        y += sin(direction);
        point(x, y);
        visited.add(new Point.Float(x, y));
    }

    private void checkForTransition() {
        if (currentState == State.HORIZONTAL) {
            if (startY > height) {
                currentState = State.VERTICAL;
            }
        } else {
            if (startX > width) {
                currentState = State.LINES;
            }
        }
    }

    boolean isHorizontalSetUp = false;
    boolean isVerticalSetUp = false;

    public void moveHorizontal() {
        if (!isHorizontalSetUp) {
            setupHorizontal();
        }
        if (x > width) {
            startY += step;
            direction = startDirection;
            x = startX;
            y = startY;
        }

    }

    public void moveVertical() {
        if (!isVerticalSetUp) {
            setupVertical();
        }
        if (y > height) {
            startX += step;
            direction = startDirection;
            x = startX;
            y = startY;
        }

    }

    public void moveLine() {
        finishing();
    }

    private void setupHorizontal() {
        print("HORIZONTAL SETUP");
        isHorizontalSetUp = true;
        direction = startDirection = 0;
        x = startX = 0;
        y = startY = step / 2;
    }

    public void setupVertical() {
        print("VERTICAL SETUP");
        isVerticalSetUp = true;
        direction = startDirection = PI / 2;
        x = startX = step / 2;
        y = startY = 0;
    }

    HashSet<Point.Float> visited;

    public void finishing() {
        noLoop();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyyhhmmss");
        String formattedDate = sdf.format(date);
        save(String.format("/Users/milenkoviclazar/sandbox/generative_art/output/hand_drawing%s.jpg", formattedDate));
        exit();
    }
}
