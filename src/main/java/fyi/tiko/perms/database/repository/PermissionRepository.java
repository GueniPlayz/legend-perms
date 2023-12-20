package fyi.tiko.perms.database.repository;

import fyi.tiko.perms.PermissionPlugin;
import fyi.tiko.perms.database.holder.DataHolder;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

/**
 * The repository for database operations on the permissions tables.
 *
 * @author tiko
 */
public class PermissionRepository extends DataHolder {

    /**
     * Constructs a new {@link DataHolder} with the given {@link PermissionPlugin} and {@link DataSource}.
     *
     * @param plugin the plugin using this holder
     */
    public PermissionRepository(PermissionPlugin plugin) {
        super(plugin);
    }

    /**
     * Checks if the given permission exists in the database.
     *
     * @param permission the permission to check
     * @return true if the permission exists, false otherwise
     */
    public boolean exists(String permission) {
        try (var conn = conn(); var stmt = conn.prepareStatement("SELECT permission FROM permissions WHERE permission=?")) {
            stmt.setString(1, permission);

            var rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException exception) {
            logger().log(Level.WARNING, "Failed to check if permission exists", exception);
        }

        return false;
    }

    public Set<String> permissions() {
        var permissions = new HashSet<String>();

        try (var conn = conn(); var stmt = conn.prepareStatement("SELECT permission FROM permissions")) {
            var rs = stmt.executeQuery();

            while (rs.next()) {
                permissions.add(rs.getString("permission"));
            }

            return permissions;
        } catch (SQLException exception) {
            logger().log(Level.WARNING, "Failed to get permissions", exception);
        }

        return Collections.emptySet();
    }

    /**
     * Adds the given permission to the database.
     *
     * @param permission the permission to add
     */
    public void addPermission(String permission) {
        if (exists(permission)) {
            return;
        }

        try (var conn = conn(); var stmt = conn.prepareStatement("INSERT INTO permissions(permission) VALUES (?);")) {
            stmt.setString(1, permission);
            stmt.executeUpdate();
        } catch (SQLException exception) {
            logger().log(Level.WARNING, "Failed to add permission", exception);
        }
    }
}
