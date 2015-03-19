package com.example.figure.root;


import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Polygon;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;



public class WaitFigure extends PortElementFigure {
	private PointList pointList = new PointList();
	public WaitFigure(){
		super();		
	}

	public Dimension getPreferredSize(int wHint, int hHint) {
		return FigureDimensionConstant.SIZE_PORT_ELEMENT;
	}

	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
			
		int x = getMainRect().x;
		int y = getMainRect().y;
		int w = getMainRect().width;
		int h = getMainRect().height;
		
		g.setLineWidth(1);
		g.drawLine(x+w/4,y+h/4,x+3*w/4, y+h/4);
		g.drawLine(x+3*w/4, y+h/4,x+w/4, y+3*h/4);
		g.drawLine(x+w/4, y+3*h/4,x+3*w/4, y+3*h/4);
		g.drawLine(x+3*w/4, y+3*h/4,x+w/4,y+h/4);
		/*
		pointList.addPoint(new Point(x,y+h));
		pointList.addPoint(new Point(x+10,y+h-10));
		pointList.addPoint(new Point(x+46,y+h-10));
		pointList.addPoint(new Point(x+w,y+h));
		Color old = g.getBackgroundColor();
		g.setBackgroundColor(getForeGround());
		//g.drawPolygon(pointList);
		g.fillPolygon(pointList);
		g.setBackgroundColor(old);
	    */
		
		if(isShow()){
			drawLabelFrame(g,getNameFigure().getBounds(),true);
			//drawLabelFrame(g,getTypeFigure().getBounds(),true);
		}
		
	}	
	
}
