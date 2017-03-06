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
public class Arch extends PolyCurve {

	public Arch(Point center, double R, double angle) {
		points.add(center);
		points.add(new Point(R, angle));

	}
}
