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
import java.util.ArrayList;
import java.util.List;
import com.mycompany.javafxfigures.engine.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author al
 */
public abstract class Shape extends Point {

    protected Paint color=Color.BLACK; 
    protected Paint fill;
    protected double lineWidth = 1;
    protected ArrayList<Point> points = new ArrayList<>();

    protected abstract void draw(GraphicsEngine ge);

    public void setColor(Paint c) {
        color = c;
    }

    public void setFill(Paint c) {
        fill = c;
    }

    public void setLineWidth(double lw) {
        this.lineWidth = lw;
    }

    public Paint getColor() {
        return color;
    }

    public Paint getFill() {
        return fill;
    }

    public double getLineWidth() {
        return lineWidth;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void show(GraphicsEngine ge) {
        ge.setColor(color);
        ge.setFillColor(fill);
        ge.setLineWidth(lineWidth);
	ge.setFillColor(Color.BROWN);
        draw(ge);
    }

}
