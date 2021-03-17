package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.utils.HomeUtils;
import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventHandler implements EventHandler {
  @Override
  public void handleEvent(SmartHome smartHome, SensorEvent event) {
    if (!isCorrectEvent(event)) return;

    Light light = HomeUtils.findLight(smartHome, event.getObjectId());
    if (light == null) return;

    if (event.getType() == LIGHT_ON) {
      handleOn(light);
    } else if (event.getType() == LIGHT_OFF) {
      handleOff(light);
    }
  }

  private boolean isCorrectEvent(SensorEvent event) {
    return event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF;
  }

  private void handleOff(Light light) {
    light.setOn(false);
    System.out.println("Light " + light.getId() + " was turned off.");
  }

  private void handleOn(Light light) {
    light.setOn(true);
    System.out.println("Light " + light.getId() + " was turned on.");
  }
}
