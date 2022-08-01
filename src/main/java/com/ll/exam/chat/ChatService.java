package com.ll.exam.chat;

public class ChatService {
    private ChatRepository chatRepository;

    ChatService(){
        chatRepository = new ChatRepository();
    }

    public long createRoom(String title, String body) {
        return chatRepository.createRoom(title, body);
    }
}
