package me.mmmjjkx.bbox.moreflags.config;

import lombok.Getter;
import lombok.Setter;
import me.mmmjjkx.bbox.moreflags.FlagNames;
import world.bentobox.bentobox.api.configuration.ConfigComment;
import world.bentobox.bentobox.api.configuration.ConfigEntry;
import world.bentobox.bentobox.api.configuration.ConfigObject;
import world.bentobox.bentobox.api.configuration.StoreAt;
import world.bentobox.bentobox.database.objects.adapters.Adapter;

@StoreAt(filename = "config.yml", path = "addons/MoreFlags")
@Getter
@Setter
public class Settings implements ConfigObject {
    @ConfigEntry(path = FlagNames.CREEPER_EXPLOSION)
    @ConfigComment("It can control creeper explosions.")
    @Adapter(FlagSetSerializer.class)
    private FlagSet creeperExplosions;

    @ConfigEntry(path = FlagNames.WITHER_EXPLOSION)
    @ConfigComment("It can control wither explosions.")
    @Adapter(FlagSetSerializer.class)
    private FlagSet witherExplosions;

    @ConfigEntry(path = FlagNames.PHANTOM_SPAWNING)
    @ConfigComment("It can control phantom spawning.")
    @Adapter(FlagSetSerializer.class)
    private FlagSet phantomSpawning;

    @ConfigEntry(path = FlagNames.WITCH_POTION_THROWING, since = "1.1.0")
    @ConfigComment("It can control witch potion throwing.")
    @Adapter(FlagSetSerializer.class)
    private FlagSet witchPotionThrowing;

    @ConfigEntry(path = FlagNames.WINDCHARGE_LAUNCHING, since = "2.0.0")
    @ConfigComment("It can control windcharge launching")
    @Adapter(FlagSetSerializer.class)
    private FlagSet windchargeLaunching;

    @ConfigEntry(path = FlagNames.GHAST_FIREBALL, since = "2.0.0")
    @ConfigComment("It can control ghast fireballs.")
    @Adapter(FlagSetSerializer.class)
    private FlagSet ghastFireball;

    @ConfigEntry(path = FlagNames.BLAZE_FIREBALL, since = "2.0.0")
    @ConfigComment("It can control blaze fireballs.")
    @Adapter(FlagSetSerializer.class)
    private FlagSet blazeFireball;
}
