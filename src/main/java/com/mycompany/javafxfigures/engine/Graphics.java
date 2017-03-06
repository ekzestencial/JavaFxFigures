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
/*
 * 
 * 
 */
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mycompany.javafxfigures.*;
import com.mycompany.javafxfigures.figures.Shape;

/**
 *
 * @author al
 */
public class Graphics {

	volatile static Graphics instance = null;
	HashMap<String, GraphicsEngine> configuredEngines = new HashMap<>();
	GraphicsEngine currentGE;

	private Graphics() {
	}

	public static Graphics getInstance() {
		if (instance == null) {
			instance = new Graphics();
		}
		return instance;
	}

	public void addEngine(GraphicsEngine ge, String name) {
		currentGE = ge;
		configuredEngines.put(name, ge);
	}

	public GraphicsEngine getCurrentGE() {
		return currentGE;
	}

	public void show(final Shape s) {
	s.show(currentGE);
	}

}
