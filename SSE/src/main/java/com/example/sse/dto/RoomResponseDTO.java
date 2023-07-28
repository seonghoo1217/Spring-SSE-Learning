package com.example.sse.dto;

import com.example.sse.domain.Status;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomResponseDTO {
    private Long roomId;
    private String roomCode;
    private Status status;
    private String timeStamp;
}
