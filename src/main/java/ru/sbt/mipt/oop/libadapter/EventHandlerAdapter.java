package ru.sbt.mipt.oop.libadapter;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.eventhandlers.EventHandler;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

public class EventHandlerAdapter {
  public com.coolcompany.smarthome.events.EventHandler adaptHandler(SmartHome smartHome_arg, EventHandler eventHandler_arg) {
    return new com.coolcompany.smarthome.events.EventHandler() {
      final private SmartHome smartHome = smartHome_arg;
      final private EventHandler eventHandler = eventHandler_arg;
      final private EventAdapter eventAdapter = new EventAdapter();

      @Override
      public void handleEvent(CCSensorEvent event) {
        eventHandler.handleEvent(smartHome, eventAdapter.adaptEvent(event));
      }
    };
  }
}
