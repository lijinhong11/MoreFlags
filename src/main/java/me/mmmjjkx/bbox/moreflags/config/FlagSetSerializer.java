package me.mmmjjkx.bbox.moreflags.config;

import java.util.Map;
import org.bukkit.configuration.ConfigurationSection;
import world.bentobox.bentobox.api.flags.Flag;
import world.bentobox.bentobox.database.objects.adapters.AdapterInterface;

public class FlagSetSerializer implements AdapterInterface<FlagSet, Map<String, Object>> {
    public FlagSetSerializer() {}

    @Override
    public FlagSet deserialize(Object o) {
        FlagSet flagSet = new FlagSet();
        if (o instanceof ConfigurationSection cs) {
            flagSet.setEnabled(cs.getBoolean("enabled", true));
            flagSet.setChangeCooldown(cs.getInt("change-cooldown", 0));
            flagSet.setDefaultValue(cs.getBoolean("default-value", true));
            flagSet.setMode(Flag.Mode.valueOf(cs.getString("mode", Flag.Mode.EXPERT.name())));
            flagSet.setType(Flag.Type.valueOf(cs.getString("type", Flag.Type.SETTING.name())));
        } else if (o instanceof Map<?, ?> m) {
            Map<String, Object> map = (Map<String, Object>) m;
            flagSet.setEnabled((boolean) map.getOrDefault("enabled", true));
            flagSet.setChangeCooldown((int) map.getOrDefault("change-cooldown", 0));
            flagSet.setDefaultValue((boolean) map.getOrDefault("default-value", true));
            flagSet.setMode(Flag.Mode.valueOf((String) map.getOrDefault("mode", Flag.Mode.EXPERT.name())));
            flagSet.setType(Flag.Type.valueOf((String) map.getOrDefault("type", Flag.Type.SETTING.name())));
        }
        return flagSet;
    }

    @Override
    public Map<String, Object> serialize(Object o) {
        if (o instanceof FlagSet fs) {
            boolean enabled = fs.isEnabled();
            int changeCooldown = fs.getChangeCooldown();
            boolean defaultValue = fs.getDefaultValue();
            String mode = fs.getMode().name();
            String type = fs.getType().name();
            return Map.of(
                    "enabled", enabled,
                    "change-cooldown", changeCooldown,
                    "default-value", defaultValue,
                    "mode", mode,
                    "type", type);
        } else {
            return null;
        }
    }
}
