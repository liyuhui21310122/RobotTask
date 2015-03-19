package com.example.ui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import com.example.ui.helper.IImageKey;
import com.example.ui.helper.RobotTaskView;


/**
 * action for showing views of UI
 * 
 * @author user
 * 
 */
public class ViewAction extends Action implements ISelectionListener,
		IWorkbenchAction {
	public static String VPROPERTY = "属性栏";
	public static String VFOUTLINE = "导航视图";
	public static String VEOUTLINE = "大纲视图";
	public static String VPROBLEM = "问题栏";

	IWorkbenchWindow window;
	private String viewType;

	public ViewAction(IWorkbenchWindow window, String viewType) {
		this.window = window;
		this.viewType = viewType;
		setId("robottaskviewer." + viewType + "viewer");
		setText(viewType);
		chooseImage(viewType);
	}

	private void chooseImage(String viewType) {
		if (viewType.equals(VPROPERTY))
			this.setImageDescriptor(IImageKey
					.getImageDescriptor(IImageKey.KEY_PROPERTY));
		else if (viewType.equals(VPROBLEM))
			this.setImageDescriptor(IImageKey
					.getImageDescriptor(IImageKey.KEY_PROBLEM));
		else if (viewType.equals(VEOUTLINE))
			this.setImageDescriptor(IImageKey
					.getImageDescriptor(IImageKey.KEY_OUTLINE));
		else if (viewType.equals(VFOUTLINE))
			this.setImageDescriptor(IImageKey
					.getImageDescriptor(IImageKey.KEY_TODOITEM));
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		showViewer();
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	// show viewer
	private void showViewer() {
		IWorkbenchPage page = window.getActivePage();
		if (viewType.equals(VPROPERTY)) {
			IWorkbenchPartReference ref = page
					.findViewReference(RobotTaskView.VIEW_DPROPERTY);

			if (ref == null) {
				try {
					page.showView(RobotTaskView.VIEW_DPROPERTY);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
				return;
			}
			page.activate(page.findView(RobotTaskView.VIEW_DPROPERTY));
			return;
		} else if (viewType.equals(VEOUTLINE)) {
			IWorkbenchPartReference ref = page
					.findViewReference(RobotTaskView.VIEW_EOUTLINE);
			if (ref == null) {
				try {
					page.showView(RobotTaskView.VIEW_EOUTLINE);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
				return;
			}
			page.activate(page.findView(RobotTaskView.VIEW_EOUTLINE));
			return;
		}

		else if (viewType.equals(VFOUTLINE)) {
			IWorkbenchPartReference ref = page
					.findViewReference(RobotTaskView.VIEW_PROJECT);
			if (ref == null) {
				try {
					page.showView(RobotTaskView.VIEW_PROJECT);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
				return;
			}
			page.activate(page.findView(RobotTaskView.VIEW_PROJECT));
			return;
		}

		else if (viewType.equals(VPROBLEM)) {
			IWorkbenchPartReference ref = page
					.findViewReference(RobotTaskView.VIEW_PROBLEM);
			if (ref == null) {
				try {
					page.showView(RobotTaskView.VIEW_PROBLEM);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
				return;
			}
			page.activate(page.findView(RobotTaskView.VIEW_PROBLEM));
			return;
		}
	}

}
