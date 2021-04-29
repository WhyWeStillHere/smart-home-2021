package ru.sbt.mipt.oop.tests.command;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.JsonSmartHomeReader;
import ru.sbt.mipt.oop.SmartHomeReader;
import ru.sbt.mipt.oop.commands.ActivateSignalingCommand;
import ru.sbt.mipt.oop.commands.CloseHallDoorCommand;
import ru.sbt.mipt.oop.commands.Command;
import ru.sbt.mipt.oop.homecomponents.Door;
import ru.sbt.mipt.oop.homecomponents.Room;
import ru.sbt.mipt.oop.homecomponents.SmartHome;
import ru.sbt.mipt.oop.tests.TestUtils;
import ru.sbt.mipt.oop.utils.HomeUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;


public class CloseHallDoorCommandTest {
  @Test
  void execute() {
    SmartHomeReader reader = new JsonSmartHomeReader(TestUtils.TEST_DATA_PATH + "smart-home-1.js");
    SmartHome smartHome = reader.readSmartHome();
    Command command = new CloseHallDoorCommand(smartHome);

    command.execute();

    Room hall = HomeUtils.findRoom(smartHome, "hall");
    List<Door> hallDoors = HomeUtils.getDoors(hall);
    for (Door door : hallDoors) {
      assertFalse(door.isOpen());
    }
  }
}
