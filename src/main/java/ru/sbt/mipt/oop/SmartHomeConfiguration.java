package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.commandsenders.DummyCommandSender;
import ru.sbt.mipt.oop.eventhandlers.*;
import ru.sbt.mipt.oop.homecomponents.SmartHome;
import ru.sbt.mipt.oop.libadapter.EventAdapter;
import ru.sbt.mipt.oop.libadapter.EventHandlerAdapter;
import ru.sbt.mipt.oop.signaling.AlarmHandlerDecorator;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class SmartHomeConfiguration {
  @Bean
  SensorEventsManager sensorEventsManager() {
    SensorEventsManager sensorEventsManager = new SensorEventsManager();

    SmartHomeReader reader = smartHomeReader();
    SmartHome smartHome = reader.readSmartHome();
    Collection<EventHandler> handlers = eventHandlers();
    EventHandlerAdapter adapter = eventHandlerAdapter();
    EventAdapter eventAdapter = eventAdapter();

    for (EventHandler eventHandler : handlers) {
      sensorEventsManager.registerEventHandler(adapter.adaptHandler(smartHome, eventHandler, eventAdapter));
    }

    return sensorEventsManager;
  }

  @Bean
  Collection<EventHandler> eventHandlers() {
    return Arrays.asList(
        new SignalingEventHandler(),
        new AlarmHandlerDecorator(new LightEventHandler()),
        new AlarmHandlerDecorator(new DoorEventHandler()),
        new AlarmHandlerDecorator(new HallDoorEventHandler(new DummyCommandSender()))
    );
  }

  @Bean
  SmartHomeReader smartHomeReader() {
    return new JsonSmartHomeReader("smart-home-1.js");
  }

  @Bean
  EventHandlerAdapter eventHandlerAdapter() {
    return new EventHandlerAdapter();
  }

  @Bean
  EventAdapter eventAdapter() {
    return new EventAdapter(eventTypeMap());
  }

  @Bean
  Map<String, SensorEventType> eventTypeMap() {
    return Stream.of(
        new HashMap.SimpleImmutableEntry<>("LightIsOn", SensorEventType.LIGHT_ON),
        new HashMap.SimpleImmutableEntry<>("LightIsOff", SensorEventType.LIGHT_OFF),
        new HashMap.SimpleImmutableEntry<>("DoorIsOpen", SensorEventType.DOOR_OPEN),
        new HashMap.SimpleImmutableEntry<>("DoorIsClosed", SensorEventType.DOOR_CLOSED)
    ).collect(Collectors.toMap(HashMap.Entry::getKey, HashMap.Entry::getValue));
  }
}
