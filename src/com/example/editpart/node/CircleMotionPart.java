/*
 * Created on 2005-1-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.example.editpart.node;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
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
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;

import com.example.editpart.NonContainerPart;

import com.example.helper.ModelStringConstant;
import com.example.model.node.CircleMotionModel;
import com.example.model.node.EndModel;
import com.example.model.node.LinearMotionModel;
import com.example.model.node.LoopModel;





/**
 * @author zhanghao
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CircleMotionPart extends NonContainerPart {

  
    public void propertyChange(PropertyChangeEvent evt) {
    	super.propertyChange(evt);
    	/*
        if (evt.getPropertyName().equals(ModelStringConstant.P_COORTYPE))
            refreshVisuals();
        else if (evt.getPropertyName().equals(ModelStringConstant.P_VELOCITY))
            refreshVisuals();
        else if (evt.getPropertyName().equals(ModelStringConstant.P_DISPLACEMENT))
            refreshVisuals();
        */
    }
/*
	@Override
	protected IFigure createFigure() {
		// TODO Auto-generated method stub
		return new CircleMotionFigure();
	}
	
	 protected void refreshVisuals() {
	        super.refreshVisuals();
	        CircleMotionModel Model = (CircleMotionModel) getModel();
	        CircleMotionFigure figure = (CircleMotionFigure) getFigure();
	        figure.setName(Model.getName());
	}
	*/
}