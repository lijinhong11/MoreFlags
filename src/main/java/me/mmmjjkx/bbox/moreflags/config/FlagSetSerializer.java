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
            flagSet.setEnabled(ms.getBoolean("enabled", true));
            flagSet.setChangeCooldown(ms.getInt("change-cooldown", 0));
            flagSet.setDefaultValue(ms.getBoolean("default-value", true));
        } else if (o instanceof Map<?, ?> m) {
            Map<String, Object> map = (Map<String, Object>) m;
            flagSet.setEnabled((boolean) map.getOrDefault("enabled", true));
            flagSet.setChangeCooldown((int) map.getOrDefault("change-cooldown", 0));
            flagSet.setDefaultValue((boolean) map.getOrDefault("default-value", true));
        }
        return flagSet;
    }

    @Override
    public Map<String, Object> serialize(Object o) {
        if (o instanceof FlagSet fs) {
            boolean enabled = fs.isEnabled();
            int changeCooldown = fs.getChangeCooldown();
            boolean defaultValue = fs.getDefaultValue();
            return Map.of("enabled", enabled, "change-cooldown", changeCooldown, "default-value", defaultValue);
        } else {
            return null;
        }
    }
}
