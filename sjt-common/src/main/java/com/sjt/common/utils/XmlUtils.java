package com.sjt.common.utils;

import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Xml帮助类
 * @author huyilan
 */
public class XmlUtils {

	/**
	 * 解析微信发来的请求（XML）
	 * 
	 * @param request
	 * @return Map<String, String>
	 * @throws Exception
	 */
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>(16);

		// 从request中取得输入流
		InputStream inputStream = request.getInputStream();
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();

		// 遍历所有子节点
		for (Element e : elementList) {
			map.put(e.getName(), e.getText());
		}

		// 释放资源
		inputStream.close();
		inputStream = null;

		return map;
	}

	/**
	 * xml -> 对象
	 * @param xml
	 * @param objClass
	 * @param <T>
	 * @return
	 */
	public static <T> T toObject(String xml, Class<T> objClass) {
		return toObject(xml, objClass, true);
	}

	/**
	 * xml -> 对象
	 * @param xml
	 * @param objClass
	 * @param <T>
	 * @return
	 */
	public static <T> T toObject(String xml, Class<T> objClass, boolean strict) {
		Persister serializer = new Persister();

		try {
			return serializer.read(objClass, xml, strict);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 对象 -> xml
	 * @param obj
	 * @return
	 */
	public static String toString(Object obj) {
		Serializer serializer = new Persister();

		try (StringWriter output = new StringWriter()) {
			serializer.write(obj, output);

			return output.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * xml -> Map
	 * @param strXML
	 * @return
	 */
	public static Map<String, String> toMap(String strXML) {
		try (InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));) {

			Map<String, String> data = new HashMap(16);

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			org.w3c.dom.Document doc = documentBuilder.parse(stream);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getDocumentElement().getChildNodes();

			for(int idx = 0; idx < nodeList.getLength(); ++idx) {
				Node node = nodeList.item(idx);
				if (node.getNodeType() == 1) {
					org.w3c.dom.Element element = (org.w3c.dom.Element)node;
					data.put(element.getNodeName(), element.getTextContent());
				}
			}

			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
