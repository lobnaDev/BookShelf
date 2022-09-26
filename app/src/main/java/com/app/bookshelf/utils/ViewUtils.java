package com.app.bookshelf.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Lobna Babker on 9/17/2022.
 */
public class ViewUtils {
    public static void showToast(Context mContext, String message){
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
}
