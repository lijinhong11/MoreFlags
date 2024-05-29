package me.mmmjjkx.bbox.moreflags.config;

import lombok.Getter;
import lombok.Setter;
import world.bentobox.bentobox.api.configuration.ConfigEntry;

@Setter
public class FlagSet {
    @Getter
    @ConfigEntry(path = "enabled")
    private boolean enabled = true;
    @Getter
    @ConfigEntry(path = "change-cooldown")
    private int changeCooldown = 0;
    @ConfigEntry(path = "default-value")
    private boolean defaultValue = false;

    public boolean getDefaultValue() {
        return defaultValue;
    }
}
