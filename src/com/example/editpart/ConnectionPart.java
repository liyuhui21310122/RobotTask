package com.example.editpart;
/*
 * Created on 2005-1-25
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;

import com.example.helper.ModelStringConstant;
import com.example.model.ConnectionModel;
import com.example.policy.CustomBendpointEditPolicy;
import com.example.policy.CustomConnectionEditPolicy;

public class ConnectionPart extends AbstractConnectionEditPart {

    protected IFigure createFigure() {
        PolylineConnection conn = new PolylineConnection();
        conn.setTargetDecoration(new PolygonDecoration());
        conn.setConnectionRouter(new BendpointConnectionRouter());
        return conn;
    }

    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new CustomConnectionEditPolicy());
        installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());
        installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, new CustomBendpointEditPolicy());
    }

    

    public void setSelected(int value) {
        super.setSelected(value);
        if (value != EditPart.SELECTED_NONE)
            ((PolylineConnection) getFigure()).setLineWidth(2);
        else
            ((PolylineConnection) getFigure()).setLineWidth(1);
    }
    
    public void propertyChange(PropertyChangeEvent event){
    	if(event.getPropertyName().equals(ModelStringConstant.P_BEND_POINT)){
    		refreshBendpoints();
    	}
    }

	protected void refreshBendpoints() {
		// TODO Auto-generated method stub
		List bendpoints = ((ConnectionModel) getModel()).getBendpoints();
		List<AbsoluteBendpoint> constraint = new ArrayList<AbsoluteBendpoint>();
		
		for(int i=0;i<bendpoints.size();i++){
			constraint.add(new AbsoluteBendpoint((Point) bendpoints.get(i)));
		}
		
		getConnectionFigure().setRoutingConstraint(constraint);
	}
	
	protected void refreshVisuals(){
		refreshBendpoints();
	}
}
