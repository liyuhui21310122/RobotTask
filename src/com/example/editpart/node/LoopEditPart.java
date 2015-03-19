package com.example.editpart.node;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.IFigure;

import com.example.editpart.ContainerPart;
import com.example.helper.ModelStringConstant;
import com.example.model.node.LoopModel;


public class LoopEditPart extends ContainerPart {
	public void propertyChange(PropertyChangeEvent evt) {
    	super.propertyChange(evt);
    	
        if (evt.getPropertyName().equals(ModelStringConstant.P_COUNT))
            refreshVisuals();
    }
/*	
	 protected void refreshVisuals() {
	        super.refreshVisuals();
	        LoopModel loopModel = (LoopModel) getModel();
	        LoopFigure figure = (LoopFigure) getFigure();
	        figure.setName(loopModel.getName());
	    }

	@Override
	protected IFigure createFigure() {
		// TODO Auto-generated method stub
		return new LoopFigure();
	}*/
}
