package com.example.ui.helper;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

import com.example.application.Activator;





/**
 * class to manager image of project
 * 
 * @author user
 * 
 */

public class IImageKey {

	
	public static String KEY_ERROR = "error";
	public static String KEY_ERROR_MARKER = "errormarker";
	public static String KEY_NEW = "diagram";
	public static String KEY_NEWPRJ = "project";
	//public static String KEY_NEWPRJEX = "newexprj_wiz";
	public static String KEY_NEWPRJEX = "folder";
	public static String KEY_NEWDEF = "new_con";
	public static String KEY_OPEN = "open_file";
	public static String KEY_SAVE = "save_edit";
	public static String KEY_FILTER = "filter_ps";
	public static String KEY_IMPORT = "import_wiz";
	public static String KEY_EXPORT = "export_wiz";
	public static String KEY_GENERATE = "generate_core";
	public static String KEY_CUT = "cut_edit";
	public static String KEY_COPY = "copy_edit";
	public static String KEY_PASTE = "paste_edit";
	public static String KEY_DELETE = "delete_edit";
	public static String KEY_BACK = "Back 2";
	public static String KEY_FORWARD = "Forward 2";
	public static String KEY_PRINT = "print_edit";
	public static String KEY_COMPILE = "run_exc";
	public static String KEY_FIND = "find_obj";
	public static String KEY_OPERATE = "General Options";

	public static String KEY_PROPERTY = "property_view";
	public static String KEY_PROBLEM = "problems_view";
	public static String KEY_CORELANG = "corelanguage_view";
	public static String KEY_TODOITEM = "todoitem_view";
	public static String KEY_OUTLINE = "outline_view";
	public static String KEY_PROJECT = "project";

	public static String KEY_DIAGRAM = "file";
	public static String KEY_WIZADSMALL = "wizard";
	public static String KEY_DIALOG = "wizard";
	public static String KEY_WINDOW = "wizard";
	public static String KEY_TTCN = "ttcn";

	
	
	public static String KEY_ACTION="../tree/t_action";	
	public static String KEY_CREATE="../tree/t_create";
	public static String KEY_DEFAULT="../tree/t_default";	
	public static String KEY_EXECUTE="../tree/t_execute";
	public static String KEY_REFERENCE="../tree/t_reference";
	public static String KEY_START="../tree/t_start";
	
	public static String KEY_EVENTCOMMENT="../tree/t_eventcomment";
	public static String KEY_TEXT="../tree/t_text";
	
	public static String KEY_STARTTIMER="../tree/t_starttimer";
	public static String KEY_STOPTIMER="../tree/t_stoptimer";
	public static String KEY_TIMEOUTTIMER="../tree/t_timeouttimer";
	public static String KEY_STARTIMPLICITTIMER="../tree/t_startimplicittimer";
	public static String KEY_TIMEOUTIMPLICITTIMER="../tree/t_timeoutimplicittimer";
	
	public static String KEY_CONDITION="../tree/t_condition";
	
	public static String KEY_INLINE = "../tree/t_inline";
	
	public static String KEY_MESSAGETO="../tree/t_messageto";
	public static String KEY_MESSAGEFROM="../tree/t_messagefrom";
	public static String KEY_FOUND="../tree/t_found";
	public static String KEY_SUSPENSIONREGION="../tree/t_suspensionregion";
	
	public static String KEY_PORT="../tree/t_port";	
	public static String KEY_COMPONENT="../tree/t_component";
	public static String KEY_LABEL="../tree/t_label";
	public static String KEY_GOTO="../tree/t_goto";
	public static String KEY_STOP="../tree/t_stop";
	public static String KEY_REPEAT="../tree/t_repeat";
	public static String KEY_RETURN="../tree/t_return";	
	public static String KEY_INSTANCEEND="../tree/t_instanceend";
	
	public static String IIMAGE_ACTION="/icons/Palette/action.gif";	
	public static String IIMAGE_CREATE="/icons/Palette/create.gif";
	public static String IIMAGE_DEFAULT="/icons/Palette/default.gif";	
	public static String IIMAGE_EXECUTE="/icons/Palette/execute.gif";
	public static String IIMAGE_REFERENCE="/icons/Palette/reference.gif";
	public static String IIMAGE_START="/icons/Palette/start.gif";
	
	public static String IIMAGE_EVENTCOMMENT="/icons/Palette/eventcomment.gif";
	public static String IIMAGE_TEXT="/icons/Palette/text.gif";
	
	public static String IIMAGE_STARTTIMER="/icons/Palette/starttimer.gif";
	public static String IIMAGE_STOPTIMER="/icons/Palette/stoptimer.gif";
	public static String IIMAGE_TIMEOUTTIMER="/icons/Palette/timeouttimer.gif";
	
	public static String IIMAGE_VERDICTCONDITION="/icons/Palette/verdictcondition.gif";
	public static String IIMAGE_DONECONDITION="/icons/Palette/donecondition.gif";
	public static String IIMAGE_BOOLEANCONDITION="/icons/Palette/booleancondition.gif";
	public static String IIMAGE_PORTOPERATION="/icons/Palette/portoperation.gif";	
	
	public static String IIMAGE_ALT="/icons/Palette/alt.gif";
	public static String IIMAGE_INTERLEAVE="/icons/Palette/interleave.gif";
	public static String IIMAGE_CALL="/icons/Palette/call.gif";
	public static String IIMAGE_DOWHILE="/icons/Palette/dowhile.gif";
	public static String IIMAGE_WHILE="/icons/Palette/while.gif";
	public static String IIMAGE_IF="/icons/Palette/if.gif";
	public static String IIMAGE_IFELSE="/icons/Palette/ifelse.gif";
	public static String IIMAGE_FOR="/icons/Palette/for.gif";
	
	public static String IIMAGE_MESSAGETO="/icons/Palette/messageto.gif";
	public static String IIMAGE_MESSAGEFROM="/icons/Palette/messagefrom.gif";
	public static String IIMAGE_MESSAGECHECK="/icons/Palette/messagecheck.gif";
	public static String IIMAGE_FOUND="/icons/Palette/found.gif";
	public static String IIMAGE_FOUNDCHECK="/icons/Palette/foundcheck.gif";
	
	public static String IIMAGE_PORT="/icons/Palette/port.gif";	
	public static String IIMAGE_LABEL="/icons/Palette/label.gif";
	public static String IIMAGE_STOPCOMPONENT="/icons/Palette/stopcomponent.gif";	

	public static Image getImage(String key) {
		ImageRegistry registry = Activator.getDefault().getImageRegistry();

		ImageHelper.getImageDescriptor(Activator.PLUGIN_ID, registry, key);
		Image image = registry.get(key);

		return image;
	}

	public static ImageDescriptor getImageDescriptor(String key) {
		ImageRegistry registry = Activator.getDefault().getImageRegistry();
		return ImageHelper.getImageDescriptor(Activator.PLUGIN_ID, registry,
				key);
	}
}
