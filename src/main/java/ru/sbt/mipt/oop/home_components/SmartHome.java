package ru.sbt.mipt.oop.home_components;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.home_components.Room;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements HomeComponent {
  Collection<Room> rooms;

  public SmartHome() {
    rooms = new ArrayList<>();
  }

  public SmartHome(Collection<Room> rooms) {
    this.rooms = rooms;
  }

  public void addRoom(Room room) {
    rooms.add(room);
  }

  public Collection<Room> getRooms() {
    return rooms;
  }

  @Override
  public void execute(Action action) {
    for (Room room : rooms) {
      action.applyAction(room);
      room.execute(action);
    }
  }
}
