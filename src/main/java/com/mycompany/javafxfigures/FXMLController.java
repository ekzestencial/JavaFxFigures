package com.mycompany.javafxfigures;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.mycompany.javafxfigures.engine.*;
import com.mycompany.javafxfigures.figures.*;
import static java.lang.Math.asin;
import static java.lang.Math.atan;
import static java.lang.Math.sin;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javax.print.attribute.standard.SheetCollate;

/**
 *
 * @author ekzestencial
 */
public class FXMLController implements Initializable {

	private Label label;
	@FXML
	private Canvas mycanvas;
	@FXML
	private ColorPicker cp;
	@FXML
	private Slider slider;
	@FXML
	private Label lbl;
	private static Scene scene;
	private GraphicsContext gc;
	private static Stage stage;
	private FXEngine ge;
	Composite sheet;
	//k1,k2 - coordinate of the first point where mouse was pressed
	//k3,k4 - coordinate of the second point where mouse was released.
	private double k1, k2, k3, k4;
	private Shape shape;
	private double width;
	public static Paint color;
	//value of the number Pi
	private final double pi = Math.PI;
	//values of the filters' event
	private EventHandler filterPaint;
	private EventHandler filter2Paint;
	private EventHandler filter3Draw;
	private EventHandler filte4Draw;
	private EventHandler filter5Fill;

	public static void transfer(Scene scn, Stage stg) {
		scene = scn;
		stage = stg;
	}
	//removal of previous event

	private void removeMouseEvent() {
		if (filterPaint != null) {
			scene.removeEventFilter(MouseEvent.MOUSE_PRESSED, filterPaint);
		}
		if (filter2Paint != null) {
			scene.removeEventFilter(MouseEvent.MOUSE_DRAGGED, filter2Paint);
		}
		if (filter3Draw != null) {
			scene.removeEventFilter(MouseEvent.MOUSE_PRESSED, filter3Draw);
		}
		if (filte4Draw != null) {
			scene.removeEventFilter(MouseEvent.MOUSE_RELEASED, filte4Draw);
		}
		if (filter5Fill != null) {
			scene.removeEventFilter(MouseEvent.MOUSE_PRESSED, filter5Fill);
		}
	}
//drawing of a specific figure

	private void paint(Drawing pic) {
		removeMouseEvent();
		pic.draw();
		//Activation of the event painting with the click of the mouse
//	Event filters allows us to handle an event during the event capturing phase.(canvas was captured by other layer)

		filterPaint = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				//reset path
				ge.beginPath();
			}
		};

		filter2Paint = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				//registration of the latest coordinates
				ge.lineTo(mouseEvent.getSceneX(), mouseEvent.getSceneY());
				ge.stroke();

			}
		};
		scene.addEventFilter(MouseEvent.MOUSE_PRESSED, filterPaint);
		scene.addEventFilter(MouseEvent.MOUSE_DRAGGED, filter2Paint);
	}

	private void draw(Drawing pic) {
		ge.setColor(cp.getValue());
		removeMouseEvent();
		filter3Draw = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				//reset the path.
				ge.beginPath();
				k1 = mouseEvent.getSceneX();
				k2 = mouseEvent.getSceneY();
			}
		};
		filte4Draw = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				k3 = mouseEvent.getSceneX();
				k4 = mouseEvent.getSceneY();
				// invoking specific method for each figure
				pic.draw();
				//set width
				shape.setLineWidth(width);
				//set color for each figure.
				shape.setColor(color);
				//adding a new shape on the sheet.
				sheet.add(shape);
				Graphics.getInstance().show(shape);

			}
		};
		scene.addEventFilter(MouseEvent.MOUSE_PRESSED, filter3Draw);
		scene.addEventFilter(MouseEvent.MOUSE_RELEASED, filte4Draw);

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		gc = mycanvas.getGraphicsContext2D();
		//the creation of a new engine
		ge = new FXEngine();
		ge.setGC(gc);
		Graphics.getInstance().addEngine(ge, "FXEngine");
		sheet = new Composite();
		//filling the canvas in white
		ge.setFillColor(Color.WHITE);
		ge.fillRect(0, 0, mycanvas.getWidth(), mycanvas.getHeight());

//sets the current line width.
		slider.setMin(0.5);
		slider.setMax(20);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		//The listener changes width of the line
		slider.valueProperty().addListener((observable) -> {
			width = slider.getValue();
			String str = String.format("%.1f", width);
			lbl.setText(str);
			ge.setLineWidth(width);
		});
		cp.setValue(Color.BLACK);
		//The listener changes the color
		cp.setOnAction((event) -> {
			color = cp.getValue();
			ge.setColor(color);

		});

	}

	@FXML

	private void ButtonPen(ActionEvent event) {
		paint(() -> {
			ge.setColor(cp.getValue());
			//ge.setLineWidth(0.5);
		});

	}

	@FXML
	void ButtonLine(ActionEvent event) {
		draw(() -> {

			shape = new Line(new Point(k1, k2), new Point(k3, k4));

		});

	}

	@FXML
	void Eraser(ActionEvent event) {
		paint(() -> {
			ge.setColor(Color.WHITE);

		});
		//draw1(gc);

	}

	@FXML
	void fileSave(ActionEvent event) {

	}

	@FXML
	void ButtonRectangle(ActionEvent event) {
		draw(() -> {
			shape = new Rectangle(new Point(k1, k2), new Point(k3, k4));
		});
	}

	double getRadius(double k1, double k2, double k3, double k4) {
		double radius = Math.sqrt(Math.pow((k3 - k1), 2) + Math.pow((k4 - k2), 2));
		return radius;
	}

	@FXML
	void ButtonCircle(ActionEvent event) {
		draw(() -> {
			shape = new Circle(new Point(k1, k2), getRadius(k1, k2, k3, k4));
		});

	}

	@FXML
	void ButtonArch(ActionEvent event) {
		draw(() -> {
			double angle = atan((k4 - k2) / (k3 - k1)) * (180 / (pi));
			//System.out.println(angle);
			shape = new Arch(new Point(k1, k2), getRadius(k1, k2, k3, k4), angle);

		});
	}

	@FXML
	void ButtonFill(ActionEvent event) {
		//Graphics.getInstance().show(sheet);
		sheet.ClearShapes();
		//Graphics.getInstance().show(sheet);
		gc.clearRect(0, 0,mycanvas.getWidth(),mycanvas.getHeight());
	}

	@FXML
	void ButtonFillAll(ActionEvent event) {
		filter5Fill = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				ge.beginPath();
				//registration the latest coordinates
				k1 = mouseEvent.getSceneX();
				k2 = mouseEvent.getSceneY();
				ge.lineTo(k1, k2);
				//	System.out.println(k1 + " " + k2);
				//Polygons.setPoint((int) k1, (int) k2);
				//creating of anonymous class and overriding of drawing method to fill figures
				Composite sheetfill = new Composite() {
					@Override
					public void draw(GraphicsEngine ge) {
						com.mycompany.javafxfigures.Polygons.Point p = new com.mycompany.javafxfigures.Polygons.Point((int) k1, (int) k2);
						for (Shape s : shapes) {
							Polygons polygon = null;

							List<Polygons.Point> dots = new ArrayList<Polygons.Point>();
							Iterator<Point> pi = s.getPoints().iterator();
							while (pi.hasNext()) {
								Point p1 = pi.next();
								//transmition of the rectangle dots to function for membership detetmination. 
								dots.add(new Polygons.Point((int) p1.getX(), (int) p1.getY()));
								//			System.out.println(shapes.size() + " " + s.getClass() + " " + p1.getX() + " " + p1.getY());
							}
							if (s instanceof Rectangle) {
								//extra point removing
								dots.remove(4);
								//
								//copying of the arraylist dots to the massiv
								Polygons.Point[] dots_final = dots.toArray(new Polygons.Point[dots.size()]);
								polygon = new Polygons(dots_final);
								//determination of the designated points relating to a rectangle

								if (polygon.isBelong(p)) {
									ge.setFillColor(cp.getValue());
									//the difference in the vertical
									int vertical_sub = dots_final[0].y - dots_final[1].y;
									ge.fillRect(dots_final[0].x, dots_final[0].y - vertical_sub, dots_final[2].x - dots_final[0].x, vertical_sub);
								}
							}
							if (s instanceof Circle) {
								//points for drawing of circle
								Polygons.Point[] dots_filling = dots.toArray(new Polygons.Point[dots.size()]);
								//getting radius.
								int Radius = dots.get(1).x;
								//extra point removing
								dots.remove(1);
								//points for determination of circle area
								dots.add(new Polygons.Point(dots.get(0).x + Radius, dots.get(0).y));
								dots.add(new Polygons.Point(dots.get(0).x, dots.get(0).y + Radius));
								dots.add(new Polygons.Point(dots.get(0).x + Radius, dots.get(0).y + Radius));
								Polygons.Point[] dots_final = dots.toArray(new Polygons.Point[dots.size()]);
								polygon = new Polygons(dots_final);
								if (polygon.isBelong(p)) {
									ge.setFillColor(cp.getValue());
									ge.fillCircle(dots_filling[0].x, dots_filling[0].y, dots_filling[1].x, dots_filling[1].y);
								}
							}

						}

					}

				};
				//copying a current sheet to a new filling sheet.
				sheetfill.copyshapes(sheet.getShapes());
				Graphics.getInstance().show(sheetfill);
			}

		};
		scene.addEventFilter(MouseEvent.MOUSE_PRESSED, filter5Fill);

	}

}
