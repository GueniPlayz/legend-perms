package fyi.tiko.perms.database.holder;

import fyi.tiko.perms.PermissionPlugin;
import java.util.logging.Logger;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * This class is used to hold the connection to the database. It is used to prevent boilerplate code in the database classes. Copied and changed
 * from:
 * <a href="https://github.com/rainbowdashlabs/sql-util/blob/master/src/main/java/de/chojo/sqlutil/base/DataHolder.java">...</a>
 */
public class DataHolder {

    private final PermissionPlugin plugin;
    private final DataSource source;

    /**
     * Constructs a new {@link DataHolder} with the given {@link PermissionPlugin} and {@link DataSource}.
     *
     * @param plugin the plugin using this holder
     */
    public DataHolder(PermissionPlugin plugin) {
        this.plugin = plugin;
        this.source = plugin.databaseProvider().dataSource();
    }

    /**
     * The plugin that uses this holder.
     *
     * @return the plugin that uses this holder
     */
    protected PermissionPlugin plugin() {
        return plugin;
    }

    /**
     * The {@link Logger} of the plugin that uses this holder.
     *
     * @return the logger of the plugin that uses this holder
     */
    protected Logger logger() {
        return plugin.getLogger();
    }

    /**
     * Attempts to establish a connection with the data source that this {@link DataHolder} contains.
     *
     * @return a new connection from the data sources
     * @throws SQLException                 if a database access error occurs
     * @throws java.sql.SQLTimeoutException when the driver has determined that the timeout value specified by the {@code setLoginTimeout} method has
     *                                      been exceeded and has at least tried to cancel the current database connection attempt
     */
    protected Connection conn() throws SQLException {
        return source.getConnection();
    }
}
