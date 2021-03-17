package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.JsonSmartHomeReader;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.SmartHomeReader;
import ru.sbt.mipt.oop.event_handlers.DoorEventHandler;
import ru.sbt.mipt.oop.event_handlers.LightEventHandler;
import ru.sbt.mipt.oop.home_components.SmartHome;
import ru.sbt.mipt.oop.utils.HomeUtils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DoorEventHandlerTest {
  @Test
  void handleEvent_doorOpen() {
    SmartHomeReader reader = new JsonSmartHomeReader(TestUtils.TEST_DATA_PATH + "smart-home-1.js");
    SmartHome smartHome = reader.readSmartHome();

    String doorId = "1";
    DoorEventHandler eventHandler = new DoorEventHandler();
    SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, doorId);

    eventHandler.handleEvent(smartHome, event);

    assertTrue(HomeUtils.findDoor(smartHome, doorId).isOpen());
  }

  @Test
  void handleEvent_doorClose() {
    SmartHomeReader reader = new JsonSmartHomeReader(TestUtils.TEST_DATA_PATH + "smart-home-1.js");
    SmartHome smartHome = reader.readSmartHome();

    String doorId = "3";
    DoorEventHandler eventHandler = new DoorEventHandler();
    SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, doorId);

    eventHandler.handleEvent(smartHome, event);

    assertFalse(HomeUtils.findDoor(smartHome, doorId).isOpen());
  }
}
