package com.ll.exam.chat;

import com.ll.exam.chat.dto.ChatRoomDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ChatRepository {
    private static List<ChatRoomDto> datum;
    private static long lastId;

    static {
        datum = new ArrayList<>();
        lastId = 0;

        makeTestData();
    }

    private static void makeTestData() {
        IntStream.rangeClosed(1, 10).forEach(id -> {
            String title = "이름%d".formatted(id);
            String body = "주제%d".formatted(id);
            createRoom(title, body);
        });
    }

    public static long createRoom(String title, String body) {
        long id = ++lastId;
        ChatRoomDto newchatRoomDto = new ChatRoomDto(id, title, body);
        datum.add(newchatRoomDto);

        return id;
    }

    public List<ChatRoomDto> findAllRooms() {
        return datum;
    }

    public ChatRoomDto findById(long id) {
        for (ChatRoomDto chatRoomDto : datum) {
            if (chatRoomDto.getId() == id) {
                return chatRoomDto;
            }
        }

        return null;
    }

    public void modifyRoom(long id, String title, String body) {
        ChatRoomDto chatRoomDto = findById(id);

        if ( chatRoomDto == null ) {
            return;
        }

        chatRoomDto.setTitle(title);
        chatRoomDto.setBody(body);
    }

    public void deleteRoom(long id) {
        ChatRoomDto chatRoomDto = findById(id);

        if ( chatRoomDto == null ) {
            return;
        }

        datum.remove(chatRoomDto);
    }
}
