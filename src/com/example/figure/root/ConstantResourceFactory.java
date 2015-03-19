package com.example.figure.root;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;


public class ConstantResourceFactory implements FigureFontConstant,
		FigureColorConstant{		
	

	/**
	 * color
	 */
	private static Color diagramBackColor = COLOR_KHAKI;
	private static Color diagramForeColor = COLOR_BLACK;
	private static Color elementBackColor = COLOR_WHITE;
	private static Color elementForeColor = COLOR_BLACK;
	private static Color labelBorderColor = COLOR_ORANGERED;
	private static Color LabelShadowColor = COLOR_BLACK;
	private static Color labelBackColor= COLOR_WHITE;
	private static Color labelForeColor = COLOR_BLACK;
	private static Color containerHighlightColor = COLOR_CORAL;
	private static Color handleFillColor = COLOR_ORANGERED;
	private static Color handleBorderColor = COLOR_WHITE;
	
	private static Color filterHighlightBackColor = COLOR_KHAKI;
	private static Color filterHighlightForeColor = COLOR_KHAKI;
	private static Color filterOppositeBackColor = COLOR_WHITE;
	private static Color filterOppositeForeColor = COLOR_BLACK;
	

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

	public static void setLabelBackColor(Color labelBackColor) {
		ConstantResourceFactory.labelBackColor = labelBackColor;
	}

	public static Color getLabelBackColor() {
		return labelBackColor;
	}

	public static void setLabelForeColor(Color labelForeColor) {
		ConstantResourceFactory.labelForeColor = labelForeColor;
	}

	public static Color getLabelForeColor() {
		return labelForeColor;
	}

	public static void setContainerHighlightColor(
			Color containerHighlightColor) {
		ConstantResourceFactory.containerHighlightColor = containerHighlightColor;
	}

	public static Color getContainerHighlightColor() {
		return containerHighlightColor;
	}

	public static void setHandleBorderColor(Color handleBorderColor) {
		ConstantResourceFactory.handleBorderColor = handleBorderColor;
	}

	public static Color getHandleBorderColor() {
		return handleBorderColor;
	}
	
	public static void setLabelBorderColor(Color labelBorderColor) {
		ConstantResourceFactory.labelBorderColor = labelBorderColor;
	}

	public static Color getLabelBorderColor() {
		return labelBorderColor;
	}

	public static void setLabelShadowColor(Color labelShadowColor) {
		LabelShadowColor = labelShadowColor;
	}

	public static Color getLabelShadowColor() {
		return LabelShadowColor;
	}

	public static void setHandleFillColor(Color handleFillColor) {
		ConstantResourceFactory.handleFillColor = handleFillColor;
	}

	public static Color getHandleFillColor() {
		return handleFillColor;
	}
	
	public static void setFilterHighlightBackColor(
			Color filterHighlightBackColor) {
		ConstantResourceFactory.filterHighlightBackColor = filterHighlightBackColor;
	}

	public static Color getFilterHighlightBackColor() {
		return filterHighlightBackColor;
	}

	public static void setFilterHighlightForeColor(
			Color filterHighlightForeColor) {
		ConstantResourceFactory.filterHighlightForeColor = filterHighlightForeColor;
	}

	public static Color getFilterHighlightForeColor() {
		return filterHighlightForeColor;
	}

	public static void setFilterOppositeBackColor(
			Color filterOppositeBackColor) {
		ConstantResourceFactory.filterOppositeBackColor = filterOppositeBackColor;
	}

	public static Color getFilterOppositeBackColor() {
		return filterOppositeBackColor;
	}

	public static void setFilterOppositeForeColor(
			Color filterOppositeForeColor) {
		ConstantResourceFactory.filterOppositeForeColor = filterOppositeForeColor;
	}

	public static Color getFilterOppositeForeColor() {
		return filterOppositeForeColor;
	}
	
	//Font
	private static Font keyWordFont = ARIAL_BOLD_10;
	private static Font textFont = ARIAL_NONE_10;
	private static Font valueFont = ARIAL_NONE_10;
	private static Font tooltipFont = ARIAL_NONE_10;
	
	public static void setKeyWordFont(Font keyWordFont) {
		ConstantResourceFactory.keyWordFont = keyWordFont;
	}

	public static Font getKeyWordFont() {
		return keyWordFont;
	}

	public static void setTextFont(Font textFont) {
		ConstantResourceFactory.textFont = textFont;
	}

	public static Font getTextFont() {
		return textFont;
	}

	public static void setValueFont(Font valueFont) {
		ConstantResourceFactory.valueFont = valueFont;
	}

	public static Font getValueFont() {
		return valueFont;
	}
	
	public static void setTooltipFont(Font tooltipFont) {
		ConstantResourceFactory.tooltipFont = tooltipFont;
	}

	public static Font getTooltipFont() {
		return tooltipFont;
	}
}
