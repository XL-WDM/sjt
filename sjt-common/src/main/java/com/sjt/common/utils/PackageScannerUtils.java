package com.sjt.common.utils;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author: yilan.hu
 * @data: 2019/6/19
 */
public class PackageScannerUtils {

    private final static String PACKAGE_JAR = "jar";
    private final static String PACKAGE_FILE = "file";
    private final static String FILE_CLASS = ".class";

    /**
     * 包扫描
     * @param packagePath 目标包
     * @param annotations 包含的注解
     * @param superClass 继承的类
     * @return
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static List<Class> scanPackage(String packagePath, Class[] annotations, Class... superClass)
            throws ClassNotFoundException, IOException {
        if (packagePath == null) {
            return new ArrayList<>();
        }

        // 获取根目录绝对路径
        URL url = new PackageScannerUtils().getClass().getResource("/" + packagePath.replaceAll("\\." ,"/"));

        if (url == null) {
            return new ArrayList<>();
        }

        if (PACKAGE_FILE.equals(url.getProtocol())) {
            return scanPackageFile(packagePath, annotations, superClass);
        } else if (PACKAGE_JAR.equals(url.getProtocol())) {
            return scanPackageJar(url, annotations, superClass);
        }
        return new ArrayList<>();
    }

    /**
     *
     * @param packagePath
     * @param annotations
     * @param superClass
     * @return
     * @throws ClassNotFoundException
     */
    private static List<Class> scanPackageFile(String packagePath, Class[] annotations, Class... superClass)
            throws ClassNotFoundException {
        List<Class> result = new ArrayList<>();

        URL url = new PackageScannerUtils().getClass().getClassLoader().getResource(packagePath.replaceAll("\\." ,"/"));
        String filePath = url.getFile();

        File file = new File(filePath);
        File[] files = file.listFiles();
        if (files != null && files.length > 0) {
            for (File f : files) {
                String fileName = packagePath.concat(".").concat(f.getName());
                // 为目录时递归
                if (f.isDirectory()) {
                    result.addAll(scanPackageFile(fileName, annotations, superClass));
                    continue;
                }

                // 不是.class的文件不做处理
                if (!fileName.endsWith(FILE_CLASS)) {
                    continue;
                }

                String className = fileName.replace(FILE_CLASS, "");
                Class<?> clazz = Class.forName(className);

                if (annotations != null && annotations.length > 0) {
                    boolean flag = true;
                    for (Class annotation : annotations) {
                        if (clazz.isAnnotationPresent(annotation)) {
                            flag = false;
                        }
                    }
                    if (flag) {
                        continue;
                    }
                }

                if (superClass != null && superClass.length > 0) {
                    boolean flag = true;
                    for (Class superClazz : superClass) {
                        if (clazz.getSuperclass() == superClazz) {
                            flag = false;
                        }
                    }
                    if (flag) {
                        continue;
                    }
                }

                result.add(clazz);
            }
        }

        return result;
    }

    /**
     * 扫描
     * @param url
     * @param annotations
     * @param superClass
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private static List<Class> scanPackageJar(URL url, Class[] annotations, Class... superClass) throws IOException, ClassNotFoundException {
        List<Class> result = new ArrayList<>();

        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
        JarFile jarFile = jarURLConnection.getJarFile();
        Enumeration<JarEntry> jarEntrys = jarFile.entries();
        while (jarEntrys.hasMoreElements()) {
            JarEntry jarEntry = jarEntrys.nextElement();

            // 获取文件名
            String jarName = jarEntry.getName();
            if (!jarName.endsWith(FILE_CLASS)) {
                continue;
            }

            String className = jarName.replace(FILE_CLASS, "").replaceAll("/", ".");

            Class<?> clazz = Class.forName(className);

            if (annotations != null && annotations.length > 0) {
                boolean flag = true;
                for (Class annotation : annotations) {
                    if (clazz.isAnnotationPresent(annotation)) {
                        flag = false;
                    }
                }
                if (flag) {
                    continue;
                }
            }

            if (superClass != null && superClass.length > 0) {
                boolean flag = true;
                for (Class superClazz : superClass) {
                    if (clazz.getSuperclass() == superClazz) {
                        flag = false;
                    }
                }
                if (flag) {
                    continue;
                }
            }

            result.add(clazz);
        }

        return result;
    }
}
