package ru.sbt.mipt.oop.utils;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.homecomponents.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

  private static class FindRoomAction implements Action {
    private Room foundRoom = null;
    private final String roomName;

    public FindRoomAction(String roomName) {
      this.roomName = roomName;
    }

    public Room getRoom() {
      return foundRoom;
    }

    @Override
    public void applyAction(HomeComponent homeComponent) {
      if (!(homeComponent instanceof Room)) {
        return;
      }

      Room room = (Room) homeComponent;
      if (room.getName().equals(roomName)) {
        foundRoom = room;
      }
    }
  }

  static class FindAllObjects implements Action {
    private final Class homeComponentClass;
    private final List<HomeComponent> components = new ArrayList<HomeComponent>();

    public List<HomeComponent> getComponents() {
      return components;
    }

    public FindAllObjects(Class homeComponentClass) {
      this.homeComponentClass = homeComponentClass;
    }

    @Override
    public void applyAction(HomeComponent homeComponent) {
      if (!(homeComponent.getClass().equals(homeComponentClass))) {
        return;
      }

      components.add(homeComponent);
    }
  }

  public static Light findLight(HomeComponent homeComponent, String lightId) {
    FindLightAction findLightAction = new FindLightAction(lightId);
    homeComponent.execute(findLightAction);
    return findLightAction.getLight();
  }

  public static Door findDoor(HomeComponent homeComponent, String doorId) {
    FindDoorAction findDoorAction = new FindDoorAction(doorId);
    homeComponent.execute(findDoorAction);
    return findDoorAction.getDoor();
  }

  public static Room findRoom(HomeComponent homeComponent, String roomName) {
    FindRoomAction findRoomAction = new FindRoomAction(roomName);
    homeComponent.execute(findRoomAction);
    return findRoomAction.getRoom();
  }

  public static List<Light> getLights(HomeComponent homeComponent_arg) {
    FindAllObjects findAllLights = new FindAllObjects(Light.class);
    homeComponent_arg.execute(findAllLights);
    return findAllLights.getComponents()
        .stream()
        .map(homeComponent -> (Light) homeComponent)
        .collect(Collectors.toList());
  }

  public static List<Door> getDoors(HomeComponent homeComponent_arg) {
    FindAllObjects findAllDoors = new FindAllObjects(Door.class);
    homeComponent_arg.execute(findAllDoors);
    return findAllDoors.getComponents()
        .stream()
        .map(homeComponent -> (Door) homeComponent)
        .collect(Collectors.toList());
  }
}
