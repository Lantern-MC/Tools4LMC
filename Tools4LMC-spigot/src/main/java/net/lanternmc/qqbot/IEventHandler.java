package net.lanternmc.qqbot;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhenxin.qqbot.api.ApiManager;
import me.zhenxin.qqbot.entity.Message;
import me.zhenxin.qqbot.entity.User;
import me.zhenxin.qqbot.event.AtMessageEvent;
import me.zhenxin.qqbot.exception.ApiException;
import me.zhenxin.qqbot.websocket.EventHandler;
import org.apache.log4j.Logger;

// 自定义事件处理器 继承EventHandler
// 自定义事件处理器 继承EventHandler
@Slf4j
@AllArgsConstructor
public class IEventHandler extends EventHandler {
    public ApiManager api;
    public static Logger log = Logger.getLogger("T4");

    // 处理错误
    public void onError(Throwable t) {
        log.error("发生错误: {}");
    }

    @Override
    public void onAtMessage(AtMessageEvent event) {
        Message message = event.getMessage();
        String guildId = message.getGuildId();
        String channelId = message.getChannelId();
        String content = message.getContent();
        String messageId = message.getId();
        User author = message.getAuthor();
        super.onAtMessage(event);
        try {
            String[] args = content.split(" ");
            String command = args[0];
            switch (command) {
                case "ping":
                    api.getMessageApi().sendMessage(
                            channelId,
                            "pong",
                            messageId
                    );
                    break;
            }
        } catch (ApiException e) {
            log.error("消息处理发生异常: " + e.getCode() + e.getMessage() + e.getError());
            api.getMessageApi().sendMessage(channelId, "消息处理失败: " + e.getMessage(), messageId);
        }
    }
}