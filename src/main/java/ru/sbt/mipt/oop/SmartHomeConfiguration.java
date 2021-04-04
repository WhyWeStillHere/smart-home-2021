package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.commandsenders.DummyCommandSender;
import ru.sbt.mipt.oop.eventhandlers.*;
import ru.sbt.mipt.oop.homecomponents.SmartHome;
import ru.sbt.mipt.oop.libadapter.EventHandlerAdapter;
import ru.sbt.mipt.oop.signaling.AlarmHandlerDecorator;

import java.util.Arrays;
import java.util.Collection;

@Configuration
public class SmartHomeConfiguration {
  @Bean
  SensorEventsManager sensorEventsManager() {
    SmartHomeReader reader = new JsonSmartHomeReader("smart-home-1.js");
    SmartHome smartHome = reader.readSmartHome();

    SensorEventsManager sensorEventsManager = new SensorEventsManager();

    Collection<EventHandler> handlers = Arrays.asList(
        new SignalingEventHandler(),
        new AlarmHandlerDecorator(new LightEventHandler()),
        new AlarmHandlerDecorator(new DoorEventHandler()),
        new AlarmHandlerDecorator(new HallDoorEventHandler(new DummyCommandSender()))
    );

    EventHandlerAdapter adapter = new EventHandlerAdapter();
    for (EventHandler eventHandler : handlers) {
      sensorEventsManager.registerEventHandler(adapter.adaptHandler(smartHome, eventHandler));
    }
    return sensorEventsManager;
  }
}
