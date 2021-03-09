package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.List;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class EventManager {
  public EventManager(SmartHome smartHome) {
    this.smartHome = smartHome;
  }

  private final SmartHome smartHome;

  public void HandleEvent(SensorEvent event) {
    System.out.println("Got event: " + event);

    List<EventHandler> eventHandlers = new ArrayList<EventHandler>();
    if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
      // событие от источника света
      for (Room room : smartHome.getRooms()) {
        for (Light light : room.getLights()) {
          if (light.getId().equals(event.getObjectId())) {
            eventHandlers.add(new LightEventHandler(light, room));
          }
        }
      }
    } else if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
      // событие от двери
      for (Room room : smartHome.getRooms()) {
        for (Door door : room.getDoors()) {
          if (door.getId().equals(event.getObjectId())) {
            eventHandlers.add(new DoorEventHandler(room, door, smartHome));
          }
        }
      }
    }

    for (EventHandler eventHandler : eventHandlers) {
      eventHandler.handle(event);
    }
  }
}
