package me.mmmjjkx.bbox.moreflags;

import lombok.Getter;
import me.mmmjjkx.bbox.moreflags.config.FlagSet;
import me.mmmjjkx.bbox.moreflags.config.Settings;
import me.mmmjjkx.bbox.moreflags.listener.EntityListener;
import org.bukkit.Material;
import world.bentobox.bentobox.api.addons.Addon;
import world.bentobox.bentobox.api.configuration.Config;
import world.bentobox.bentobox.api.flags.Flag;

@Getter
public class MoreFlagsAddon extends Addon {
    private Settings settings;

    private EntityListener entityListener;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        saveConfig();

        this.settings = new Config<>(this, Settings.class).loadConfigObject();

        this.entityListener = new EntityListener(settings);

        registerFlags();

        getLogger().info("MoreFlags is enabled!");
    }

    private void registerFlags() {
        registerFlagSet(
                FlagNames.CREEPER_EXPLOSION,
                Material.CREEPER_HEAD,
                settings.getCreeperExplosions()
        );
        registerFlagSet(
                FlagNames.WITHER_EXPLOSION,
                Material.WITHER_SKELETON_SKULL,
                settings.getWitherExplosions()
        );
        registerFlagSet(
                FlagNames.PHANTOM_SPAWNING,
                Material.PHANTOM_SPAWN_EGG,
                settings.getPhantomSpawning()
        );
        registerFlagSet(
                FlagNames.WITCH_POTION_THROWING,
                Material.SPLASH_POTION,
                settings.getWitchPotionThrowing()
        );
        registerFlagSet(
                FlagNames.WINDCHARGE_LAUNCHING,
                Material.WIND_CHARGE,
                settings.getWindchargeLaunching()
        );
        registerFlagSet(
                FlagNames.GHAST_FIREBALL,
                Material.GHAST_SPAWN_EGG,
                settings.getGhastFireball()
        );
        registerFlagSet(
                FlagNames.BLAZE_FIREBALL,
                Material.BLAZE_SPAWN_EGG,
                settings.getBlazeFireball()
        );
    }

    @Override
    public void onReload() {
        super.onReload();
        saveConfig();

        settings = new Config<>(this, Settings.class).loadConfigObject();
        new Config<>(this, Settings.class).saveConfigObject(settings);
    }

    @Override
    public void onDisable() {
        getLogger().info("MoreFlags is disabled!");
    }

    private void registerFlagSet(String id, Material icon, FlagSet flagSet) {
        if (flagSet.isEnabled()) {
            Flag.Builder builder = new Flag.Builder(id, icon);
            Flag flag = builder.addon(this)
                    .mode(flagSet.getMode())
                    .listener(entityListener)
                    .type(flagSet.getType())
                    .cooldown(flagSet.getChangeCooldown())
                    .defaultSetting(flagSet.getDefaultValue())
                    .build();
            registerFlag(flag);
        }
    }
}
