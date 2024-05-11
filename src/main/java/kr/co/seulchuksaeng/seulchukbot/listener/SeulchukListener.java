package kr.co.seulchuksaeng.seulchukbot.listener;

import kr.co.seulchuksaeng.seulchukbot.command.MessageParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SeulchukListener extends ListenerAdapter {

    private final MessageParser messageParser;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        User author = event.getAuthor();
        TextChannel textChannel = event.getChannel().asTextChannel();
        Message message = event.getMessage();

        if(author.isBot()) {
            return;
        }

        if(!messageParser.checkValidCommand(event, message.getContentDisplay().split(" "))) {
            return;
        }

        String result = messageParser.responseCommand(event, message.getContentDisplay().split(" "));

        if(result.isEmpty()) {
            log.warn("처리 결과 값이 존재하지 않습니다");
        }

        sendMessage(event, result);

    }

    private void sendMessage(MessageReceivedEvent event, String returnMessage) {
        TextChannel textChannel = event.getChannel().asTextChannel();
        textChannel.sendMessage(returnMessage).queue();
    }

}
