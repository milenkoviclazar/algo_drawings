import processing.core.PApplet;
import toxi.geom.*;
import toxi.processing.ToxiclibsSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;


public class Voronoi extends PApplet {
    static public void main(String args[]) {
        PApplet.main(new String[]{"Voronoi"});
    }

    public void settings() {
        size(1630, 630);
    }

    public void setup() {
        noLoop();
    }

    ToxiclibsSupport gfx;

    toxi.geom.mesh2d.Voronoi voronoi = new toxi.geom.mesh2d.Voronoi();


    void setupVoronoi() {
        gfx = new ToxiclibsSupport(this);
    }

    int pointNum = 500; //number of total points

    void drawVoronoi() {
        stroke(0); //sets your line color
        strokeWeight(0); //sets your line width
        noFill();
        colorMode(HSB, pointNum, 100, 100);
        HashMap<String, Integer> clr = new HashMap<>();
        for (Polygon2D poly : voronoi.getRegions()) {
            ArrayList<Integer> usedColors = new ArrayList<>();
            for (Polygon2D neigh : voronoi.getRegions())
            {
                if (!clr.containsKey(neigh.toString()))
                {
                    break;
                }
                for (Vec2D v1 : neigh.vertices)
                {
                    if (poly.containsPoint(v1))
                    {
                        usedColors.add(clr.get(neigh.toString()));
                        break;
                    }
                }
            }
            Integer hue = (int)random(pointNum);
            if (!usedColors.isEmpty())
            {
                Collections.sort(usedColors);
                usedColors.add(usedColors.get(0) + pointNum);
                print(usedColors);
                int idx = 0;
                for (int i = 0; i + 1 < usedColors.size(); i++)
                {
                    if (usedColors.get(i + 1) - usedColors.get(i) > usedColors.get(idx + 1) - usedColors.get(idx))
                    {
                        idx = i;
                    }
                }
                hue = (usedColors.get(idx + 1) + usedColors.get(idx)) / 2;
            }
            while (hue > pointNum)
            {
                hue -= pointNum;
            }
            println(hue);
            fill(hue, 100, 100);
            clr.put(poly.toString(), hue);
            gfx.polygon2D(poly);
        }

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyyhhmmss");
        String formattedDate = sdf.format(date);
        save(String.format("/Users/milenkoviclazar/sandbox/generative_art/voronoi%s.jpg", formattedDate));
    }

    public void draw() {
        setupVoronoi();
        for (int i = 0; i < pointNum; i++) {
            float x = random(0, width);
            float y = random(0, height);
            voronoi.addPoint(new Vec2D(x, y));
        }

        drawVoronoi();
    }

}
