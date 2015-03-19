package com.example.figure.root;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;

public class ValueLabel extends Label {
	private LabelToolTipFigure tooltip;
	private String tipString;
	
	public ValueLabel(String s,String tip,LabelToolTipFigure tooltip){
		super(s);		
		setToolTip(tooltip);
		this.tooltip = tooltip;
		this.tipString = tip;
	}
	
	public void setText(String s){
		super.setText(s);		
		
		if(tooltip!=null){
			tooltip.setText(s+"\n"+tipString);
		}			
	}
	
	@Override
	protected void paintFigure(Graphics graphics) {
		// TODO Auto-generated method stub
		setFont(ConstantResourceFactory.getValueFont());
		setBackgroundColor(ConstantResourceFactory.getLabelBackColor());
		setForegroundColor(ConstantResourceFactory.getLabelForeColor());
		super.paintFigure(graphics);
	}
}
