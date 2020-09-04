package me.authorname.commands;

import me.authorname.ParticleNativeAPI_Test;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class CommandTestSpigot implements CommandExecutor {
    private ParticleNativeAPI_Test plugin;

    public CommandTestSpigot(ParticleNativeAPI_Test plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command is designed for players!");
            return true;
        }
        final Player p = (Player) sender;

        if (!p.isOp()) {
            sender.sendMessage(ChatColor.RED + "This is command for OP users only!");
            return true;
        }

        if (args.length != 1) {
            p.sendMessage(ChatColor.RED + "Wrong syntax, use /pspigot <number>");
            return true;
        }

        final int amount;
        try {
            amount = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            p.sendMessage(ChatColor.RED + "Its not a number!");
            return true;
        }

        Random random = new Random();
        Location loc = p.getLocation().subtract(3D, 3D, 3D);
        Location randomLoc = p.getLocation();
        long start, end;

        start = System.nanoTime();
        for (int i = 0; i < amount; ++i) {
            randomLoc.setX(loc.getX() + random.nextDouble() * 6D);
            randomLoc.setY(loc.getY() + random.nextDouble() * 6D);
            randomLoc.setZ(loc.getZ() + random.nextDouble() * 6D);

            p.spawnParticle(Particle.FLAME, randomLoc, 1, 0D, 0D, 0D, 0D);
        }
        end = System.nanoTime();

        p.sendMessage(ChatColor.GREEN + "asdf.Test of " + amount + " particles (Spigot API): " + ((end - start) / 1000000D) + " ms");

        return true;
    }

}
