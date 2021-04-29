package ru.sbt.mipt.oop.homecomponents;

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

  public String getName() {
    return name;
  }

  @Override
  public void execute(Action action) {
    action.applyAction(this);

    for (Light light : lights) {
      light.execute(action);
    }
    for (Door door : doors) {
      door.execute(action);
    }
  }
}
