package com.example.miniprojetevents;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miniprojetevents.entities.Event;
import com.example.miniprojetevents.step.event.DataManager;
import com.example.miniprojetevents.step.event.StepperAdapter;
import com.mapbox.mapboxsdk.Mapbox;
import com.stepstone.stepper.StepperLayout;

public class AddStepEvent extends AppCompatActivity implements DataManager {

    private StepperLayout mStepperLayout;
    private Event event;
    private static final String DATA = "event";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1IjoibWFoZXIxNzE3IiwiYSI6ImNqcGI3em54bjA2N2gza2x4enp1aGZzaDAifQ.FuZ8LLu_2I_5I9pVKVmGZA");
        setContentView(R.layout.activity_add_step_event);
        int startingStepPosition = savedInstanceState != null ? savedInstanceState.getInt("current") : 0;
        event = savedInstanceState != null ? (Event) savedInstanceState.getSerializable(DATA) : null;
        mStepperLayout = findViewById(R.id.stepperLayout);
        mStepperLayout.setAdapter(new StepperAdapter(getSupportFragmentManager(), this), startingStepPosition);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("current", mStepperLayout.getCurrentStepPosition());
        outState.putSerializable(DATA, event);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        final int currentStepPosition = mStepperLayout.getCurrentStepPosition();
        if (currentStepPosition > 0) {
            mStepperLayout.onBackClicked();
        } else {
            finish();
        }
    }

    @Override
    public void saveData(Event event) {
        this.event = event;
    }

    @Override
    public Event getData() {
        return event;
    }
}
