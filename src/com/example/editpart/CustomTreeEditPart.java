package com.example.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import org.eclipse.gef.editparts.AbstractTreeEditPart;

import com.example.model.AbstractModel;


public class CustomTreeEditPart extends AbstractTreeEditPart implements
		PropertyChangeListener {

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		super.activate();
		((AbstractModel) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		((AbstractModel) getModel()).removePropertyChangeListener(this);
		super.deactivate();
	}
	
	
}
