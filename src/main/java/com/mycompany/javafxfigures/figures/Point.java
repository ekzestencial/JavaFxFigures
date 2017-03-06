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
public class Point {
    protected double x;
    protected double y;

    public Point() {
        this(0,0);
    }
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    public void moveTo(Point p){
        x=p.getX();
        y=p.getY();
    }

}