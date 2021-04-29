package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.homecomponents.HomeComponent;
import ru.sbt.mipt.oop.homecomponents.Light;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

public class TurnOnAllLightCommand extends SmartHomeCommand {
  public TurnOnAllLightCommand(SmartHome smartHome) {
    super(smartHome);
  }

  @Override
  public void execute() {
    smartHome.execute((HomeComponent homeComponent) -> {
      if (!(homeComponent instanceof Light)) return;
      Light light = (Light) homeComponent;
      light.setOn(true);
    });
  }
}
