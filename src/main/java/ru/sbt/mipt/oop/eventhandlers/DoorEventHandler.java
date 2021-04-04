package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.homecomponents.Door;
import ru.sbt.mipt.oop.homecomponents.HomeComponent;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class DoorEventHandler implements EventHandler {

  @Override
  public void handleEvent(SmartHome smartHome, SensorEvent event) {
    if (!isCorrectEvent(event)) return;

    smartHome.execute((HomeComponent homeComponent) -> {
      if (!(homeComponent instanceof Door)) return;
      Door door = (Door) homeComponent;
      if (!event.getObjectId().equals(door.getId())) return;

      if (event.getType() == DOOR_OPEN) {
        DoorEventHandler.handleOpenDoor(door);
      } else if (event.getType() == DOOR_CLOSED) {
        DoorEventHandler.handleCloseDoor(door);
      }
    });
  }

  private boolean isCorrectEvent(SensorEvent event) {
    return event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED;
  }

  private static void handleOpenDoor(Door door) {
    door.setOpen(true);
    System.out.println("Door " + door.getId() + " was closed.");
  }

  private static void handleCloseDoor(Door door) {
    door.setOpen(false);
    System.out.println("Door " + door.getId() + " was opened.");
  }

}
