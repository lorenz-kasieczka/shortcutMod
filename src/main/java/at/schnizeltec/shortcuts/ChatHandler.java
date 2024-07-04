package at.schnizeltec.shortcuts;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;

import java.util.Map;

public class ChatHandler {
    public ChatHandler() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent event) {
        String fullMessage = event.message.getUnformattedText();
        String messageContent = extractMessageContent(fullMessage);
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;

        for (Map.Entry<String, String> entry : ModConfig.shortcuts.entrySet()) {
            if (messageContent.startsWith(entry.getKey())) {
                event.setCanceled(true); // Cancel the original message
                player.sendChatMessage(entry.getValue()); // Send the command instead
                break;
            }
        }
    }

    private String extractMessageContent(String fullMessage) {
        int firstSpaceIndex = fullMessage.indexOf(' ');
        if (firstSpaceIndex != -1) {
            return fullMessage.substring(firstSpaceIndex + 1);
        }
        return fullMessage; // Return the full message if no space is found (edge case)
    }
}
