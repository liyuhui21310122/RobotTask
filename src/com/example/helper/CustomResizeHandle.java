package com.example.helper;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.ResizeHandle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;


public class CustomResizeHandle extends ResizeHandle {
	protected static final int HANDLE_SIZE = 11; 

	public CustomResizeHandle(GraphicalEditPart owner, int direction) {
		super(owner, direction);
		// TODO Auto-generated constructor stub
	}

	public CustomResizeHandle(GraphicalEditPart owner, Locator loc, Cursor c) {
		super(owner, loc, c);
		// TODO Auto-generated constructor stub
	}
	
	protected void init() {
		setPreferredSize(new Dimension(HANDLE_SIZE, HANDLE_SIZE));
	}
	
	public void paintFigure(Graphics g) {
		Rectangle r = getBounds().getCopy();
		r.shrink(1, 1);
		g.setBackgroundColor(getFillColor());
		g.fillRectangle(r);			
		g.setForegroundColor(getBorderColor());
		g.drawRectangle(r);	
		
	}
	/* 
	protected Color getFillColor() {
		 return ConstantResourceFactory.getHandleFillColor();
	}
	
	protected Color getBorderColor(){
		return ConstantResourceFactory.getHandleBorderColor();
	}
    */
}
