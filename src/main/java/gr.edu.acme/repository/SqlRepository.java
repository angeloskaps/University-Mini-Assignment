package gr.edu.acme.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static java.lang.System.exit;

public class SqlRepository {


    private static final Logger logger = LoggerFactory.getLogger(SqlRepository.class);
    private static final Properties sqlCommands = new Properties();

    private SqlRepository() {
    }


    //load the sql.properties file once
    static {
        try (InputStream inputStream = DataSource.class.getClassLoader().getResourceAsStream(
                "sql.properties")) {
            if (inputStream == null) {
                logger.info("Sorry there is no sql.properties in that file,exit application");
                exit(-1);
            }
            sqlCommands.load(inputStream);
        } catch (IOException e) {
            logger.info("Sorry there is no sql.properties that file,exit application", e);
            exit(-1);
        }
    }

    public static String getCommand(String command) {
        return sqlCommands.getProperty(command);
    }


}
