package pl.inzynierka.monia.mapa.utils;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

public class Keyboard {
    private Activity activity;

    public Keyboard(Activity activity) {
        this.activity = activity;
    }

    @SuppressWarnings("ConstantConditions")
    public void hideSoftKeyboard() {
        final InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void showSoftKeyboard() {
        final InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }
}
