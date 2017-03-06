/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.javafxfigures.engine;

/**
 *
 * @author ekzestencial
 */
import javafx.scene.paint.Paint;

/**
 *
 * @author al
 */
public interface GraphicsEngine {
    public void strokeLine(double x1, double y1, double x2, double y2);
    public void strokeCurve(double x1, double y1, double x2, double y2);
    public void setColor(Paint c);
    public void setFillColor(Paint c);
    public void setLineWidth(double w);
    public void fillRect(double x,double y,double w,double h);
    public void fillCircle(double x1, double y1, double x2, double y2);
    public void clearRect(double x,double y,double w,double h);
    public void lineTo(double k1,double k2);
    public void beginPath();
    public void stroke();

}
