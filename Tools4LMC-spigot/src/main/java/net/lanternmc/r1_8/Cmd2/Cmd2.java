package net.lanternmc.r1_8.Cmd2;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Cmd2 extends Command {
    protected Cmd2(String name) {
        super(name);
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        return false;
    }
}
