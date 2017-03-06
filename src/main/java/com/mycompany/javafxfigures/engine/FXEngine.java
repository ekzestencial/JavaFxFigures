/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.javafxfigures.engine;

/*
 * 
 * 
 */
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.paint.Paint;

/**
 *
 * @author al
 */
public class FXEngine implements GraphicsEngine {

	GraphicsContext gc;

	public void setGC(GraphicsContext gc) {
		this.gc = gc;
	}

	@Override
	public void strokeLine(double x1, double y1, double x2, double y2) {
		gc.strokeLine(x1, y1, x2, y2);
	}

	public void strokeCurve(double x1, double y1, double x2, double y2) {
		gc.strokeArc(x1, y1, x2, x2, y2, y2, ArcType.CHORD);
	}

	@Override
	public void setColor(Paint color) {
		gc.setStroke(color);
	}

	@Override
	public void setLineWidth(double w) {
		gc.setLineWidth(w);
	}

	@Override
	public void setFillColor(Paint color) {
		gc.setFill(color);
	}

	@Override
	public void fillRect(double x, double y, double w, double h) {
		gc.fillRect(x, y, w, h);
	}

	@Override
	public void fillCircle(double x1, double y1, double x2, double y2) {
		gc.fillArc(x1, y1, x2, x2, y2, y2, ArcType.CHORD);
	}

	@Override
	public void beginPath() {
		gc.beginPath();
	}

	@Override
	public void lineTo(double k1, double k2) {
		gc.lineTo(k1, k2);
	}

	@Override
	public void stroke() {
		gc.stroke();
	}

	@Override
	public void clearRect(double x, double y, double w, double h) {
		gc.clearRect(x, y, w, h);
	}

	public void strokeCurve() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
