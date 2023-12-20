package fyi.tiko.perms;

import fyi.tiko.perms.database.DataSourceTest;
import fyi.tiko.perms.database.MockedLogger;
import fyi.tiko.perms.database.repository.PermissionRepository;
import org.junit.Assert;
import org.junit.Test;

/**
 * Adds a test for the permission repository. If there are any mistakes please let me know as I am NOT familiar with Junit
 * and this is the first time I have used it.
 *
 * @author tiko
 */
public class PermissionRepositoryTest {

    @Test
    public void testAddPermission() {
        var sourceTest = new DataSourceTest();
        var permissionRepository = new PermissionRepository(MockedLogger.LOGGER, sourceTest.dataSource());

        permissionRepository.addPermission("test");
        Assert.assertTrue(permissionRepository.exists("test"));
    }

    @Test
    public void testExists() {
        var sourceTest = new DataSourceTest();
        var permissionRepository = new PermissionRepository(MockedLogger.LOGGER, sourceTest.dataSource());
        Assert.assertTrue(permissionRepository.exists("test"));
    }

    @Test
    public void testPermissions() {
        var sourceTest = new DataSourceTest();
        var permissionRepository = new PermissionRepository(MockedLogger.LOGGER, sourceTest.dataSource());

        Assert.assertTrue(0 < permissionRepository.permissions().size());
    }
}