package fyi.tiko.perms;

import static org.junit.Assert.*;

import fyi.tiko.perms.database.DataSourceTest;
import fyi.tiko.perms.database.MockedLogger;
import fyi.tiko.perms.user.repository.UserRepository;
import java.util.Collections;
import java.util.UUID;
import org.junit.Test;

/**
 * Adds the tests for the user repository.
 *
 * @author tiko
 */
public class UserRepositoryTest {

    @Test
    public void byUuid() {
        var dataSourceTest = new DataSourceTest();
        var userRepository = new UserRepository(MockedLogger.LOGGER, dataSourceTest.dataSource(), Collections.emptySet());

        var uuid = UUID.randomUUID();
        userRepository.updateUser(uuid, "test");
        assertEquals("test", userRepository.byUuid(uuid));
    }

    @Test
    public void byName() {
        var dataSourceTest = new DataSourceTest();
        var userRepository = new UserRepository(MockedLogger.LOGGER, dataSourceTest.dataSource(), Collections.emptySet());

        var uuid = UUID.randomUUID();
        userRepository.addUser(uuid, "test" + uuid.toString().substring(0, 8));
        assertEquals(uuid, userRepository.byName("test" + uuid.toString().substring(0, 8)));
    }

    @Test
    public void addGroup() {
        var dataSourceTest = new DataSourceTest();
        var userRepository = new UserRepository(MockedLogger.LOGGER, dataSourceTest.dataSource(), Collections.emptySet());

        var uuid = UUID.randomUUID();
        userRepository.updateUser(uuid, "test");
        userRepository.addGroup(uuid, "test", -1);
        assertTrue(userRepository.isInGroup(uuid, "test"));
    }

    @Test
    public void groups() {
        var dataSourceTest = new DataSourceTest();
        var userRepository = new UserRepository(MockedLogger.LOGGER, dataSourceTest.dataSource(), Collections.emptySet());

        var uuid = UUID.randomUUID();
        userRepository.updateUser(uuid, "test");
        userRepository.addGroup(uuid, "test", -1);
        assertTrue(0 < userRepository.groups(uuid).size());
    }

    @Test
    public void isInGroup() {
        var dataSourceTest = new DataSourceTest();
        var userRepository = new UserRepository(MockedLogger.LOGGER, dataSourceTest.dataSource(), Collections.emptySet());

        var uuid = UUID.randomUUID();
        userRepository.updateUser(uuid, "test");
        userRepository.addGroup(uuid, "test", -1);
        assertTrue(userRepository.isInGroup(uuid, "test"));
    }

    @Test
    public void highestPermissionGroup() {
        var dataSourceTest = new DataSourceTest();
        var userRepository = new UserRepository(MockedLogger.LOGGER, dataSourceTest.dataSource(), Collections.emptySet());

        var uuid = UUID.randomUUID();
        userRepository.updateUser(uuid, "test");
        userRepository.addGroup(uuid, "test", -1);
        assertEquals("test", userRepository.highestPermissionGroup(uuid).name());
    }

    @Test
    public void removeGroup() {
        var dataSourceTest = new DataSourceTest();
        var userRepository = new UserRepository(MockedLogger.LOGGER, dataSourceTest.dataSource(), Collections.emptySet());

        var uuid = UUID.randomUUID();
        userRepository.updateUser(uuid, "test");
        userRepository.addGroup(uuid, "test", -1);
        userRepository.removeGroup(uuid, "test");
        assertFalse(userRepository.isInGroup(uuid, "test"));
    }

    @Test
    public void addPermission() {
        var dataSourceTest = new DataSourceTest();
        var userRepository = new UserRepository(MockedLogger.LOGGER, dataSourceTest.dataSource(), Collections.emptySet());

        var uuid = UUID.randomUUID();
        userRepository.updateUser(uuid, "test");
        userRepository.addPermission(uuid, "test");
        assertTrue(userRepository.hasPermission(uuid, "test"));
    }

    @Test
    public void hasPermission() {
        var dataSourceTest = new DataSourceTest();
        var userRepository = new UserRepository(MockedLogger.LOGGER, dataSourceTest.dataSource(), Collections.emptySet());

        var uuid = UUID.randomUUID();
        userRepository.updateUser(uuid, "test");
        userRepository.addPermission(uuid, "test");
        assertTrue(userRepository.hasPermission(uuid, "test"));
    }

    @Test
    public void updateUser() {
        var dataSourceTest = new DataSourceTest();
        var userRepository = new UserRepository(MockedLogger.LOGGER, dataSourceTest.dataSource(), Collections.emptySet());

        var uuid = UUID.randomUUID();
        userRepository.updateUser(uuid, "test");
        assertEquals("test", userRepository.byUuid(uuid));
    }
}