package me.mmmjjkx.bbox.moreflags;

import lombok.Getter;
import me.mmmjjkx.bbox.moreflags.config.FlagSet;
import me.mmmjjkx.bbox.moreflags.config.Settings;
import me.mmmjjkx.bbox.moreflags.listener.EntityListener;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import world.bentobox.bentobox.api.addons.Addon;
import world.bentobox.bentobox.api.configuration.Config;
import world.bentobox.bentobox.api.flags.Flag;

@Getter
public class MoreFlagsAddon extends Addon {
    private Settings settings;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        saveConfig();

        settings = new Config<>(this, Settings.class).loadConfigObject();

        new Config<>(this, Settings.class).saveConfigObject(settings);

        EntityListener entityListener = new EntityListener(settings);

        registerFlags(entityListener);
    }
    
    private void registerFlags(EntityListener entityListener) {
        registerFlagSet(FlagNames.CREEPER_EXPLOSION, Material.CREEPER_HEAD, settings.getCreeperExplosions(), entityListener);
        registerFlagSet(FlagNames.WITHER_EXPLOSION, Material.WITHER_SKELETON_SKULL, settings.getWitherExplosions(), entityListener);
        registerFlagSet(FlagNames.PHANTOM_SPAWNING, Material.PHANTOM_SPAWN_EGG, settings.getPhantomSpawning(), entityListener);
        registerFlagSet(FlagNames.WITCH_POTION_THROWING, Material.SPLASH_POTION, settings.getWitchPotionThrowing(), entityListener);
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
    }

    private void registerFlagSet(String id, Material icon, FlagSet flagSet, Listener listener) {
        if (flagSet.isEnabled()) {
            Flag.Builder builder = new Flag.Builder(id, icon);
            Flag flag = builder.addon(this)
                    .mode(Flag.Mode.EXPERT)
                    .listener(listener)
                    .type(Flag.Type.SETTING)
                    .cooldown(flagSet.getChangeCooldown())
                    .defaultSetting(flagSet.getDefaultValue())
                    .build();
            registerFlag(flag);
        }
    }
}
