/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author hari
 */
public class ApplicationProperties {

    private static ApplicationProperties INSTANCE;
    private Properties properties;

    private ApplicationProperties() {
    }

    public static ApplicationProperties getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ApplicationProperties();
            INSTANCE.loadProperties();
        }
        return INSTANCE;
    }

    private void loadProperties() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException ex) {
            Logger.getLogger(ApplicationProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getMySqlSchemaName() {
        return properties.getProperty("db.mysql.schema");
    }

    public String getMySqlUserName() {
        return properties.getProperty("db.mysql.user");
    }

    public String getMySqlPassword() {
        return properties.getProperty("db.mysql.password");
    }
}
