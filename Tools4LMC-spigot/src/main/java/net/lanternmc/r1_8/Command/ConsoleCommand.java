package net.lanternmc.r1_8.Command;

import net.lanternmc.r1_8.Utils.bukkit.permission.PermissionWrapper;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * This class provides a skeletal implementation of the Command interface, to minimize the effort
 * required to implement this interface for console commands.
 *
 * @author TozyMC
 * @since 1.0
 */
public abstract class ConsoleCommand extends AbstractCommand {

  private static final CommandResult NOT_CONSOLE_COMMAND_RESULT;

  static {
    NOT_CONSOLE_COMMAND_RESULT = CommandResult.from(CommandResult.Type.FAILURE, CommonMessage.getNotConsole());
  }


  public ConsoleCommand(@Nullable Command root, @NotNull String name,
      @NotNull List<String> aliases) {
    super(root, name, aliases);
  }

  public ConsoleCommand(@Nullable Command root, @NotNull String name,
      @NotNull String... aliases) {
    super(root, name, aliases);
  }

  public ConsoleCommand(@NotNull Command root, @NotNull String name) {
    super(root, name);
  }

  public ConsoleCommand(@NotNull String name) {
    super(name);
  }

  public ConsoleCommand(@NotNull String name, @NotNull String... aliases) {
    super(name, aliases);
  }

  public ConsoleCommand(@NotNull String name, @NotNull List<String> aliases) {
    super(name, aliases);
  }


  @NotNull
  @Override
  public abstract CommandResult onConsoleCommand(@NotNull ConsoleCommandSender console,
      @NotNull String[] params);

  @NotNull
  @Override
  public abstract TabResult onConsoleTab(@NotNull ConsoleCommandSender console,
      @NotNull String[] params);

  /**
   * Executes given command, returns the results.
   *
   * <p>Default implementation to prevent player from executing console commands.
   *
   * @param player Player executed command
   * @param params Passed command parameters
   * @return An failure result with NotConsole message.
   */
  @NotNull
  @Override
  public CommandResult onCommand(@NotNull Player player, @NotNull String[] params) {
    return NOT_CONSOLE_COMMAND_RESULT;
  }

  /**
   * Requests a list of possible completions for a command parameters.
   *
   * <p>Default implementation to prevent player from performing console completions for a
   * command parameter.
   *
   * @param player Player executed command
   * @param params The parameters pass to the to the command, including final partial parameter to
   *               be completed and command label
   * @return An empty tab result.
   */
  @NotNull
  @Override
  public TabResult onTab(@NotNull Player player, @NotNull String[] params) {
    return TabResult.empty();
  }

  @NotNull
  @Override
  public PermissionWrapper getPermission() {
    return PermissionWrapper.of("");
  }
}
