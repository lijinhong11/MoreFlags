package me.mmmjjkx.bbox.moreflags.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import world.bentobox.bentobox.api.flags.Flag;

@AllArgsConstructor
@Setter
public class FlagSet {
    @Getter
    private boolean enabled;

    private boolean defaultValue;

    @Getter
    private int changeCooldown;

    @Getter
    private Flag.Mode mode;

    @Getter
    private Flag.Type type;

    public FlagSet() {
        this.enabled = true;
        this.changeCooldown = 0;
        this.defaultValue = true;
        this.mode = Flag.Mode.EXPERT;
        this.type = Flag.Type.SETTING;
    }

    public boolean getDefaultValue() {
        return defaultValue;
    }
}
