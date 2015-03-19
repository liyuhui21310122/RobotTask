package com.example.ui.wizards;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import com.example.ui.helper.IImageKey;
/*
import ttcn3.gft.editor.ui.helper.IImageKey;
import ttcn3.gft.editor.ui.projectnature.TTCNProjectNature;
import ttcn3.gft.editor.ui.projectnature.TTCNProjectUTIL;
*/
public class NewProjectWizard extends Wizard  implements INewWizard{

	public final static String ID = "robottaskviewer.newprojectwizard";
	private NewProjectWizardPage projectPage;
 
	
	public NewProjectWizard() {
		super();
		// TODO Auto-generated constructor stub
		setNeedsProgressMonitor(true);
		this.setWindowTitle("新建机器人任务工程");
	}

	@Override
	public void createPageControls(Composite pageContainer) {
		// TODO Auto-generated method stub
		super.createPageControls(pageContainer);
		this.getShell().setImage(IImageKey.getImage(IImageKey.KEY_WIZADSMALL));
	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		final String projectName = projectPage.getProjectName();

		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException {
				try {
					// create the project
					doFinish(projectName, monitor);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};
		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "工程创建错误", realException
					.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public void addPages() {
		// TODO Auto-generated method stub
		projectPage = new NewProjectWizardPage();
		addPage(projectPage);
	}

	private void doFinish(String projectName, IProgressMonitor monitor)
			throws CoreException {
		monitor.beginTask("创建工程:" + projectName+" ...", 1);

		IProject newProject = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(projectName);

		// create project
		newProject.create(monitor);
		newProject.open(monitor);
        /*
		// add project nature and builder
		IProjectDescription description = newProject.getDescription();
		TTCNProjectUTIL.addNature2Project(description,
				new String[] { TTCNProjectNature.ID }, null);
		newProject.setDescription(description, null);
		*/
		monitor.worked(1);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub
		//do nothing
	}



}
