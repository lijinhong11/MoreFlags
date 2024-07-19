package me.mmmjjkx.bbox.moreflags.config;

import lombok.Getter;
import lombok.Setter;

@Setter
public class FlagSet {
    public FlagSet() {
        enabled = true;
        changeCooldown = 0;
        defaultValue = true;
    }

    @Getter
    private boolean enabled;
    private boolean defaultValue;
    @Getter
    private int changeCooldown;

    public boolean getDefaultValue() {
        return defaultValue;
    }
}
