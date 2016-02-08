package pl.inzynierka.monia.mapa.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.inzynierka.monia.mapa.R;

public class BuildingSpinnerAdapter extends ArrayAdapter<String> {

    private List<String> list = new ArrayList<>();

    public BuildingSpinnerAdapter(Context context, int resource, List<String> list) {
        super(context, resource, list);

        this.list = list;
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.fragment_building_unit, parent, false);
        }

        final TextView textViewSign = (TextView) view.findViewById(R.id.textViewBuildingSign);
        final TextView textViewName = (TextView) view.findViewById(R.id.textViewBuildingName);

        final String[] stringSplit = list.get(position).split(":");

        textViewSign.setText(stringSplit[0]);
        textViewName.setText(stringSplit[1]);

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
}
