package com.example.miniprojetevents.step.event;

import com.example.miniprojetevents.entities.Event;

public interface DataManager {

    void saveData(Event event);

    Event getData();
}
