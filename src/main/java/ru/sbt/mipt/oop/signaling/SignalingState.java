package ru.sbt.mipt.oop.signaling;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.eventhandlers.EventHandler;

public interface SignalingState {
  void Activate(String code);
  void Deactivate(String code);
  boolean isAlarm();
}
