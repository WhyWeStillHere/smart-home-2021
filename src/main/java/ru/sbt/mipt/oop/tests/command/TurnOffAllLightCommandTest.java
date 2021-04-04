package ru.sbt.mipt.oop.tests.command;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.JsonSmartHomeReader;
import ru.sbt.mipt.oop.SmartHomeReader;
import ru.sbt.mipt.oop.commands.Command;
import ru.sbt.mipt.oop.commands.TurnOffAllLightCommand;
import ru.sbt.mipt.oop.commands.TurnOnHallLightCommand;
import ru.sbt.mipt.oop.homecomponents.Light;
import ru.sbt.mipt.oop.homecomponents.Room;
import ru.sbt.mipt.oop.homecomponents.SmartHome;
import ru.sbt.mipt.oop.tests.TestUtils;
import ru.sbt.mipt.oop.utils.HomeUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TurnOffAllLightCommandTest {
  @Test
  void execute() {
    SmartHomeReader reader = new JsonSmartHomeReader(TestUtils.TEST_DATA_PATH + "smart-home-1.js");
    SmartHome smartHome = reader.readSmartHome();
    Command command = new TurnOffAllLightCommand(smartHome);

    command.execute();

    List<Light> lights = HomeUtils.getLights(smartHome);
    for (Light light : lights) {
      assertFalse(light.isOn());
    }
  }
}
