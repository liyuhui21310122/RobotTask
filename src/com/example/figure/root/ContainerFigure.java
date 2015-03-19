package com.example.figure.root;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;


public class ContainerFigure extends PortElementFigure {
	
	private IFigure contentPane;
	
	public ContainerFigure(){
		// TODO Auto-generated method stub
		contentPane = new Layer();
		contentPane.setLayoutManager(new XYLayout());	
		//setBorder(new LineBorder());
		add(contentPane);
		
		setConstraint(contentPane,new Rectangle(0,0,-1,-1));	
	}

	public IFigure getContentPane(){
		return contentPane;
	}
}
