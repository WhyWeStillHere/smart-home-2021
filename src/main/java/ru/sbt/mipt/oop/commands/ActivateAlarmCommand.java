package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.homecomponents.SmartHome;

public class ActivateAlarmCommand extends SmartHomeCommand {
  public ActivateAlarmCommand(SmartHome smartHome) {
    super(smartHome);
  }

  @Override
  public void execute() {
    smartHome.getSignaling().alarm();
  }
}
