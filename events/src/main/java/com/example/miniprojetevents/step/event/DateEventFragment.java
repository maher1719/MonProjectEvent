package com.example.miniprojetevents.step.event;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.miniprojetevents.R;
import com.example.miniprojetevents.entities.Event;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class DateEventFragment extends Fragment implements BlockingStep {


    TextView text;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    int Year, Month, Day, Hour, Minute;
    Calendar calendar;
    private DataManager dataManager;
    View view;
    Event event;

    public DateEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_date_event, container, false);
        text = view.findViewById(R.id.date_fragment_spinner);
        final Button button_datepicker = view.findViewById(R.id.button_datepicker);
        final Button button_datepicker2 = view.findViewById(R.id.button_datepicker_fin);
        final Button button_time = view.findViewById(R.id.date_timepicker);
        final Button button_time_fin = view.findViewById(R.id.date_timepicker_fin);
        Calendar now = Calendar.getInstance();
        datePickerDialog = DatePickerDialog.newInstance((view1, year, monthOfYear, dayOfMonth) -> {
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH));
        button_datepicker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //datePickerDialog.setMinDate(Calendar.getInstance());
                datePickerDialog.setThemeDark(false);
                datePickerDialog.showYearPickerFirst(false);
                datePickerDialog.setTitle("Date Debut");
                datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        String date = "Date: " + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        Toast.makeText(getContext(), date, Toast.LENGTH_LONG).show();
                        event = dataManager.getData();

                        Date dateY = new Date(year - 1900, monthOfYear, dayOfMonth);
                        try {
                            String d = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            Date dateu = formatter.parse(d);
                            event.setDateDebEvent(dateu);
                            Log.d("EventD", String.format("onDateSet: " + formatter.format(dateu)));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }
                });
                datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        Toast.makeText(getContext(), "Datepicker Canceled", Toast.LENGTH_SHORT).show();
                    }
                });
                datePickerDialog.show(getFragmentManager(), "DatePickerDialog");
            }
        });
        button_datepicker2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //datePickerDialog.setMinDate(Calendar.getInstance());
                datePickerDialog.setThemeDark(false);
                datePickerDialog.showYearPickerFirst(false);
                datePickerDialog.setTitle("Date Debut");
                datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        String date = "Date: " + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        Toast.makeText(getContext(), date, Toast.LENGTH_LONG).show();
                        event = dataManager.getData();
                        Date dateY = new Date(year - 1900, monthOfYear, dayOfMonth);
                        try {
                            String d = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            Date dateu = formatter.parse(d);
                            event.setDateFin(dateu);
                            Log.d("EventD", String.format("onDateSet: " + formatter.format(dateu)));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }
                });
                datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        Toast.makeText(getContext(), "Datepicker Canceled", Toast.LENGTH_SHORT).show();
                    }
                });
                datePickerDialog.show(getFragmentManager(), "DatePickerDialog");
            }
        });
        timePickerDialog = TimePickerDialog.newInstance((view1, hourOfDay, minute, second) -> {
        }, now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), true);
        button_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.setOnTimeSetListener(new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                        Date date = new Date(0, 0, 0, hourOfDay, minute, second);
                        dataManager.getData().setTimeDebEvent(date);
                    }
                });
                timePickerDialog.show(getFragmentManager(), "TimePicker fin");

            }
        });
        button_time_fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.setOnTimeSetListener(new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                        Date date = new Date(0, 0, 0, hourOfDay, minute, second);
                        dataManager.getData().setTimeFinEvent(date);
                    }
                });
                timePickerDialog.show(getFragmentManager(), "TimePicker");

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof DataManager) {
            dataManager = (DataManager) context;
        } else {
            throw new IllegalStateException("Activity must implement DataManager interface!");
        }


    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {
        text.setText(dataManager.getData().getLieuEvent() + " " + dataManager.getData().getTitle() + " " + dataManager.getData().getCategorie());
        Log.d("EventF", "onSelected: " + dataManager.getData().getLieuEvent());
    }

    @Override
    public void onError(@NonNull VerificationError error) {
    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        dataManager.saveData(event);
        callback.goToNextStep();
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
        getActivity().finish();
        callback.complete();
    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }
}
