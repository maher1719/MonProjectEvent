package com.example.miniprojetevents.step.event;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.miniprojetevents.R;
import com.example.miniprojetevents.entities.Event;
import com.google.android.material.textfield.TextInputEditText;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroEventFragment extends Fragment implements BlockingStep {


    private String Adressechoisi;
    Spinner spinner;
    private DataManager dataManager;
    private Spinner customSpinnerCateg;
    View view;
    String categoChoisi;
    Event e;

    public IntroEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        e = new Event();
        view = inflater.inflate(R.layout.fragment_step1, container, false);
        spinner = view.findViewById(R.id.lieu__spinner);
        Spinner spinner = view.findViewById(R.id.lieu__spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.Gouvernerat, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                Adressechoisi = text;

                e.setLieuEvent(Adressechoisi);
                //Log.d("EventF", "onItemSelected: " + dataManager.getData().getLieuEvent());


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        customSpinnerCateg = view.findViewById(R.id.spinner__cat);
        ArrayList<CustomItemsCat> customList = new ArrayList<>();
        customList.add(new CustomItemsCat("Musique", R.drawable.ic_music_note_black_24dp));
        customList.add(new CustomItemsCat("Affaires et Professionnel", R.drawable.ic_business_center_black_24dp));
        customList.add(new CustomItemsCat("Nourriture", R.drawable.ic_restaurant_menu_black_24dp));
        customList.add(new CustomItemsCat("Gouvernements & Politique", R.drawable.ic_account_balance_black_24dp));
        customList.add(new CustomItemsCat("Voyage", R.drawable.ic_airplanemode_active_black_24dp));
        customList.add(new CustomItemsCat("Sports & Fitness", R.drawable.ic_fitness_center_black_24dp));
        customList.add(new CustomItemsCat("Mode & Beauté", R.drawable.ic_face_black_24dp));
        customList.add(new CustomItemsCat("Maison & Mode de vie", R.drawable.ic_home_black_24dp));
        customList.add(new CustomItemsCat("Science & Technologie", R.drawable.ic_laptop_mac_black_24dp));
        customList.add(new CustomItemsCat("Santé & Bien-être", R.drawable.ic_local_pharmacy_black_24dp));
        customList.add(new CustomItemsCat("Charité", R.drawable.ic_people_black_24dp));
        customList.add(new CustomItemsCat("Loisir & centres d'interets", R.drawable.ic_palette_black_24dp));
        customList.add(new CustomItemsCat("Films & Médias", R.drawable.ic_perm_media_black_24dp));
        customList.add(new CustomItemsCat("Culture & Communauté", R.drawable.ic_public_black_24dp));
        customList.add(new CustomItemsCat("Famille & Éducation", R.drawable.ic_pregnant_woman_black_24dp));
        customList.add(new CustomItemsCat("Religion & Spiritualité", R.drawable.ic_spa_black_24dp));
        customList.add(new CustomItemsCat("Transports", R.drawable.ic_train_black_24dp));
        customList.add(new CustomItemsCat("Arts visiuels & Performance", R.drawable.ic_camera_black_24dp));
        customList.add(new CustomItemsCat("Autres", R.drawable.ic_priority_high_black_24dp));
        CustomAdapter customAdapter = new CustomAdapter(getContext(), customList);
        if (customSpinnerCateg != null) {
            customSpinnerCateg.setAdapter(customAdapter);
            customSpinnerCateg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    CustomItemsCat items = (CustomItemsCat) parent.getSelectedItem();
                    categoChoisi = items.getSpinnerText();
                    //Toast.makeText(AjoutEvent.this, categoChoisi, Toast.LENGTH_SHORT).show();
                    e.setCategorie(categoChoisi);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }

        return view;
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

    @Override
    public VerificationError verifyStep() {
        //return null if the user can go to the next step, create a new VerificationError instance otherwise
        return null;
    }

    @Override
    public void onSelected() {
        //update UI when selected
    }

    @Override
    public void onError(@NonNull VerificationError error) {
        //handle error inside of the fragment, e.g. show error on EditText
    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        TextInputEditText titre = view.findViewById(R.id.titre);
        TextInputEditText description = view.findViewById(R.id.description_ajout);
        e.setTitle(titre.getText().toString());
        e.setDescription(description.getText().toString());
        dataManager.saveData(e);
        callback.goToNextStep();

    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
        callback.complete();
    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }
}
