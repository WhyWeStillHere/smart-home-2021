package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.utils.HomeUtils;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class DoorEventHandler implements EventHandler {

  @Override
  public void handleEvent(SmartHome smartHome, SensorEvent event) {
    if (!isCorrectEvent(event)) return;

    Door eventDoor = HomeUtils.findDoor(smartHome, event.getObjectId());

    if (event.getType() == DOOR_OPEN) {
      openDoor(eventDoor);
    } else if (event.getType() == DOOR_CLOSED) {
      closeDoor(eventDoor);
    }
  }

  private boolean isCorrectEvent(SensorEvent event) {
    return event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED;
  }

  private void closeDoor(Door door) {
    door.setOpen(false);
    System.out.println("Door " + door.getId() + " was closed.");
  }

  private void openDoor(Door door) {
    door.setOpen(true);
    System.out.println("Door " + door.getId() + " was opened.");
  }

}
