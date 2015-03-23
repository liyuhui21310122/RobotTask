package com.example.editpart.node;

import java.beans.PropertyChangeEvent;

import com.example.editpart.ContainerPart;
import com.example.helper.ModelStringConstant;

public class WhilePart extends ContainerPart {
	public void propertyChange(PropertyChangeEvent evt) {
    	super.propertyChange(evt);
    	/*
        if (evt.getPropertyName().equals(ModelStringConstant.P_CONDITION))
            refreshVisuals();
        else if (evt.getPropertyName().equals(ModelStringConstant.P_INDEX))
            refreshVisuals();
        else if (evt.getPropertyName().equals(ModelStringConstant.P_VALUE))
            refreshVisuals();
        */    
    }
}
