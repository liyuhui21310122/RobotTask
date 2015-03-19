package com.example.ui.wizards;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.example.application.Activator;
import com.example.ui.jiaohu.MyServer;


public class WizardHandle {
	public static String TYPE_LOCATION = "位置";
	public static String TYPE_FILE = "文件";
	public static String TYPE_FOLDER = "文件夹";
	//public static String TYPE_DEFINE = "definition file";
	public static String TYPE_DIAGRAM = "流程图";
	public static String TYPE_PROJECT = "工程";

	//public static String ERROR_NODEF = "Error:The definition file is not exist.";
	public static String ERROR_OPEN = "错误：无法打开文件";
	public static String ERROR_NOPRJ = "错误：找不到工程";

	public static String STATE_NOPRJNAME = "必须定义工程名";
	public static String STATE_PRJEXIST = "工程已经存在";

	public static String STATE_NOFILENAME = "文件名不存在";
	public static String STATE_NOFILELOC = "文件位置不存在";
	public static String STATE_NOFOLDERSPE = "请选择文件夹位置";
	public static String STATE_NOFOLDER = "必须选择一个文件夹";
	public static String STATE_WRONGFOLDER = "文件夹必须创建在工程下面";
	//public static String STATE_NOEXDEF = "External Defintive file does not specified.";
	public static String STATE_FILEEXIST = "文件名已经存在";
	public static String STATE_NOFOLDERNAME = "文件夹不存在";
	public static String STATE_NOFOLDERLOC = "文件夹位置不存在";
	public static String STATE_NOPRJLOC = "请选择工程位置";
	public static String STATE_FOLDEREXIST = "文件夹名已经存在";
	public static String STATE_NOPRJFDLOC = "请选择工程或者文件夹的位置";
	public static String STATE_NOFOLDERSEL = "请选择一个文件夹";
	//public static String STATE_DUPDEF = "This definitive file has exisited,a module have only one definitive file.";
	public static String STATE_DIFSYSRUNS = "Component on Systems must be different from Runs On's.";

	public static String VALIDATE_FILE = "You must select a .ttcn file.";
	public static String VALIDATE_ONEFILE = "只能选择一个文件";
	public static String VALIDATE_FOLDER = "请选择一个文件夹";
	public static String VALIDATE_ONEFOLDER = "只能选择一个文件夹";
	public static String VALIDATE_PRJ = "请选择一个工程";
	public static String VALIDATE_ONEPRJ = "只能选择一个工程";

	public static String VALI_SEL(String name, String type) {
		return "选择" + " " + type + ":" + name + ".";
	}

	public static String NOT_EXIST(String name, String type) {
		return "错误:" + type + ":" + "\"" + name + "\"" + " "
				+ "不存在";
	}

	public static String NOT_SOLVE(String name) {
		return "错误: 无法处理:" + name + ".";
	}

	public static String NOT_TYPE(String resource, String type) {
		return "错误:\"" + resource + "\"" + " 不 " + type + ".";
	}
	
	public static String ERROR_ACCESSPRJ(String name){
		return "错误："+"工程："+name+" 不存在";
	}

	public static void throwCoreException(String message) throws CoreException {
		IStatus status = new Status(IStatus.ERROR, Activator.PLUGIN_ID,
				IStatus.OK, message, null);
		throw new CoreException(status);
	}
	
	


}