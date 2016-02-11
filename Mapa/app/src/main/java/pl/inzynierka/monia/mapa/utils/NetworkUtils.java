package pl.inzynierka.monia.mapa.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class NetworkUtils {

    public static final String GOOGLE_IP = "www.google.pl";
    public static final int PORT = 80;

    public boolean hasInternetAccess() {
        Socket socket = null;

        try {
            final InetAddress byName = InetAddress.getByName(GOOGLE_IP);
            socket = new Socket(byName, PORT);

            return true;
        } catch (IOException e) {
            return false;
        } finally {
            if (socket != null) {

                try {
                    socket.close();
                } catch(IOException ignored) {}
            }
        }
    }

    @SuppressWarnings("deprecation")
    public boolean hasWifiOrNetworkEnabled(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        final ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo[] netInfo = connectivityManager.getAllNetworkInfo();

        for (NetworkInfo networkInfo : netInfo) {
            if (networkInfo.getTypeName().equalsIgnoreCase("WIFI")) {
                if (networkInfo.isConnected()) {
                    haveConnectedWifi = true;
                }
            }

            if (networkInfo.getTypeName().equalsIgnoreCase("MOBILE")) {
                if (networkInfo.isConnected()) {
                    haveConnectedMobile = true;
                }
            }
        }

        return haveConnectedWifi || haveConnectedMobile;
    }
}
