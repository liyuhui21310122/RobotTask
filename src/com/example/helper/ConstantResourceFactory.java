package com.example.helper;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;



public class ConstantResourceFactory implements ModelStringConstant,
             FigureColorConstant,FigureDimensionConstant,FigureFontConstant {
	
	
	
	/**
	 * color
	 */
	private static Color diagramBackColor = COLOR_KHAKI;
	private static Color diagramForeColor = COLOR_BLACK;
	private static Color elementBackColor = COLOR_WHITE;
	private static Color elementForeColor = COLOR_BLACK;
	
	public static void setDiagramBackColor(Color diagramBackColor) {
		ConstantResourceFactory.diagramBackColor = diagramBackColor;
	}

	public static Color getDiagramBackColor() {
		return diagramBackColor;
	}

	public static void setDiagramForeColor(Color diagramForeColor) {
		ConstantResourceFactory.diagramForeColor = diagramForeColor;
	}

	public static Color getDiagramForeColor() {
		return diagramForeColor;
	}
	
	public static void setElementBackColor(Color elementBackColor) {
		ConstantResourceFactory.elementBackColor = elementBackColor;
	}

	public static Color getElementBackColor() {
		return elementBackColor;
	}

	public static void setElementForeColor(Color elementForeColor) {
		ConstantResourceFactory.elementForeColor = elementForeColor;
	}

	public static Color getElementForeColor() {
		return elementForeColor;
	}	
	
}
