package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.commandsenders.DummyCommandSender;
import ru.sbt.mipt.oop.eventcreators.RandomSensorEventCreator;
import ru.sbt.mipt.oop.eventhandlers.DoorEventHandler;
import ru.sbt.mipt.oop.eventhandlers.EventHandler;
import ru.sbt.mipt.oop.eventhandlers.HallDoorEventHandler;
import ru.sbt.mipt.oop.eventhandlers.LightEventHandler;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

import java.util.Arrays;
import java.util.Collection;

public class Application {

  public static void main(String... args) {
    SmartHomeReader reader = new JsonSmartHomeReader("smart-home-1.js");
    SmartHome smartHome = reader.readSmartHome();

    Collection<EventHandler> handlers = Arrays.asList(
        new LightEventHandler(),
        new DoorEventHandler(),
        new HallDoorEventHandler(new DummyCommandSender())
    );
    SensorEventLoop eventLoop = new SensorEventLoop(
        new EventManager(smartHome, handlers),
        new RandomSensorEventCreator());
    eventLoop.startLoop();
  }
}
