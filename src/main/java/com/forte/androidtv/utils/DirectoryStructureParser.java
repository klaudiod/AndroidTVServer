package com.forte.androidtv.utils;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DirectoryStructureParser {
    public static final String CONTENT_PROPERTIES_FILE = "content.properties";
    public static final String CONTENT_FOLDER_PATH = "contentFolderPath";
    private Map<String, List<String>> folderStructureMap = new HashMap<String, List<String>>();
    static final Logger logger = Logger.getLogger(DirectoryStructureParser.class);

    public Map<String, List<String>> getContentDirectoryStructure() {
        File[] files = new File(this.getContentFolderPath()).listFiles();
        Map<String, List<String>> structure = this.parseDirectoryStructure(files);
        return structure;
    }

    private Map<String, List<String>> parseDirectoryStructure(File[] files) {
        for (File currentFile : files) {
            if (currentFile.isFile()) {
                ArrayList<String> fileList = new ArrayList<String>();
                fileList.add(currentFile.getName());
                String parentFolder = currentFile.getParentFile().getName();
                if (this.folderStructureMap.get(parentFolder) == null) {
                    this.folderStructureMap.put(parentFolder, fileList);
                } else if (this.folderStructureMap.get(parentFolder).isEmpty()) {
                    this.folderStructureMap.get(parentFolder).addAll(fileList);
                } else {
                    this.folderStructureMap.get(parentFolder).add(currentFile.getName());
                }
            }
            if (!currentFile.isDirectory()) continue;
            this.parseDirectoryStructure(currentFile.listFiles());
        }
        return this.folderStructureMap;
    }

    private String getContentFolderPath() {
        String path = new String();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties propertiesFile = new Properties();
        try {
            InputStream resourceStream = loader.getResourceAsStream("content.properties");
            Throwable throwable = null;
            try {
                propertiesFile.load(resourceStream);
                path = propertiesFile.getProperty("contentFolderPath");
            } catch (Throwable var6_9) {
                throwable = var6_9;
                //throw var6_9;
                Thread.currentThread().stop(var6_9);
            } finally {
                if (resourceStream != null) {
                    if (throwable != null) {
                        try {
                            resourceStream.close();
                        } catch (Throwable var6_8) {
                            throwable.addSuppressed(var6_8);
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
        return path;
    }
}