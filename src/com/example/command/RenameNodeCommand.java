/*
 * Created on 2005-1-25
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.example.command;

import org.eclipse.gef.commands.Command;

import com.example.model.NodeModel;

/**
 * @author zhanghao
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RenameNodeCommand extends Command {

    private NodeModel nodeModel;

    private String newName;

    private String oldName;

    public void setName(String name) {
        this.newName = name;
    }

    public void setNode(NodeModel nodeModel) {
        this.nodeModel = nodeModel;
    }

    public void execute() {
        oldName = this.nodeModel.getName();
        this.nodeModel.setName(newName);
    }

    public void redo() {
        nodeModel.setName(newName);
    }

    public void undo() {
        nodeModel.setName(oldName);
    }

    public String getLabel() {
        return "Rename Node";
    }
}