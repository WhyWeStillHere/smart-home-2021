package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonSmartHomeReader implements SmartHomeReader {
  public JsonSmartHomeReader(String pathname) {
    this.pathname = pathname;
  }

  private final String pathname;

  @Override
  public SmartHome readSmartHome() {
    Gson gson = new Gson();
    String json;
    try {
      json = new String(Files.readAllBytes(Paths.get(pathname)));
    } catch (IOException e) {
      throw new RuntimeException("Read from file error: " + e.getMessage());
    }
    return gson.fromJson(json, SmartHome.class);
  }
}
