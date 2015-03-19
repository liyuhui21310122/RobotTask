package com.example.figure.root;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;



public class LinearMotionFigure extends PortElementFigure {
		
	public LinearMotionFigure(){
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
		
		g.drawLine(x+w/6,y+5*h/6,x+w/3, y+h/3);
		g.drawLine(x+3*w/6,y+2*h/3,x+w/3, y+h/3);
		g.drawLine(x+5*w/6,y+h/6,x+3*w/6,y+2*h/3);
		
		if(isShow()){
			drawLabelFrame(g,getNameFigure().getBounds(),true);
			//drawLabelFrame(g,getTypeFigure().getBounds(),true);
		}
		
	}	
	
}
