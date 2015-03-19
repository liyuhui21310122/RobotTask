package com.example.ui.wizards;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import com.example.model.ModelType;
import com.example.ui.editors.DiagramEditor;
import com.example.ui.helper.GEFXMLView;
import com.example.ui.helper.IImageKey;
import com.example.ui.helper.NewEditor;
import com.example.ui.helper.ProjectHelper;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;



import com.example.ui.helper.GEFXMLView;
import com.example.ui.helper.IImageKey;



public class NewDiagramWizard extends Wizard implements INewWizard{
	public final static String ID = "robottaskviewer.newdiagramwizard";
	
	private boolean isfinish = false;
	/*private IFolder module = null;*/
	private ISelection selection = null;
	// parameters from wizard page
	/*private ParameterTableViewer param;*/
	
	public NewDiagramWizard() {

		super();
		// TODO Auto-generated constructor stub
		setNeedsProgressMonitor(true);
		this.setWindowTitle("新建机器人任务流程图");
		
	}
	
	
	protected NewDiagramWizard getWizard() {
		return this;
	}
    
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub
		this.selection = selection;
	}
    
	@Override
	public void addPages() {
		// TODO Auto-generated method stub
		this.addPage(new NewDiagramWizardPageA(selection));
		
	}
	
    
	@Override
	public void createPageControls(Composite pageContainer) {
		// TODO Auto-generated method stub
		/*
		getPage(GEFXMLView.NAME_NEWWIZARDPAGEA).createControl(pageContainer);
		this.getShell().setBounds(300, 300, 900, 600);
		this.getShell().setImage(IImageKey.getImage(IImageKey.KEY_WIZADSMALL));
		*/
		super.createPageControls(pageContainer);
		this.getShell().setImage(IImageKey.getImage(IImageKey.KEY_WIZADSMALL));
	}

	public boolean performFinish() {
		// TODO Auto-generated method stub

		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException {
				try {
					// create the file
					doFinish(getWizard(), monitor);
				}catch(CoreException e){
					throw new InvocationTargetException(e);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    finally {
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
	
	public void doFinish(NewDiagramWizard wizard, final IProgressMonitor monitor)
			throws CoreException, IOException {		
		
		final NewDiagramWizardPageA newpage = (NewDiagramWizardPageA) (getPage(GEFXMLView.NAME_NEWWIZARDPAGEA));	
		final String dName = newpage.getFileName();
		String locate = newpage.getLocation();
		//第一个任务，创建文件
		monitor.beginTask("新建文件"+ dName, 2);
        
		monitor.setTaskName("新建文件:"+ dName + "...");
        
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(locate));
        //选择的位置不存在
		if (resource == null || !resource.exists()
				|| !(resource instanceof IContainer)) {
			WizardHandle.throwCoreException(WizardHandle.NOT_EXIST(
					locate, WizardHandle.TYPE_LOCATION));
		}
        //不是建立在folder下
		if (!(resource instanceof IFolder)
				|| !(resource.getParent() instanceof IProject)) {
			WizardHandle.throwCoreException(WizardHandle.NOT_TYPE(
					resource.toString(), WizardHandle.TYPE_FOLDER));
		}
		IFolder folder = (IFolder)resource;
		final IFile file = folder.getFile(new Path(dName));
      
		// get initial diagram xml info
		InputStream is = openDiagramContentStream();
		if (file.exists()) {
			//do nothing
		} else {
			file.create(is, true, monitor);
		}

		is.close();
		monitor.worked(1);
		
		//Opening file for editing 
		monitor.setTaskName("打开文件编辑 ...");
		getShell().getDisplay().asyncExec(new Runnable() {
			public void run() {

				NewEditor editor = new NewEditor(file);
				DiagramEditor editorPart = (DiagramEditor) editor
						.openFileEditor();

				try {
					if (editorPart == null) {
						WizardHandle
								.throwCoreException(WizardHandle.ERROR_OPEN);
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					//ModuleResourceHelper.getInstance().setActiveModule(null);
					e.printStackTrace();
				}

			}
		});
		monitor.worked(1);
	}
    
	
	
	
	private InputStream openDiagramContentStream() {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement(ModelType.ELE_ROOT);
		Element diagram = root.addElement(ModelType.ELE_DIAGRAM);
	
		return new ByteArrayInputStream(doc.asXML().getBytes());
	}
    
	
	public boolean canFlipToNextPage() {
		// TODO Auto-generated method stub
		return false;
	}
	
}


