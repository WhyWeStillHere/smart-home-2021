package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventhandlers.EventHandler;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

import java.util.Collection;

public class EventManager {
  private final SmartHome smartHome;
  private final Collection<EventHandler> eventHandlers;

  public EventManager(SmartHome smartHome, Collection<EventHandler> eventHandlers) {
    this.smartHome = smartHome;
    this.eventHandlers = eventHandlers;
  }


  public void HandleEvent(Event event) {
    System.out.println("Got event: " + event);

    for (EventHandler eventHandler : eventHandlers) {
      eventHandler.handleEvent(smartHome, event);
    }
  }
}
