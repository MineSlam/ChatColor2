package com.sulphate.chatcolor2.utils;

import com.sulphate.chatcolor2.main.ChatColor;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlaceholderAPIHook extends PlaceholderExpansion {

    private ChatColor plugin;
    private ConfigUtils configUtils;
    private Messages M;

    public PlaceholderAPIHook(ChatColor plugin, ConfigUtils configUtils, Messages M) {
        this.plugin = plugin;
        this.configUtils = configUtils;
        this.M = M;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public String getIdentifier() {
        return "cc";
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {

        // Ignore if player is null.
        if (player == null) {
            return "";
        }

        UUID uuid = player.getUniqueId();

        switch (identifier) {
            // returns color
            case "color": {
                String colour = configUtils.getColour(uuid);
                String colourPart = colour.substring(0, 2);

                return GeneralUtils.colourise(colourPart);
            }

            // returns color name
            case "color_text": {
                String colour = configUtils.getColour(uuid);

                if (colour.contains("rainbow")) {
                    return GeneralUtils.getTextEquivalent("rainbow", M, configUtils);
                }
                else {
                    return GeneralUtils.getTextEquivalent(colour.substring(0, 2), M, configUtils);
                }
            }
        }

        return null;
    }

}
