package pl.inzynierka.monia.mapa.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import pl.inzynierka.monia.mapa.R;
import pl.inzynierka.monia.mapa.models.Building;
import pl.inzynierka.monia.mapa.models.Faculty;
import pl.inzynierka.monia.mapa.models.Unit;

public class BuildingInfoAdapter extends BaseAdapter {
    private Context context;
    private Building building;
    private List<Unit> units;

    public BuildingInfoAdapter(Context context, Building building) {
        this.context = context;
        this.building = building;
        this.units = building.getUnits();
    }

    @Override
    public int getCount() {
        return units.size();
    }

    @Override
    public Object getItem(int position) {
        return units.get(position);
    }

    @Override
    public long getItemId(int position) {
        return units.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Unit unit = units.get(position);
        View view = convertView;

        if (view == null) {
            final LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.fragment_building_info_unit, null);
        }

        final TextView textViewFacultyName = (TextView) view.findViewById(R.id.textViewFacultyName);
        final TextView textViewUnitName = (TextView) view.findViewById(R.id.textViewUnitName);

        textViewUnitName.setText(unit.getIdentifier().getName());

        for (Faculty faculty : building.getFaculties()) {
            if (faculty.getId() == unit.getFacultyID()) {
                textViewFacultyName.setText(faculty.getIdentifier().getName());
                break;
            }
        }

        return view;
    }
}