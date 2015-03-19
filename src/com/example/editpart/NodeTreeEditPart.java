package com.example.editpart;

import java.beans.PropertyChangeEvent;
import com.example.model.NodeModel;

public class NodeTreeEditPart extends CustomTreeEditPart {
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
        refreshVisuals();
    }
    
	protected void refreshVisuals() {
		NodeModel model = (NodeModel) getModel();
		setWidgetText(((NodeModel) getModel()).getModelName());
    }

	
}
