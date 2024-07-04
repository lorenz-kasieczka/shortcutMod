package at.schnizeltec.shortcuts;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent.KeyboardInputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class GuiOpener {
    boolean isGuiOpen = false;

    public GuiOpener() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        handleKeyInput();
    }

    @SubscribeEvent
    public void onKeyboardInput(KeyboardInputEvent event) {
        handleKeyInput();
    }

    private void handleKeyInput() {
        // Check for a key press to open/close the config GUI (e.g., "J" key)
        int key = Keyboard.getEventKey();
        boolean keyState = Keyboard.getEventKeyState();

        if (key == Keyboard.KEY_J && keyState) {
            if (isGuiOpen) {
                // Close the GUI and return to the previous screen
                Minecraft.getMinecraft().displayGuiScreen((GuiScreen) null);
                isGuiOpen = false;
            } else {
                // Open the GUI
                Minecraft.getMinecraft().displayGuiScreen(new GuiEditShortcuts(Minecraft.getMinecraft().currentScreen));
                isGuiOpen = true;
            }
        }
    }
}
