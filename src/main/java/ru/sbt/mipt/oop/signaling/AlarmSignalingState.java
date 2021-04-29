package ru.sbt.mipt.oop.signaling;

public class AlarmSignalingState implements SignalingState {
  private final Signaling signaling;

  public AlarmSignalingState(Signaling signaling) {
    this.signaling = signaling;
  }

  @Override
  public void activate(String code) {
  }

  @Override
  public void deactivate(String code) {
  }

  @Override
  public void alarm() {

  }

  @Override
  public boolean isAlarm() {
    return true;
  }

}
