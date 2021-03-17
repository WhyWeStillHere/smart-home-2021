package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.command_senders.DummyCommandSender;
import ru.sbt.mipt.oop.event_creators.RandomSensorEventCreator;
import ru.sbt.mipt.oop.event_handlers.DoorEventHandler;
import ru.sbt.mipt.oop.event_handlers.EventHandler;
import ru.sbt.mipt.oop.event_handlers.HallDoorEventHandler;
import ru.sbt.mipt.oop.event_handlers.LightEventHandler;
import ru.sbt.mipt.oop.home_components.SmartHome;

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
