package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.homecomponents.SmartHome;

public abstract class SmartHomeCommand implements Command {
  protected final SmartHome smartHome;

  public SmartHomeCommand(SmartHome smartHome) {
    this.smartHome = smartHome;
  }
}
