package com.example.ui.wizards;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import com.example.ui.helper.IImageKey;
import com.example.ui.helper.NewEditor;
import com.example.ui.helper.ProjectHelper;
/*
import ttcn3.gft.editor.ui.helper.IImageKey;
import ttcn3.gft.editor.ui.helper.NewEditor;
import ttcn3.gft.editor.ui.helper.ProjectHelper;
import ttcn3.gft.editor.ui.helper.ResourceURLHelper;
*/
public class NewFolderWizard extends Wizard implements INewWizard {
	private NewFolderWizardPage page;
	private ISelection selection = null;

	public final static String ID = "robottaskviewer.newfolderwizard";
    
	public NewFolderWizard() {
		super();
		// TODO Auto-generated constructor stub
		setNeedsProgressMonitor(true);
		this.setWindowTitle("新建机器人任务文件夹");
	}
	
	
	@Override
	public void addPages() {
		// TODO Auto-generated method stub
		page = new NewFolderWizardPage(selection);
		addPage(page);
	}

	@Override
	public void createPageControls(Composite pageContainer) {
		// TODO Auto-generated method stub
		this.getShell().setImage(IImageKey.getImage(IImageKey.KEY_WIZADSMALL));
		super.createPageControls(pageContainer);
		
	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		//final String projectName = page.getProjectName();
		final String folderName = page.getNames();
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException {
				try {
					// create the project
					doFinish(folderName, monitor);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} catch (IOException e) {
					// TODO Auto-generated catch block
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
			MessageDialog.openError(getShell(), "错误", realException
					.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub
		this.selection = selection;
	}

	private void doFinish(String folderName, IProgressMonitor monitor)
			throws CoreException, IOException {
		final String projectName = page.getProjectName();
		//final String folderName = page.getNames();
        
		monitor.beginTask("创建文件夹:" + folderName + " ...", 1);

		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(
				projectName);
		IFolder newFolder = project.getFolder(folderName);
		if (project == null || !project.exists()) {
			WizardHandle.throwCoreException(WizardHandle.ERROR_NOPRJ);
			return;
		}
        //create folder
		newFolder.create(true, true, monitor);
		
		monitor.worked(1);
        /*
		ProjectHelper.createModule(project, folderName, monitor);
		IFolder mod = project.getFolder(folderName);

		monitor.worked(1);
		monitor.setTaskName("Creating Control Diagram:" + moduleName + ".gft"
				+ " ...");
		IFolder control = mod.getFolder(ProjectHelper.FOLDER_OUTPUT);

		final IFile file = control.getFile(moduleName + ".gft");
		InputStream is = ResourceURLHelper.getControlContentInput(moduleName);
		if (file.exists()) {
		} else {
			file.create(is, true, monitor);
		}

		monitor.worked(1);
       
		monitor.setTaskName("Open control DiagramEditor ...");
        
		getShell().getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				NewEditor editor = new NewEditor(file);
				editor.openFileEditor();
			}

		});

		monitor.worked(1);
		*/
	}

}
