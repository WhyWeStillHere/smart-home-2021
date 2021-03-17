package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.home_components.SmartHome;

public interface EventHandler {
  void handleEvent(SmartHome smartHome, SensorEvent event);
}
