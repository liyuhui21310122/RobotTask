package com.example.figure.root;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Path;



public class RotateFigure extends PortElementFigure {
		
	public RotateFigure(){
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
		
		Rectangle bottom = new Rectangle(x,y+w-17, w, 20);
		Rectangle right = new Rectangle(x+2*w/3,y, w/3, h);
		
		g.setLineDash(new int[] { 5, 5 });
		g.setLineStyle(Graphics.LINE_CUSTOM);
		g.drawArc(new Rectangle(x,y,80,80), 90, 80);
	
		g.drawLine(x+20-10, y+6,x+20, y+6);
		g.drawLine(x+20, y+16,x+20, y+6);
		
		g.fillRectangle(right);
		g.drawRectangle(right);
		
		Color old = g.getBackgroundColor();
		g.setBackgroundColor(getForeGround());
		g.fillRectangle(bottom);
		g.setBackgroundColor(old);
		
		if(isShow()){
			drawLabelFrame(g,getNameFigure().getBounds(),true);
			//drawLabelFrame(g,getTypeFigure().getBounds(),true);
		}
		
	}	
	
}
