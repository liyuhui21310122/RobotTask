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
        PaletteGroup controlGroup = new PaletteGroup("控制组");

        ArrayList<Object> entries = new ArrayList<Object>();
        ToolEntry tool = new SelectionToolEntry();
        tool.setLabel("选择");
        entries.add(tool);
        root.setDefaultEntry(tool);

        tool = new ConnectionCreationToolEntry("连线", "创建一个连线", null, null, null);
        entries.add(tool);

        controlGroup.addAll(entries);
        return controlGroup;
    }

    private static PaletteContainer createComponentsDrawer() {

        PaletteDrawer drawer = new PaletteDrawer("简单运动模块");

        ArrayList<Object> entries = new ArrayList<Object>();
        ImageDescriptor image;
        
        image = AbstractUIPlugin.imageDescriptorFromPlugin
                    (Application.PLUGIN_ID, IImageKey.IIMAGE_IF);
        ToolEntry tool = new CombinedTemplateCreationEntry("初始化", "读取机器人基本信息", StartModel.class, new SimpleFactory(
        		StartModel.class), image, image);
        entries.add(tool);
        
        image = AbstractUIPlugin.imageDescriptorFromPlugin
                    (Application.PLUGIN_ID, IImageKey.IIMAGE_TEXT);
        tool = new CombinedTemplateCreationEntry("直线运动", "创建直线运动", LinearMotionModel.class, new SimpleFactory(
        		LinearMotionModel.class), image, image);//
        entries.add(tool);
       
        image = AbstractUIPlugin.imageDescriptorFromPlugin
                    (Application.PLUGIN_ID, IImageKey.IIMAGE_CREATE);//
        tool = new CombinedTemplateCreationEntry("圆周运动", "创建圆周运动", CircleMotionModel.class, new SimpleFactory(
        		CircleMotionModel.class), image, image);//
        entries.add(tool);
     
        image = AbstractUIPlugin.imageDescriptorFromPlugin
        (Application.PLUGIN_ID, IImageKey.IIMAGE_ACTION);
        tool = new CombinedTemplateCreationEntry("轴旋转运动", "创建轴旋转运动", RotateModel.class, new SimpleFactory(
        		RotateModel.class), image, image);//
        entries.add(tool);
        
        image = AbstractUIPlugin.imageDescriptorFromPlugin
        (Application.PLUGIN_ID, IImageKey.IIMAGE_ALT);
        tool = new CombinedTemplateCreationEntry("轴平移运动", "创建轴平移运动", ShiftModel.class, new SimpleFactory(
        		ShiftModel.class), image, image);//
        entries.add(tool);
        
        image = AbstractUIPlugin.imageDescriptorFromPlugin
        (Application.PLUGIN_ID, IImageKey.IIMAGE_CALL); 
        tool = new CombinedTemplateCreationEntry("等待触发信号", "等待触发信号", InputModel.class, new SimpleFactory(
        		InputModel.class), image, image);
        entries.add(tool);
        
        image = AbstractUIPlugin.imageDescriptorFromPlugin
        (Application.PLUGIN_ID, IImageKey.IIMAGE_CALL);
        tool = new CombinedTemplateCreationEntry("输出信号", "输出信号", OutputModel.class, new SimpleFactory(
        		OutputModel.class), image, image);
        entries.add(tool);
        
        image = AbstractUIPlugin.imageDescriptorFromPlugin
        (Application.PLUGIN_ID, IImageKey.IIMAGE_FOR);
        tool = new CombinedTemplateCreationEntry("等待", "等待一段时间", WaitModel.class, new SimpleFactory(
        		WaitModel.class), image, image);//
        entries.add(tool);
        
        image = AbstractUIPlugin.imageDescriptorFromPlugin
        (Application.PLUGIN_ID, IImageKey.IIMAGE_IF);
        tool = new CombinedTemplateCreationEntry("结束任务", "结束任务", EndModel.class, new SimpleFactory(
        		EndModel.class), image, image);//
        entries.add(tool);
   
        drawer.addAll(entries);
        return drawer;
    }
    
    private static PaletteContainer createContainersDrawer() {

        PaletteDrawer drawer = new PaletteDrawer("复杂运动模块");

        ArrayList<Object> entries = new ArrayList<Object>();
        ImageDescriptor image;
        
        image = AbstractUIPlugin.imageDescriptorFromPlugin
                    (Application.PLUGIN_ID, IImageKey.IIMAGE_START);
        ToolEntry tool = new CombinedTemplateCreationEntry("循环", "创建循环模块", LoopModel.class, new SimpleFactory(
        		LoopModel.class), image, image);
        entries.add(tool);
        
        
        
        image = AbstractUIPlugin.imageDescriptorFromPlugin
        (Application.PLUGIN_ID, IImageKey.IIMAGE_LABEL);
        tool = new CombinedTemplateCreationEntry("条件判断", "创建条件判断模块", ConditionModel.class, new SimpleFactory(
        		ConditionModel.class), image, image);
        entries.add(tool);
        
        drawer.addAll(entries);
        return drawer;
    }
}
