package ru.sbt.mipt.oop.remotecontrol;

import rc.RemoteControl;
import ru.sbt.mipt.oop.commands.Command;
import ru.sbt.mipt.oop.commands.SmartHomeCommand;

import java.util.*;

public class MappedRemoteControl implements RemoteControl {
  private final Map<String, Command> buttonMappings = new HashMap<>();
  private final Set<String> availableButtons = new HashSet<>(Arrays.asList("A", "B", "C", "D", "1", "2", "3", "4"));

  @Override
  public void onButtonPressed(String buttonCode) {
    if (buttonMappings.containsKey(buttonCode)) {
      buttonMappings.get(buttonCode).execute();
    }
  }

  public void registerButtonCommand(String buttonName, Command command) {
    if (!availableButtons.contains(buttonName))
      return;

    buttonMappings.put(buttonName, command);
  }
}
