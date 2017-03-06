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
public class Rectangle extends PolyLine {


    public Rectangle(Point A, Point C) {
	            points.add(A);
        points.add(new Point(A.x,C.y));
        points.add(C);
        points.add(new Point(C.x,A.y));
        points.add(A);
    }
	




	
        //points.add(new Point(A.x,A.y));
        //points.add(new Point(A.x,C.y));
      //  points.add(new Point(C.x,A.y));
      //  points.add(A);
    }
