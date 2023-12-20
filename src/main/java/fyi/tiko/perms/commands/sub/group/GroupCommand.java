package fyi.tiko.perms.commands.sub.group;

import fyi.tiko.perms.PermissionPlugin;
import fyi.tiko.perms.commands.sub.SubCommand;
import fyi.tiko.perms.group.PermissionGroup;
import fyi.tiko.perms.group.repository.GroupPermissionRepository;
import fyi.tiko.perms.user.language.UserTranslator;
import fyi.tiko.perms.utils.BukkitServer;
import fyi.tiko.perms.utils.LoadingActions;
import java.util.Collections;
import java.util.List;
import org.bukkit.command.CommandSender;

/**
 * Responsible for handling the group command.
 *
 * @author tiko
 */
public class GroupCommand extends SubCommand {

    private final PermissionPlugin plugin;
    private final GroupPermissionRepository groupRepository;

    /**
     * Constructs a new group command.
     *
     * @param plugin The plugin instance.
     */
    public GroupCommand(PermissionPlugin plugin) {
        this.plugin = plugin;

        groupRepository = plugin.groupRepository();
    }

    /**
     * Executes the group command.
     *
     * @param sender The sender of the command.
     * @param args   The arguments of the command.
     */
    @Override
    public void execute(CommandSender sender, String[] args) {
        var translator = plugin.userTranslator();

        plugin.runAsync(() -> {
            switch (args.length) {
                case 2 -> {
                    var name = args[0].toLowerCase();

                    switch (args[1].toLowerCase()) {
                        case "create" -> {
                            if (groupRepository.exists(name)) {
                                translator.sendTranslatedMessage(sender, "commands.group.already-exists", name);
                                return;
                            }

                            groupRepository.addGroup(name);
                            translator.sendTranslatedMessage(sender, "commands.group.created", name);
                        }

                        case "delete", "remove" -> {
                            if (!groupRepository.exists(name)) {
                                translator.sendTranslatedMessage(sender, "commands.group.not-existing", name);
                                return;
                            }

                            groupRepository.removeGroup(name);
                            translator.sendTranslatedMessage(sender, "commands.group.deleted", name);
                        }

                        case "info" -> {
                            if (!groupRepository.exists(name)) {
                                translator.sendTranslatedMessage(sender, "commands.group.not-existing", name);
                                return;
                            }

                            var group = groupRepository.byName(name);

                            translator.sendTranslatedMessage(sender, "commands.group.info",
                                group.name(),
                                group.isDefault(),
                                group.weight(),
                                group.prefix(),
                                group.suffix(),
                                String.join("§8, §f", group.permissions())
                            );
                        }

                        default -> sendHelpMessage(sender);
                    }
                }
                case 3 -> {
                    var name = args[0];

                    if (!groupRepository.exists(name)) {
                        translator.sendTranslatedMessage(sender, "commands.group.not-existing", name);
                        return;
                    }

                    var group = groupRepository.byName(name);

                    switch (args[1].toLowerCase()) {
                        case "add" -> {
                            var perm = args[2];

                            if (group.hasPermission(perm)) {
                                translator.sendTranslatedMessage(sender, "commands.group.already-has-permission", name, perm);
                                return;
                            }

                            group.addPermission(perm);
                            LoadingActions.updateGroup(plugin, group);

                            translator.sendTranslatedMessage(sender, "commands.group.added-permission", perm, name);
                        }

                        case "remove" -> {
                            var perm = args[2];

                            if (!group.hasPermission(perm)) {
                                translator.sendTranslatedMessage(sender, "commands.group.does-not-have-permission", name, perm);
                                return;
                            }

                            group.removePermission(perm);
                            LoadingActions.updateGroup(plugin, group);

                            translator.sendTranslatedMessage(sender, "commands.group.removed-permission", perm, name);
                        }

                        case "default" -> {
                            var isDefault = Boolean.parseBoolean(args[2]);

                            if (group.isDefault() == isDefault) {
                                translator.sendTranslatedMessage(sender, "commands.group.already-default-status", isDefault);
                                return;
                            }

                            group.updateDefault(isDefault);
                            LoadingActions.updateGroup(plugin, group);

                            translator.sendTranslatedMessage(sender, "commands.group.updated-default-status", name, isDefault);
                        }

                        case "suffix" -> {
                            var suffix = args[2];

                            if (group.suffix().equals(suffix)) {
                                translator.sendTranslatedMessage(sender, "commands.group.already-has-suffix", name, suffix);
                                return;
                            }

                            group.updateSuffix(suffix);
                            LoadingActions.updateGroup(plugin, group);

                            translator.sendTranslatedMessage(sender, "commands.group.updated-suffix", name, suffix);

                        }

                        case "prefix" -> {
                            var prefix = args[2];

                            if (group.prefix().equals(prefix)) {
                                translator.sendTranslatedMessage(sender, "commands.group.already-has-prefix", name, prefix);
                                return;
                            }

                            group.updatePrefix(prefix);
                            LoadingActions.updateGroup(plugin, group);

                            translator.sendTranslatedMessage(sender, "commands.group.updated-prefix", name, prefix);
                        }

                        case "weight" -> {
                            try {
                                var weight = Integer.parseInt(args[2]);

                                if (group.weight() == weight) {
                                    translator.sendTranslatedMessage(sender, "commands.group.already-has-weight", name, weight);
                                    return;
                                }

                                group.updateWeight(weight);
                                LoadingActions.updateGroup(plugin, group);

                                translator.sendTranslatedMessage(sender, "commands.group.updated-weight", name, weight);
                            } catch (NumberFormatException e) {
                                translator.sendTranslatedMessage(sender, "commands.group.invalid-weight", args[2]);
                            }
                        }
                        default -> sendHelpMessage(sender);
                    }
                }
                default -> sendHelpMessage(sender);
            }
        });
    }

    /**
     * Sends the help message to the sender.
     *
     * @param sender The sender of the command.
     */
    private void sendHelpMessage(CommandSender sender) {
        var translator = plugin.userTranslator();
        translator.sendTranslatedMessage(sender, "commands.group.help-message");
    }

    /**
     * @return Names that can be used to execute the sub command.
     */
    @Override
    public String[] names() {
        return new String[]{"group"};
    }

    /**
     * @return The permission required to execute the sub command.
     */
    @Override
    public String permission() {
        return "perms.command.group";
    }

    /**
     * Responsible for suggesting the next argument for the command.
     *
     * @param sender The sender of the command.
     * @param args   The arguments of the command.
     * @return A list of suggestions.
     */
    @Override
    public List<String> suggest(CommandSender sender, String[] args) {
        return switch (args.length) {
            case 0 -> groupRepository.groups().stream().map(PermissionGroup::name).toList();
            case 2 -> List.of("add", "remove", "info", "default", "suffix", "prefix", "weight", "create", "remove");
            case 3 -> switch (args[1].toLowerCase()) {
                case "add", "remove" -> BukkitServer.PERMISSIONS.stream().toList();
                case "default" -> List.of("true", "false");
                default -> Collections.emptyList();
            };
            default -> Collections.emptyList();
        };
    }
}
