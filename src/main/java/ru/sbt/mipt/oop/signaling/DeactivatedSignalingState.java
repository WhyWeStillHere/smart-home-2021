package ru.sbt.mipt.oop.signaling;

public class DeactivatedSignalingState implements SignalingState {
  private final Signaling signaling;

  public DeactivatedSignalingState(Signaling signaling) {
    this.signaling = signaling;
  }

  @Override
  public void activate(String code) {
    signaling.changeState(new ActivatedSignalingState(signaling, code));
  }

  @Override
  public void deactivate(String code) {
  }

  @Override
  public boolean isAlarm() {
    return false;
  }

}
