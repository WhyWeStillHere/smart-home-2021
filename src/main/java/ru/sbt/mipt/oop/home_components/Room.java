package ru.sbt.mipt.oop.home_components;

import ru.sbt.mipt.oop.Action;

import java.util.Collection;

public class Room implements HomeComponent {
  private Collection<Light> lights;
  private Collection<Door> doors;
  private String name;

  public Room(Collection<Light> lights, Collection<Door> doors, String name) {
    this.lights = lights;
    this.doors = doors;
    this.name = name;
  }

  public Collection<Light> getLights() {
    return lights;
  }

  public Collection<Door> getDoors() {
    return doors;
  }


  public boolean hasDoor(String doorId) {
    for (Door door: doors) {
      if (door.getId().equals(doorId)) {
        return true;
      }
    }
    return false;
  }

  public String getName() {
    return name;
  }

  @Override
  public void execute(Action action) {
    for (Light light : lights) {
      action.applyAction(light);
      light.execute(action);
    }
    for (Door door : doors) {
      action.applyAction(door);
      door.execute(action);
    }
  }
}
