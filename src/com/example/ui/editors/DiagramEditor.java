package com.example.ui.editors;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.EventObject;
import javax.sql.rowset.spi.XmlWriter;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.parts.ScrollableThumbnail;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.AlignmentAction;
import org.eclipse.gef.ui.actions.DeleteAction;
import org.eclipse.gef.ui.actions.DirectEditAction;
import org.eclipse.gef.ui.actions.RedoAction;
import org.eclipse.gef.ui.actions.SaveAction;
import org.eclipse.gef.ui.actions.SelectAllAction;
import org.eclipse.gef.ui.actions.UndoAction;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IPersistableEditor;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.ide.ResourceUtil;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import com.example.dnd.DiagramTemplateTransferDropTargetListener;
import com.example.factory.PaletteFactory;
import com.example.factory.PartFactory;
import com.example.factory.TreePartFactory;
import com.example.model.DiagramModel;
import com.example.ui.helper.ResourceURLHelper;
import com.example.ui.jiaohu.MyClient;
import com.example.ui.jiaohu.MyServer;
import com.example.ui.jiaohu.ServerHelper;


/**
 * @author zhanghao
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DiagramEditor extends GraphicalEditorWithPalette implements IPersistableEditor {
	public static final String ID = "com.example.ui.DiagramEditor";
	public static String WARN_FILEEXIST = "diagram file is not exists.";
    private DiagramModel diagramModel = new DiagramModel();
    
    private PaletteRoot paletteRoot;

    public DiagramModel getDiagram() {
        return this.diagramModel;
    }

    public DiagramEditor() {
        setEditDomain(new DefaultEditDomain(this));
    }

    protected void configureGraphicalViewer() {
        super.configureGraphicalViewer();
        
        ScalableRootEditPart rootEditPart = new ScalableRootEditPart();
        getGraphicalViewer().setRootEditPart(rootEditPart);
        
        ZoomManager zoomManager = rootEditPart.getZoomManager();
        
        //放大比例数组
        double[] zoomLevels = new double[]{
        		0.25,0.5,0.75,1.0,1.5,2.0,2.5,3.0,4.0,5.0,10.0,20.0};
        zoomManager.setZoomLevels(zoomLevels);
        //非百分比缩放
        ArrayList zoomContributions = new ArrayList();
        zoomContributions.add(ZoomManager.FIT_ALL);
        zoomContributions.add(ZoomManager.FIT_HEIGHT);
        zoomContributions.add(ZoomManager.FIT_WIDTH);
        zoomManager.setZoomLevelContributions(zoomContributions);
        
		IAction action = new ZoomInAction(zoomManager);//放大
		getActionRegistry().registerAction(action);
		action = new ZoomOutAction(zoomManager);//缩小
		getActionRegistry().registerAction(action);
        
		
        getGraphicalViewer().setEditPartFactory(new PartFactory());
        
        ContextMenuProvider provider = new DiagramContextMenuProvider(getGraphicalViewer(),
				getActionRegistry());
        getGraphicalViewer().setContextMenu(provider);
		getSite().registerContextMenu(provider, getGraphicalViewer());
    }

    protected void initializeGraphicalViewer() {
        getGraphicalViewer().setContents(this.diagramModel);
        getGraphicalViewer().addDropTargetListener(new DiagramTemplateTransferDropTargetListener(getGraphicalViewer()));
    }
    
    @Override
	protected void createActions() {
    	super.createActions();
		// TODO Auto-generated method stub
		ActionRegistry registry = getActionRegistry();
		
		IAction action;
        
		action = new UndoAction(this);
		registry.registerAction(action);
		getStackActions().add(action.getId());

		action = new RedoAction(this);
		registry.registerAction(action);
		getStackActions().add(action.getId());
		/*
		action = new CutAction((IWorkbenchPart) this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		
		action = new CopyAction((IWorkbenchPart) this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new PasteAction((IWorkbenchPart) this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		*/
		action = new DeleteAction((IWorkbenchPart) this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new SelectAllAction(this);
		registry.registerAction(action);

		action = new SaveAction(this);
		registry.registerAction(action);
		getPropertyActions().add(action.getId());
		
		action = new DirectEditAction((IWorkbenchPart) this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		
		
		getSelectionActions().add(action.getId());
		//水平方向对齐
		action = new AlignmentAction((IWorkbenchPart) this ,PositionConstants.LEFT);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		
		action = new AlignmentAction((IWorkbenchPart) this ,PositionConstants.CENTER);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		
		action = new AlignmentAction((IWorkbenchPart) this ,PositionConstants.RIGHT);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		
		//垂直方向对齐
		action = new AlignmentAction((IWorkbenchPart) this ,PositionConstants.TOP);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		
		action = new AlignmentAction((IWorkbenchPart) this ,PositionConstants.MIDDLE);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		
		action = new AlignmentAction((IWorkbenchPart) this ,PositionConstants.BOTTOM);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		
    }

    public Object getAdapter(Class type) {
        if (type == IContentOutlinePage.class)
            return new EditorContentOutlinePage();
        if (type == ZoomManager.class)
            return ((ScalableRootEditPart) getGraphicalViewer().getRootEditPart()).getZoomManager();
        return super.getAdapter(type);
        
    }

    protected PaletteRoot getPaletteRoot() {
        if (this.paletteRoot == null) {
            this.paletteRoot = PaletteFactory.createPalette();
        }
        return this.paletteRoot;
    }

    protected void initializePaletteViewer() {
        super.initializePaletteViewer();
        getPaletteViewer().addDragSourceListener(new TemplateTransferDragSourceListener(getPaletteViewer()));
    }
  

	class EditorContentOutlinePage extends ContentOutlinePage {
		private SashForm sash;

		private ScrollableThumbnail thumbnail;
		private DisposeListener disposeListener;

		public EditorContentOutlinePage() {
			super(new TreeViewer());
			// TODO Auto-generated constructor stub
		}

		@Override
		public void init(IPageSite pageSite) {
			// TODO Auto-generated method stub
			super.init(pageSite);

			ActionRegistry registry = getActionRegistry();
			IActionBars bars = pageSite.getActionBars();

			String id = ActionFactory.UNDO.getId();
			bars.setGlobalActionHandler(id, registry.getAction(id));
			id = ActionFactory.REDO.getId();
			bars.setGlobalActionHandler(id, registry.getAction(id));
			id = ActionFactory.DELETE.getId();
			bars.setGlobalActionHandler(id, registry.getAction(id));
			id = ActionFactory.SAVE.getId();
			bars.setGlobalActionHandler(id, registry.getAction(id));
			id = ActionFactory.SAVE_ALL.getId();
			bars.setGlobalActionHandler(id, registry.getAction(id));

			bars.updateActionBars();
		}

		@Override
		public void createControl(Composite parent) {
			// TODO Auto-generated method stub

			sash = new SashForm(parent, SWT.VERTICAL);

			getViewer().createControl(sash);

			getViewer().setEditDomain(getEditDomain());
			getViewer().setEditPartFactory(new TreePartFactory());

			getViewer().setContents(diagramModel);
			getSelectionSynchronizer().addViewer(getViewer());

			Canvas canvas = new Canvas(sash, SWT.BORDER);
			LightweightSystem lws = new LightweightSystem(canvas);

			thumbnail = new ScrollableThumbnail(
					(Viewport) ((ScalableRootEditPart) getGraphicalViewer()
							.getRootEditPart()).getFigure());
			thumbnail.setSource(((ScalableRootEditPart) getGraphicalViewer()
					.getRootEditPart())
					.getLayer(LayerConstants.PRINTABLE_LAYERS));

			lws.setContents(thumbnail);

			disposeListener = new DisposeListener() {

				@Override
				public void widgetDisposed(DisposeEvent e) {
					// TODO Auto-generated method stub
					if (thumbnail != null) {
						thumbnail.deactivate();
						thumbnail = null;
					}
				}

			};

			getGraphicalViewer().getControl().addDisposeListener(
					disposeListener);
		}

		@Override
		public Control getControl() {
			// TODO Auto-generated method stub
			return sash;
		}

		@Override
		public void dispose() {
			// TODO Auto-generated method stub
			getSelectionSynchronizer().removeViewer(getViewer());

			if (getGraphicalViewer().getControl() != null
					&& !getGraphicalViewer().getControl().isDisposed()) {
				getGraphicalViewer().getControl().removeDisposeListener(
						disposeListener);

			}
			super.dispose();
		}

	}
    
    
    //my save
    public void doSave(IProgressMonitor monitor){
    	performSave(monitor,getEditorInput());
    	getCommandStack().markSaveLocation();
    }
    
    //保存为xml文件
	private void performSave(IProgressMonitor monitor, IEditorInput input) {
		// TODO Auto-generated method stub
		if (diagramModel == null)
			return;
		diagramModel.doSaveXML();
		Document xmlTree = diagramModel.getDiagramDoc();
		if (xmlTree == null)
			return;
		
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("GBK" );   
		IFile file = ResourceUtil.getFile(getEditorInput());
		
		try {
			FileWriter fileWriter;
			fileWriter = new FileWriter((String) input.getAdapter(Path.class));

			XMLWriter writer = new XMLWriter(fileWriter, format);
			writer.write(xmlTree);
			writer.close();

			if (file != null) {
				file.refreshLocal(IResource.DEPTH_ZERO,
						new NullProgressMonitor());
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		String filelocation = file.getLocation().toOSString();
//    	MyClient myclient = new MyClient();
//		myclient.send("save file?"+filelocation);
	}
	
	
	@Override
    public boolean isSaveAsAllowed() {
	     return true;
	}
	 
	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return getCommandStack().isDirty();
	} 
	
	
	
	public void doSaveAs() {
		//创建SaveAsDialog
		Shell shell = getSite().getShell();
		SaveAsDialog dialog = new SaveAsDialog(shell);
		
		//打开dialog
		final IEditorInput input = getEditorInput();
		IFile original = (input instanceof IFileEditorInput) ? ((IFileEditorInput) input).getFile(): null;
		if (original != null)
			dialog.setOriginalFile(original);

		dialog.create();
       
		int result = dialog.open();
		if (result == Window.CANCEL) {
			return;
		}
		
		//获取路径
		IPath filePath = dialog.getResult();
		IFile file = null;
		
		if (filePath == null) {
			return;
		} else {
			//根据路径获取file
			file = ResourcesPlugin.getWorkspace().getRoot().getFile(filePath);
		}
		if (input.exists()) {

		} else {
			dialog.setErrorMessage(null);
			dialog.setMessage(WARN_FILEEXIST, IMessageProvider.WARNING);
			return;
		}

		if(file.exists()){
			
		}else{
			try {
				file.create(ResourceURLHelper.getNullInputStream(), true, null);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		DiagramEditorInput newInput = new DiagramEditorInput(file.getLocation());
		performSave(getProgressMonitor(),newInput);
	}
	 
	protected IProgressMonitor getProgressMonitor() {

			IProgressMonitor pm = null;

			IStatusLineManager manager = getStatusLineManager();
			if (manager != null)
				pm = manager.getProgressMonitor();

			return pm != null ? pm : new NullProgressMonitor();
    }

	protected IStatusLineManager getStatusLineManager() {
			return getEditorSite().getActionBars().getStatusLineManager();
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
				throws PartInitException {
			// TODO Auto-generated method stub

			input = changeEditorInput(input);

			setInput(input);
			load();
			
			setSite(site);
			setEditDomain(new DefaultEditDomain(this));
			getCommandStack().addCommandStackListener(this);
			getSite().getWorkbenchWindow().getSelectionService()
					.addSelectionListener(this);
			initializeActionRegistry();
			setPartName(input.getName());
			
			
//			File file = new File((String) getEditorInput().getAdapter(Path.class));
//			MyClient myclient = new MyClient();
//			String filelocation = file.getPath().toString();
//			myclient.send("new file"+"?"+filelocation);
			
			
		    /*
			ZoomManager manager = (ZoomManager) getGraphicalViewer().getProperty(ZoomManager.class.toString());
			if (manager != null )
			manager.setZoom(diagramModel.getZoom());
			*/
	}
	
	

	public DiagramEditorInput changeEditorInput(IEditorInput input)
				throws PartInitException {
			if (input instanceof DiagramEditorInput)
				return (DiagramEditorInput) input;
			else if (input instanceof IPathEditorInput) {

				IPath path = ((IPathEditorInput) input).getPath();
				DiagramEditorInput dinput = new DiagramEditorInput(path);
				return dinput;
			}

			return null;
	}
		
	@Override
	protected void setPartName(String partName) {
		// TODO Auto-generated method stub
		IPathEditorInput input = (IPathEditorInput) getEditorInput();
		super.setPartName(input.getName());
	}
  
	public DiagramModel load() {
			try {
				File file = new File((String) getEditorInput().getAdapter(
						Path.class));

				SAXReader reader = new SAXReader();
				Document document = reader.read(file);

				// get diagram type
				//Element root = document.getRootElement();
				//Element e = root.element("Diagram");
				//Element d = e.element(ModelType.HEAD_DIAGRAMHEAD);
				//type = d.attributeValue(ModelType.ATR_DIAGRAM);

				// parser(分析) diagram info
				diagramModel = DiagramRebuilder.rebuildFromRoot(
						document.getRootElement(), new ArrayList<Object>());

			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return diagramModel;
	}
   
    
  
	@Override
	public void restoreState(IMemento memento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveState(IMemento memento) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void commandStackChanged(EventObject event) {
			// TODO Auto-generated method stub

			firePropertyChange(IEditorPart.PROP_DIRTY);
			super.commandStackChanged(event);
	}
   
	protected void closeEditor(boolean request) {
		if (isDirty() && request) {
			boolean result = MessageDialog.open(MessageDialog.WARNING,
					getSite().getShell(), "关闭编辑器警告",
					"文件还未保存确定想要关闭吗？",
					MessageDialog.QUESTION);

			if (result)
				doSave(new NullProgressMonitor());
		}
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.closeEditor(this, true);
     }
	
	protected boolean openEditorMessageBox(String message, int style) {

		return MessageDialog.open(style, getSite().getShell(),
				"打开编辑器", message, SWT.NONE);

	}
      
}  


