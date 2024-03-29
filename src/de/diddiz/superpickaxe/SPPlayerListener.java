package de.diddiz.superpickaxe;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerListener;

public class SPPlayerListener extends PlayerListener
{
	private final Superpickaxe sp;

	SPPlayerListener(Superpickaxe sp) {
		this.sp = sp;
	}

	@Override
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
		if (!event.isCancelled()) {
			final String msg = event.getMessage().toLowerCase();
			if (msg.equals("/") || msg.equals("//") || msg.equals("/,") || msg.equals("/sp")) {
				event.setMessage("dummy");
				event.setCancelled(true);
				sp.getServer().dispatchCommand(event.getPlayer(), "spa");
			}
		}
	}

	@Override
	public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
		final Player player = event.getPlayer();
		if (sp.hasEnabled(player) && !sp.hasPermission(player, "superpickaxe.use"))
			sp.removePlayer(player);
	}
}
