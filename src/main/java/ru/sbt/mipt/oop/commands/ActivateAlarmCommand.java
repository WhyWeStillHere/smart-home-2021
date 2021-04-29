package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.homecomponents.SmartHome;

public class ActivateAlarmCommand implements Command {
  private final SmartHome smartHome;
  public ActivateAlarmCommand(SmartHome smartHome) {
    this.smartHome = smartHome;
  }

  @Override
  public void execute() {
    smartHome.getSignaling().alarm();
  }
}
