package ru.sbt.mipt.oop.signaling;

public class AlarmSignalingState implements SignalingState {
  private final Signaling signaling;

  public AlarmSignalingState(Signaling signaling) {
    this.signaling = signaling;
  }

  @Override
  public void Activate(String code) {
  }

  @Override
  public void Deactivate(String code) {
  }

  @Override
  public boolean isAlarm() {
    return true;
  }

}
