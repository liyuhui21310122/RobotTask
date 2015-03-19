package com.example.model;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

public abstract class NonContainerModel extends NodeModel{

	public NonContainerModel(String label, String modelID, Dimension size) {
		super(label, modelID, size);
		// TODO Auto-generated constructor stub
	}
/*
	@Override
	public abstract Object copy();
*/
	@Override
	public abstract IPropertyDescriptor[] getPropertyDescriptors();
	
    
}
