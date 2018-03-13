package com.txmpay.ewallet.model;

import com.txmpay.ewallet.app.MyApp;

import io.objectbox.Box;

/**
 * created by czh on 2018-03-13
 */

public class ObjectBoxHelper {

    public static <T>Box<T> getBox(Class<T> clazz){
        return MyApp.getBoxStore().boxFor(clazz);
    }
}
