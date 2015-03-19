 package com.example.editpart;

import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.example.model.AbstractModel;

public abstract class RootEditPart extends AbstractGraphicalEditPart implements
		PropertyChangeListener {	
	
	public void activate(){
		super.activate();
		((AbstractModel)getModel()).addPropertyChangeListener(this);		
	}
	
	public void deactivate(){
		super.deactivate();
		((AbstractModel)getModel()).removePropertyChangeListener(this);	
	}


}
