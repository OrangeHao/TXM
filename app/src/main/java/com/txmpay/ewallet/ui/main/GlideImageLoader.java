package com.txmpay.ewallet.ui.main;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

/**
 * created by czh on 2017-12-21
 */

public class GlideImageLoader extends ImageLoader {


    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        if (path instanceof Integer){
            imageView.setImageResource((int)path);
        }else {
            Log.i("czh","Object:"+path);
        }

    }


}
