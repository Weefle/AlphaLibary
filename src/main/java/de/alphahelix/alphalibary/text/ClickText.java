package de.alphahelix.alphalibary.text;

import de.alphahelix.alphalibary.command.SimpleCommand;
import de.alphahelix.alphalibary.uuid.UUIDFetcher;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class ClickText {

    public static void sendText(Player p, String text, TextAction action) {
        UUID id = UUIDFetcher.getUUID(p);
        new ActionTextCommand(id, action);

        TextComponent txt = new TextComponent(text);

        txt.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/atcPerform_" + id));

        p.spigot().sendMessage(txt);
    }

    private static class ActionTextCommand extends SimpleCommand {

        private TextAction action;

        public ActionTextCommand(UUID player, TextAction action) {
            super("atcPerform_" + player.toString(), "", "");
            this.action = action;
        }

        @Override
        public boolean execute(CommandSender cs, String label, String[] args) {
            action.run(cs);
            return true;
        }

        @Override
        public List<String> tabComplete(CommandSender cs, String label, String[] args) {
            return null;
        }
    }
}