package com.example.model;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public interface IConstraint {
	public static final int STICK_COMP = 0;
	public static final int STICK_SUSP = 1;	
	
	public void setConstraint(Rectangle constraint);
	//public void setConstraintEx(int i,int sel);
	public Rectangle getConstraint();
	public Dimension getPreferredDimension();
	//public Point getAttachableLoc(Rectangle r,int dest);	
	//public boolean isPortRelatedConstraint();
	//public Rectangle getPortRelatedConstraint(Rectangle r,int portSize);
	public Point getLocation();
}
