package pl.inzynierka.monia.mapa.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import pl.inzynierka.monia.mapa.R;
import pl.inzynierka.monia.mapa.adapters.BuildingSpinnerAdapter;
import pl.inzynierka.monia.mapa.callbacks.MainActivityCallbacks;
import pl.inzynierka.monia.mapa.models.Building;
import pl.inzynierka.monia.mapa.models.Identifier;
import pl.inzynierka.monia.mapa.utils.NetworkUtils;

public class NavigationFragment extends Fragment implements View.OnClickListener {

    private View view;
    private CheckBox checkBoxMyLocalization;
    private Spinner spinnerPointA;
    private Spinner spinnerPointB;
    private RadioGroup radioGroupTypeOfTravel;
    private Button buttonNavigate;
    private Realm realm;
    private MainActivityCallbacks mainActivityCallbacks;
    private List<Building> buildings;
    private boolean isMyLocalizationChecked = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_navigation, container, false);

        initView();
        setSpinners();
        setListeners();

        return view;
    }

    private void initView() {
        realm = Realm.getInstance(getActivity());
        mainActivityCallbacks = (MainActivityCallbacks) getActivity();
        buildings = realm.where(Building.class).findAll();

        checkBoxMyLocalization = (CheckBox) view.findViewById(R.id.checkBoxMyLocalization);
        spinnerPointA = (Spinner) view.findViewById(R.id.spinnerPointA);
        spinnerPointB = (Spinner) view.findViewById(R.id.spinnerPointB);
        radioGroupTypeOfTravel = (RadioGroup) view.findViewById(R.id.radioGroupTypeOfTravel);
        buttonNavigate = (Button) view.findViewById(R.id.buttonNavigate);
    }

    private void setSpinners() {
        final List<String> list = getBuildingsNames();

        final BuildingSpinnerAdapter buildingSpinnerAdapter =
                new BuildingSpinnerAdapter(getContext(), R.layout.fragment_building_unit, list);

        spinnerPointA.setAdapter(buildingSpinnerAdapter);
        spinnerPointB.setAdapter(buildingSpinnerAdapter);
    }

    @NonNull
    private List<String> getBuildingsNames() {
        final List<String> list = new ArrayList<>();

        for (Building building : buildings) {
            final Identifier buildingIdentifier = building.getIdentifier();
            final String buildingSign = buildingIdentifier.getMarkLetter().toUpperCase() +
                    buildingIdentifier.getMarkNumber();
            final String buildingTitle = buildingSign + ":" + buildingIdentifier.getName();

            list.add(buildingTitle);
        }
        return list;
    }

    private void setListeners() {
        checkBoxMyLocalization.setOnClickListener(this);
        buttonNavigate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonNavigate:
                onButtonNavigateClick();
                break;

            case R.id.checkBoxMyLocalization:
                onCheckBoxClick();
                break;
        }
    }

    private void onButtonNavigateClick() {
        final LocationManager manager =
                (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        if (isMyLocalizationChecked && !manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
            return;
        }

        final NetworkUtils networkUtils = new NetworkUtils();

        if (!networkUtils.hasWifiOrNetworkEnabled(getActivity())) {
            buildAlertMessageNoInternet();
            return;
        }

        mainActivityCallbacks.passDataToMap(radioGroupTypeOfTravel.getCheckedRadioButtonId(),
                isMyLocalizationChecked, buildings.get(spinnerPointA.getSelectedItemPosition()),
                buildings.get(spinnerPointB.getSelectedItemPosition()));
        mainActivityCallbacks.changeToMapFragment("");
    }

    private void buildAlertMessageNoInternet() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setMessage(getString(R.string.no_internet_want_enable));
        builder.setCancelable(false);

        builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, final int id) {
                startActivity(new Intent(Settings.ACTION_SETTINGS));
            }
        });

        builder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, final int id) {
                dialog.cancel();
            }
        });

        final AlertDialog alert = builder.create();
        alert.show();
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setMessage(getString(R.string.no_gps_want_enable));
        builder.setCancelable(false);

        builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, final int id) {
                startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        });

        builder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, final int id) {
                dialog.cancel();
                checkBoxMyLocalization.setChecked(false);
            }
        });

        final AlertDialog alert = builder.create();
        alert.show();
    }

    private void onCheckBoxClick() {
        isMyLocalizationChecked = !isMyLocalizationChecked;
        checkBoxMyLocalization.setChecked(isMyLocalizationChecked);

        if (isMyLocalizationChecked) {
            spinnerPointA.setVisibility(View.GONE);
        } else {
            spinnerPointA.setVisibility(View.VISIBLE);
        }
    }
}
