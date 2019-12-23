package com.example.miniprojetevents.step.event;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.example.miniprojetevents.entities.Event;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;


public class StepperAdapter extends AbstractFragmentStepAdapter {


    public StepperAdapter(FragmentManager fm, Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {
        Event event = new Event();
        if (position == 0) {
            final IntroEventFragment step = new IntroEventFragment();
            Bundle b = new Bundle();
            b.putInt("current", position);
            b.putSerializable("event", event);
            step.setArguments(b);
            return step;
        } else if (position == 1) {
            final DateEventFragment step = new DateEventFragment();
            Bundle b = new Bundle();
            b.putInt("current", position);
            //b.putSerializable("event",event);
            step.setArguments(b);
            return step;
        } else {
            final MapStepFragment step = new MapStepFragment();
            Bundle b = new Bundle();
            b.putInt("current", position);
            //b.putSerializable("event",event);
            step.setArguments(b);
            return step;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        //Override this method to set Step title for the Tabs, not necessary for other stepper types
        StepViewModel.Builder builder = new StepViewModel.Builder(context)
                .setTitle("test"); //can be a CharSequence instead
        if (position == 0) {
            builder
                    .setEndButtonLabel("Date Event")
                    .setBackButtonLabel("Cancel")
                    .setBackButtonStartDrawableResId(StepViewModel.NULL_DRAWABLE);
        } else if (position == 1) {
            builder
                    .setEndButtonLabel("Location Event")
                    .setBackButtonLabel("Go to first");
        } else if (position == 2) {
            builder
                    .setBackButtonLabel("Go back")
                    .setEndButtonLabel("I'm done!")
            ;
        } else {
            throw new IllegalArgumentException("Unsupported position: " + position);
        }
        return builder.create();
    }
}
