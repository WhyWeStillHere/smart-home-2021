package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventHandler implements EventHandler {
  Light light;
  Room room;

  public LightEventHandler(Light light, Room room) {
    this.light = light;
    this.room = room;
  }

  public void handle(SensorEvent event) {
    if (event.getType() == LIGHT_ON) {
      handleOn();
    } else if (event.getType() == LIGHT_OFF) {
      handleOff();
    }
  }

  private void handleOff() {
    light.setOn(false);
    System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
  }

  private void handleOn() {
    light.setOn(true);
    System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
  }
}
