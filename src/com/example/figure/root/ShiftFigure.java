package com.example.figure.root;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;



public class ShiftFigure extends PortElementFigure {
		
	public ShiftFigure(){
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
		
		Rectangle left = new Rectangle(x,y, w/3, h);
		Rectangle right = new Rectangle(x+2*w/3,y, w/3, h);
		
		Color old = g.getBackgroundColor();
		g.setBackgroundColor(getForeGround());
		g.fillRectangle(left);
		
		g.setBackgroundColor(old);
		
		g.setLineDash(new int[] { 5, 5 });
		g.setLineStyle(Graphics.LINE_CUSTOM);				
		g.drawLine(x+w/3, y+h/2, x+2*w/3, y+h/2);	
		
		g.drawLine(x+2*w/3-4, y+h/2-4,x+2*w/3, y+h/2);
		g.drawLine(x+2*w/3-4, y+h/2+4,x+2*w/3, y+h/2);
		
		g.fillRectangle(right);
		g.drawRectangle(right);
		
		if(isShow()){
			drawLabelFrame(g,getNameFigure().getBounds(),true);
			//drawLabelFrame(g,getTypeFigure().getBounds(),true);
		}
		
	}	
	
}
