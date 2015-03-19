package com.example.ui.wizards;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import com.example.application.Activator;
import com.example.ui.helper.GEFXMLView;
import com.example.ui.helper.NewEditor;
import com.example.ui.helper.ProjectHelper;


/*
import ttcn3.gft.editor.Activator;
import ttcn3.gft.editor.ui.helper.NewEditor;
import ttcn3.gft.editor.ui.helper.ProjectHelper;
import ttcn3.gft.editor.ui.helper.TTCNView;
*/
public class NewDiagramWizardPageA extends WizardPage {
	
	private ISelection selection;
	private Composite composite;//?
	private String names = null;
	private String loc = null;
	

	private int button = 0;

	//private Button importBT;

	private Text filetext;
	private Text loctext;
	
    
	class ButtonSelectionListener implements SelectionListener {

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void widgetSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
			Button b = (Button) e.widget;
			if (b.getSelection()) {
				button = (Integer) b.getData();
				//设置next可用，这部分应该要修改。
				canFlipToNextPage();
				getContainer().updateButtons();
			}
		}

	}
    
	public NewDiagramWizardPageA(ISelection selection) {
		this(GEFXMLView.NAME_NEWWIZARDPAGEA);
		this.selection = selection;
	}

	

	protected NewDiagramWizardPageA(String pageName) {
		super(pageName);
		// TODO Auto-generated constructor stub

		this.setTitle("新建机器人流程图");
		/*this.setDescription("This wizard creates a new file with *.robotTask extension " +
				"that can be opened by a Diagram Eidtor");*/
		this.setDescription("新建机器人流程图，后缀名默认为.robotTask");
		this.setPageComplete(true);
	}
    
	@Override
	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
        //设置了边距和两行长条，用来设置名字和位置
		composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout(2, false);
		layout.marginTop = 40;
		layout.marginLeft = 60;
		layout.verticalSpacing = 15;
		composite.setLayout(layout);

		//名字设置
		Label nameLabel = new Label(composite, SWT.NONE);
		GridData gridData = new GridData();
		nameLabel.setText("名字:");

		filetext = new Text(composite, SWT.BORDER | SWT.LEFT);
		gridData = new GridData();
		gridData.horizontalSpan = 2;
		gridData.widthHint = 700;
		filetext.setLayoutData(gridData);
		//命名要求
		filetext.addVerifyListener(new TextPatternVerifyListener(
				TextPatternVerifyListener.VarPattern,
				TextPatternVerifyListener.TYPE_VAR));
        //修改会得知
		filetext.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				Text text = (Text) e.widget;
				names = text.getText();
				updateDialog();
			}

		});

		
		//位置设置
		Label locLabel = new Label(composite, SWT.NONE);
		gridData = new GridData();
		locLabel.setText("位置:");

		loctext = new Text(composite, SWT.BORDER | SWT.LEFT);
		gridData = new GridData();
		gridData.widthHint = 620;
		loctext.setLayoutData(gridData);
		loctext.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				Text text = (Text) e.widget;
				loc = text.getText();
				updateDialog();
			}

		});

		//browse按钮
		Button browse = new Button(composite, SWT.BORDER);
		browse.setText("展开...");
		browse.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String userloc = handleLocBrowse();

				if (userloc == null)
					return;
				else
					loctext.setText(userloc);
			}
		});
      
		initialize();
		this.setControl(composite);
		
	}
    /*
	@Override
	public IWizardPage getNextPage() {
		// TODO Auto-generated method stub

		IWizardPage page;
		IResource resource = ResourcesPlugin.getWorkspace().getRoot()
				.findMember(loc);

		if (resource instanceof IFolder)
			((TTCNNewWizard) getWizard()).setModule((IFolder) resource);

		switch (button) {
		case TESTCASE:
			page = getWizard().getPage(TTCNView.NAME_TESTCASEPAGEA);
			if (page.getControl() != null)
				page.dispose();
			return page;
		case FUNCTION:
			((TTCNNewWizard) getWizard()).setFinish(true);
			page = getWizard().getPage(TTCNView.NAME_FUNTIONPAGE);
			if (page.getControl() != null)
				page.dispose();
			return page;
		case ALTSTEP:
			((TTCNNewWizard) getWizard()).setFinish(true);
			page = getWizard().getPage(TTCNView.NAME_ALTSTEPPAGEA);
			if (page.getControl() != null)
				page.dispose();
			return page;
		default:
			return null;
		}
	}
    */
	@Override
	public boolean canFlipToNextPage() {
		// TODO Auto-generated method stub
		if (!isPageComplete())
			return false;

		return true;
	}

	public String getDiagramName() {
		return names;
	}

	public String getFileName() {
		String filename = names + ".robotTask";

		return filename;
	}

	public int getButton() {
		return button;
	}

	public int getFileType() {
		return NewEditor.OPENFILE_GEF;
	}

	public String getLocation() {
		return loc;
	}
    /*
	public String getdefFile() {
		if (defSPE)
			return def;
		return null;
	}

	public boolean isDefSPE() {
		return defSPE;
	}
    */
	protected String handleLocBrowse() {
		ElementTreeSelectionDialog dialog = new ElementTreeSelectionDialog(
				getShell(), new WorkbenchLabelProvider(),
				new WorkbenchContentProvider());

		dialog.setInput(ResourcesPlugin.getWorkspace().getRoot());

		dialog.setTitle("选择一个文件夹");
		dialog.setMessage("选择流程图所属文件夹");

		dialog.addFilter(new ViewerFilter() {

			@Override
			public boolean select(Viewer viewer, Object parentElement,
					Object element) {
				// TODO Auto-generated method stub

				if (element instanceof IProject) {
					if (((IProject) element).getName().endsWith(".project"))
						return false;
					else if (((IProject) element).isAccessible()
							&& ((IProject) element).isOpen())
						return true;
					else return false;
				}

				if (parentElement instanceof IProject
						&& element instanceof IFolder) {
					return true;
				}

				return false;
			}
		});

		dialog.setValidator(new ISelectionStatusValidator() {

			@Override
			public IStatus validate(Object[] selection) {
				// TODO Auto-generated method stub
				IStatus status = null;
                //如果是选择文件夹
				if (selection.length == 0) {
					status = new Status(IStatus.ERROR, Activator.PLUGIN_ID,
							IStatus.OK, WizardHandle.VALIDATE_FOLDER, null);
					//"You must select a  folder."
				}
                //如果选择的是工程，那么判定第一步选的是不是文件夹
				else if (selection.length == 1) {
					//不是文件夹
					if (!(selection[0] instanceof IFolder)) {
						status = new Status(IStatus.ERROR, Activator.PLUGIN_ID,
								IStatus.OK, WizardHandle.VALIDATE_FOLDER, null);
						//"You must select a  folder."
					}
                    //是文件夹
					else {
						IFolder folder = (IFolder) selection[0];
						status = new Status(IStatus.OK, Activator.PLUGIN_ID,
								IStatus.OK, WizardHandle.VALI_SEL(folder
										.getName(), WizardHandle.TYPE_FOLDER),
								null);
						//"folder"
					}

					return status;
				}

				status = new Status(IStatus.ERROR, Activator.PLUGIN_ID,
						IStatus.OK, WizardHandle.VALIDATE_ONEFOLDER, null);
                //"You must select only one folder."
				return status;
			}

		});

		if (loc != null && loc.length() > 0) {
			IResource resource = ResourcesPlugin.getWorkspace().getRoot()
					.findMember(loc);
			if (resource != null && resource.exists()) {
				if (resource.getParent() instanceof IProject)
					dialog.setInitialSelection(resource);
			}
		}

		if (dialog.open() == ElementTreeSelectionDialog.OK) {
			Object[] result = dialog.getResult();
			if (result.length == 1) {
				return ((IContainer) result[0]).getFullPath().toString();
			}

		}

		return null;
	}
	
    /*
	protected String handleDefBrowse() {
		ElementTreeSelectionDialog dialog = new ElementTreeSelectionDialog(
				getShell(), new CustomWorkbenchLabelProvider(),
				new WorkbenchContentProvider());

		dialog.setInput(ResourcesPlugin.getWorkspace().getRoot());

		dialog.setTitle("Select A Definition File");
		dialog.setMessage("choose a definition file relative to this diagram:");

		dialog.addFilter(new ViewerFilter() {

			@Override
			public boolean select(Viewer viewer, Object parentElement,
					Object element) {
				// TODO Auto-generated method stub

				if (element instanceof IProject) {
					if (((IProject) element).getName().endsWith(".project"))
						return false;
					return true;
				}

				if (element instanceof IContainer) {
					return true;
				}

				if (element instanceof IFile) {
					if (((IFile) element).getName().endsWith(".ttcn3")) {
						return true;
					}
				}

				return false;
			}

		});

		dialog.setValidator(new ISelectionStatusValidator() {

			@Override
			public IStatus validate(Object[] selection) {
				// TODO Auto-generated method stub
				IStatus status = null;

				if (selection.length == 0) {
					status = new Status(IStatus.ERROR, Activator.PLUGIN_ID,
							IStatus.OK, WizardHandle.VALIDATE_FILE, null);
				}

				else if (selection.length == 1) {
					if (!(selection[0] instanceof IFile)) {
						status = new Status(IStatus.ERROR, Activator.PLUGIN_ID,
								IStatus.OK, WizardHandle.VALIDATE_FILE, null);
					}

					else {
						IFile file = (IFile) selection[0];
						status = new Status(IStatus.OK, Activator.PLUGIN_ID,
								IStatus.OK, WizardHandle.VALI_SEL(file
										.getName(), WizardHandle.TYPE_FILE),
								null);
					}

					return status;

				}
				status = new Status(IStatus.ERROR, Activator.PLUGIN_ID,
						IStatus.OK, WizardHandle.VALIDATE_ONEFILE, null);

				return status;
			}

		});

		if (def != null && def.length() > 0) {
			IResource resource = ResourcesPlugin.getWorkspace().getRoot()
					.findMember(def);
			if (resource != null && resource.exists()) {
				dialog.setInitialSelection(resource);
			}
		}

		if (dialog.open() == ElementTreeSelectionDialog.OK) {
			Object[] result = dialog.getResult();
			if (result.length == 1) {
				return ((IFile) result[0]).getFullPath().toString();
			}
		}

		return null;
	}
    */
	protected void updateDialog() {
		if (names == null || names.length() == 0) {
			updateStatus("File name must exist.");
			return;
		}

		if (loc == null || loc.length() == 0) {
			updateStatus(WizardHandle.STATE_NOFILENAME);// "File name does not exist."
			return;
		}

		IResource resource = ResourcesPlugin.getWorkspace().getRoot()
				.findMember(loc);

		if (resource == null || !resource.exists()) {
			updateStatus(WizardHandle.STATE_NOFILELOC);
			//"File location does not exist."
			return;
		}

		if (resource instanceof IFile) {
			updateStatus(WizardHandle.STATE_NOFOLDERSPE);
			//"A folder location must be specified."
			return;
		}

		if (resource instanceof IProject) {
			updateStatus(WizardHandle.STATE_NOFOLDER);
			//"A folder must be selected."
			return;
		}

		if (resource instanceof IFolder) {
			if (!(((IFolder) resource).getParent() instanceof IProject)) {
				updateStatus(WizardHandle.STATE_WRONGFOLDER);
				//"A folder selected must be just under a project."
				return;
			}
		}
        /*
		if (defSPE) {
			if (def == null || def.length() == 0) {
				updateStatus(WizardHandle.STATE_NOEXDEF);
				return;
			}

			IResource defresource = ResourcesPlugin.getWorkspace().getRoot()
					.findMember(def);
			if (defresource == null || !defresource.exists()
					|| !(defresource instanceof IFile)) {
				updateStatus(WizardHandle.STATE_NOEXDEF);
				return;
			}

			IFile file = (IFile) defresource;
			if (!file.getName().endsWith(".ttcn3")) {
				updateStatus(WizardHandle.STATE_NOEXDEF);
				return;
			}
		}
        else {
			IFolder modFolder = ((IFolder) resource)
					.getFolder(ProjectHelper.FOLDER_MODULE);
			if (!modFolder.exists()) {
				updateStatus(null);
				return;
			}

			IFile gftfile = ((IContainer) modFolder).getFile(new Path(names
					+ ".gft"));
			if (gftfile.exists()) {
				updateStatus(WizardHandle.STATE_FILEEXIST);
				return;
			}
		}
        */
		
		updateStatus(null);
	}

	protected void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);

	}

	protected void initialize() {

		if (selection != null && selection.isEmpty() == false
				&& selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			if (ssel.size() > 1)
				return;
			Object obj = ssel.getFirstElement();
			
			if (obj instanceof IResource) {
				IResource resource = (IResource) obj;
				IFolder folder= ProjectHelper.getGEFFolder(resource);

				if (folder!= null) {
					loctext.setText(folder.getFullPath().toString());
					//IFile defFile = ProjectHelper.getDefineFile(mod);
					//if (defFile != null)
						//deftext.setText(defFile.getFullPath().toString());
				}
			}	
			
		}

		filetext.setText("newRobotTask");
	}
}


