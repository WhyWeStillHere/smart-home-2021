package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.homecomponents.SmartHome;

public class ActivateSignalingCommand extends SmartHomeCommand {
  private final String alarmCode;

  public ActivateSignalingCommand(SmartHome smartHome, String alarmCode) {
    super(smartHome);
    this.alarmCode = alarmCode;
  }

  public ActivateSignalingCommand(SmartHome smartHome) {
    super(smartHome);
    this.alarmCode = "0000";
  }

  @Override
  public void execute() {
    // Activating with default code
    smartHome.getSignaling().activate(alarmCode);
  }
}
