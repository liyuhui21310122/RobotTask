package com.example.model;

import java.util.List;

import org.eclipse.draw2d.geometry.Point;

public interface IContainer {
	//public int getIndexOfModel(Point p);
	//public boolean canAddChild(Point p, Object child);
	public boolean canAddChild(Object child);
	public boolean canDeleteChild(Object child);
}
