package com.example.ui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import com.example.ui.helper.IImageKey;
import com.example.ui.wizards.NewFolderWizard;



/**
 * open new GFT module wizard action
 * 
 * @author user
 * 
 */

public class NewFolderAction extends Action implements IWorkbenchAction {
	private IWorkbenchWindow window;
	private static String ID = "robottaskviewer.newgeffolder";

	public NewFolderAction(IWorkbenchWindow window) {
		super();
		this.window = window;
		this.setId(ID);
		setText("机器人任务文件夹");
		setImageDescriptor(IImageKey.getImageDescriptor(IImageKey.KEY_NEWPRJEX));
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		openNewDialog();
	}

	private void openNewDialog() {
		// TODO Auto-generated method stub
		WizardDialog dlg = new WizardDialog(window.getShell(),
				new NewFolderWizard());
		dlg.open();

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
