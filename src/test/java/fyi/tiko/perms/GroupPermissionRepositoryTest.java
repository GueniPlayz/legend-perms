package fyi.tiko.perms;

import static org.junit.Assert.*;

import fyi.tiko.perms.database.DataSourceTest;
import fyi.tiko.perms.database.MockedLogger;
import fyi.tiko.perms.group.PermissionGroup;
import fyi.tiko.perms.group.repository.GroupPermissionRepository;
import java.util.Collections;
import org.junit.Test;

/**
 * Adds the tests for the group permission repository.
 *
 * @author tiko
 */
public class GroupPermissionRepositoryTest {

    @Test
    public void addGroup() {
        var sourceTest = new DataSourceTest();
        var groupPermissionRepository = new GroupPermissionRepository(MockedLogger.LOGGER, sourceTest.dataSource(), Collections.emptySet());

        groupPermissionRepository.addGroup("test");
        assertTrue(groupPermissionRepository.exists("test"));
    }

    @Test
    public void byName() {
        var sourceTest = new DataSourceTest();
        var groupPermissionRepository = new GroupPermissionRepository(MockedLogger.LOGGER, sourceTest.dataSource(), Collections.emptySet());

        assertEquals("test", groupPermissionRepository.byName("test").name());
    }

    @Test
    public void saveGroup() {
        var sourceTest = new DataSourceTest();
        var groupPermissionRepository = new GroupPermissionRepository(MockedLogger.LOGGER, sourceTest.dataSource(), Collections.emptySet());

        groupPermissionRepository.saveGroup(new PermissionGroup("test", Collections.emptySet(), "", "", 0, false));
        assertEquals(0, groupPermissionRepository.weight("test"));
    }

    @Test
    public void exists() {
        var sourceTest = new DataSourceTest();
        var groupPermissionRepository = new GroupPermissionRepository(MockedLogger.LOGGER, sourceTest.dataSource(), Collections.emptySet());

        assertTrue(groupPermissionRepository.exists("test"));
    }

    @Test
    public void groups() {
        var sourceTest = new DataSourceTest();
        var groupPermissionRepository = new GroupPermissionRepository(MockedLogger.LOGGER, sourceTest.dataSource(), Collections.emptySet());

        assertTrue(0 < groupPermissionRepository.groups().size());
    }

    @Test
    public void addPermission() {
        var sourceTest = new DataSourceTest();
        var groupPermissionRepository = new GroupPermissionRepository(MockedLogger.LOGGER, sourceTest.dataSource(), Collections.emptySet());

        groupPermissionRepository.addPermission("test", "test");
        assertTrue(groupPermissionRepository.permissions("test").contains("test"));
    }

    @Test
    public void weight() {
        var sourceTest = new DataSourceTest();
        var groupPermissionRepository = new GroupPermissionRepository(MockedLogger.LOGGER, sourceTest.dataSource(), Collections.emptySet());

        assertEquals(0, groupPermissionRepository.weight("test"));
    }

    @Test
    public void permissions() {
        var sourceTest = new DataSourceTest();
        var groupPermissionRepository = new GroupPermissionRepository(MockedLogger.LOGGER, sourceTest.dataSource(), Collections.emptySet());

        groupPermissionRepository.addPermission("test", "test");
        assertTrue(0 < groupPermissionRepository.permissions("test").size());
    }

    public void removeGroup() {
        var sourceTest = new DataSourceTest();
        var groupPermissionRepository = new GroupPermissionRepository(MockedLogger.LOGGER, sourceTest.dataSource(), Collections.emptySet());

        groupPermissionRepository.removeGroup("test");
        assertFalse(groupPermissionRepository.exists("test"));
    }
}