package com.example.figure.root;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;


public class LabelToolTipFigure extends Figure {
	private FlowLabel text;

	public LabelToolTipFigure(){
		setBorder(new MarginBorder(6));
		text = new FlowLabel(FlowLabel.FONT_TOOLTIP);

		setLayoutManager(new StackLayout());
		add(text);		
	}
	
	public Dimension getPreferredSize(int wHint, int hHint) {
		Dimension d = super.getPreferredSize(wHint,hHint);
		if(d.width > FigureDimensionConstant.TOOLTIP_MAX_WIDTH){
			return super.getPreferredSize(FigureDimensionConstant.TOOLTIP_MAX_WIDTH, -1);
		}
		else{
			return d;
		}
			
	}

	public String getText() {
		return text.getText();
	}

	public void setText(String newText) {
		text.setText(newText + "\n< Tool tip >");
	}
}
