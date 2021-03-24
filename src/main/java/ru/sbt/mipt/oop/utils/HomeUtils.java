package ru.sbt.mipt.oop.utils;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.homecomponents.*;

public class HomeUtils {
  private static class FindLightAction implements Action {
    private Light foundLight = null;
    private final String lightId;

    public FindLightAction(String lightId) {
      this.lightId = lightId;
    }

    public Light getLight() {
      return foundLight;
    }

    @Override
    public void applyAction(HomeComponent homeComponent) {
      if (!(homeComponent instanceof Light)) {
        return;
      }

      Light light = (Light) homeComponent;
      if (light.getId().equals(lightId)) {
        foundLight = light;
      }
    }
  }

  private static class FindDoorAction implements Action {
    private Door foundDoor = null;
    private final String doorId;

    public FindDoorAction(String doorId) {
      this.doorId = doorId;
    }

    public Door getDoor() {
      return foundDoor;
    }

    @Override
    public void applyAction(HomeComponent homeComponent) {
      if (!(homeComponent instanceof Door)) {
        return;
      }

      Door door = (Door) homeComponent;
      if (door.getId().equals(doorId)) {
        foundDoor = door;
      }
    }
  }

  public static Light findLight(SmartHome smartHome, String lightId) {
    FindLightAction findLightAction = new FindLightAction(lightId);
    smartHome.execute(findLightAction);
    return findLightAction.getLight();
  }

  public static Door findDoor(SmartHome smartHome, String doorId) {
    FindDoorAction findDoorAction = new FindDoorAction(doorId);
    smartHome.execute(findDoorAction);
    return findDoorAction.getDoor();
  }
}
