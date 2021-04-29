package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.Test;
import rc.RemoteControl;
import ru.sbt.mipt.oop.JsonSmartHomeReader;
import ru.sbt.mipt.oop.SmartHomeReader;
import ru.sbt.mipt.oop.commands.Command;
import ru.sbt.mipt.oop.commands.TurnOffAllLightCommand;
import ru.sbt.mipt.oop.commands.TurnOnAllLightCommand;
import ru.sbt.mipt.oop.homecomponents.Light;
import ru.sbt.mipt.oop.homecomponents.SmartHome;
import ru.sbt.mipt.oop.remotecontrol.MappedRemoteControl;
import ru.sbt.mipt.oop.utils.HomeUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MappedRemoteControlTest {
  @Test
  void registerButton() {
    SmartHomeReader reader = new JsonSmartHomeReader(TestUtils.TEST_DATA_PATH + "smart-home-1.js");
    SmartHome smartHome = reader.readSmartHome();
    Command command = new TurnOffAllLightCommand(smartHome);
    MappedRemoteControl remoteControl = new MappedRemoteControl(new HashSet<>(Arrays.asList("A", "B", "C", "D", "1", "2", "3", "4")));
    remoteControl.registerButtonCommand("A", command);

    remoteControl.onButtonPressed("A");

    List<Light> lights = HomeUtils.getLights(smartHome);
    for (Light light : lights) {
      assertFalse(light.isOn());
    }
  }

  @Test
  void registerButton_twoRegistrations_1() {
    SmartHomeReader reader = new JsonSmartHomeReader(TestUtils.TEST_DATA_PATH + "smart-home-1.js");
    SmartHome smartHome = reader.readSmartHome();
    Command command_1 = new TurnOffAllLightCommand(smartHome);
    Command command_2 = new TurnOnAllLightCommand(smartHome);
    MappedRemoteControl remoteControl = new MappedRemoteControl(new HashSet<>(Arrays.asList("A", "B", "C", "D", "1", "2", "3", "4")));
    remoteControl.registerButtonCommand("A", command_1);
    remoteControl.registerButtonCommand("A", command_2);

    remoteControl.onButtonPressed("A");

    List<Light> lights = HomeUtils.getLights(smartHome);
    for (Light light : lights) {
      assertTrue(light.isOn());
    }
  }

  @Test
  void registerButton_twoRegistrations_2() {
    SmartHomeReader reader = new JsonSmartHomeReader(TestUtils.TEST_DATA_PATH + "smart-home-1.js");
    SmartHome smartHome = reader.readSmartHome();
    Command command_1 = new TurnOffAllLightCommand(smartHome);
    Command command_2 = new TurnOnAllLightCommand(smartHome);
    MappedRemoteControl remoteControl = new MappedRemoteControl(new HashSet<>(Arrays.asList("A", "B", "C", "D", "1", "2", "3", "4")));
    remoteControl.registerButtonCommand("A", command_1);
    remoteControl.registerButtonCommand("B", command_2);

    remoteControl.onButtonPressed("A");

    List<Light> lights = HomeUtils.getLights(smartHome);
    for (Light light : lights) {
      assertFalse(light.isOn());
    }

    remoteControl.onButtonPressed("B");

    lights = HomeUtils.getLights(smartHome);
    for (Light light : lights) {
      assertTrue(light.isOn());
    }

    remoteControl.onButtonPressed("A");

    lights = HomeUtils.getLights(smartHome);
    for (Light light : lights) {
      assertFalse(light.isOn());
    }
  }
}
