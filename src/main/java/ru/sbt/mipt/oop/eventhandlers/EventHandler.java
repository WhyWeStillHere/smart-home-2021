package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.Event;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

public interface EventHandler {
  void handleEvent(SmartHome smartHome, Event event);
}
