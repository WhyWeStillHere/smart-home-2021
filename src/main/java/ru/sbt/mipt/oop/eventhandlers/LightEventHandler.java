package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.Event;
import ru.sbt.mipt.oop.homecomponents.HomeComponent;
import ru.sbt.mipt.oop.homecomponents.Light;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventHandler implements EventHandler {
  @Override
  public void handleEvent(SmartHome smartHome, Event event) {
    if (!isCorrectEvent(event)) return;

    SensorEvent sensorEvent = (SensorEvent) event;
    smartHome.execute((HomeComponent homeComponent) -> {
      if (!(homeComponent instanceof Light)) return;
      Light light = (Light) homeComponent;
      if (!sensorEvent.getObjectId().equals(light.getId())) return;

      if (sensorEvent.getType() == LIGHT_ON) {
        LightEventHandler.handleOn(light);
      } else if (sensorEvent.getType() == LIGHT_OFF) {
        LightEventHandler.handleOff(light);
      }
    });
  }

  private boolean isCorrectEvent(Event event) {
    if (!(event instanceof SensorEvent)) {
      return false;
    }
    SensorEvent sensorEvent = (SensorEvent) event;
    return sensorEvent.getType() == LIGHT_ON || sensorEvent.getType() == LIGHT_OFF;
  }

  private static void handleOff(Light light) {
    light.setOn(false);
    System.out.println("Light " + light.getId() + " was turned off.");
  }

  private static void handleOn(Light light) {
    light.setOn(true);
    System.out.println("Light " + light.getId() + " was turned on.");
  }
}
