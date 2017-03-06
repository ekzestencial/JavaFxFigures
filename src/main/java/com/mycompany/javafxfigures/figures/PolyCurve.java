/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.javafxfigures.figures;

/**
 *
 * @author ekzestencial
 */
import java.util.Iterator;
import java.util.List;
import com.mycompany.javafxfigures.engine.GraphicsEngine;

/**
 *
 * @author al
 */
public class PolyCurve extends Shape{

    public void addPoint(Point p) {
        points.add(p);
    }

    public void PolyLine(List<Point> pl) {
        points.addAll(pl);
    }
    protected void draw(GraphicsEngine ge) {
                Iterator<Point> pi =points.iterator();
                Point p = pi.next();
                while (pi.hasNext()) {
                    Point p2 = pi.next();
		 ge.strokeCurve(p.getX(), p.getY(),p2.getX(),p2.getY());
                    p = p2;
                }       
    }

    


    
}