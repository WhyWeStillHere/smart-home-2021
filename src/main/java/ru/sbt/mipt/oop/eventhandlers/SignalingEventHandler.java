package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.Event;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SignalingEvent;
import ru.sbt.mipt.oop.homecomponents.Door;
import ru.sbt.mipt.oop.homecomponents.HomeComponent;
import ru.sbt.mipt.oop.homecomponents.SmartHome;
import ru.sbt.mipt.oop.signaling.Signaling;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;
import static ru.sbt.mipt.oop.SignalingEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.SignalingEventType.ALARM_DEACTIVATE;

public class SignalingEventHandler implements EventHandler {
  @Override
  public void handleEvent(SmartHome smartHome, Event event) {
    if (!isCorrectEvent(event)) return;
    SignalingEvent signalingEvent = (SignalingEvent) event;

    Signaling signaling = smartHome.getSignaling();
    if (signalingEvent.getType() == ALARM_ACTIVATE) {
      signaling.Activate(signalingEvent.getCode());
    } else if (signalingEvent.getType() == ALARM_DEACTIVATE) {
      signaling.Deactivate(signalingEvent.getCode());
    }
  }

  private boolean isCorrectEvent(Event event) {
    return event instanceof SignalingEvent;
  }
}
