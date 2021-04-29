package ru.sbt.mipt.oop.signaling;

public class Signaling {
  private SignalingState state;

  void changeState(SignalingState newState) {
    state = newState;
  }

  public Signaling() {
    this.state = new DeactivatedSignalingState(this);
  }

  public void activate(String code) {
    state.activate(code);
  }

  public void deactivate(String code) {
    state.deactivate(code);
  }

  public void alarm() {
    state.alarm();
  }

  public boolean isAlarm() {
    return state instanceof AlarmSignalingState;
  }
}
