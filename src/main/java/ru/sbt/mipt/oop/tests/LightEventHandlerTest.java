package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.event_handlers.LightEventHandler;
import ru.sbt.mipt.oop.home_components.Light;
import ru.sbt.mipt.oop.home_components.Room;
import ru.sbt.mipt.oop.home_components.SmartHome;
import ru.sbt.mipt.oop.utils.HomeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LightEventHandlerTest {
  @Test
  void handleEvent_on() {
    SmartHomeReader reader = new JsonSmartHomeReader(TestUtils.TEST_DATA_PATH + "smart-home-1.js");
    SmartHome smartHome = reader.readSmartHome();

    String lightId = "1";
    LightEventHandler eventHandler = new LightEventHandler();
    SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, lightId);

    eventHandler.handleEvent(smartHome, event);

    assertTrue(HomeUtils.findLight(smartHome, lightId).isOn());
  }

  @Test
  void handleEvent_off() {
    SmartHomeReader reader = new JsonSmartHomeReader(TestUtils.TEST_DATA_PATH + "smart-home-1.js");
    SmartHome smartHome = reader.readSmartHome();

    String lightId = "2";
    LightEventHandler eventHandler = new LightEventHandler();
    SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, lightId);

    eventHandler.handleEvent(smartHome, event);

    assertFalse(HomeUtils.findLight(smartHome, lightId).isOn());
  }
}
