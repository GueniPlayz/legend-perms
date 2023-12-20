package fyi.tiko.perms.database;

import javax.sql.DataSource;

/**
 * @author tiko
 */
public class DataSourceTest {
    private final DataSource dataSource;

    public DataSourceTest() {
        // Change your credentials here
        var databaseProvider = DatabaseProvider.create(
            "localhost",
            "junit",
            "junit",
            "junit",
            3306
        );

        databaseProvider.connect();
        DatabaseSetup.executeQueries(MockedLogger.LOGGER, databaseProvider.dataSource());

        this.dataSource = databaseProvider.dataSource();
    }

    public DataSource dataSource() {
        return dataSource;
    }
}
