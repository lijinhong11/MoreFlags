package me.mmmjjkx.bbox.moreflags.config;

import lombok.Getter;
import lombok.Setter;
import world.bentobox.bentobox.api.configuration.*;
import world.bentobox.bentobox.database.objects.adapters.Adapter;

@StoreAt(filename = "config.yml", path = "flags")
@Getter
@Setter
public class Settings implements ConfigObject {
    @ConfigEntry(path = "creeper_explosion")
    @ConfigComment("It can control creeper explosions.")
    @Adapter(FlagSetSerializer.class)
    private FlagSet creeperExplosions = new FlagSet();

    @ConfigEntry(path = ".wither_explosion")
    @ConfigComment("It can control wither explosions.")
    @Adapter(FlagSetSerializer.class)
    private FlagSet witherExplosions = new FlagSet();

    @ConfigEntry(path = "phantom_spawning")
    @ConfigComment("It can control phantom spawning.")
    @Adapter(FlagSetSerializer.class)
    private FlagSet phantomSpawning = new FlagSet();
}