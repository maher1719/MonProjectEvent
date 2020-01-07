package com.example.miniprojetevents.step.event;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.miniprojetevents.R;
import com.example.miniprojetevents.database.dao.IEvent;
import com.example.miniprojetevents.entities.Event;
import com.example.miniprojetevents.entities.MessageNetwork;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapStepFragment extends Fragment implements BlockingStep {

    DataManager dataManager;


    private MapView mapView;
    View root;

    public MapStepFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_map_step, container, false);
        mapView = root.findViewById(R.id.mapViewStep);
        //mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        mapboxMap.addOnMapClickListener(new MapboxMap.OnMapClickListener() {
                            @Override
                            public boolean onMapClick(@NonNull LatLng point) {
                                mapboxMap.addMarker(new MarkerOptions().setPosition(point));
                                dataManager.getData().setLatitude(point.getLatitude());
                                dataManager.getData().setLongtitude(point.getLongitude());
                                return true;
                            }
                        });
                        mapboxMap.setOnMarkerClickListener(new MapboxMap.OnMarkerClickListener() {
                            @Override
                            public boolean onMarkerClick(@NonNull Marker marker) {
                                mapboxMap.removeMarker(marker);
                                dataManager.getData().setLatitude(0);
                                dataManager.getData().setLongtitude(0);
                                return false;
                            }
                        });
                        // Map is set up and the style has loaded. Now you can add data or make other map adjustments
                    }
                });

            }
        });
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();

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
        Toast.makeText(getContext(), dataManager.getData().getDateDebEvent().toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(@NonNull VerificationError error) {
    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
        final String BASE_URL = "http://10.0.2.2:81";
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        IEvent user = retrofit.create(IEvent.class);
        Event event = dataManager.getData();/*
        @Field("titre") String titre,
        @Field("categorie") String categorie,
        @Field("type") String type,
        @Field("imgEvent") String imgEvent,
        @Field("montant") String montant,
        @Field("description") String description,
        @Field("capacite") String capacite,
        @Field("dateDebEvent") String dateDebEvent,
        @Field("timeDebEvent") String timeDebEvent,
        @Field("duree") int duree,
        @Field("dateFin") String dateFin,
        @Field("timeFinEvent") String timeFinEvent,
        @Field("lieuEvent") String lieuEvent,
        @Field("userMail") String userMail,
        @Field("longituse") double longitude,
        @Field("latitude") double latitude
    );*/
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");

        String des = event.getDescription();
        String dateDeb = String.format(formatter.format(event.getDateDebEvent()));
        String datefin = String.format(formatter.format(event.getDateFin()));

        Call<MessageNetwork> call = user.addEventData(
                event.getTitle(), event.getCategorie(), event.getType(),
                null, "23", des, "34", dateDeb, event.getTimeDebEvent(), 4, datefin, event.getTimeFinEvent()
                , event.getLieuEvent(), "mail", event.getLongtitude(), event.getLatitude()
        );
        call.enqueue(new Callback<MessageNetwork>() {
            @Override
            public void onResponse(Call<MessageNetwork> call, Response<MessageNetwork> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "server " + event.getDateDebEvent() + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("EventD", "onResponse: " + "server returned so many repositories: " + dateDeb + response.body().getMessage());
                    Log.d("EventD", "onResponse: " + event.toString());
                    // todo display the data instead of just a toast
                } else {
                    // error case
                    switch (response.code()) {
                        case 404:
                            Toast.makeText(getContext(), "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(getContext(), "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(getContext(), "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<MessageNetwork> call, Throwable t) {
                Log.d("EventD", "onFailure: " + t.getMessage());
                Log.d("EventD", "onFailure: " + call.request().toString());
                Log.d("EventD", "onFailure: " + call.toString());

            }
        });
        callback.complete();
        getActivity().finish();
    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }
}
