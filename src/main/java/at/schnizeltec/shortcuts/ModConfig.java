package at.schnizeltec.shortcuts;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ModConfig {
    public static Configuration config;
    public static Map<String, String> shortcuts = new HashMap<String, String>();

    public static void loadConfig(FMLPreInitializationEvent event) {
        File configFile = new File(event.getModConfigurationDirectory(), "mymod.cfg");
        config = new Configuration(configFile);

        try {
            config.load();
            String[] defaultShortcuts = config.getStringList("shortcuts", Configuration.CATEGORY_GENERAL, new String[] {
                    "home=/warp home",
                    "hub=/warp hub",
                    "gar=/warp garden",
            }, "List of shortcuts in format shortcut=command");

            for (String entry : defaultShortcuts) {
                String[] parts = entry.split("=", 2);
                if (parts.length == 2) {
                    shortcuts.put(parts[0], parts[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }

    public static void saveConfig() {
        String[] shortcutsArray = new String[shortcuts.size()];
        int index = 0;
        for (Map.Entry<String, String> entry : shortcuts.entrySet()) {
            shortcutsArray[index] = entry.getKey() + "=" + entry.getValue();
            index++;
        }

        config.get(Configuration.CATEGORY_GENERAL, "shortcuts", new String[0]).set(shortcutsArray);

        if (config.hasChanged()) {
            config.save();
        }
    }
}
