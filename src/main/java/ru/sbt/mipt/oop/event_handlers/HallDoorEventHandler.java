package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.command_senders.CommandSender;
import ru.sbt.mipt.oop.utils.HomeUtils;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class HallDoorEventHandler implements EventHandler {
  private final CommandSender commandSender;

  public HallDoorEventHandler(CommandSender commandSender) {
    this.commandSender = commandSender;
  }

  @Override
  public void handleEvent(SmartHome smartHome, SensorEvent event) {
    if (!isCorrectEvent(event)) return;
    Room room = HomeUtils.findDoorRoom(smartHome, event.getObjectId());
    if (room == null) return;

    if (room.getName().equals("hall")) {
      for (Room homeRoom : smartHome.getRooms()) {
        for (Light light : homeRoom.getLights()) {
          light.setOn(false);
          SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
          commandSender.sendCommand(command);
        }
      }
    }
  }

  private boolean isCorrectEvent(SensorEvent event) {
    return event.getType() == DOOR_CLOSED || event.getType() == DOOR_OPEN;
  }
}
