package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.commandsenders.CommandSender;
import ru.sbt.mipt.oop.homecomponents.*;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class HallDoorEventHandler implements EventHandler {
  private final CommandSender commandSender;

  public HallDoorEventHandler(CommandSender commandSender) {
    this.commandSender = commandSender;
  }

  @Override
  public void handleEvent(SmartHome smartHome, SensorEvent event) {
    if (!isCorrectEvent(event)) return;

    smartHome.execute((HomeComponent homeComponent) -> {
      if (!(homeComponent instanceof Room)) return;
      Room room = (Room) homeComponent;

      if (room.getName().equals("hall") && room.hasDoor(event.getObjectId())) {
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

  private boolean isCorrectEvent(SensorEvent event) {
    return event.getType() == DOOR_CLOSED;
  }
}
