package ru.sbt.mipt.oop.home_components;

import ru.sbt.mipt.oop.Action;

public class Door implements HomeComponent {
  private final String id;
  private boolean isOpen;

  public Door(boolean isOpen, String id) {
    this.isOpen = isOpen;
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setOpen(boolean open) {
    isOpen = open;
  }

  public boolean isOpen() {
    return isOpen;
  }

  @Override
  public void execute(Action action) {
  }
}
