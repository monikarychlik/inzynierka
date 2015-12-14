package pl.inzynierka.monia.mapa.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pl.inzynierka.monia.mapa.R;
import pl.inzynierka.monia.mapa.utils.DrawerItem;

public class DrawerListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<DrawerItem> drawerItems;

    public DrawerListAdapter(Context context, ArrayList<DrawerItem> drawerItems) {
        this.context = context;
        this.drawerItems = drawerItems;
    }

    @Override
    public int getCount() {
        return drawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return drawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            final LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.drawer_item, null);
        }

        final TextView titleView = (TextView) view.findViewById(R.id.title);
        final TextView subtitleView = (TextView) view.findViewById(R.id.subTitle);
        final ImageView iconView = (ImageView) view.findViewById(R.id.icon);

        titleView.setText(drawerItems.get(position).getTitle());
        subtitleView.setText(drawerItems.get(position).getSubtitle());
        iconView.setImageResource(drawerItems.get(position).getIcon());

        return view;
    }
}