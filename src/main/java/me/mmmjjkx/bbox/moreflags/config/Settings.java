package me.mmmjjkx.bbox.moreflags.config;

import lombok.Getter;
import lombok.Setter;
import world.bentobox.bentobox.api.configuration.*;

@StoreAt(filename = "config.yml")
@Getter
@Setter
public class Settings implements ConfigObject {
    @ConfigEntry(path = "flags.creeper_explosion")
    @ConfigComment("It can control creeper explosions.")
    private FlagSet creeperExplosions = new FlagSet();

    @ConfigEntry(path = "flags.wither_explosion")
    @ConfigComment("It can control wither explosions.")
    private FlagSet witherExplosions = new FlagSet();

    @ConfigEntry(path = "flags.phantom_spawning")
    @ConfigComment("It can control phantom spawning.")
    private FlagSet phantomSpawning = new FlagSet();
}