package com.txmpay.ewallet.common;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.txmpay.ewallet.app.MyApp;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


/**
 * created by czh on 2018-03-14
 */

public class ImgLoader {


    public static void loadImgDefault(Context context, String url, ImageView imageView){
        GlideApp.with(context).load(url).transition(withCrossFade()).into(imageView);
    }


    public static void loadImgCircle(Context context, String url, ImageView imageView){
        GlideApp.with(context).load(url).transition(withCrossFade())
                .circleCrop()
                .into(imageView);
    }

    public static void loadImgWithHolder(Context context, String url, int drawableId,ImageView imageView){
        GlideApp.with(context).load(url).transition(withCrossFade())
                .placeholder(drawableId)
                .into(imageView);
    }

    public static void loadImgAdv(Context context, String url, ImageView imageView){
        GlideApp.with(context).load(url).transition(withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);

    }

    public static void clearCache( ){

        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                Glide.get(MyApp.getContext()).clearDiskCache();
                return null;
            }
        };
    }





}
