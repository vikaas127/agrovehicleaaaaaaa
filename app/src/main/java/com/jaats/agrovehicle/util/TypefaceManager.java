package com.jaats.agrovehicle.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.HashMap;
import java.util.HashSet;

public class TypefaceManager {

    private static final TypefaceManager INSTANCE = new TypefaceManager();

    public static TypefaceManager getInstance() {
        return INSTANCE;
    }

    public static void addTextStyleExtractor(TextStyleExtractor textStyleExtractor) {
        INSTANCE.mTextStyleExtractors.add(textStyleExtractor);
    }

    private final HashMap<TextStyle, Typeface> mTypefaces = new HashMap<TextStyle, Typeface>();
    private final HashSet<TextStyleExtractor> mTextStyleExtractors = new HashSet<TextStyleExtractor>();

    private TypefaceManager() {
        // Singleton
    }

    /**
     * Method called from the {@TypefaceTextView} constructor to
     * apply a custom {@link TextStyle} defined in the application theme.
     *
     * @param textView the {@link TextView} to have the {@link TextStyle} applied
     * @param context  the {@link Context} of the {@link TextView}
     * @param attrs    the {@link AttributeSet} of the {@link TextView}
     */
    public void applyTypeface(TextView textView, Context context, AttributeSet attrs) {
        final TypedArray styleValues = context.obtainStyledAttributes(attrs, new int[]{android.R.attr.fontFamily});
        final String fontFamily = styleValues.getString(0);

        if (!TextUtils.isEmpty(fontFamily)) {
            for (TextStyleExtractor extractor : mTextStyleExtractors) {
                final TextStyle textStyle = extractor.getTextStyle(fontFamily);
                if (textStyle != null) {
                    applyTypeface(textView, textStyle);
                    break;
                }
            }
        }
        styleValues.recycle();
    }

    /**
     * Method called from code to apply a custom {@link TextStyle}.
     *
     * @param textView  the {@link TextView} to have the {@link TextStyle} applied
     * @param textStyle the {@link TextStyle} to be applied
     */
    public void applyTypeface(TextView textView, TextStyle textStyle) {
        final Typeface typeface = getTypeface(textView.getContext(), textStyle);
        if (typeface != null) {
            textView.setTypeface(typeface);
        }
    }

    /**
     * Apply a {@link Typeface} for a given {@link TextStyle}.
     *
     * @param context   the {@link Context} of the {@link TextView}
     * @param textStyle the {@link TextStyle} to be applied
     * @return the {@link Typeface} corresponding to the {@link TextStyle}, if defined
     */
    private Typeface getTypeface(Context context, TextStyle textStyle) {
        if (textStyle == null) {
            throw new IllegalArgumentException("Param 'textStyle' can't be null.");
        }
        if (mTypefaces.containsKey(textStyle)) {
            return mTypefaces.get(textStyle);
        }

        final Typeface typeface = Typeface.createFromAsset(context.getAssets(), textStyle.getFontName());
        if (typeface == null) {
            throw new RuntimeException("Can't create Typeface for font '" + textStyle.getFontName() + "'");
        }

        mTypefaces.put(textStyle, typeface);
        return typeface;
    }
}
