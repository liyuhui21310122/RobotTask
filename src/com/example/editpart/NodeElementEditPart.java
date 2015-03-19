package com.example.editpart;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.tools.DirectEditManager;

import com.example.factory.CustomFigureFactory;
import com.example.figure.root.NodeFigure;
import com.example.figure.root.PortElementFigure;
import com.example.helper.CustomRequestConstants;
import com.example.helper.ModelStringConstant;
import com.example.model.ConnectionModel;
import com.example.model.NodeModel;
import com.example.model.NonContainerModel;

import com.example.policy.CustomComponentEditPolicy;
import com.example.policy.CustomContainerComponentEditPolicy;
import com.example.policy.CustomGraphicalNodeEditPolicy;

public abstract class NodeElementEditPart extends RootEditPart implements  NodeEditPart {
	protected DirectEditManager manager;

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		//ɾ����ģ��
		if(getParent() instanceof DiagramEditPart){
			installEditPolicy(EditPolicy.COMPONENT_ROLE,new CustomComponentEditPolicy());
		}
		else if(getParent() instanceof ContainerPart){
			installEditPolicy(EditPolicy.COMPONENT_ROLE,new CustomContainerComponentEditPolicy());
		}
		//installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new CustomDirectEditPolicy());
		//�ƶ����߸ı�ߴ�
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new CustomGraphicalNodeEditPolicy());
	}
    
	protected IFigure createFigure() {
    	return CustomFigureFactory.createFigure(this.getModel());
    }
	
	protected void refreshVisuals() {
    	
		refreshText();
    	
        NodeModel nodeModel = (NodeModel) getModel();
//        Point loc = nodeModel.getLocation();
//        Dimension size = ((NodeModel) this.getModel()).getPreferredDimension();
//        Rectangle rectangle = new Rectangle(loc,size);
//        ((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
        Rectangle r = nodeModel.getConstraint();
//        r.x = nodeModel.getLocation().x;
//        r.y = nodeModel.getLocation().y;
        ((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), r);
    	
    }

    protected void refreshText(){
		NodeModel m = (NodeModel) getModel();
		//Object id = m.getModelID();
		
		
			PortElementFigure f = (PortElementFigure)getFigure();
			//f.setType(m.getModelName());	
			f.setName(m.getName());
		

	}
    
	//*******************************��Ӧrequest**************************************************
	 /*public void performRequest(Request req) {
	        if (req.getType().equals(CustomRequestConstants.REQ_DIRECT_EDIT)) {
	            if (manager == null) {
	                NodeFigure figure = (NodeFigure) getFigure();
	                manager = new NodeDirectEditManager(this, TextCellEditor.class, new NodeCellEditorLocator(figure));
	            }
	            manager.show();
	        }
	    }
	*/
	//*******************************��Ӧrequest**************************************************
	
	
	
	//*******************************��Ӧ��model�е�firePropertyChange*****************************
	public void propertyChange(PropertyChangeEvent evt) {
	    if (evt.getPropertyName().equals(ModelStringConstant.P_NODE_LOCATION))
	        refreshVisuals();
	    else if (evt.getPropertyName().equals(ModelStringConstant.P_NODE_CONSTRAINT))
	        refreshVisuals();
	    else if (evt.getPropertyName().equals(ModelStringConstant.P_NODE_SIZE))
	        refreshVisuals();
	    else if (evt.getPropertyName().equals(ModelStringConstant.P_NODE_INPUTS))
	        refreshTargetConnections();
	    else if (evt.getPropertyName().equals(ModelStringConstant.P_NODE_OUTPUTS))
	        refreshSourceConnections();
	    else if (evt.getPropertyName().equals(ModelStringConstant.P_NODE_TEXT))
	    	refreshVisuals();
	}
    /*
	protected void refreshVisuals(){
		NodeModel nodeModel = (NodeModel) getModel();
        Point loc = nodeModel.getLocation();
        Dimension size = nodeModel.getPreferredDimension();
        Rectangle rectangle = new Rectangle(loc, size);
        //((NodeFigure) this.getFigure()).setName(((NodeModel) this.getModel()).getName());
        ((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
	}*/
	//*******************************��Ӧ��model�е�firePropertyChange*****************************
	
	
	
	// ************************Abstract methods from NodeEditPart**********************************
	//�õ�Դ���ӵ����ӵ�
    public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
        return new ChopboxAnchor(getFigure());
    }

    public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        return new ChopboxAnchor(getFigure());
    }
    //�õ�Ŀ�����ӵ����ӵ�
    public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
        return new ChopboxAnchor(getFigure());
    }

    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        return new ChopboxAnchor(getFigure());
    }
    //�õ�ģ�͵������ɵ��б�
    protected List<ConnectionModel> getModelSourceConnections() {
        return ((NodeModel) this.getModel()).getOutgoingConnections();
    }
    //�õ�ģ�͵�������ɵ��б�
    protected List<ConnectionModel> getModelTargetConnections() {
        return ((NodeModel) this.getModel()).getIncomingConnections();
    }
    //************************Abstract methods from NodeEditPart**********************************
    
}
