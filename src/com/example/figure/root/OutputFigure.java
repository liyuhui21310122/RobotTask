package com.example.figure.root;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;



public class OutputFigure extends PortElementFigure {
		
	public OutputFigure(){
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
		
		g.drawLine(x,y+h-20,x+10, y+h-20);
		g.drawLine(x+10,y+h-20,x+10, y+h-40);
		g.drawLine(x+10,y+h-40,x+20, y+h-40);
		g.drawLine(x+20,y+h-40,x+20, y+h-20);
		g.drawLine(x+20,y+h-20,x+40, y+h-20);
		g.drawLine(x+40,y+h-20,x+40, y+h-40);
		g.drawLine(x+40,y+h-40,x+56, y+h-40);
		
		if(isShow()){
			drawLabelFrame(g,getNameFigure().getBounds(),true);
			//drawLabelFrame(g,getTypeFigure().getBounds(),true);
		}
		
	}	
	
}
