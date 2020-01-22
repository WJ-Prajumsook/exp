package org.wj.prajumsook.reservation.roomservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wj.prajumsook.reservation.persistence.Room;
import org.wj.prajumsook.reservation.persistence.RoomRepository;

import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    public Iterable<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    public Room addNewRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(Room room) {
        return roomRepository.save(room);
    }

    public boolean deleteRoom(Long id) {
        roomRepository.deleteById(id);

        return true;
    }
}
