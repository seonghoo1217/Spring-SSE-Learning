package com.example.sse.api;

import com.example.sse.dto.RoomRequestDTO;
import com.example.sse.dto.RoomResponseDTO;
import com.example.sse.service.room.details.RoomChannel;
import com.example.sse.service.room.details.RoomChannels;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequiredArgsConstructor
public class RoomSseAPI {

    private final RoomChannel roomChannel;

    private final RoomChannels roomChannels;

    private final AtomicInteger id=new AtomicInteger();

    @GetMapping("/channels/room/{roomId}/messages")
    public Flux<ServerSentEvent<String>> connect(@PathVariable("roomId")Long roomId){
        int no = id.getAndAdd(1);
        System.out.println("no="+no);
        Flux<String> useStream = roomChannels.connect(roomId).toFlux();
        Flux<String> tickStream = Flux.interval(Duration.ofSeconds(3))
                .map(tick -> "Check " + no);
        return Flux.merge(useStream,tickStream)
                .map(str->ServerSentEvent.builder(str).build());
    }

    @PostMapping(path = "/channels/room/{roomId}/messages")
    public void send(@PathVariable("roomId")Long roomId, @RequestBody String message){
        roomChannels.post(roomId,message);
    }

    @PostMapping(path = "/room/{roomId}")
    public Flux<ServerSentEvent<RoomResponseDTO>> roomSSE(@PathVariable("roomId")Long roomId, @RequestBody RoomRequestDTO roomRequestDTO){
        /*return Flux.interval(Duration.ofSeconds(1))
                .map(t -> RoomResponseDTO.builder()
                        .roomId(roomId)
                        .timeStamp(roomRequestDTO.getTimeStamp())
                        .status(roomRequestDTO.getStatus())
                        .build())
                .map(rs -> ServerSentEvent.builder(rs).build());
*/
        return Flux.just(RoomResponseDTO.builder()
                .roomId(roomId)
                .timeStamp(roomRequestDTO.getTimeStamp())
                .status(roomRequestDTO.getStatus())
                .build())
                .map(rs->ServerSentEvent.builder(rs).build());
    }
}
