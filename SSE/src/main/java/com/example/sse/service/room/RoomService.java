package com.example.sse.service.room;

import com.example.sse.domain.Room;
import com.example.sse.repo.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public void createRoom(Room room){
        roomRepository.save(room);
    }
}
