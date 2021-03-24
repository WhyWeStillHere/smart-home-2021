package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.commandsenders.DummyCommandSender;
import ru.sbt.mipt.oop.eventhandlers.DoorEventHandler;
import ru.sbt.mipt.oop.eventhandlers.EventHandler;
import ru.sbt.mipt.oop.eventhandlers.HallDoorEventHandler;
import ru.sbt.mipt.oop.eventhandlers.LightEventHandler;
import ru.sbt.mipt.oop.homecomponents.SmartHome;
import ru.sbt.mipt.oop.utils.HomeUtils;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventManagerTest {
  @Test
  void HandleEvent_1() {
    SmartHomeReader reader = new JsonSmartHomeReader(TestUtils.TEST_DATA_PATH + "smart-home-1.js");
    SmartHome smartHome = reader.readSmartHome();
    Collection<EventHandler> handlers = Arrays.asList(
        new LightEventHandler(),
        new DoorEventHandler(),
        new HallDoorEventHandler(new DummyCommandSender())
    );
    EventManager eventManager = new EventManager(smartHome, handlers);

    String doorId = "1";
    SensorEvent doorEvent = new SensorEvent(SensorEventType.DOOR_OPEN, doorId);
    eventManager.HandleEvent(doorEvent);
    assertTrue(HomeUtils.findDoor(smartHome, doorId).isOpen());

    String lightId = "1";
    SensorEvent lightEvent = new SensorEvent(SensorEventType.LIGHT_ON, lightId);
    eventManager.HandleEvent(lightEvent);
    assertTrue(HomeUtils.findLight(smartHome, lightId).isOn());
  }

  @Test
  void HandleEvent_2() {
    SmartHomeReader reader = new JsonSmartHomeReader(TestUtils.TEST_DATA_PATH + "smart-home-1.js");
    SmartHome smartHome = reader.readSmartHome();
    Collection<EventHandler> handlers = Arrays.asList(
        new LightEventHandler(),
        new DoorEventHandler(),
        new HallDoorEventHandler(new DummyCommandSender())
    );
    EventManager eventManager = new EventManager(smartHome, handlers);

    String firstLightId = "1";
    SensorEvent lightEvent = new SensorEvent(SensorEventType.LIGHT_ON, firstLightId);
    eventManager.HandleEvent(lightEvent);
    assertTrue(HomeUtils.findLight(smartHome, firstLightId).isOn());

    String doorId = "4";
    SensorEvent doorEvent_1 = new SensorEvent(SensorEventType.DOOR_OPEN, doorId);
    eventManager.HandleEvent(doorEvent_1);
    assertTrue(HomeUtils.findDoor(smartHome, doorId).isOpen());

    SensorEvent doorEvent_2 = new SensorEvent(SensorEventType.DOOR_CLOSED, doorId);
    eventManager.HandleEvent(doorEvent_2);
    assertFalse(HomeUtils.findDoor(smartHome, doorId).isOpen());

    for (int lightId = 1; lightId <= 9; ++lightId) {
      assertFalse(HomeUtils.findLight(smartHome, Integer.toString(lightId)).isOn());
    }

  }
}
