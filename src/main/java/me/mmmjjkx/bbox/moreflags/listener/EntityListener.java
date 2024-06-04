package me.mmmjjkx.bbox.moreflags.listener;

import lombok.ToString;
import me.mmmjjkx.bbox.moreflags.FlagNames;
import me.mmmjjkx.bbox.moreflags.config.Settings;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.api.flags.Flag;
import world.bentobox.bentobox.database.objects.Island;
import world.bentobox.bentobox.managers.FlagsManager;
import world.bentobox.bentobox.managers.IslandsManager;

import java.util.Optional;

@ToString
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
                if (settings.getCreeperExplosions().isEnabled() && !inIsland(en, FlagNames.CREEPER_EXPLOSION, true)) {
                    e.setCancelled(true);
                }
            }
            case WITHER -> {
                if (settings.getWitherExplosions().isEnabled() && !inIsland(en, FlagNames.WITHER_EXPLOSION, true)) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void spawn(EntitySpawnEvent e) {
        Entity en = e.getEntity();
        EntityType et = en.getType();
        if (et == EntityType.PHANTOM) {
            if (settings.getPhantomSpawning().isEnabled() && !inIsland(en, FlagNames.PHANTOM_SPAWNING, true)) {
                e.setCancelled(true);
            }
        }
    }

    private boolean inIsland(Entity en, String id, boolean destExpression) {
        IslandsManager im = BentoBox.getInstance().getIslands();
        Optional<Island> island = im.getIslandAt(en.getLocation());
        FlagsManager fm = BentoBox.getInstance().getFlagsManager();
        Optional<Flag> flag = fm.getFlag(id);
        if (flag.isPresent()) {
            Flag f = flag.get();
            return island.map(value -> value.isAllowed(f) == destExpression).orElseGet(() -> f.isSetForWorld(en.getWorld()));
        }
        return destExpression;
    }
}
