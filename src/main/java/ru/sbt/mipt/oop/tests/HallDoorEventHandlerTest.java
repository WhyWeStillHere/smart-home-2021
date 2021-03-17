package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.JsonSmartHomeReader;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.SmartHomeReader;
import ru.sbt.mipt.oop.command_senders.DummyCommandSender;
import ru.sbt.mipt.oop.event_handlers.HallDoorEventHandler;
import ru.sbt.mipt.oop.home_components.SmartHome;
import ru.sbt.mipt.oop.utils.HomeUtils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HallDoorEventHandlerTest {
  @Test
  void handleEvent_doorOpen() {
    SmartHomeReader reader = new JsonSmartHomeReader(TestUtils.TEST_DATA_PATH + "smart-home-1.js");
    SmartHome smartHome = reader.readSmartHome();

    String doorId = "4";
    HallDoorEventHandler eventHandler = new HallDoorEventHandler(new DummyCommandSender());
    SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, doorId);

    eventHandler.handleEvent(smartHome, event);

    assertFalse(HomeUtils.findDoor(smartHome, doorId).isOpen());
  }

  @Test
  void handleEvent_doorClose() {
    SmartHomeReader reader = new JsonSmartHomeReader(TestUtils.TEST_DATA_PATH + "smart-home-1.js");
    SmartHome smartHome = reader.readSmartHome();

    String doorId = "4";
    HallDoorEventHandler eventHandler = new HallDoorEventHandler(new DummyCommandSender());
    SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, doorId);

    eventHandler.handleEvent(smartHome, event);

    for (int lightId = 1; lightId <= 9; ++lightId) {
      assertFalse(HomeUtils.findLight(smartHome, Integer.toString(lightId)).isOn());
    }
  }
}
