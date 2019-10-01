package com.dilonexus.sigmalauncher.misc;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class FontManager {
    private static List<Font> list;
    public static List<Font> getFonts(){
        return list;
    }
    private static Typeface currentFont;
    public static Typeface getCurrentFont(){
        return currentFont;
    }
    private static AssetManager assets;


    public static void setCurrentFont(String name){
        Font font = getFontByName(name);
        if(font == null) setCurrentFont(Typeface.DEFAULT);
        else setCurrentFont(font.get());
    }

    private static Font getFontByName(String name){
        for (Font font : list) {
            if(font.getName() == name) return font;
        }
        return null;
    }

    private static void setCurrentFont(Typeface typeface){
        assert typeface != null;
        currentFont = typeface;
    }

    private static void loadFonts(){
        try {
            String[] folder = assets.list("fonts/");
            assert folder != null;
            for(String file : folder){
                Typeface typeface = Typeface.createFromAsset(assets, "fonts/" + file);

                Font font = new Font(file, typeface);
                list.add(font);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void init(Context context){
        assets = context.getAssets();
        loadFonts();
    }

    private static class Font implements Serializable {
        private Typeface typeface;
        Typeface get(){
            return typeface;
        }

        private String name;
        Font(String name, Typeface typeface){
            this.name = name;
            this.typeface = typeface;
        }

        String getName() {
            return name;
        }
    }
}
