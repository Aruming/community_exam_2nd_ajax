package com.ll.exam.chat;

import com.ll.exam.chat.dto.ChatRoomDto;

import java.util.List;

public class ChatService {
    private ChatRepository chatRepository;

    ChatService(){
        chatRepository = new ChatRepository();
    }

    public long createRoom(String title, String body) {
        return chatRepository.createRoom(title, body);
    }

    public List<ChatRoomDto> findAllRooms() {
        return chatRepository.findAllRooms();
    }
}
