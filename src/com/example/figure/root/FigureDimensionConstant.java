package com.example.figure.root;

import org.eclipse.draw2d.geometry.Dimension;

public interface FigureDimensionConstant {
	
	/**
	 * fixed dimensions
	 */

	public static final Dimension SIZE_LITERAL_ELEMENT_GOTO = new Dimension(70,65);	
	public static final Dimension SIZE_LITERAL_ELEMENT_LABEL = new Dimension(140,50);
	public static final Dimension SIZE_FIGURE_ELEMENT_REPEAT_RETURN = new Dimension(100,65);
	public static final Dimension SIZE_FIGURE_ELEMENT_SUSPENSIONREGION = new Dimension(30,50);
	public static final Dimension SIZE_FIGURE_ELEMENT_INSTANCEEND = new Dimension(100,20);
	public static final Dimension SIZE_FIGURE_ELEMENT_STOPCOMPONENT = new Dimension(100,50);
	
	public static final Dimension SIZE_PORT_ELEMENT = new Dimension(60,80);
	
	public static final Dimension SIZE_SEPERATOR = new Dimension(-1,1);
	public static final Dimension SIZE_CONTAINER_ELEMENT_ALTERNATIVE = new Dimension(490,80);
	public static final Dimension SIZE_CONTAINER_ELEMENT_CALLREPLY = new Dimension(490,95);
	public static final Dimension SIZE_CONTAINER_ELEMENT_IFELSE = new Dimension(490,65);
	public static final Dimension SIZE_CONTAINER_ELEMENT_INTERLEAVE = new Dimension(490,80);
	public static final Dimension SIZE_CONTAINER_ELEMENT_WHILELOOP = new Dimension(490,65);
	public static final Dimension SIZE_CONTAINER_ELEMENT_FORLOOP = SIZE_CONTAINER_ELEMENT_WHILELOOP;
	public static final Dimension SIZE_CONNECTION_ELEMENT_ROUNDVECTOR = new Dimension(335,90);
	
		
	/**
	 * adjustable dimensions
	 */
	public static Dimension SIZE_LITERAL_ELEMENT = new Dimension(450,40);
	public static final int MAX_WIDTH_LITERAL_ELEMENT = 450;
	public static final int MIN_WIDTH_LITERAL_ELEMENT = 300;
	public static Dimension SIZE_LITERAL_ELEMENT_CONDITION = new Dimension(120,40);	
	public static final int MAX_WIDTH_LITERAL_ELEMENT_CONDITION = 200;
	public static final int MIN_WIDTH_LITERAL_ELEMENT_CONDITION = 100;
	public static Dimension SIZE_LITERAL_ELEMENT_COMMENT = new Dimension(300,40);
	public static final int MAX_WIDTH_LITERAL_ELEMENT_COMMENT = 300;
	public static final int MIN_WIDTH_LITERAL_ELEMENT_COMMENT = 150;
	public static Dimension SIZE_FIGURE_ELEMENT_TIMER = new Dimension(120,50);
	public static final Dimension MAX_SIZE_FIGURE_ELEMENT_TIMER = new Dimension(200,90);
	public static final Dimension MIN_SIZE_FIGURE_ELEMENT_TIMER = new Dimension(120,50);
	public static Dimension SIZE_CONNECTION_ELEMENT_MESSAGE = new Dimension(100,90);
	public static final int MAX_WIDTH_CONNECTION_ELEMENT_MESSAGE = 300;
	public static final int MIN_WIDTH_CONNECTION_ELEMENT_MESSAGE = 100;
	public static Dimension SIZE_DIAGRAMHEAD = new Dimension(500,80);
	public static final Dimension MAX_SIZE_DIAGRAMHEAD = new Dimension(600,100);
	public static final Dimension MIN_SIZE_DIAGRAMHEAD = new Dimension(300,60);
	
	
	/**
	 * distance
	 */
	public static final int X_PORT_ELEMENT = 220;
	public static final int DX_PORT_ELEMENT = 120;
	public static final int DX_FIRST_PORT_ELEMENT = 350;
	public static final int Y_PORT_ELEMENT = 120;
	public static final int W_PORT_ELEMENT = SIZE_PORT_ELEMENT.width;
	public static final int H_PORT_ELEMENT = SIZE_PORT_ELEMENT.height;

	public static final int DX_CONTAINER_COMPONENT = 170;
	public static final int DX_LITERAL_COMPONENT = 150;
	
	public static final int TOOLTIP_MAX_WIDTH = 400;
	

	
	
}
