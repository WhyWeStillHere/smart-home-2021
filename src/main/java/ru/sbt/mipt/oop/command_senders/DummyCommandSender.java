package ru.sbt.mipt.oop.command_senders;

import ru.sbt.mipt.oop.SensorCommand;
import ru.sbt.mipt.oop.command_senders.CommandSender;

public class DummyCommandSender implements CommandSender {
  @Override
  public void sendCommand(SensorCommand command) {
    System.out.println("Pretend we're sending command " + command);
  }
}
