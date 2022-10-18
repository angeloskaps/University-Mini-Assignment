package gr.edu.acme.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import static java.lang.System.exit;

public class DataSource {


    private static final Logger logger = LoggerFactory.getLogger(DataSource.class);

    private static final String DB_CONNECTION_URL_FILE_MODE = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";

    private static final String DB_USERNAME = "thanos";
    private static final String DB_PASSWORD = "pass1311";
    private static Properties sqlCommands = new Properties();
    private static final HikariDataSource hikariDataSource;

    //    Singleton object because we need only one and restrict creation of more
    private DataSource() {

    }

    //    create a pool once, configure it as assignment dictates
    static {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(DB_CONNECTION_URL_FILE_MODE);
        hikariConfig.setUsername(DB_USERNAME);
        hikariConfig.setPassword(DB_PASSWORD);
//      The number of connections in the connection pool should be able to get up to 10
        hikariConfig.setMaximumPoolSize(10);
//        The number of ready-to-use connections in the connection pool should be at least 2.
        hikariConfig.setMinimumIdle(2);
//      A connection should close if nobody uses it for 5 minutes, 5 min - 300.000 ms
        hikariConfig.setConnectionTimeout(300000);
        hikariConfig.setAutoCommit(true);
//      Add Properties to config
        hikariConfig.addDataSourceProperty("MaximumPoolSize", "true");
        hikariConfig.addDataSourceProperty("MinimumIdl", "250");
        hikariConfig.addDataSourceProperty("IdleTimeout", "2");
        hikariConfig.addDataSourceProperty("useServerPrepStmts", "true");
        hikariDataSource = new HikariDataSource(hikariConfig);
    }


    public static Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }

    private static void loadDatabaseDriver() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        logger.info("JDBC Server is up and running");
    }
//
//    private static void dropTables() {
//
//        List<String> dropStatements = null;
//        List.of(
//                sqlCommands.getProperty("drop.table.enrollment"),
//                sqlCommands.getProperty("drop.table.unit"),
//                sqlCommands.getProperty("drop.table.student"),
//                sqlCommands.getProperty("drop.table.department")
//                sqlCommands.getProperty("drop.table.university")).forEach(property -> SqlRepository.executeCommands(property));
//
//    }

    private static void createFirstTables(Connection connection, Properties sqlCommands) {
        //@formatter:off
        List<String> createStatements = List.of(
                sqlCommands.getProperty("create.table.university"),
                sqlCommands.getProperty("create.table.department"),
                sqlCommands.getProperty("create.table.student"),
                sqlCommands.getProperty("create.table.unit"),
                sqlCommands.getProperty("create.table.enrollment")
        );

        List<String> existStatements = List.of(
                sqlCommands.getProperty("exist.table.university"),
                sqlCommands.getProperty("exist.table.department"),
                sqlCommands.getProperty("exist.table.student"),
                sqlCommands.getProperty("exist.table.unit"),
                sqlCommands.getProperty("exist.table.enrollment")
        );
        //@formatter:on

        try (Statement statement = connection.createStatement()) {
            for (int i = 0; i < createStatements.size(); i++) {
                if (statement.executeUpdate(existStatements.get(i)) == 0) {
                    int result = statement.executeUpdate(createStatements.get(i));
                    logger.info("Create Database Object command was successful with result {}.", result);
                } else {
                    logger.info("Database Object already exists. Query: '{}'", createStatements.get(i));
                }
            }
        } catch (SQLException e) {
            logger.error("Error while creating a table.", e);
            exit(-1);
        }
    }

}




