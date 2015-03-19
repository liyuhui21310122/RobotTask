package com.example.ui.wizards;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.example.ui.helper.GEFXMLView;



public class NewProjectWizardPage extends WizardPage {

	private String projectName = null;


	public NewProjectWizardPage() {
		this(GEFXMLView.NAME_PROJECTPAGE);
		setDescription("新建机器人任务工程");
		setTitle("新建机器人任务工程");
		setDescription("新建机器人任务工程");
		this.setPageComplete(true);

	}

	protected NewProjectWizardPage(String pageName) {
		super(pageName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		Composite composite = new Composite(parent, SWT.NULL);
		
		GridLayout layout =  new GridLayout();
		composite.setLayout(layout);

		layout.marginTop = 50;
		layout.numColumns = 2;
		layout.verticalSpacing = 9;

		Label label = new Label(composite, SWT.NULL);
		label.setText("工程名:");

		final Text projectText = new Text(composite, SWT.BORDER | SWT.SINGLE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		projectText.setLayoutData(gd);
		projectText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				projectName = projectText.getText();
				dialogChanged();
			}
		});

		dialogChanged();
		setControl(composite);
	}

	private void dialogChanged() {
		if (projectName == null || projectName.length() == 0) {
			updateStatus(WizardHandle.STATE_NOPRJNAME);
			return;
		}

		IProject newProject = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(projectName);

		if (newProject.exists()) {
			updateStatus(WizardHandle.STATE_PRJEXIST);
			return;
		}

		updateStatus(null);
	}

	public String getProjectName() {
		return projectName;
	}

	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}

}
