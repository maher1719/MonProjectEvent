package com.example.miniprojetevents.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.miniprojetevents.database.Repository.EventRepository;
import com.example.miniprojetevents.entities.Event;

import java.util.List;

public class EventViewModel extends AndroidViewModel {

    private EventRepository mRepository;


    private LiveData<List<Event>> mAllEvents;

    public EventViewModel(@NonNull Application application) {
        super(application);
        mRepository = new EventRepository(application);
        mAllEvents = mRepository.getEvents();
    }

    public LiveData<List<Event>> getAllEvents() {
        return mAllEvents;
    }

    public void insert(Event event) {
        mRepository.insert(event);
    }
}
