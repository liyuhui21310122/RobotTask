package com.example.ui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import com.example.ui.helper.IImageKey;
import com.example.ui.wizards.NewDiagramWizard;



public class NewDiagramAction extends Action implements ISelectionListener,
        IWorkbenchAction{
	private IWorkbenchWindow window;
	//private static String ID = "ttcnviewer.newfile";
	private static String ID = "robottaskviewer.newfile";
	
	public NewDiagramAction(IWorkbenchWindow window) {
		this.window = window;
		this.setId(ID);
		//this.setText("GFT Diagram");
		this.setText("机器人任务流程图");
		setImageDescriptor(IImageKey.getImageDescriptor(IImageKey.KEY_NEW));
		window.getSelectionService().addSelectionListener(this);

	}
	
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		openNewDialog();
		// System.out.println(this.getImageDescriptor().toString());
	}
	
	protected void openNewDialog() {
		//WizardDialog dlg = new WizardDialog(window.getShell(),new TTCNNewWizard());
		WizardDialog dlg = new WizardDialog(window.getShell(),new NewDiagramWizard());
		dlg.open();
	}
}
