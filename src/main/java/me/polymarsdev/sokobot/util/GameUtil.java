package me.polymarsdev.sokobot.util;

import me.polymarsdev.sokobot.Bot;
import me.polymarsdev.sokobot.Game;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.HashMap;

public class GameUtil {

    private static final HashMap<Long, Game> games = new HashMap<>();

    public static void setGame(long userId, Game game) {
        games.put(userId, game);
    }

    public static boolean hasGame(long userId) {
        return games.containsKey(userId);
    }

    public static Game getGame(long userId) {
        return games.get(userId);
    }

    public static void removeGame(long userId) {
        games.remove(userId);
    }

    public static void sendGameEmbed(MessageChannel channel, String level, String game, User user) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Sokobot | Level " + level);
        embed.setDescription(game);
        embed.addField("Enter direction (``up``, ``down``, ``left``, ``right``/``wasd``) or ``r`` to reset", "", false);
        embed.addField("Player", user.getAsMention(), false);
        embed.setFooter("Game of " + user.getAsMention(), user.getAvatarUrl());
        channel.sendMessage(embed.build()).queue();
    }

    public static void updateGameEmbed(Message message, String level, String game, User user) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Sokobot | Level " + level);
        embed.setDescription(game);
        embed.addField("Enter direction (``up``, ``down``, ``left``, ``right``/``wasd``) or ``r`` to reset", "", false);
        embed.setFooter("Game of " + user.getAsMention(), user.getAvatarUrl());
        message.editMessage(embed.build()).queue();
    }

    public static void sendWinEmbed(Guild guild, Message message, String level) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Sokobot | You win!");
        embed.setDescription(
                "Type ``" + Bot.getPrefix(guild) + "continue`` to continue to Level " + level + " or ``" + Bot
                        .getPrefix(guild) + "stop`` to quit ");
        embed.setFooter("You can also press any reaction to continue.");
        message.editMessage(embed.build()).queue();
    }
}
