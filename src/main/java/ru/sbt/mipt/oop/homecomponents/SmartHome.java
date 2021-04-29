package ru.sbt.mipt.oop.homecomponents;

import rc.RemoteControl;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.signaling.Signaling;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements HomeComponent {
  private final Signaling signaling = new Signaling();
  private final Collection<Room> rooms;
  private RemoteControlRegistry remoteControlRegistry;

  public SmartHome() {
    rooms = new ArrayList<>();
  }

  public SmartHome(Collection<Room> rooms) {
    this.rooms = rooms;
  }

  public void addRoom(Room room) {
    rooms.add(room);
  }

  public void addRemoteControlRegistry(RemoteControlRegistry remoteControlRegistry) {
    this.remoteControlRegistry = remoteControlRegistry;
  }

  public void registerRemoteControl(RemoteControl remoteControl, String rcId) {
    if (remoteControlRegistry != null) {
      remoteControlRegistry.registerRemoteControl(remoteControl, rcId);
    }
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
