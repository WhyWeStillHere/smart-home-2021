package ru.sbt.mipt.oop.remotecontrol;

import rc.RemoteControl;
import ru.sbt.mipt.oop.commands.Command;

import java.util.*;

public class MappedRemoteControl implements RemoteControl {
  private final Map<String, Command> buttonMappings = new HashMap<>();
  private final Set<String> availableButtons;

  public MappedRemoteControl(Set<String> availableButtons) {
    this.availableButtons = availableButtons;
  }

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
