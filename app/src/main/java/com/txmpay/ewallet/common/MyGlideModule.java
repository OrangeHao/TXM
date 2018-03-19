package com.txmpay.ewallet.common;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.AppGlideModule;

/**
 * created by czh on 2018-03-14
 */

@GlideModule
public class MyGlideModule extends AppGlideModule{

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        int diskCacheSizeBytes =1024*1024*150;  //150 MB
        builder.setDiskCache(
                new InternalCacheDiskCacheFactory(context, "ImgCache", diskCacheSizeBytes));
    }

}
