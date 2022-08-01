package com.ll.exam.chat;

import com.ll.exam.chat.dto.ChatMessageDto;
import com.ll.exam.chat.dto.ChatRoomDto;

import java.util.List;

public class ChatService {
    private ChatRepository chatRepository;
    private ChatMessageRepository chatMessageRepository;

    ChatService(){
        chatRepository = new ChatRepository();
        chatMessageRepository = new ChatMessageRepository();
    }

    public long createRoom(String title, String body) {
        return chatRepository.createRoom(title, body);
    }

    public List<ChatRoomDto> findAllRooms() {
        return chatRepository.findAllRooms();
    }

    public ChatRoomDto findRoomById(long id) {
        return chatRepository.findById(id);
    }

    public void modifyRoom(long id, String title, String body) {
        chatRepository.modifyRoom(id, title, body);
    }

    public void deleteRoom(long id) {
        chatRepository.deleteRoom(id);
    }

    public long writeMessage(long roomId, String body) {
        return chatMessageRepository.writeMessage(roomId, body);
    }

    public List<ChatMessageDto> findMessagesByRoomId(long id) {
        return chatMessageRepository.findMessagesByRoomId(id);
    }

    public List<ChatMessageDto> findMessagesByRoomIdGreaterThan(long roomId, long fromId) {
        return chatMessageRepository.findMessagesByRoomIdGreaterThan(roomId, fromId);
    }

    public ChatMessageDto findMessageById(long id) {
        return chatMessageRepository.findMessageById(id);
    }

    public void deleteMessage(long id) {
        chatMessageRepository.deleteMessage(id);
    }

    public void modifyMessage(long id, String body) {
        chatMessageRepository.modifyMessage(id, body);
    }
}
