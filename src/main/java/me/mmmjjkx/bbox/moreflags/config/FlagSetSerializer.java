package me.mmmjjkx.bbox.moreflags.config;

import org.bukkit.configuration.MemorySection;
import world.bentobox.bentobox.database.objects.adapters.AdapterInterface;

import java.util.Map;

public class FlagSetSerializer implements AdapterInterface<FlagSet, Map<String, Object>> {

    public FlagSetSerializer() {
    }

    @Override
    public FlagSet deserialize(Object o) {
        FlagSet flagSet = new FlagSet();
        if (o instanceof MemorySection ms) {
            flagSet.setEnabled(ms.getBoolean("enabled"));
            flagSet.setChangeCooldown(ms.getInt("change-cooldown"));
        }
        return flagSet;
    }

    @Override
    public Map<String, Object> serialize(Object o) {
        if (o instanceof FlagSet fs) {
            boolean enabled = fs.isEnabled();
            int changeCooldown = fs.getChangeCooldown();
            return Map.of("enabled", enabled, "change-cooldown", changeCooldown);
        } else {
            return null;
        }
    }
}
