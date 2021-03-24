package ru.sbt.mipt.oop.signaling;

public class Signaling {
  private SignalingState state;

  void changeState(SignalingState newState) {
    state = newState;
  }

  public Signaling() {
    this.state = new DeactivatedSignalingState(this);
  }

  public void Activate(String code) {
    state.Activate(code);
  }

  public void Deactivate(String code) {
    state.Deactivate(code);
  }

  public boolean isAlarm() {
    return state.isAlarm();
  }
}
