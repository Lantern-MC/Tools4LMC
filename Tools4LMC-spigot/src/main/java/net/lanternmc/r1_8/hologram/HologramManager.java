package net.lanternmc.r1_8.hologram;

import java.util.List;

import org.bukkit.Location;

public class HologramManager {
	
	public Hologram createHologram(String header, List<String> lines, Location location) {
		return new Hologram(header, lines, location);
	}
	
}