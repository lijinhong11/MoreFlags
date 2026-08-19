package me.mmmjjkx.bbox.moreflags.listener;

import java.util.Optional;
import me.mmmjjkx.bbox.moreflags.FlagNames;
import me.mmmjjkx.bbox.moreflags.config.Settings;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.projectiles.ProjectileSource;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.api.flags.Flag;
import world.bentobox.bentobox.database.objects.Island;
import world.bentobox.bentobox.managers.FlagsManager;
import world.bentobox.bentobox.managers.IslandsManager;

public class EntityListener implements Listener {
    private final Settings settings;

    public EntityListener(Settings settings) {
        this.settings = settings;
    }

    @EventHandler
    public void explosion(EntityExplodeEvent e) {
        Entity en = e.getEntity();
        if (en instanceof Fireball fireball) {
            if (fireball.getShooter() instanceof Ghast
                    && settings.getGhastFireball().isEnabled()
                    && !isAllowed(en, FlagNames.GHAST_FIREBALL)) {
                e.setCancelled(true);
            } else if (fireball.getShooter() instanceof Blaze
                    && settings.getBlazeFireball().isEnabled()
                    && !isAllowed(en, FlagNames.BLAZE_FIREBALL)) {
                e.setCancelled(true);
            }
            return;
        }

        EntityType et = en.getType();
        switch (et) {
            case CREEPER -> {
                if (settings.getCreeperExplosions().isEnabled() && !isAllowed(en, FlagNames.CREEPER_EXPLOSION)) {
                    e.setCancelled(true);
                }
            }
            case WITHER -> {
                if (settings.getWitherExplosions().isEnabled() && !isAllowed(en, FlagNames.WITHER_EXPLOSION)) {
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
            if (settings.getPhantomSpawning().isEnabled() && !isAllowed(en, FlagNames.PHANTOM_SPAWNING)) {
                e.setCancelled(true);
            }
        }

        if (et == EntityType.GLOW_SQUID) {
            if (settings.getGlowsquidSpawning().isEnabled() && !isAllowed(en, FlagNames.GLOWSQUID_SPAWNING)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void potionDrop(PotionSplashEvent e) {
        ProjectileSource source = e.getPotion().getShooter();
        if (source instanceof Witch witch) {
            if (settings.getWitchPotionThrowing().isEnabled() && !isAllowed(witch, FlagNames.WITCH_POTION_THROWING)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void windcharge(ProjectileLaunchEvent e) {
        Projectile entity = e.getEntity();
        if (entity instanceof WindCharge && e.getEntity().getShooter() instanceof Player p) {
            if (settings.getWindchargeLaunching().isEnabled() && !isAllowed(p, FlagNames.WINDCHARGE_LAUNCHING)) {
                e.setCancelled(true);
            }
        }
    }

    private boolean isAllowed(Entity en, String id) {
        IslandsManager im = BentoBox.getInstance().getIslands();
        Optional<Island> island = im.getIslandAt(en.getLocation());
        FlagsManager fm = BentoBox.getInstance().getFlagsManager();
        Optional<Flag> flag = fm.getFlag(id);

        if (flag.isPresent()) {
            Flag f = flag.get();
            return island.map(value -> value.isAllowed(f)).orElseGet(() -> f.isSetForWorld(en.getWorld()));
        }

        return true;
    }
}
