package de.hsrm.mi.netze07.shared.messaging;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Message {
    private MessageType type;
    private Map<String, String> content;

    public Message(MessageType t, Map<String, String> c) {
        type = t;
        content = c;
    }

    public static Message rawToMessage(String raw) {
        if (raw == null || raw.isEmpty() || raw.isBlank()) {
            return null;
        }
        String[] context = raw.split(Messaging.TYPE_CONTENT_SPLIT);
        MessageType type = MessageType.valueOf(context[0]);
        Map<String, String> content = new HashMap<>();
        if (context.length > 1) {
            Arrays.stream(context[1].split(Messaging.MESSAGE_SECTIONING)).forEach(keyValuePair -> {
                String[] entry = keyValuePair.split(Messaging.KEY_VALUE_ASSIGNMENT);
                content.put(entry[0], entry[1]);
            });
        }
        return new Message(type, content);
    }

    public String toRaw() {
        String body = content.entrySet()
                .stream()
                .map(e -> String.format("%s=%s", e.getKey(), e.getValue()))
                .collect(Collectors.joining(","));
        return String.format("%s;%s\n", type, body);
    }

    public MessageType getType() {
	
        return type;
	
    }
}
