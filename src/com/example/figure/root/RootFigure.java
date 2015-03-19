package com.example.figure.root;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

public class RootFigure extends Figure implements IFilterFigure,IHighlight{
	private boolean show,isFilter;
	//protected boolean highlight;
	private boolean highlight;
	private boolean backOrHigh;
	private Rectangle mainRect;
	
	
	public static final Color COLOR_ORANGE = ColorConstants.orange;	
	public static final Color COLOR_BLACK = ColorConstants.black;
	public static final Color COLOR_WHITE = ColorConstants.white;
	public static final Color COLOR_GRAY = ColorConstants.gray;
	public static final Color COLOR_TOMATO = new Color(null,255,99,71);	
	public static final Color COLOR_CORAL = new Color(null,255,127,80);
	public static final Color COLOR_GOLD = new Color(null,255,215,0);
	public static final Color COLOR_KHAKI = new Color(null,240,230,160);
	public static final Color COLOR_IVORY = new Color(null,255,255,240);
	public static final Color COLOR_ANTIQWHITE = new Color(null,250,235,215);
	public static final Color COLOR_GOLDENROD = new Color(null,218,165,32);	
	public static final Color COLOR_ORANGERED = new Color(null,255,69,0);
	
	private static Color diagramBackColor = COLOR_KHAKI;
	private static Color diagramForeColor = COLOR_BLACK;
	private static Color elementBackColor = COLOR_WHITE;
	private static Color elementForeColor = COLOR_BLACK;
	private static Color labelBorderColor = COLOR_ORANGERED;
	private static Color labelShadowColor = COLOR_BLACK;
	private static Color labelBackColor= COLOR_WHITE;
	private static Color labelForeColor = COLOR_BLACK;
	private static Color containerHighlightColor = COLOR_CORAL;
	private static Color handleFillColor = COLOR_ORANGERED;
	private static Color handleBorderColor = COLOR_WHITE;
	
	private static Color filterHighlightBackColor = COLOR_KHAKI;
	private static Color filterHighlightForeColor = COLOR_KHAKI;
	private static Color filterOppositeBackColor = COLOR_WHITE;
	private static Color filterOppositeForeColor = COLOR_BLACK;

	
	public RootFigure(){
		super();
		isFilter = false;
		backOrHigh = false;
		show = false;
		highlight= false;
		
		setLayoutManager(new XYLayout());
	}
	
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		g.setBackgroundColor(getBackGround());
		g.setForegroundColor(getForeGround());		
	}
	
	protected void paintCheck(Graphics g) {		
	}
	
	protected void drawLabelFrame(Graphics g,Rectangle r,boolean b){
		Rectangle bounds = r.getCopy();
		Color old = g.getForegroundColor();
		
		if(b){
			//g.setForegroundColor(ConstantResourceFactory.getLabelBorderColor());
			g.setForegroundColor(labelBorderColor);
			g.drawRectangle(bounds);
			//g.setForegroundColor(ConstantResourceFactory.getLabelShadowColor());
			g.setForegroundColor(labelShadowColor);
			bounds.translate(1, 1);
			
			g.drawLine(bounds.getBottomLeft().translate(1, 0),bounds.getBottomRight());
			g.drawLine(bounds.getTopRight().translate(0, 1), bounds.getBottomRight());
		}
		
		g.setForegroundColor(old);
	}
	
	public void show(boolean show){
		this.show = show;
		repaint();
	}
	
	public boolean isShow(){
		return this.show;		
	}

	public void setMainRect(Rectangle mainRect) {
		this.mainRect = mainRect;
	}

	public Rectangle getMainRect() {
		return mainRect;
	}

	public Color getForeGround() {
		Color color;
		
		if(isFilter){
			color = backOrHigh?
					//ConstantResourceFactory.getFilterHighlightForeColor():
					filterHighlightForeColor:diagramBackColor;
					//ConstantResourceFactory.getDiagramBackColor();
						
		}
		else{
			color = elementForeColor;
				//ConstantResourceFactory.getElementForeColor();
		}
		return color;
	}

	public Color getBackGround() {
		Color color;
		if(highlight){
			//color = ConstantResourceFactory.getContainerHighlightColor();
			color = COLOR_CORAL;
		}
		else{
			if(isFilter){
				color = backOrHigh?
						//ConstantResourceFactory.getFilterHighlightBackColor():
						//ConstantResourceFactory.getDiagramBackColor();
						filterHighlightBackColor:diagramBackColor;
			}
			else{
				//color = ConstantResourceFactory.getElementBackColor();
				color = elementBackColor;
			}
		}
		return color;
	}

	@Override
	public void HighlightFigure(boolean b) {
		// TODO Auto-generated method stub
		highlight = b;
		repaint();
	}
    /*
	@Override
	public void filterFigure(int method) {
		// TODO Auto-generated method stub
		isFilter = true;
		
		switch(method){
		case FilterFigureDialog.METHOD_INVISIBLE:
			setVisible(false);
			break;
		case FilterFigureDialog.METHOD_BACKGROUND:
			backOrHigh = false;
			setVisible(true);
			break;
		case FilterFigureDialog.METHOD_HIGHLIGHT:
			backOrHigh = true;
			setVisible(true);
			break;
		default:break;
		}		
		
	}
    */
	
	@Override
	public void filterFigure(int method) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isFiltered() {
		// TODO Auto-generated method stub
		return isFilter;
	}

	@Override
	public void unfilterFigure() {
		// TODO Auto-generated method stub
		isFilter = false;
		setVisible(true);
	}

	


}
