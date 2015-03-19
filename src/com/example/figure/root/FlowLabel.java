package com.example.figure.root;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.ParagraphTextLayout;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.swt.graphics.Font;


public class FlowLabel extends FlowPage {
	public static final int FONT_VALUE = 1;
	public static final int FONT_TEXT = 2;
	public static final int FONT_KEYWORD = 3;
	public static final int FONT_TOOLTIP = 4;
	//An inline flow figure that renders a string of text across one or more lines.
	//A TextFlow cannot contain children. All InlineFlow figure's must be parented by a FlowFigure. 
	private TextFlow contents;
	private int ft;

    public FlowLabel(int ft) {
        this("",ft);
    }

    public FlowLabel(String text,int ft) {
        contents = new TextFlow();
        
        contents.setLayoutManager(new ParagraphTextLayout(contents, ParagraphTextLayout.WORD_WRAP_SOFT));
        contents.setText(text);
        add(contents);
        
        this.ft = ft;
    }

    public void setText(String text) {
        contents.setText(text);
    }

    public String getText() {
        return contents.getText();
    }

	@Override
	protected void paintFigure(Graphics graphics) {
		// TODO Auto-generated method stub
		setFont(getLableFont());
		setBackgroundColor(ConstantResourceFactory.getLabelBackColor());
		setForegroundColor(ConstantResourceFactory.getLabelForeColor());
		super.paintFigure(graphics);
	}  
	
	protected Font getLableFont(){
		Font f;
		switch(ft){
		case FONT_VALUE:
			f = ConstantResourceFactory.getValueFont();
			break;
		case FONT_TEXT:
			f = ConstantResourceFactory.getTextFont();
			break;
		case FONT_TOOLTIP:
			f = ConstantResourceFactory.getTooltipFont();
			break;
		case FONT_KEYWORD:
			f = ConstantResourceFactory.getKeyWordFont();
			break;
		default:
			f = getFont();
			break;
		}
		return f;
	}
    
    

}
