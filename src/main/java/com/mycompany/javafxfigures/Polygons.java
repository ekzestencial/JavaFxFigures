/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.javafxfigures;

import java.util.ArrayList;
import java.util.Date;
 
public class Polygons {
 
    public Polygons(Point points[]) {
        assert(points.length>=3);
        for(int i=0;i<points.length-1;i++) {
            segments.add(new Segment(points[i],points[i+1]));
        }
        segments.add(new Segment(points[points.length-1],points[0]));
    }
 
    public boolean isBelong(Point p) {
        int count=0;
        for(Segment s:segments) {
            if(s.isAbove(p))
                count++;
        }
        return (count % 2)==1;
    }
    /**
     * Integer point 
     *
     */
    static public final class Point {
        public Point(int _x, int _y) {
            x=_x;
            y=_y;
        }
        final public int x,y;
       
        public String toString() {
            return "["+x+", "+y+"]";
        }
    }
 
   /** Segment descriptor of the segment. x1 and x2 - the X coordinates of the beginning and end
* cut a and b are the appropriate coefficients for graphics direct
* passing through the origin and the end (i.e. y=a*x+b).*/
    static private class Segment {
        final int x1, x2;
        final int a,b;
        final boolean vertical;
        final boolean first_exact;
 
        static private int FIXED_BASE = 1000;
       
        Segment(Point beg, Point end) {
            if(beg.x<end.x) {
                first_exact=true;
                x1=beg.x;
                x2=end.x;
            }
            else {
                x1=end.x;
                x2=beg.x;
                first_exact=false;
            }
            if(beg.x==end.x) { //vertical line 
                vertical=true;
                a=b=0;
            } else {
                vertical=false;
                a=(FIXED_BASE*(end.y-beg.y))/(end.x-beg.x);
                b=beg.y-(a*beg.x)/FIXED_BASE;
            }
        }
 
        boolean isBetween(int x) {
                if(first_exact) {
                        if(x<x1)
                                return false;
                        return x<x2;
                } else {
                        if(x<=x1)
                                return false;
                        return x<=x2;
                }
        }
       
        /**
         * Is line under current point?
         */
        boolean isAbove(Point p) {
            if(vertical)
                return false;
            if(! isBetween(p.x))
                return false;
            return (p.x*a)/FIXED_BASE+b>p.y;
        }
 
        public String toString() {
            return "["+x1+" - "+x2+", a="+a+", b="+b+"]";
        }
    }
 
    private static final long serialVersionUID = 9148860719644951284L;
    private final ArrayList<Segment> segments=new ArrayList<Segment>();
   
//    static public void main(String args[]) {
//        Polygons pols=new Polygons(
//                new Point[] {
//                               new Point(0,0),
//                               new Point(10,10),
//                               new Point(11,0),
//                               
//                            });
//        Point p1=new Point(5,2);
//        Point p2=new Point(5,6);
//        Point p3=new Point(5,-6);
//        Point p4=new Point(11,2);
// 
//        int NUM_TESTS = 1000000;
//       
//        long milliseconds=(new Date()).getTime();
//        for(int i=0;i<NUM_TESTS;i++) {
//                pols.isBelong(p1);
//                pols.isBelong(p2);
//                pols.isBelong(p3);
//                pols.isBelong(p4);
//        }
//        milliseconds-=(new Date()).getTime();
//        System.out.println("Average time of calculation per 4 million executions:"+(-milliseconds));
//       
//        System.out.println(p1+":"+pols.isBelong(p1));
//        System.out.println(p2+":"+pols.isBelong(p2));
//        System.out.println(p3+":"+pols.isBelong(p3));
//        System.out.println(p4+":"+pols.isBelong(p4));
//    }
}