package com.example.editpart;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import com.example.helper.ModelStringConstant;
import com.example.model.DiagramModel;


public class DiagramTreeEditPart extends CustomTreeEditPart {

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub

		if (arg0.getPropertyName().equals(
				ModelStringConstant.P_DIAGRAM_CHILDREN_ADD)) {
			refreshChildren();
		} else if (arg0.getPropertyName().equals(
				ModelStringConstant.P_DIAGRAM_CHILDREN_DELETE)) {
			refreshChildren();
		}
	}

	@Override
	public List<Object> getModelChildren() {
		// TODO Auto-generated method stub
		List<Object> children = ((DiagramModel) getModel()).getChildren();
		/*List<Object> children = new ArrayList<Object>();
		Object model;
		for (int i = 0; i < list.size(); i++) {
			model = list.get(i);
			if (model instanceof PortElementModel) {
				children.add(model);
			}
		}
        */
		return children;
	}

}
