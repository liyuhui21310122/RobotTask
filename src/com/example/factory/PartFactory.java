package com.example.factory;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import com.example.editpart.ConnectionPart;
import com.example.editpart.ContainerPart;
import com.example.editpart.DiagramEditPart;
import com.example.editpart.node.CircleMotionPart;
import com.example.editpart.node.ConditionEditPart;
import com.example.editpart.node.EndPart;
import com.example.editpart.node.InputPart;
import com.example.editpart.node.LinearMotionPart;
import com.example.editpart.node.LoopEditPart;
import com.example.editpart.node.OutputPart;
import com.example.editpart.node.RotatePart;
import com.example.editpart.node.ShiftPart;
import com.example.editpart.node.StartPart;
import com.example.editpart.node.WaitPart;
import com.example.editpart.node.WhilePart;
import com.example.model.ConnectionModel;
import com.example.model.ContainerModel;
import com.example.model.DiagramModel;
import com.example.model.NonContainerModel;
import com.example.model.node.CircleMotionModel;
import com.example.model.node.ConditionModel;
import com.example.model.node.EndModel;
import com.example.model.node.InputModel;
import com.example.model.node.LinearMotionModel;
import com.example.model.node.LoopModel;
import com.example.model.node.OutputModel;
import com.example.model.node.RotateModel;
import com.example.model.node.ShiftModel;
import com.example.model.node.StartModel;
import com.example.model.node.WaitModel;
import com.example.model.node.WhileModel;


/**
 * @author liyuhui
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PartFactory implements EditPartFactory {

    public EditPart createEditPart(EditPart context, Object model) {
        EditPart part = null;
        if (model instanceof DiagramModel)
            part = new DiagramEditPart();
        else if (model instanceof ConnectionModel) 
            part = new ConnectionPart();
        else if(model instanceof LoopModel)
        	part = new LoopEditPart();
        else if (model instanceof LinearMotionModel) 
        	part = new LinearMotionPart();
        else if (model instanceof EndModel) 
        	part = new EndPart();
        else if (model instanceof StartModel) 
        	part = new StartPart();
        else if (model instanceof ConditionModel) 
        	part = new ConditionEditPart();
        else if (model instanceof CircleMotionModel) 
        	part = new CircleMotionPart();
        else if (model instanceof RotateModel) 
        	part = new RotatePart();
        else if (model instanceof ShiftModel) 
        	part = new ShiftPart();
        else if (model instanceof OutputModel) 
        	part = new OutputPart();
        else if (model instanceof InputModel) 
        	part = new InputPart();
        else if (model instanceof WaitModel) 
        	part = new WaitPart();
        else if (model instanceof WhileModel) 
        	part = new WhilePart();
        part.setModel(model);
        return part;
    }
}
