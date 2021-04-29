package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.homecomponents.SmartHome;

public class ActivateSignalingCommand implements Command {
  private final String alarmCode;
  private final SmartHome smartHome;

  public ActivateSignalingCommand(SmartHome smartHome, String alarmCode) {
    this.smartHome = smartHome;
    this.alarmCode = alarmCode;
  }

  public ActivateSignalingCommand(SmartHome smartHome) {
    this.smartHome = smartHome;
    this.alarmCode = "0000";
  }

  @Override
  public void execute() {
    // Activating with default code
    smartHome.getSignaling().activate(alarmCode);
  }
}
