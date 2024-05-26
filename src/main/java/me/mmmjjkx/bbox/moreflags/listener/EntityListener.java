package me.mmmjjkx.bbox.moreflags.listener;

import me.mmmjjkx.bbox.moreflags.FlagNames;
import me.mmmjjkx.bbox.moreflags.config.Settings;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.api.flags.Flag;
import world.bentobox.bentobox.database.objects.Island;
import world.bentobox.bentobox.managers.FlagsManager;
import world.bentobox.bentobox.managers.IslandsManager;

import java.util.Optional;

public class EntityListener implements Listener {
    private final Settings settings;

    public EntityListener(Settings settings) {
        this.settings = settings;
    }

    @EventHandler
    public void explosion(EntityExplodeEvent e) {
        Entity en = e.getEntity();
        EntityType et = en.getType();
        switch (et) {
            case CREEPER -> {
                if (settings.getCreeperExplosions().isEnabled() && inIsland(en, FlagNames.CREEPER_EXPLOSION)) {
                    e.setCancelled(true);
                }
            }
            case WITHER -> {
                if (settings.getWitherExplosions().isEnabled() && inIsland(en, FlagNames.WITHER_EXPLOSION)) {
                    e.setCancelled(true);
                }
            }
        }
    }

    private boolean inIsland(Entity en, String id) {
        IslandsManager im = BentoBox.getInstance().getIslands();
        Optional<Island> island = im.getIslandAt(en.getLocation());
        if (island.isPresent()) {
            FlagsManager fm = BentoBox.getInstance().getFlagsManager();
            Optional<Flag> flag = fm.getFlag(id);
            if (flag.isPresent()) {
                return island.get().isAllowed(flag.get());
            }
        }
        return false;
    }
}
