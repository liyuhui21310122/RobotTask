package com.example.model;
import java.util.List;
import org.dom4j.Element;

public interface IXmlModel {
    public void initModel();//xmlHeadºÍxmlModel
    public void doSaveXML();
    public void rebuildFromXml(Element e, List<Object>err);
    
}
