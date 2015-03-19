package com.example.helper;

import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Handle;
import org.eclipse.swt.graphics.Cursor;

public class CustomResizableHandleKit {

	public static void addCornerHandles(GraphicalEditPart part, List<Handle> handles, 
			DragTracker tracker, Cursor cursor) {
		handles.add(createHandle(part, PositionConstants.SOUTH_EAST, tracker, cursor));
		handles.add(createHandle(part, PositionConstants.SOUTH_WEST, tracker, cursor));
		handles.add(createHandle(part, PositionConstants.NORTH_WEST, tracker, cursor));
		handles.add(createHandle(part, PositionConstants.NORTH_EAST, tracker, cursor));
	}
	
	public static void addResizeCornerHandles(GraphicalEditPart part, List<Handle> handles, 
			DragTracker tracker, Cursor cursor) {
		addCornerHandles(part,handles,tracker,cursor);
	}
	
	public static void addSideHandles(GraphicalEditPart part, List<Handle> handles, 
			DragTracker tracker, Cursor cursor) {
		handles.add(createHandle(part, PositionConstants.EAST, tracker, cursor));
		handles.add(createHandle(part, PositionConstants.WEST, tracker, cursor));
	}
	
	public static void addResizeSideHandles(GraphicalEditPart part, List<Handle> handles, 
			DragTracker tracker, Cursor cursor) {
		handles.add(createHandle(part, PositionConstants.EAST));
		handles.add(createHandle(part, PositionConstants.WEST, tracker, cursor));
	}
	
	public static Handle createHandle(GraphicalEditPart owner, int direction, DragTracker tracker,
			   Cursor cursor) {
		CustomResizeHandle handle = new CustomResizeHandle(owner, direction);
		handle.setCursor(cursor);
		handle.setDragTracker(tracker);
		return handle;
	}
	
	public static Handle createHandle(GraphicalEditPart owner, int direction) {
		CustomArrowResizeHandle handle = new CustomArrowResizeHandle(owner, direction);
		return handle;
	}	
	

}
