package pl.inzynierka.monia.mapa.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.realm.Realm;
import pl.inzynierka.monia.mapa.R;
import pl.inzynierka.monia.mapa.callbacks.MainActivityCallbacks;
import pl.inzynierka.monia.mapa.models.Contact;
import pl.inzynierka.monia.mapa.models.Faculty;
import pl.inzynierka.monia.mapa.models.Identifier;
import pl.inzynierka.monia.mapa.models.Unit;
import pl.inzynierka.monia.mapa.utils.Keyboard;

public class UnitInfoFragment extends Fragment {
    private View view;
    private int unitId;
    private Realm realm;
    private Unit chosenUnit;
    private TextView textViewUnitFaculty;
    private View separator;
    private TextView textViewUnitSign;
    private TextView textViewUnitName;
    private TextView textViewUnitEmail;
    private TextView textViewUnitPhone1;
    private TextView textViewUnitPhone2;
    private TextView textViewUnitFax;
    private ImageView imageViewShowUnitOnMap;
    private MainActivityCallbacks mainActivityCallbacks;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_unit_info, container, false);

        initView();
        findUnit();
        setInfo();

        return view;
    }

    private void initView() {
        final Keyboard keyboard = new Keyboard(getActivity());
        keyboard.hideSoftKeyboard();

        realm = Realm.getInstance(getActivity());
        mainActivityCallbacks = (MainActivityCallbacks) getActivity();

        textViewUnitFaculty = (TextView) view.findViewById(R.id.textViewUnitFaculty);
        separator = view.findViewById(R.id.separator);

        textViewUnitSign = (TextView) view.findViewById(R.id.textViewTitleUnitSign);
        textViewUnitName = (TextView) view.findViewById(R.id.textViewTitleUnitName);
        textViewUnitEmail = (TextView) view.findViewById(R.id.textViewUnitEmail);
        textViewUnitPhone1 = (TextView) view.findViewById(R.id.textViewUnitPhone1);
        textViewUnitPhone2 = (TextView) view.findViewById(R.id.textViewUnitPhone2);
        textViewUnitFax = (TextView) view.findViewById(R.id.textViewUnitFax);
        imageViewShowUnitOnMap = (ImageView) view.findViewById(R.id.imageViewShowUnitOnMap);
        setListeners();
    }

    private void setListeners() {
        imageViewShowUnitOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityCallbacks.passBuildingsIDs(chosenUnit.getBuildingsIDs());
                mainActivityCallbacks.changeToMapFragment("");
            }
        });
    }

    private void setInfo() {
        final Contact unitContact = chosenUnit.getContact();
        final Identifier unitIdentifier = chosenUnit.getIdentifier();
        final String unitSign =
                unitIdentifier.getMarkLetter().toUpperCase() + unitIdentifier.getMarkNumber();

        setFaculty();

        textViewUnitSign.setText(unitSign);
        textViewUnitName.setText(unitIdentifier.getName());
        textViewUnitEmail.setText(unitContact.getEmail());
        textViewUnitPhone1.setText(unitContact.getPhoneNumber1());
        textViewUnitPhone2.setText(unitContact.getPhoneNumber2());
        textViewUnitFax.setText(unitContact.getFax());
    }

    private void setFaculty() {
        final List<Faculty> faculties = realm.where(Faculty.class).findAll();

        if (chosenUnit.getFacultyID() == 0) {
            setVisibility(View.GONE);
        } else {
            setVisibility(View.VISIBLE);

            for (Faculty faculty : faculties) {
                if (chosenUnit.getFacultyID() == faculty.getId()) {
                    textViewUnitFaculty.setText(faculty.getIdentifier().getName());
                    break;
                }
            }
        }
    }

    private void setVisibility(int visibility) {
        view.findViewById(R.id.facultyLayout).setVisibility(visibility);
        separator.setVisibility(visibility);
    }

    private void findUnit() {
        final List<Unit> units = realm.where(Unit.class).findAll();
        for (Unit unit : units) {
            if (unit.getId() == unitId) {
                chosenUnit = unit;
            }
        }
    }

    public void passData(int id) {
        this.unitId = id;
    }
}
