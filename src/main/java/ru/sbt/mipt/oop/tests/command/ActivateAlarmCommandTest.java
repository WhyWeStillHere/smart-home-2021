package ru.sbt.mipt.oop.tests.command;

import org.junit.jupiter.api.Test;
import org.springframework.aop.scope.ScopedObject;
import ru.sbt.mipt.oop.JsonSmartHomeReader;
import ru.sbt.mipt.oop.SmartHomeReader;
import ru.sbt.mipt.oop.commands.ActivateAlarmCommand;
import ru.sbt.mipt.oop.commands.Command;
import ru.sbt.mipt.oop.homecomponents.SmartHome;
import ru.sbt.mipt.oop.tests.TestUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ActivateAlarmCommandTest {
  @Test
  void execute_deactivatedSignaling() {
    SmartHomeReader reader = new JsonSmartHomeReader(TestUtils.TEST_DATA_PATH + "smart-home-1.js");
    SmartHome smartHome = reader.readSmartHome();
    Command command = new ActivateAlarmCommand(smartHome);

    command.execute();

    assertTrue(smartHome.getSignaling().isAlarm());
  }

  @Test
  void execute_activatedSignaling() {
    SmartHomeReader reader = new JsonSmartHomeReader(TestUtils.TEST_DATA_PATH + "smart-home-1.js");
    SmartHome smartHome = reader.readSmartHome();
    smartHome.getSignaling().activate("1234");
    Command command = new ActivateAlarmCommand(smartHome);

    command.execute();

    assertTrue(smartHome.getSignaling().isAlarm());
  }
}
