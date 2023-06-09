package net.lanternmc.r1_8.Command;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * This class provides a skeletal implementation of the Command interface, combines player command
 * handler and console command handler into one handler.
 *
 * <p>It means only need to implement two methods, {@link #onCommand(CommandSender, String[])} and
 * {@link #onTab(CommandSender, String[])}. When the player or console executes this command, the
 * implemented method executes and returns its results.</p>
 *
 * @author TozyMC
 * @since 1.0
 */
public abstract class CombinedCommand extends AbstractCommand {

  public CombinedCommand(@Nullable Command root, @NotNull String name,
      @NotNull List<String> aliases) {
    super(root, name, aliases);
  }

  public CombinedCommand(@Nullable Command root, @NotNull String name,
      @NotNull String... aliases) {
    super(root, name, aliases);
  }

  public CombinedCommand(@NotNull Command root, @NotNull String name) {
    super(root, name);
  }

  public CombinedCommand(@NotNull String name) {
    super(name);
  }

  public CombinedCommand(@NotNull String name, @NotNull String... aliases) {
    super(name, aliases);
  }

  public CombinedCommand(@NotNull String name,
      @NotNull List<String> aliases) {
    super(name, aliases);
  }

  /**
   * Executes given command, returns the results.
   *
   * @param sender Sources of the command
   * @param params Passed command parameters
   * @return Command's result was executed
   * @see #onCommand(Player, String[])
   * @see #onConsoleCommand(ConsoleCommandSender, String[])
   */
  @NotNull
  public abstract CommandResult onCommand(@NotNull CommandSender sender, @NotNull String[] params);

  /**
   * Requests a list of possible completions for a command parameters.
   *
   * @param sender Sources of the command
   * @param params The parameters pass to the to the command, including final partial parameter to
   *               be completed and command label
   * @return A List of possible completions for the final argument, or an empty list to default to
   * the command executor
   * @see #onTab(Player, String[])
   * @see #onConsoleTab(ConsoleCommandSender, String[])
   */
  @NotNull
  public abstract TabResult onTab(@NotNull CommandSender sender, @NotNull String[] params);

  @Override
  public @NotNull
  CommandResult onCommand(@NotNull Player player, @NotNull String[] params) {
    return onCommand((CommandSender) player, params);
  }

  @Override
  public @NotNull
  CommandResult onConsoleCommand(@NotNull ConsoleCommandSender console,
      @NotNull String[] params) {
    return onCommand(console, params);
  }

  @Override
  public @NotNull
  TabResult onTab(@NotNull Player player, @NotNull String[] params) {
    return onTab(((CommandSender) player), params);
  }

  @Override
  public @NotNull
  TabResult onConsoleTab(@NotNull ConsoleCommandSender console,
      @NotNull String[] params) {
    return onTab(console, params);
  }
}
