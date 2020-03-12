package com.jaats.agrovehicle.util;

import android.graphics.Typeface;

import java.util.ArrayList;

import com.jaats.agrovehicle.R;
import com.jaats.agrovehicle.app.App;
import com.jaats.agrovehicle.config.TypefaceCache;

/**
 * Created by Jemsheer K D on 06 September, 2018.
 * Package com.jaats.agrovehicle.util
 * Project LaTaxi
 */
public class FontUtil {

    private static FontUtil instance = new FontUtil();

    private final ArrayList<String> fontFamilyList = new ArrayList<>();
    private final ArrayList<String> fontList = new ArrayList<>();

    public static FontUtil getInstance() {
        if (instance == null) {
            instance = new FontUtil();
        }
        return instance;
    }

    private FontUtil() {
       // fontFamilyList.add(App.getInstance().getString(R.string.font_roboto));
        //fontFamilyList.add(App.getInstance().getString(R.string.font_lato));
    }

    public static String getFontFamily(String fontName) {

        if (fontName != null && !fontName.equalsIgnoreCase("")) {
            for (String fontFamily : getInstance().fontFamilyList) {
                if (fontFamily.equalsIgnoreCase(fontName))
                    return fontFamily;
            }
          //  fontName = App.getInstance().getString(R.string.font_roboto);
        } else {
            //fontName = App.getInstance().getString(R.string.font_roboto);
        }
        return fontName;
    }


}
