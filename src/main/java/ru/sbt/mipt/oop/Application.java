package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.commandsenders.DummyCommandSender;
import ru.sbt.mipt.oop.eventcreators.RandomSensorEventCreator;
import ru.sbt.mipt.oop.eventhandlers.*;
import ru.sbt.mipt.oop.homecomponents.SmartHome;
import ru.sbt.mipt.oop.libadapter.EventHandlerAdapter;
import ru.sbt.mipt.oop.signaling.AlarmHandlerDecorator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Application {

  public static void main(String... args) {
    SmartHomeReader reader = new JsonSmartHomeReader("smart-home-1.js");
    SmartHome smartHome = reader.readSmartHome();

    Collection<EventHandler> handlers = Arrays.asList(
        new SignalingEventHandler(),
        new AlarmHandlerDecorator(new LightEventHandler()),
        new AlarmHandlerDecorator(new DoorEventHandler()),
        new AlarmHandlerDecorator(new HallDoorEventHandler(new DummyCommandSender()))
    );

    SensorEventsManager sensorEventsManager = new SensorEventsManager();

    EventHandlerAdapter adapter = new EventHandlerAdapter();
    for (EventHandler eventHandler : handlers) {
      sensorEventsManager.registerEventHandler(adapter.adaptHandler(smartHome, eventHandler));
    }

    sensorEventsManager.start();
  }
}
