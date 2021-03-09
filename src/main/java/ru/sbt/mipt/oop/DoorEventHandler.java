package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventHandler implements EventHandler {
  Room room;
  Door door;
  SmartHome smartHome;

  public DoorEventHandler(Room room, Door door, SmartHome smartHome) {
    this.room = room;
    this.door = door;
    this.smartHome = smartHome;
  }

  public void handle(SensorEvent event) {
    if (event.getType() == DOOR_OPEN) {
      handleOpenedDoor();
    } else {
      handleClosedDoor();
    }
  }

  private void handleClosedDoor() {
    door.setOpen(false);
    System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");

    // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
    // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
    if (room.getName().equals("hall")) {
      for (Room homeRoom : smartHome.getRooms()) {
        for (Light light : homeRoom.getLights()) {
          light.setOn(false);
          SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
          sendCommand(command);
        }
      }
    }
  }

  private void handleOpenedDoor() {
    door.setOpen(true);
    System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
  }


  private static void sendCommand(SensorCommand command) {
    System.out.println("Pretend we're sending command " + command);
  }
}
