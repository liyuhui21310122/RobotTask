package com.example.editpart.node;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.IFigure;
import com.example.editpart.NonContainerPart;
import com.example.helper.ModelStringConstant;
import com.example.model.node.ConditionModel;


public class ConditionEditPart extends NonContainerPart {
/*
	@Override
	protected IFigure createFigure() {
		// TODO Auto-generated method stub
		return new ConditionFigure();
	}
*/
	public void propertyChange(PropertyChangeEvent evt) {
    	super.propertyChange(evt);
    	
        if (evt.getPropertyName().equals(ModelStringConstant.P_VALUE))
            refreshVisuals(); 
    }
/*	
	protected void refreshVisuals() {
        super.refreshVisuals();
        ConditionModel Model = (ConditionModel) getModel();
        ConditionFigure figure = (ConditionFigure) getFigure();
        figure.setName(Model.getName());
  }*/
}
