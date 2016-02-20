package pl.inzynierka.monia.mapa.utils;

import org.osmdroid.bonuspack.overlays.MarkerInfoWindow;
import org.osmdroid.views.MapView;

import pl.inzynierka.monia.mapa.R;

public class CustomInfoWindow extends MarkerInfoWindow {
    public CustomInfoWindow(MapView mapView) {
        super(R.layout.bubble_info, mapView);
    }
}