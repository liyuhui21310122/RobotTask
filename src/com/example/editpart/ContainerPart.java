package com.example.editpart;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;


import com.example.figure.root.ContainerFigure;

import com.example.helper.CustomConstraintFactory;
import com.example.helper.ModelStringConstant;
import com.example.model.ConnectionModel;
import com.example.model.ContainerModel;
import com.example.model.DiagramModel;
import com.example.model.NodeModel;
import com.example.model.NonContainerModel;
import com.example.policy.CustomContainerEditPolicy;
import com.example.policy.CustomContainerLayoutEditPolicy;






public abstract class ContainerPart extends NodeElementEditPart {
	
	public IFigure getContentPane(){		
		//return ((ContainerElementFigure)getFigure()).getAttributeFigure();		
		return ((ContainerFigure)getFigure()).getContentPane();
	}
	
	protected void createEditPolicies() {
		super.createEditPolicies();	
		//container中添加子模型
		installEditPolicy(EditPolicy.LAYOUT_ROLE,new CustomContainerLayoutEditPolicy());
		//做什么用的？
		installEditPolicy(EditPolicy.CONTAINER_ROLE,new CustomContainerEditPolicy());
	}
	
	
	
	//**********************************property change*************************************
	public void propertyChange(PropertyChangeEvent event){
		super.propertyChange(event);
		
        if(event.getPropertyName().equals(ModelStringConstant.P_CONTAINER_CHILDREN_ADD)){
        	//adjust all children and parent container
        	refreshContainerChildren();
        	//refreshChidlren must be called.
			refreshChildren();
			//notify parent edit part that his child has adjustment.
			notifyParentEditPart();
		}
        else if(event.getPropertyName().equals(ModelStringConstant.P_CONTAINER_CHILDREN_DELETE)){
        	//adjust all children and parent container
        	refreshContainerChildren();
        	//refreshChidlren must be called.
			refreshChildren();
			//notify parent edit part that his child has adjustment.
			notifyParentEditPart();
        }
       
        else if(event.getPropertyName().equals(ModelStringConstant.P_CONTAINER_REFRESH)){
			refreshContainerChildren();
			notifyParentEditPart();
		}    
	}
	
	
	protected void refreshContainerChildren(){
		CustomConstraintFactory.refreshContainerChildren(getContainerModel());
	}
	
	
	protected void notifyParentEditPart(){
		if(getParent() instanceof DiagramEditPart){
			((DiagramModel)getParent().getModel()).fireToRefresh(getModel());			
		}
		else if(getParent() instanceof ContainerPart){
			((ContainerModel)getParent().getModel()).fireToRefresh(getModel());
		}
	}
    
	protected List<Object> getModelChildren(){		
		return getContainerModel().getChildren();
	}
	
	protected ContainerModel getContainerModel(){
		return (ContainerModel)getModel();
	}
	//************************************property change***********************************
}
