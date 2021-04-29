package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.homecomponents.*;

public class TurnOnHallLightCommand extends SmartHomeCommand {
  public TurnOnHallLightCommand(SmartHome smartHome) {
    super(smartHome);
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
