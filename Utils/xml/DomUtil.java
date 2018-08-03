package blogs.waston;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 
 * @author Wangtao
 * @date   2017年6月10日
 */
public class DomUtil {
    
    /**
     * 从文件中获取
     * @param path
     * @param name
     * @return
     * @throws Exception
     */
    public static String getValueByName(String path, String name) throws Exception{
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(path));
        Element root = document.getRootElement();
        Element node = root.element(name);
        if(node == null)
            return null;
        return node.getTextTrim();
    }
    
    /**
     * 从文本字符串xml中获取
     * @param xml
     * @param name
     * @return
     * @throws Exception
     */
    public static String getValue(String xml, String name) throws Exception{
        Document document = DocumentHelper.parseText(xml);
        Element root = document.getRootElement();
        Element node = root.element(name);
        if(node == null) 
            return null;
        return node.getTextTrim();
    }
    
    /**
     * 将xml转成map
     * @param path 文件路径
     * @return
     * @throws Exception
     */
    public static Map<String, String> xmlToMapFromText(String path) throws Exception{
        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(path));
        Element root = document.getRootElement();
        Iterator<?> it = root.elementIterator();
        while(it.hasNext()) {
            Element ele = (Element) it.next();
            map.put(ele.getName(), ele.getTextTrim());
        }
        return map;
    }
    
    /**
     * 
     * @param xml字符串
     * @return
     * @throws Exception
     */
    public static Map<String, String> xmlToMapFromString(String xml) throws Exception{
        Map<String, String> map = new HashMap<String, String>();
        Document document = DocumentHelper.parseText(xml);
        Element root = document.getRootElement();
        Iterator<?> it = root.elementIterator();
        while(it.hasNext()) {
            Element ele = (Element) it.next();
            map.put(ele.getName(), ele.getTextTrim());
        }
        return map;
    }
}
