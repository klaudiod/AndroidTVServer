package com.forte.androidtv.utils;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class FilePathConverter {
    private static final String SERVER_URL = "serverURL";
    static final Logger logger = Logger.getLogger(FilePathConverter.class);

    public static String convertToURL(String filepath) {
        StringBuilder stringBuilder = new StringBuilder();
        if (filepath.indexOf(".rss") >= 0 || filepath.indexOf(".weather") >= 0 || filepath.indexOf(".stock") >= 0 || filepath.indexOf(".m3u8") >= 0 || filepath.indexOf("http://") >= 0) {
            stringBuilder.append(filepath);
        } else {
            stringBuilder.append(FilePathConverter.getServerUrl()).append(filepath);
        }
        return stringBuilder.toString();
    }

    private static String getServerUrl() {
        String serverURL = new String();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties propertiesFile = new Properties();
        try {
            InputStream resourceStream = loader.getResourceAsStream("content.properties");
            Throwable throwable = null;
            try {
                propertiesFile.load(resourceStream);
                serverURL = propertiesFile.getProperty("serverURL");
            } catch (Throwable var5_8) {
                throwable = var5_8;
                //throw var5_8;
                Thread.currentThread().stop(var5_8);
            } finally {
                if (resourceStream != null) {
                    if (throwable != null) {
                        try {
                            resourceStream.close();
                        } catch (Throwable var5_7) {
                            throwable.addSuppressed(var5_7);
                        }
                    } else {
                        resourceStream.close();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            logger.error((Object) "File <content.properties> for not found.", (Throwable) e);
        } catch (IOException e) {
            logger.error((Object) "IOException on reading server info from file", (Throwable) e);
        }
        return serverURL;
    }
}