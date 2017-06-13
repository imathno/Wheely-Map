package aia.com.wheely_map.utils;

import android.content.Context;
import android.content.Intent;

public abstract class ActivityUtils {

    public static void openActivity(Context context, Class<?> cls) {
        Intent activity = new Intent(context, cls);
        context.startActivity(activity);
    }
}
