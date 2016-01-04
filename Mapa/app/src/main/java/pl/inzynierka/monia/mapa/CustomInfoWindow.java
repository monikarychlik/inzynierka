package pl.inzynierka.monia.mapa;

import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import org.osmdroid.bonuspack.overlays.MarkerInfoWindow;
import org.osmdroid.views.MapView;

public class CustomInfoWindow extends MarkerInfoWindow {

    public CustomInfoWindow(View view, MapView mapView) {
        super(R.layout.bubble_info, mapView);

//        ImageButton imageButtonGoTo = (ImageButton) view.findViewById(R.id.button_goto);
//
//        imageButtonGoTo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), R.string.go_to, Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}