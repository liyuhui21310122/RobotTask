package com.example.editpart;

import java.beans.PropertyChangeEvent;
import java.util.List;

import com.example.helper.ModelStringConstant;
import com.example.model.ContainerModel;


public class ContainerTreeEditPart extends CustomTreeEditPart {

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getPropertyName().equals(
				ModelStringConstant.P_CONTAINER_CHILDREN_ADD)) {
			refreshChildren();
		} else if (arg0.getPropertyName().equals(
				ModelStringConstant.P_CONTAINER_CHILDREN_DELETE)) {
			refreshChildren();
		}
	}

	@Override
	public List<Object> getModelChildren() {
		return ((ContainerModel) getModel()).getChildren();

	}

	@Override
	protected void refreshVisuals() {
		// TODO Auto-generated method stub
		ContainerModel model = (ContainerModel) getModel();
		//setWidgetImage(TreeEditorPartDescriptorFactory.getContainerImage(model));
		setWidgetText(((ContainerModel) getModel()).getModelName());
	}

}
