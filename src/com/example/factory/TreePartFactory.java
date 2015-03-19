package com.example.factory;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.example.editpart.ContainerTreeEditPart;
import com.example.editpart.DiagramTreeEditPart;
import com.example.editpart.NodeTreeEditPart;
import com.example.editpart.NonContainerTreePart;
import com.example.model.ConnectionModel;
import com.example.model.ContainerModel;
import com.example.model.DiagramModel;
import com.example.model.NonContainerModel;
import com.example.model.node.LinearMotionModel;
import com.example.model.node.LoopModel;

public class TreePartFactory implements EditPartFactory {

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		// TODO Auto-generated method stub
		EditPart part=null;
		if(model instanceof DiagramModel){
			part=new DiagramTreeEditPart();
		}
		else if(model instanceof ContainerModel)
			part=new ContainerTreeEditPart();
		/*
		else if(model instanceof ConnectionModel)
			part=new ConnectionTreeEditPart();
			*/
		else if(model instanceof NonContainerModel){
			part=new NonContainerTreePart();
		}
		
		if(part!=null)
			part.setModel(model);
		
		return part;
	}

}
