package com.example.command;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.example.model.ConnectionModel;

public class CreateBendpointCommand extends Command {
      private ConnectionModel con;
      private Point loc;
      private int index;
      
      public void excute(){
    	  con.addBendPoint(index, loc);
      }
      
      public void setConnection(Object object){
    	  this.con = (ConnectionModel) object;
      }
      
      public void setIndex(int i){
    	  this.index = i;
      }
      
      public void setLocation(Point loc){
    	  this.loc = loc;
      }
      
      public void undo(){
    	 con.removeBendPoint(index);
      }
}
