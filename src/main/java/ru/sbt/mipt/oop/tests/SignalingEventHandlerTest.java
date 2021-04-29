package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.eventhandlers.DoorEventHandler;
import ru.sbt.mipt.oop.eventhandlers.SignalingEventHandler;
import ru.sbt.mipt.oop.homecomponents.SmartHome;
import ru.sbt.mipt.oop.utils.HomeUtils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignalingEventHandlerTest {
  @Test
  void handleActivate_noAlarm() {
    SmartHomeReader reader = new JsonSmartHomeReader(TestUtils.TEST_DATA_PATH + "smart-home-1.js");
    SmartHome smartHome = reader.readSmartHome();

    SignalingEventHandler eventHandler = new SignalingEventHandler();
    String alarmCode = "asdfasdfsf123134";
    SignalingEvent event = new SignalingEvent(SignalingEventType.ALARM_ACTIVATE, alarmCode);

    eventHandler.handleEvent(smartHome, event);

    assertFalse(smartHome.getSignaling().isAlarm());
  }

  @Test
  void handleActivate_alarm() {
    SmartHomeReader reader = new JsonSmartHomeReader(TestUtils.TEST_DATA_PATH + "smart-home-1.js");
    SmartHome smartHome = reader.readSmartHome();

    SignalingEventHandler eventHandler = new SignalingEventHandler();
    String alarmCode = "asdfasdfsf123134";
    SignalingEvent event = new SignalingEvent(SignalingEventType.ALARM_ACTIVATE, alarmCode);

    eventHandler.handleEvent(smartHome, event);
    eventHandler.handleEvent(smartHome, event);

    assertTrue(smartHome.getSignaling().isAlarm());
  }

  @Test
  void handleDeactivate_noAlarm() {
    SmartHomeReader reader = new JsonSmartHomeReader(TestUtils.TEST_DATA_PATH + "smart-home-1.js");
    SmartHome smartHome = reader.readSmartHome();

    SignalingEventHandler eventHandler = new SignalingEventHandler();
    String alarmCode = "asdfasdfsf123134";
    SignalingEvent event_1 = new SignalingEvent(SignalingEventType.ALARM_ACTIVATE, alarmCode);
    SignalingEvent event_2 = new SignalingEvent(SignalingEventType.ALARM_DEACTIVATE, alarmCode);

    eventHandler.handleEvent(smartHome, event_1);
    eventHandler.handleEvent(smartHome, event_2);

    assertFalse(smartHome.getSignaling().isAlarm());
  }

  @Test
  void handleDeactivate_alarm() {
    SmartHomeReader reader = new JsonSmartHomeReader(TestUtils.TEST_DATA_PATH + "smart-home-1.js");
    SmartHome smartHome = reader.readSmartHome();

    SignalingEventHandler eventHandler = new SignalingEventHandler();
    String alarmCode = "asdfasdfsf123134";
    SignalingEvent event_1 = new SignalingEvent(SignalingEventType.ALARM_ACTIVATE, alarmCode);
    SignalingEvent event_2 = new SignalingEvent(SignalingEventType.ALARM_DEACTIVATE, "adsfasf");

    eventHandler.handleEvent(smartHome, event_1);
    eventHandler.handleEvent(smartHome, event_2);

    assertTrue(smartHome.getSignaling().isAlarm());
  }
}
