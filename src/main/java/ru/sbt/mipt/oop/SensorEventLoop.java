package ru.sbt.mipt.oop;

public class SensorEventLoop {
  private final EventManager eventManager;
  private final SensorEventCreator eventCreator;

  public SensorEventLoop(EventManager eventManager, SensorEventCreator eventCreator) {
    this.eventManager = eventManager;
    this.eventCreator = eventCreator;
  }

  public void startLoop() {
    SensorEvent event = eventCreator.getNextEvent();

    while (event != null) {
      eventManager.HandleEvent(event);
      event = eventCreator.getNextEvent();
    }
  }
}
