package com.example.sse.dto;

import com.example.sse.domain.Status;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RoomRequestDTO {
    private String timeStamp;
    private Status status;
}
