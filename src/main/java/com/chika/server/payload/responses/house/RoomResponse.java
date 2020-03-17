package com.chika.server.payload.responses.house;

import com.chika.server.Formatter;
import com.chika.server.models.house.Room;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoomResponse {

    private String id;
    private String logo;
    private String name;
    private String createDay;

    public RoomResponse(Room room) {
        this.id = room.getId();
        this.logo = room.getLogo();
        this.name = room.getName();
        this.createDay = Formatter.formatTimeDay(room.getCreatedAt());
    }
}
