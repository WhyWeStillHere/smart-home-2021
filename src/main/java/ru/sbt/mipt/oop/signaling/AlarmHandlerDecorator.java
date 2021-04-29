package ru.sbt.mipt.oop.signaling;

import ru.sbt.mipt.oop.Event;
import ru.sbt.mipt.oop.eventhandlers.EventHandler;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

public class AlarmHandlerDecorator implements EventHandler {
  private final EventHandler decoratedHandler;

  public AlarmHandlerDecorator(EventHandler decoratedHandler) {
    this.decoratedHandler = decoratedHandler;
  }

  @Override
  public void handleEvent(SmartHome smartHome, Event event) {
    Signaling signaling = smartHome.getSignaling();
    if (!signaling.isAlarm()) {
      decoratedHandler.handleEvent(smartHome, event);
    } else {
      System.out.println("Sending sms");
    }
  }
}
