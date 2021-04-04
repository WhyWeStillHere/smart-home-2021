package ru.sbt.mipt.oop.tests.command;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.JsonSmartHomeReader;
import ru.sbt.mipt.oop.SmartHomeReader;
import ru.sbt.mipt.oop.commands.ActivateAlarmCommand;
import ru.sbt.mipt.oop.commands.ActivateSignalingCommand;
import ru.sbt.mipt.oop.commands.Command;
import ru.sbt.mipt.oop.homecomponents.SmartHome;
import ru.sbt.mipt.oop.tests.TestUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ActivateSignalingCommandTest {
  @Test
  void execute_deactivatedSignaling() {
    SmartHomeReader reader = new JsonSmartHomeReader(TestUtils.TEST_DATA_PATH + "smart-home-1.js");
    SmartHome smartHome = reader.readSmartHome();
    Command command = new ActivateSignalingCommand(smartHome);

    command.execute();

    assertFalse(smartHome.getSignaling().isAlarm());

    smartHome.getSignaling().activate("1234");

    assertTrue(smartHome.getSignaling().isAlarm());
  }
}
