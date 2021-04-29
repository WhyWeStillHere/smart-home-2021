package ru.sbt.mipt.oop.libadapter;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.Event;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EventAdapter {
  private final Map<String, SensorEventType> eventTypeMap;

  public EventAdapter(Map<String, SensorEventType> eventTypeMap) {
    this.eventTypeMap = eventTypeMap;
  }

  public Event adaptEvent(CCSensorEvent event) {
    return new SensorEvent(eventTypeMap.getOrDefault(event.getEventType(), SensorEventType.UNKNOWN), event.getObjectId());
  }
}
