package ru.sbt.mipt.oop.signaling;

public class ActivatedSignalingState implements SignalingState {
  private final Signaling signaling;
  private final String alarmCode;

  public ActivatedSignalingState(Signaling signaling, String alarmCode) {
    this.signaling = signaling;
    this.alarmCode = alarmCode;
  }

  @Override
  public void alarm() {
    SetAlarm();
  }

  @Override
  public void activate(String code) {
    SetAlarm();
  }

  @Override
  public void deactivate(String code) {
    if (code.equals(alarmCode)) {
      signaling.changeState(new DeactivatedSignalingState(signaling));
    } else {
      SetAlarm();
    }
  }

  void SetAlarm() {
    System.out.println("Sending sms");
    signaling.changeState(new AlarmSignalingState(signaling));
  }
}
