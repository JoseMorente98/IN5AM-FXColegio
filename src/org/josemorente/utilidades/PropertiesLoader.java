/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.utilidades;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Morente
 */
public class PropertiesLoader {
    private static PropertiesLoader instance;
    private Properties properties = new Properties();
    private HashMap<String, String> hashMap = new HashMap<>();

    private PropertiesLoader() {
    }

    public static PropertiesLoader getInstance() {
        if (instance == null) {
            instance = new PropertiesLoader();
        }
        return instance;
    }
    
    public HashMap<String, String> load(String propertyFile) {
        hashMap.clear();
        properties.clear();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream(propertyFile));
        } catch (IOException ex) {
            Logger.getLogger(PropertiesLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (String key : properties.stringPropertyNames()) {
            hashMap.put(key, properties.getProperty(key));
        }
        return hashMap;
    }
}