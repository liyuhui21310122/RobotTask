package com.example.ui.wizards;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.example.application.Activator;
import com.example.ui.jiaohu.MyServer;


public class WizardHandle {
	public static String TYPE_LOCATION = "λ��";
	public static String TYPE_FILE = "�ļ�";
	public static String TYPE_FOLDER = "�ļ���";
	//public static String TYPE_DEFINE = "definition file";
	public static String TYPE_DIAGRAM = "����ͼ";
	public static String TYPE_PROJECT = "����";

	//public static String ERROR_NODEF = "Error:The definition file is not exist.";
	public static String ERROR_OPEN = "�����޷����ļ�";
	public static String ERROR_NOPRJ = "�����Ҳ�������";

	public static String STATE_NOPRJNAME = "���붨�幤����";
	public static String STATE_PRJEXIST = "�����Ѿ�����";

	public static String STATE_NOFILENAME = "�ļ���������";
	public static String STATE_NOFILELOC = "�ļ�λ�ò�����";
	public static String STATE_NOFOLDERSPE = "��ѡ���ļ���λ��";
	public static String STATE_NOFOLDER = "����ѡ��һ���ļ���";
	public static String STATE_WRONGFOLDER = "�ļ��б��봴���ڹ�������";
	//public static String STATE_NOEXDEF = "External Defintive file does not specified.";
	public static String STATE_FILEEXIST = "�ļ����Ѿ�����";
	public static String STATE_NOFOLDERNAME = "�ļ��в�����";
	public static String STATE_NOFOLDERLOC = "�ļ���λ�ò�����";
	public static String STATE_NOPRJLOC = "��ѡ�񹤳�λ��";
	public static String STATE_FOLDEREXIST = "�ļ������Ѿ�����";
	public static String STATE_NOPRJFDLOC = "��ѡ�񹤳̻����ļ��е�λ��";
	public static String STATE_NOFOLDERSEL = "��ѡ��һ���ļ���";
	//public static String STATE_DUPDEF = "This definitive file has exisited,a module have only one definitive file.";
	public static String STATE_DIFSYSRUNS = "Component on Systems must be different from Runs On's.";

	public static String VALIDATE_FILE = "You must select a .ttcn file.";
	public static String VALIDATE_ONEFILE = "ֻ��ѡ��һ���ļ�";
	public static String VALIDATE_FOLDER = "��ѡ��һ���ļ���";
	public static String VALIDATE_ONEFOLDER = "ֻ��ѡ��һ���ļ���";
	public static String VALIDATE_PRJ = "��ѡ��һ������";
	public static String VALIDATE_ONEPRJ = "ֻ��ѡ��һ������";

	public static String VALI_SEL(String name, String type) {
		return "ѡ��" + " " + type + ":" + name + ".";
	}

	public static String NOT_EXIST(String name, String type) {
		return "����:" + type + ":" + "\"" + name + "\"" + " "
				+ "������";
	}

	public static String NOT_SOLVE(String name) {
		return "����: �޷�����:" + name + ".";
	}

	public static String NOT_TYPE(String resource, String type) {
		return "����:\"" + resource + "\"" + " �� " + type + ".";
	}
	
	public static String ERROR_ACCESSPRJ(String name){
		return "����"+"���̣�"+name+" ������";
	}

	public static void throwCoreException(String message) throws CoreException {
		IStatus status = new Status(IStatus.ERROR, Activator.PLUGIN_ID,
				IStatus.OK, message, null);
		throw new CoreException(status);
	}
	
	


}