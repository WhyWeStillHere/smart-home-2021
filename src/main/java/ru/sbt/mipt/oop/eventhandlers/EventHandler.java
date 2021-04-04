package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

public interface EventHandler {
  void handleEvent(SmartHome smartHome, SensorEvent event);
}
