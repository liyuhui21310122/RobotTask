package com.example.figure.root;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;

public class PortElementFigure extends RootFigure {	
	private ValueLabel name,type;
	
	public PortElementFigure(){
		super();
		
		name = new ValueLabel("","Ãû×Ö",new LabelToolTipFigure());		
		add(name);
		
		//type = new ValueLabel("","Type",new LabelToolTipFigure());
		//add(type);		
        
	}
	
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		Rectangle r = getBounds().getCopy();
			
		setConstraint(name,new Rectangle(0,2,r.width,-1).shrink(0, 0));
		//setConstraint(type,new Rectangle(0,22,r.width,-1).shrink(8, 0));
		
		setMainRect(r.getCropped(new Insets(20,2,2,2)));
		g.setLineWidth(1);	
		
	}
	
	public void setName(String s){
		this.name.setText(s);
	}
	
	public String getName(){
		return this.name.getText();
	}
	
	public IFigure getNameFigure(){
		return this.name;
	}
	
	public void setType(String s){
		this.type.setText(s);
	}
	
	public String getType(){
		return this.type.getText();
	}
	
	public IFigure getTypeFigure(){
		return this.type;
	}


}
