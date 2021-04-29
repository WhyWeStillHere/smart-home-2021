package ru.sbt.mipt.oop.tests.command;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.JsonSmartHomeReader;
import ru.sbt.mipt.oop.SmartHomeReader;
import ru.sbt.mipt.oop.commands.Command;
import ru.sbt.mipt.oop.commands.TurnOnAllLightCommand;
import ru.sbt.mipt.oop.homecomponents.Light;
import ru.sbt.mipt.oop.homecomponents.SmartHome;
import ru.sbt.mipt.oop.tests.TestUtils;
import ru.sbt.mipt.oop.utils.HomeUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TurnOnAllLightCommandTest {
  @Test
  void execute() {
    SmartHomeReader reader = new JsonSmartHomeReader(TestUtils.TEST_DATA_PATH + "smart-home-1.js");
    SmartHome smartHome = reader.readSmartHome();
    Command command = new TurnOnAllLightCommand(smartHome);

    command.execute();

    List<Light> lights = HomeUtils.getLights(smartHome);
    for (Light light : lights) {
      assertTrue(light.isOn());
    }
  }
}
