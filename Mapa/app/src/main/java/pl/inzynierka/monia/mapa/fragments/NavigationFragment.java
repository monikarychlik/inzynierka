package pl.inzynierka.monia.mapa.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import pl.inzynierka.monia.mapa.R;
import pl.inzynierka.monia.mapa.adapters.BuildingSpinnerAdapter;
import pl.inzynierka.monia.mapa.callbacks.MainActivityCallbacks;
import pl.inzynierka.monia.mapa.models.Building;
import pl.inzynierka.monia.mapa.models.Identifier;

public class NavigationFragment extends Fragment {

    private View view;
    private Spinner spinnerPointA;
    private Spinner spinnerPointB;
    private RadioGroup radioGroupTypeOfTravel;
    private Button buttonNavigate;
    private Realm realm;
    private MainActivityCallbacks mainActivityCallbacks;
    private List<Building> buildings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_navigation, container, false);

        initView();
        setSpinners();
        setButtonListener();

        return view;
    }

    private void setSpinners() {
        final List<String> list = new ArrayList<>();

        for (Building building : buildings) {
            final Identifier buildingIdentifier = building.getIdentifier();
            final String buildingSign = buildingIdentifier.getMarkLetter().toUpperCase() +
                    buildingIdentifier.getMarkNumber();
            final String buildingTitle = buildingSign + ":" + buildingIdentifier.getName();

            list.add(buildingTitle);
        }

        final BuildingSpinnerAdapter buildingSpinnerAdapter =
                new BuildingSpinnerAdapter(getContext(), R.layout.fragment_building_unit, list);

        spinnerPointA.setAdapter(buildingSpinnerAdapter);
        spinnerPointB.setAdapter(buildingSpinnerAdapter);
    }

    private void setButtonListener() {
        buttonNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityCallbacks.passTypeOfTravelToMap(
                        radioGroupTypeOfTravel.getCheckedRadioButtonId());
                mainActivityCallbacks.changeToMapFragment("");
            }
        });
    }

    private void initView() {
        mainActivityCallbacks = (MainActivityCallbacks) getActivity();
        buildings = realm.where(Building.class).findAll();

        spinnerPointA = (Spinner) view.findViewById(R.id.spinnerPointA);
        spinnerPointB = (Spinner) view.findViewById(R.id.spinnerPointB);
        radioGroupTypeOfTravel = (RadioGroup) view.findViewById(R.id.radioGroupTypeOfTravel);
        buttonNavigate = (Button) view.findViewById(R.id.buttonNavigate);
    }

    public void passData(Realm realm) {
        this.realm = realm;
    }
}
