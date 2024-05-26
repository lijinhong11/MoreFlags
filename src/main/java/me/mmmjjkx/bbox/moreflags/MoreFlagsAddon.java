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

        registerFlag(convertToFlag(FlagNames.CREEPER_EXPLOSION, Material.CREEPER_HEAD, settings.getCreeperExplosions(), entityListener));
        registerFlag(convertToFlag(FlagNames.WITHER_EXPLOSION, Material.WITHER_SKELETON_SKULL, settings.getWitherExplosions(), entityListener));
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

    private Flag convertToFlag(String id, Material icon, FlagSet flagSet, Listener listener) {
        Flag.Builder builder = new Flag.Builder(id, icon);
        return builder.addon(this)
                .defaultSetting(flagSet.getDefaultValue())
                .listener(listener)
                .type(Flag.Type.SETTING)
                .cooldown(flagSet.getChangeCooldown())
                .build();
    }
}
