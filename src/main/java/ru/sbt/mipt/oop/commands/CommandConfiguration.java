package ru.sbt.mipt.oop.commands;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.homecomponents.SmartHome;

@Configuration
public class CommandConfiguration {
  @Bean
  ActivateAlarmCommand activateAlarmCommand(SmartHome smartHome) {
    return new ActivateAlarmCommand(smartHome);
  }

  @Bean
  ActivateSignalingCommand activateSignalingCommand(SmartHome smartHome) {
    return new ActivateSignalingCommand(smartHome);
  }

  @Bean
  CloseHallDoorCommand closeHallDoorCommand(SmartHome smartHome) {
    return new CloseHallDoorCommand(smartHome);
  }

  @Bean
  TurnOffAllLightCommand turnOffAllLightCommand(SmartHome smartHome) {
    return new TurnOffAllLightCommand(smartHome);
  }

  @Bean
  TurnOnAllLightCommand turnOnAllLightCommand(SmartHome smartHome) {
    return new TurnOnAllLightCommand(smartHome);
  }

  @Bean
  TurnOnHallLightCommand turnOnHallLightCommand(SmartHome smartHome) {
    return new TurnOnHallLightCommand(smartHome);
  }
}
