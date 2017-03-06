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
import com.mycompany.javafxfigures.FXMLController;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.javafxfigures.engine.GraphicsEngine;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import javax.sound.midi.ControllerEventListener;

/**
 *
 * @author al
 */
public class Composite extends Shape {

	public List<Shape> shapes;

	public Composite() {
		shapes = Collections.synchronizedList(new ArrayList<>());
	}

	@Override
	public void draw(GraphicsEngine ge) {
//method newCachedThreadPool() creates threads if it needs and closes them
			ExecutorService executor = Executors.newCachedThreadPool();
//			ExecutorService executor = Executors.newFixedThreadPool(3);
		for (Shape s : shapes) {
			executor.submit(new Runnable() {
				@Override
				public void run() {
					s.setColor(FXMLController.color);
						s.show(ge);
				}
			}
			);
		}
			executor.shutdown();
	}

	public void add(Shape s) {
		shapes.add(s);
	}

	public List<Shape> getShapes() {
		return shapes;
	}

	public void copyshapes(List<Shape> shapes) {
		this.shapes = shapes;

	}

}
