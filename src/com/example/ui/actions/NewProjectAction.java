package com.example.ui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import com.example.ui.helper.IImageKey;
import com.example.ui.wizards.NewProjectWizard;



/**
 * open new project action wizard
 * 
 * @author user
 * 
 */

public class NewProjectAction extends Action implements ISelectionListener,
		IWorkbenchAction {
	private IWorkbenchWindow window;
	private static String ID = "robottaskviewer.newproject";

	public NewProjectAction(IWorkbenchWindow window) {
		super();
		this.window = window;

		this.setId(ID);
		this.setText("机器人任务工程");
		setImageDescriptor(IImageKey.getImageDescriptor(IImageKey.KEY_NEWPRJ));
		window.getSelectionService().addSelectionListener(this);
		

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		openProjectDialog();
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	private void openProjectDialog() {
		WizardDialog dlg = new WizardDialog(window.getShell(),
				new NewProjectWizard());
		try{
		dlg.open();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
