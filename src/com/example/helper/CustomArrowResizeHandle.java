package com.example.helper;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.ResizeHandle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;


public class CustomArrowResizeHandle extends ResizeHandle {
	protected static final int HANDLE_SIZE_L = 26;
	protected static final int HANDLE_SIZE_S = 15;
	private int direct;

	public CustomArrowResizeHandle(GraphicalEditPart owner, int direction) {
		super(owner, direction);
		// TODO Auto-generated constructor stub
		this.direct = direction;
		
		if(direct == PositionConstants.NORTH
				|| direct == PositionConstants.SOUTH){
			setPreferredSize(new Dimension(HANDLE_SIZE_S, HANDLE_SIZE_L));
		}
		else if(direct == PositionConstants.WEST
				|| direct== PositionConstants.EAST){
			setPreferredSize(new Dimension(HANDLE_SIZE_L, HANDLE_SIZE_S));
			
		}
	}

	public CustomArrowResizeHandle(GraphicalEditPart owner, Locator loc, Cursor c) {
		super(owner, loc, c);
		// TODO Auto-generated constructor stub
	}
	
	public void paintFigure(Graphics g) {
		Rectangle r = getBounds().getCopy();
		r.shrink(1, 1);
		
		if(direct == PositionConstants.NORTH
				|| direct == PositionConstants.SOUTH){
				
		}
		else if(direct == PositionConstants.WEST
				|| direct == PositionConstants.EAST){	
			PointList triRight = new PointList();
			triRight.addPoint(r.getRight());
			triRight.addPoint(r.x+r.width/2+1,r.y);
			triRight.addPoint(r.x+r.width/2+1,r.y+r.height);
			
			PointList triLeft = new PointList();
			triLeft.addPoint(r.getLeft());
			triLeft.addPoint(r.x+r.width/2-1,r.y);
			triLeft.addPoint(r.x+r.width/2-1,r.y+r.height);
			
			g.setBackgroundColor(getFillColor());
			g.fillPolygon(triRight);
			g.fillPolygon(triLeft);
			g.setForegroundColor(getBorderColor());
			g.drawPolygon(triRight);
			g.drawPolygon(triLeft);
		}
		
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
