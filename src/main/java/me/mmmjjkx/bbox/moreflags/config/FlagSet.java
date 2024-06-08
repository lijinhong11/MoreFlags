package me.mmmjjkx.bbox.moreflags.config;

import lombok.Getter;
import lombok.Setter;
import world.bentobox.bentobox.api.configuration.ConfigEntry;

@Getter
@Setter
public class FlagSet {
    public FlagSet() {
    }

    @ConfigEntry(path = "enabled")
    private boolean enabled = true;
    @ConfigEntry(path = "change-cooldown")
    private int changeCooldown = 0;
}
