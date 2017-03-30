package net.studymongolian.mongollibrary;


import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

// This class is used to cache fonts to avoid memory leaks.
// It is also a central place to organize the included fonts.
public class MongolFont {

    public static final String QAGAN = "fonts/MQG8F02.ttf";                 // White (plain)
    public static final String GARQAG = "fonts/MenksoftGarqag.ttf";         // Title
    public static final String HARA = "fonts/MenksoftHara.ttf";             // Bold
    public static final String SCNIN = "fonts/MenksoftScnin.ttf";           // News
    public static final String HAWANG = "fonts/MenksoftHawang.ttf";         // Handwriting
    public static final String QIMED = "fonts/MenksoftQimed.ttf";           // Handwriting pen
    public static final String NARIN = "fonts/MenksoftNarin.ttf";           // Thin
    public static final String MCDVNBAR = "fonts/MenksoftMcdvnbar.ttf";     // Wood carving
    public static final String AMGLANG = "fonts/MAM8102.ttf";               // Brush
    public static final String SIDAM = "fonts/MBN8102.ttf";                 // Fat round
    public static final String QINGMING = "fonts/MQI8102.ttf";              // Qing Ming style
    public static final String ONQA_HARA = "fonts/MTH8102.ttf";             // Thick stem
    public static final String SVGVNAG = "fonts/MSO8102.ttf";               // Thick stem thin lines
    public static final String SVLBIYA = "fonts/MBJ8102.ttf";               // Double stem
    public static final String JCLGQ = "fonts/MenksoftJclgq.ttf";           // Computer

    private static Hashtable<String, Typeface> fontCache = new Hashtable<>();

    public static Typeface get(String name, Context context) {
        Typeface tf = fontCache.get(name);
        if (tf == null) {
            try {
                switch (name) {
                    case QAGAN:
                    case GARQAG:
                    case HARA:
                    case SCNIN:
                    case HAWANG:
                    case QIMED:
                    case NARIN:
                    case MCDVNBAR:
                    case AMGLANG:
                    case SIDAM:
                    case QINGMING:
                    case ONQA_HARA:
                    case SVGVNAG:
                    case SVLBIYA:
                    case JCLGQ:
                        tf = Typeface.createFromAsset(context.getAssets(), name);
                        break;
                    default:
                        tf = Typeface.createFromAsset(context.getAssets(), QAGAN);
                        break;
                }

            }
            catch (Exception e) {
                return null;
            }
            fontCache.put(name, tf);
        }
        return tf;
    }
}