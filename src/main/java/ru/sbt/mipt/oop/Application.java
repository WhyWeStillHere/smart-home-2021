package ru.sbt.mipt.oop;

public class Application {

  public static void main(String... args) {
    // считываем состояние дома из файла
    SmartHomeReader reader = new SmartHomeReader("smart-home-1.js");
    SmartHome smartHome = reader.readSmartHome();
    // начинаем цикл обработки событий
    eventLoop(smartHome);
  }

  private static void eventLoop(SmartHome smartHome) {
    EventManager eventManager = new EventManager(smartHome);
    SensorEventCreator eventCreator = new SensorEventCreatorImpl();

    SensorEvent event = eventCreator.getNextEvent();
    while (event != null) {
      eventManager.HandleEvent(event);
      event = eventCreator.getNextEvent();
    }
  }

}
