package net.lanternmc.r1_8.Command;

import net.lanternmc.r1_8.Utils.Lists;
import net.lanternmc.r1_8.Utils.Preconditions;
import org.bukkit.command.PluginCommand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides a skeletal implementation of the Command interface.
 *
 * @author TozyMC
 * @see CombinedCommand
 * @see PlayerCommand
 * @see ConsoleCommand
 * @since 1.0
 */
public abstract class AbstractCommand implements Command {

  protected final Command root;
  protected final String name;
  private final List<String> aliases;

  /**
   * A command with full specific {@code root}, {@code name}, {@code aliases}.
   *
   * <p>If {@code root} is null, this command will be parent which registered as bukkit
   * command.</p>
   *
   * @param root    Root of this command
   * @param name    Name of this command
   * @param aliases List of aliases
   */
  public AbstractCommand(@Nullable Command root, @NotNull String name,
      @NotNull List<String> aliases) {
    this.root = root;
    this.name = name;
    this.aliases = Lists.addAll(aliases, name);
  }

  /**
   * A command with full specific {@code root}, {@code name}, {@code aliases}.
   *
   * @param root    Parent of this command
   * @param name    Name of this command
   * @param aliases Array of aliases
   * @see #AbstractCommand(Command, String, List)
   */
  public AbstractCommand(@Nullable Command root, @NotNull String name,
      @NotNull String... aliases) {
    this(root, name, Lists.newArrayList(aliases));
  }

  /**
   * A command without the specified {@code aliases}.
   *
   * @param root Parent of this command
   * @param name Name of this command
   * @see #AbstractCommand(Command, String, List)
   */
  public AbstractCommand(@NotNull Command root, @NotNull String name) {
    this(root, name, new ArrayList<>());
  }

  /**
   * Defaults {@code root} to null, create a parent-command instance without {@code aliases}.
   *
   * @param name Name of this command
   */
  public AbstractCommand(@NotNull String name) {
    this(null, name, new ArrayList<>());
  }

  /**
   * Defaults {@code root} to null, create a parent-command instance.
   *
   * @param name    Name of this command
   * @param aliases Array of aliases
   */
  public AbstractCommand(@NotNull String name, @NotNull String... aliases) {
    this(null, name, aliases);
  }

  /**
   * Defaults {@code root} to null, create a parent-command instance.
   *
   * @param name    Name of this command
   * @param aliases List of aliases
   */
  public AbstractCommand(@NotNull String name, @NotNull List<String> aliases) {
    this(null, name, aliases);
  }

  @NotNull
  public static PluginCommand asPluginCommandCopy(@NotNull Command command,
      @NotNull CommandController controller) {
    PluginCommand plCmd = Reflections.newPluginCommand(command.getName(), controller.getPlugin());
    Preconditions.checkNotNull(plCmd);

    plCmd.setDescription(command.getDescription());
    plCmd.setUsage(command.getSyntax());
    plCmd.setAliases(command.getAliases());

    plCmd.setExecutor(controller.getCommandHandler());
    plCmd.setTabCompleter(controller.getTabHandler());

    return plCmd;
  }

  @Override
  public @Nullable
  Command getParent() {
    return getRoot();
  }

  @Nullable
  @Override
  public Command getRoot() {
    return root;
  }

  @Override
  public @NotNull
  String getName() {
    return name;
  }

  @Override
  public @NotNull
  List<String> getAliases() {
    return aliases;
  }

  @Override
  public String toString() {
    StringBuilder strBuilder = new StringBuilder(getClass().getSimpleName());
    strBuilder.append('(');
    if (root != null) {
      strBuilder.append("root=");
      strBuilder.append(root.getName());
      strBuilder.append(',');
    }
    strBuilder.append("name=");
    strBuilder.append(name);
    strBuilder.append(')');
    return strBuilder.toString();
  }
}
