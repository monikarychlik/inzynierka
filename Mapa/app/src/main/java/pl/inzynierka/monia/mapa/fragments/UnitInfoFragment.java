package pl.inzynierka.monia.mapa.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.realm.Realm;
import pl.inzynierka.monia.mapa.utils.Keyboard;

public class UnitInfoFragment extends Fragment {
    private View view;
    private int unitId;
    private Realm realm;
    private Keyboard keyboard;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);

        initView();

        return view;
    }

    private void initView() {
        keyboard = new Keyboard(getActivity());
        keyboard.hideSoftKeyboard();
    }

    public void passData(int id, Realm realm) {
        this.unitId = id;
        this.realm = realm;
    }
}
