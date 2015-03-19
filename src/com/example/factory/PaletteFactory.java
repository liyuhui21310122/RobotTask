package com.example.factory;

import java.util.ArrayList;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.example.application.Application;
import com.example.model.node.CircleMotionModel;
import com.example.model.node.ConditionModel;
import com.example.model.node.EndModel;
import com.example.model.node.InputModel;
import com.example.model.node.LinearMotionModel;
import com.example.model.node.LoopModel;
import com.example.model.node.OutputModel;
import com.example.model.node.RotateModel;
import com.example.model.node.ShiftModel;
import com.example.model.node.StartModel;
import com.example.model.node.WaitModel;
import com.example.ui.helper.IImageKey;

public class PaletteFactory {
    public static PaletteRoot createPalette() {
        PaletteRoot paletteRoot = new PaletteRoot();
        paletteRoot.addAll(createCategories(paletteRoot));
        return paletteRoot;
    }

    private static ArrayList<Object> createCategories(PaletteRoot root) {
    	ArrayList<Object> categories = new ArrayList<Object>();

        categories.add(createControlGroup(root));
        categories.add(createComponentsDrawer());
        categories.add(createContainersDrawer());
        return categories;
    }

    private static PaletteContainer createControlGroup(PaletteRoot root) {
        PaletteGroup controlGroup = new PaletteGroup("������");

        ArrayList<Object> entries = new ArrayList<Object>();
        ToolEntry tool = new SelectionToolEntry();
        tool.setLabel("ѡ��");
        entries.add(tool);
        root.setDefaultEntry(tool);

        tool = new ConnectionCreationToolEntry("����", "����һ������", null, null, null);
        entries.add(tool);

        controlGroup.addAll(entries);
        return controlGroup;
    }

    private static PaletteContainer createComponentsDrawer() {

        PaletteDrawer drawer = new PaletteDrawer("���˶�ģ��");

        ArrayList<Object> entries = new ArrayList<Object>();
        ImageDescriptor image;
        
        image = AbstractUIPlugin.imageDescriptorFromPlugin
                    (Application.PLUGIN_ID, IImageKey.IIMAGE_IF);
        ToolEntry tool = new CombinedTemplateCreationEntry("��ʼ��", "��ȡ�����˻�����Ϣ", StartModel.class, new SimpleFactory(
        		StartModel.class), image, image);
        entries.add(tool);
        
        image = AbstractUIPlugin.imageDescriptorFromPlugin
                    (Application.PLUGIN_ID, IImageKey.IIMAGE_TEXT);
        tool = new CombinedTemplateCreationEntry("ֱ���˶�", "����ֱ���˶�", LinearMotionModel.class, new SimpleFactory(
        		LinearMotionModel.class), image, image);//
        entries.add(tool);
       
        image = AbstractUIPlugin.imageDescriptorFromPlugin
                    (Application.PLUGIN_ID, IImageKey.IIMAGE_CREATE);//
        tool = new CombinedTemplateCreationEntry("Բ���˶�", "����Բ���˶�", CircleMotionModel.class, new SimpleFactory(
        		CircleMotionModel.class), image, image);//
        entries.add(tool);
     
        image = AbstractUIPlugin.imageDescriptorFromPlugin
        (Application.PLUGIN_ID, IImageKey.IIMAGE_ACTION);
        tool = new CombinedTemplateCreationEntry("����ת�˶�", "��������ת�˶�", RotateModel.class, new SimpleFactory(
        		RotateModel.class), image, image);//
        entries.add(tool);
        
        image = AbstractUIPlugin.imageDescriptorFromPlugin
        (Application.PLUGIN_ID, IImageKey.IIMAGE_ALT);
        tool = new CombinedTemplateCreationEntry("��ƽ���˶�", "������ƽ���˶�", ShiftModel.class, new SimpleFactory(
        		ShiftModel.class), image, image);//
        entries.add(tool);
        
        image = AbstractUIPlugin.imageDescriptorFromPlugin
        (Application.PLUGIN_ID, IImageKey.IIMAGE_CALL); 
        tool = new CombinedTemplateCreationEntry("�ȴ������ź�", "�ȴ������ź�", InputModel.class, new SimpleFactory(
        		InputModel.class), image, image);
        entries.add(tool);
        
        image = AbstractUIPlugin.imageDescriptorFromPlugin
        (Application.PLUGIN_ID, IImageKey.IIMAGE_CALL);
        tool = new CombinedTemplateCreationEntry("����ź�", "����ź�", OutputModel.class, new SimpleFactory(
        		OutputModel.class), image, image);
        entries.add(tool);
        
        image = AbstractUIPlugin.imageDescriptorFromPlugin
        (Application.PLUGIN_ID, IImageKey.IIMAGE_FOR);
        tool = new CombinedTemplateCreationEntry("�ȴ�", "�ȴ�һ��ʱ��", WaitModel.class, new SimpleFactory(
        		WaitModel.class), image, image);//
        entries.add(tool);
        
        image = AbstractUIPlugin.imageDescriptorFromPlugin
        (Application.PLUGIN_ID, IImageKey.IIMAGE_IF);
        tool = new CombinedTemplateCreationEntry("��������", "��������", EndModel.class, new SimpleFactory(
        		EndModel.class), image, image);//
        entries.add(tool);
   
        drawer.addAll(entries);
        return drawer;
    }
    
    private static PaletteContainer createContainersDrawer() {

        PaletteDrawer drawer = new PaletteDrawer("�����˶�ģ��");

        ArrayList<Object> entries = new ArrayList<Object>();
        ImageDescriptor image;
        
        image = AbstractUIPlugin.imageDescriptorFromPlugin
                    (Application.PLUGIN_ID, IImageKey.IIMAGE_START);
        ToolEntry tool = new CombinedTemplateCreationEntry("ѭ��", "����ѭ��ģ��", LoopModel.class, new SimpleFactory(
        		LoopModel.class), image, image);
        entries.add(tool);
        
        
        
        image = AbstractUIPlugin.imageDescriptorFromPlugin
        (Application.PLUGIN_ID, IImageKey.IIMAGE_LABEL);
        tool = new CombinedTemplateCreationEntry("�����ж�", "���������ж�ģ��", ConditionModel.class, new SimpleFactory(
        		ConditionModel.class), image, image);
        entries.add(tool);
        
        drawer.addAll(entries);
        return drawer;
    }
}
