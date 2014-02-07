package com.kingsandthings.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

	/*
	 * Returns a list of all the files within a directory.
	 */
    public static List<File> listFiles(String directoryName) {
    	
    	List<File> results = new ArrayList<File>();    	
    	
        File directory = new File(directoryName);
        
        File[] fList = directory.listFiles();
        for (File file : fList) {
        	
        	if (file.isDirectory()) {
        		// Recursively add files within sub-directories
        		results.addAll(listFiles(file.getAbsolutePath()));
        	} else {
        		results.add(file);
        	}
        	
        }
        
        return results;
        
    }

}
