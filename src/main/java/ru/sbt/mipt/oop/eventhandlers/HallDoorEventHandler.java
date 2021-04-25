package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.commandsenders.CommandSender;
import ru.sbt.mipt.oop.homecomponents.*;

import static ru.sbt.mipt.oop.SensorEventType.*;
import static ru.sbt.mipt.oop.utils.HomeUtils.findDoor;

public class HallDoorEventHandler implements EventHandler {
  private final CommandSender commandSender;

  public HallDoorEventHandler(CommandSender commandSender) {
    this.commandSender = commandSender;
  }

  @Override
  public void handleEvent(SmartHome smartHome, Event event) {
    if (!isCorrectEvent(event)) return;

    SensorEvent sensorEvent = (SensorEvent) event;
    smartHome.execute((HomeComponent homeComponent) -> {
      if (!(homeComponent instanceof Room)) return;
      Room room = (Room) homeComponent;

      if (room.getName().equals("hall") && findDoor(room, sensorEvent.getObjectId()) != null) {
        smartHome.execute((HomeComponent otherHomeComponent) -> {
          if (!(otherHomeComponent instanceof Light)) return;
          Light light = (Light) otherHomeComponent;

          light.setOn(false);
          SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
          commandSender.sendCommand(command);
        });
      }
    });
  }

  private boolean isCorrectEvent(Event event) {
    if (!(event instanceof SensorEvent)) {
      return false;
    }
    SensorEvent sensorEvent = (SensorEvent) event;
    return sensorEvent.getType() == DOOR_CLOSED;
  }
}
