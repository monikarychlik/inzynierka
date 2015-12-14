package pl.inzynierka.monia.mapa.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.inzynierka.monia.mapa.R;


public class Container extends Fragment {
    public Container() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_container, container, false);
    }

}
