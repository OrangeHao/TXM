package routeplan.utils;

import android.util.Log;

import routeplan.BuildConfig;


public class xLog {
	public static final String TAG="czh";

	public static void D(String tips) {
		if (BuildConfig.DEBUG) {
			Log.d(TAG, tips);
		}
	}
	
	public static void D(String tag, String tips) {
		if (BuildConfig.DEBUG) {
			Log.d(tag, tips);
		}
	}
}
