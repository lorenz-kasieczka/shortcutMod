package at.schnizeltec.shortcuts;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;

public class ModGuiConfig extends GuiConfig {

    public ModGuiConfig(GuiScreen parentScreen) {
        super(parentScreen, getConfigElements(), ShortcutMod.MODID, false, false, "Shortcut Mod Configuration");
    }

    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> elements = new ArrayList<IConfigElement>();
        elements.add(new DummyConfigElement.DummyCategoryElement("shortcuts", "Shortcuts", ShortcutEntry.class));
        return elements;
    }

    public static class ShortcutEntry extends GuiConfigEntries.CategoryEntry {

        public ShortcutEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop) {
            super(owningScreen, owningEntryList, prop);
        }

        @Override
        protected GuiScreen buildChildScreen() {
            return new GuiEditShortcuts(this.owningScreen);
        }
    }
}
