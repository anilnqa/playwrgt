package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertyFile {


    /**
     * This method return the value of given key present in property file
     */
    public static String getProperty(String Property,String filePath) {
        try {
            Properties prop =loadProperties(filePath);
            return prop.getProperty(Property);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * This method loads the the property file
     */
    public static Properties loadProperties(String filePath) throws IOException {
        Properties properties = null;
        InputStream inputStream = null;
        try {
            inputStream =    loadResource(filePath);;
            if (inputStream != null) {
                properties = new Properties();
                properties.load(inputStream);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return properties;
    }


    /**
     * This method returns the InputStream of given file
     */
    public static InputStream loadResource(String filePath) throws IOException {
        ClassLoader classLoader = ReadPropertyFile.class.getClassLoader();
        InputStream inputStream = null;
        if (classLoader != null) {
            inputStream = classLoader.getResourceAsStream(filePath);
        }
        if (inputStream == null) {
            classLoader = ClassLoader.getSystemClassLoader();
            if (classLoader != null) {
                inputStream = classLoader.getResourceAsStream(filePath);
            }
        }
        if (inputStream == null) {
            File file = new File(filePath);
            if (file.exists()) {
                inputStream = new FileInputStream(file);
            }
        }
        return inputStream;
    }
}
