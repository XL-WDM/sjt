package com.sjt.common.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

/**
 * XML工具类
 * @author: yilan.hu
 * @data: 2019/7/31
 */
public class XstreamUtils {

    /**
     * Object -> XML
     * @param o
     * @return
     */
    public static String toXml(Object o, String rootName) {
        if (o == null) {
            return null;
        }

        XStream xStream = new XStream(new DomDriver(null, new XmlFriendlyNameCoder("_-", "_")));
        xStream.alias(rootName == null || rootName.length() == 0 ? "ROOT" : rootName, o.getClass());
        return xStream.toXML(o);
    }
}
