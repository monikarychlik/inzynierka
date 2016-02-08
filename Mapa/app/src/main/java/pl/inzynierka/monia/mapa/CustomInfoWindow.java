package pl.inzynierka.monia.mapa;

import org.osmdroid.bonuspack.overlays.MarkerInfoWindow;
import org.osmdroid.views.MapView;

public class CustomInfoWindow extends MarkerInfoWindow {

    public CustomInfoWindow(MapView mapView) {
        super(R.layout.bubble_info, mapView);
    }
}