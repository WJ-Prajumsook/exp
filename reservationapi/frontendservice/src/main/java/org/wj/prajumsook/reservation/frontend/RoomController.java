package org.wj.prajumsook.reservation.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wj.prajumsook.reservation.frontend.exception.ResourceNotFoundException;
import org.wj.prajumsook.reservation.persistence.Room;
import org.wj.prajumsook.reservation.roomservice.RoomService;

@RestController
public class RoomController {

    @Autowired
    RoomService roomService;

    @GetMapping("/rooms")
    public @ResponseBody Iterable<Room> getRooms() {
        Iterable<Room> rooms = roomService.getAllRooms();

        return rooms;
    }

    @GetMapping("/rooms/{id}")
    public @ResponseBody Room getRoom(@PathVariable("id")String id) {
        return roomService.getRoomById(Long.parseLong(id)).orElseThrow(() -> new ResourceNotFoundException("Room not found with id="+id));
    }

    @PostMapping("/rooms")
    public @ResponseBody  Room addNewRoom(@RequestBody Room room) {
        return roomService.addNewRoom(room);
    }

    @PutMapping("/rooms")
    public @ResponseBody Room updateRome(@RequestBody Room room) {
        return roomService.updateRoom(room);
    }

    @DeleteMapping("/rooms/{id}")
    public boolean deleteRoom(@PathVariable("id")String id) {
        Room room = roomService.getRoomById(Long.parseLong(id)).orElseThrow(() -> new ResourceNotFoundException("Error deleting room with id="+id));

        roomService.deleteRoom(room.getId());

        return true;
    }
}
