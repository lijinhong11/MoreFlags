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

        settings = new Config<>(this, Settings.class).loadConfigObject();

        Settings save = new Settings();
        new Config<>(this, Settings.class).saveConfigObject(save);

        EntityListener entityListener = new EntityListener(settings);

        registerListener(entityListener);
        registerFlags(entityListener);
    }
    
    private void registerFlags(EntityListener entityListener) {
        registerFlagSet(FlagNames.CREEPER_EXPLOSION, Material.CREEPER_HEAD, settings.getCreeperExplosions(), entityListener);
        registerFlagSet(FlagNames.WITHER_EXPLOSION, Material.WITHER_SKELETON_SKULL, settings.getWitherExplosions(), entityListener);
        registerFlagSet(FlagNames.PHANTOM_SPAWNING, Material.PHANTOM_SPAWN_EGG, settings.getPhantomSpawning(), entityListener);
    }

    @Override
    public void onReload() {
        super.onReload();

        settings = new Config<>(this, Settings.class).loadConfigObject();
        Settings save = new Settings();
        new Config<>(this, Settings.class).saveConfigObject(save);
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
                    .defaultSetting(flagSet.getDefaultValue())
                    .cooldown(flagSet.getChangeCooldown())
                    .build();
            registerFlag(flag);
        }
    }
}
