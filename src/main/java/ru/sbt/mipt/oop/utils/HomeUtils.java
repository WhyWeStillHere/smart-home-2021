package ru.sbt.mipt.oop.utils;

import ru.sbt.mipt.oop.home_components.Door;
import ru.sbt.mipt.oop.home_components.Light;
import ru.sbt.mipt.oop.home_components.Room;
import ru.sbt.mipt.oop.home_components.SmartHome;

public class HomeUtils {
  public static Light findLight(SmartHome smartHome, String lightId) {
    for (Room room : smartHome.getRooms()) {
      for (Light light : room.getLights()) {
        if (light.getId().equals(lightId)) {
          return light;
        }
      }
    }
    return null;
  }

  public static Door findDoor(SmartHome smartHome, String doorId) {
    for (Room room : smartHome.getRooms()) {
      for (Door door : room.getDoors()) {
        if (door.getId().equals(doorId)) {
          return door;
        }
      }
    }
    return null;
  }

  public static Room findDoorRoom(SmartHome smartHome, String doorId) {
    for (Room room : smartHome.getRooms()) {
      for (Door door : room.getDoors()) {
        if (door.getId().equals(doorId)) {
          return room;
        }
      }
    }
    return null;
  }
}
