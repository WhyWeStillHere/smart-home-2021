package ru.sbt.mipt.oop.homecomponents;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.signaling.Signaling;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements HomeComponent {
  private final Collection<Room> rooms;
  private Signaling signaling;

  public SmartHome() {
    rooms = new ArrayList<>();
    signaling = new Signaling();
  }

  public SmartHome(Collection<Room> rooms) {
    this.rooms = rooms;
  }

  public void addRoom(Room room) {
    rooms.add(room);
  }

  public Signaling getSignaling() {
    return signaling;
  }

  @Override
  public void execute(Action action) {
    action.applyAction(this);

    for (Room room : rooms) {
      room.execute(action);
    }
  }
}
