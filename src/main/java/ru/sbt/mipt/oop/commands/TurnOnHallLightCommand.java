package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.homecomponents.*;

public class TurnOnHallLightCommand implements Command {
  private final SmartHome smartHome;
  public TurnOnHallLightCommand(SmartHome smartHome) {
    this.smartHome = smartHome;
  }

  @Override
  public void execute() {
    smartHome.execute((HomeComponent homeComponent) -> {
      if (!(homeComponent instanceof Room)) return;
      Room room = (Room) homeComponent;

      if (room.getName().equals("hall")) {
        room.execute((HomeComponent homeComponent_) -> {
          if (!(homeComponent_ instanceof Light)) return;
          Light light = (Light) homeComponent_;
          light.setOn(true);
        });
      }
    });
  }
}
