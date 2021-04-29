package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.homecomponents.HomeComponent;
import ru.sbt.mipt.oop.homecomponents.Light;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

public class TurnOffAllLightCommand implements Command {
  private final SmartHome smartHome;
  public TurnOffAllLightCommand(SmartHome smartHome) {
    this.smartHome = smartHome;
  }


  @Override
  public void execute() {
    smartHome.execute((HomeComponent homeComponent) -> {
      if (!(homeComponent instanceof Light)) return;
      Light light = (Light) homeComponent;
      light.setOn(false);
    });
  }
}
