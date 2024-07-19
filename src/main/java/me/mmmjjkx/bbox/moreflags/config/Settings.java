package me.mmmjjkx.bbox.moreflags.config;

import lombok.Getter;
import lombok.Setter;
import world.bentobox.bentobox.api.configuration.*;
import world.bentobox.bentobox.database.objects.adapters.Adapter;

@StoreAt(filename = "config.yml", path = "addons/MoreFlags")
@Getter
@Setter
public class Settings implements ConfigObject {
    @ConfigEntry(path = "creeper_explosion")
    @ConfigComment("It can control creeper explosions.")
    @Adapter(FlagSetSerializer.class)
    private FlagSet creeperExplosions;

    @ConfigEntry(path = "wither_explosion")
    @ConfigComment("It can control wither explosions.")
    @Adapter(FlagSetSerializer.class)
    private FlagSet witherExplosions;

    @ConfigEntry(path = "phantom_spawning")
    @ConfigComment("It can control phantom spawning.")
    @Adapter(FlagSetSerializer.class)
    private FlagSet phantomSpawning;

    @ConfigEntry(path = "witch_potion_throwing", since = "1.1")
    @ConfigComment("It can control witch potion throwing.")
    @Adapter(FlagSetSerializer.class)
    private FlagSet witchPotionThrowing;
}