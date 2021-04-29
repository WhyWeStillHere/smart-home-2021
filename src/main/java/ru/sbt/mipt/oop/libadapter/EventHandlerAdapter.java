package ru.sbt.mipt.oop.libadapter;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.eventhandlers.EventHandler;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

public class EventHandlerAdapter {
  public com.coolcompany.smarthome.events.EventHandler adaptHandler(
      SmartHome smartHome, EventHandler eventHandler, EventAdapter eventAdapter) {
    return new AdaptedEventHandler(smartHome, eventHandler, eventAdapter);
  }

  private class AdaptedEventHandler implements com.coolcompany.smarthome.events.EventHandler {
    final private SmartHome smartHome;
    final private EventHandler eventHandler;
    final private EventAdapter eventAdapter;

    private AdaptedEventHandler(SmartHome smartHome, EventHandler eventHandler, EventAdapter eventAdapter) {
      this.smartHome = smartHome;
      this.eventHandler = eventHandler;
      this.eventAdapter = eventAdapter;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
      eventHandler.handleEvent(smartHome, eventAdapter.adaptEvent(event));
    }
  }
}
