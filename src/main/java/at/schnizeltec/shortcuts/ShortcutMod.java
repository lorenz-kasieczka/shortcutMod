package at.schnizeltec.shortcuts;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ShortcutMod.MODID, version = ShortcutMod.VERSION, name = ShortcutMod.NAME, guiFactory = "at.schnizeltec.shortcuts.ModGuiFactory")
public class ShortcutMod {
    public static final String MODID = "shortcuts";
    public static final String NAME = "Shortcuts";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModConfig.loadConfig(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        new ChatHandler();
        new GuiOpener();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }
}
