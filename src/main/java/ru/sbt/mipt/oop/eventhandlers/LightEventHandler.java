package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.homecomponents.HomeComponent;
import ru.sbt.mipt.oop.homecomponents.Light;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventHandler implements EventHandler {
  @Override
  public void handleEvent(SmartHome smartHome, SensorEvent event) {
    if (!isCorrectEvent(event)) return;

    smartHome.execute((HomeComponent homeComponent) -> {
      if (!(homeComponent instanceof Light)) return;
      Light light = (Light) homeComponent;
      if (!event.getObjectId().equals(light.getId())) return;

      if (event.getType() == LIGHT_ON) {
        LightEventHandler.handleOn(light);
      } else if (event.getType() == LIGHT_OFF) {
        LightEventHandler.handleOff(light);
      }
    });
  }

  private boolean isCorrectEvent(SensorEvent event) {
    return event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF;
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
