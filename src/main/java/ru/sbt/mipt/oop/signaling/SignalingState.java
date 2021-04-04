package ru.sbt.mipt.oop.signaling;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.eventhandlers.EventHandler;

public interface SignalingState {
  void activate(String code);
  void deactivate(String code);
  boolean isAlarm();
}
