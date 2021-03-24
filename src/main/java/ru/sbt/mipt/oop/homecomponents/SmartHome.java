package ru.sbt.mipt.oop.homecomponents;

import ru.sbt.mipt.oop.Action;

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

  @Override
  public void execute(Action action) {
    action.applyAction(this);

    for (Room room : rooms) {
      room.execute(action);
    }
  }
}
