package at.schnizeltec.shortcuts;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

import java.io.IOException;

public class GuiEditShortcuts extends GuiScreen {

    private final GuiScreen parent;
    private GuiTextField shortcutField;
    private GuiTextField commandField;
    private GuiButton addButton;
    private GuiButton saveButton;
    private GuiButton removeButton;

    public GuiEditShortcuts(GuiScreen parent) {
        this.parent = parent;
    }

    @Override
    public void initGui() {
        shortcutField = new GuiTextField(0, this.fontRendererObj, this.width / 2 - 100, this.height / 4 - 10, 200, 20);
        commandField = new GuiTextField(1, this.fontRendererObj, this.width / 2 - 100, this.height / 4 + 20, 200, 20);
        addButton = new GuiButton(2, this.width / 2 - 100, this.height / 4 + 50, 200, 20, "Add Shortcut");
        saveButton = new GuiButton(3, this.width / 2 - 100, this.height / 4 + 80, 200, 20, "Save Changes");
        removeButton = new GuiButton(4, this.width / 2 - 100, this.height / 4 + 110, 200, 20, "Remove Shortcut");

        this.buttonList.add(addButton);
        this.buttonList.add(saveButton);
        this.buttonList.add(removeButton);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button == addButton) {
            String shortcut = shortcutField.getText();
            String command = commandField.getText();
            if (!shortcut.isEmpty() && !command.isEmpty()) {
                ModConfig.shortcuts.put(shortcut, command);
                shortcutField.setText("");
                commandField.setText("");
            }
        } else if (button == saveButton) {
            ModConfig.saveConfig();
            this.mc.displayGuiScreen(parent);
        } else if (button == removeButton) {
            String shortcut = shortcutField.getText();
            if (!shortcut.isEmpty() && ModConfig.shortcuts.containsKey(shortcut)) {
                ModConfig.shortcuts.remove(shortcut);
                shortcutField.setText("");
                commandField.setText("");
            }
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        shortcutField.textboxKeyTyped(typedChar, keyCode);
        commandField.textboxKeyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        shortcutField.mouseClicked(mouseX, mouseY, mouseButton);
        commandField.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Edit Shortcuts", this.width / 2, this.height / 4 - 40, 0xFFFFFF);
        this.drawString(this.fontRendererObj, "Shortcut", this.width / 2 - 100, this.height / 4 - 20, 0xFFFFFF);
        shortcutField.drawTextBox();
        this.drawString(this.fontRendererObj, "Command", this.width / 2 - 100, this.height / 4 + 10, 0xFFFFFF);
        commandField.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}

