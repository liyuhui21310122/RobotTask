package com.example.ui.wizards;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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



public class NewFolderWizardPage extends WizardPage {
    
	private ISelection selection = null;

	private String names = null;
	private String loc = null;
	private Composite composite;

	private Text filetext;
	private Text loctext;

	public NewFolderWizardPage(ISelection selection) {
		this(GEFXMLView.NAME_GEFFOLDER);
		this.selection = selection;

		setDescription("新建机器人任务文件夹");
		setTitle("新建机器人任务文件夹");
		setDescription("新建机器人任务文件夹");

		this.setPageComplete(true);
	}

	protected NewFolderWizardPage(String pageName) {
		super(pageName);
		// TODO Auto-generated constructor stub
	}

	public String getNames() {
		return names;
	}

	public String getProjectName() {
		return loc;
	}

	@Override
	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(3, false);
		layout.marginTop = 40;
		layout.marginLeft = 60;
		layout.verticalSpacing = 30;
		composite.setLayout(layout);

		Label nameLabel = new Label(composite, SWT.NONE);
		GridData gridData = new GridData();
		nameLabel.setText("名字:");

		filetext = new Text(composite, SWT.BORDER | SWT.LEFT);
		gridData = new GridData();
		gridData.horizontalSpan = 2;
		gridData.widthHint = 700;
		filetext.setLayoutData(gridData);
		filetext.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				Text text = (Text) e.widget;
				names = text.getText();
				updateDialog();
			}

		});

		Label locLabel = new Label(composite, SWT.NONE);
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

		Button browse = new Button(composite, SWT.BORDER);
		browse.setText("展开");
		browse.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String userloc = handleBrowse();
				if (userloc == null)
					return;
				else
					loctext.setText(userloc);
			}
		});

		initialize();

		setControl(composite);

	}

	protected void updateDialog() {
		if (names == null || names.length() == 0) {
			updateStatus(WizardHandle.STATE_NOFOLDER);
			return;
		}

		if (loc == null || loc.length() == 0) {
			updateStatus(WizardHandle.STATE_NOFOLDERLOC);
			return;
		}

		IResource resource = ResourcesPlugin.getWorkspace().getRoot()
				.findMember(loc);
		if (resource == null || !resource.exists()) {
			updateStatus(WizardHandle.STATE_NOFOLDERLOC);
			return;
		}

		if (!(resource instanceof IProject)) {
			updateStatus(WizardHandle.STATE_NOPRJLOC);
			return;
		}

		if (!resource.isAccessible()) {
			updateStatus(WizardHandle.ERROR_ACCESSPRJ(resource.getName()));
			return;
		}

		IFolder container = ((IProject) resource).getFolder(names);
		if (container.exists()) {
			updateStatus(WizardHandle.STATE_FOLDEREXIST);
			return;
		}

		updateStatus(null);
	}

	protected void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);

	}

	protected String handleBrowse() {
		ElementTreeSelectionDialog dialog = new ElementTreeSelectionDialog(
				getShell(), new WorkbenchLabelProvider(),
				new WorkbenchContentProvider());

		dialog.setTitle("选择文件夹位置");
		dialog.setMessage("选择文件夹所属工程");
		dialog.setInput(ResourcesPlugin.getWorkspace().getRoot());
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
							IStatus.OK, WizardHandle.VALIDATE_PRJ, null);
				}

				else if (selection.length == 1) {
					if (selection[0] instanceof IProject) {

						IProject project = (IProject) selection[0];

						if (!project.isAccessible()) {
							status = new Status(IStatus.ERROR,
									Activator.PLUGIN_ID, IStatus.OK,
									WizardHandle.ERROR_ACCESSPRJ(project
											.getName()), null);
						} else {

							status = new Status(IStatus.OK,
									Activator.PLUGIN_ID, IStatus.OK,
									WizardHandle.VALI_SEL(project.getName(),
											WizardHandle.TYPE_PROJECT), null);
						}
					}

					else {
						status = new Status(IStatus.ERROR, Activator.PLUGIN_ID,
								IStatus.OK, WizardHandle.VALIDATE_ONEPRJ, null);
					}

					return status;
				}

				status = new Status(IStatus.ERROR, Activator.PLUGIN_ID,
						IStatus.OK, "你只能选择一个工程", null);

				return status;
			}

		});

		if (dialog.open() == ElementTreeSelectionDialog.OK) {
			Object[] result = dialog.getResult();
			if (result.length == 1) {
				return ((IProject) result[0]).getFullPath().toString();
			}
		}

		return null;
	}

	protected void initialize() {
		if (selection != null && selection.isEmpty() == false
				&& selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			if (ssel.size() > 1)
				return;
			Object obj = ssel.getFirstElement();
			if (obj instanceof IContainer) {

				IProject project = ((IContainer) obj).getProject();
				loctext.setText(project.getFullPath().toString());
			}
		}

		filetext.setText("新建机器人任务文件夹");
	}

}
