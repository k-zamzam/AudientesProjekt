package com.example.audientesprojekt.presetlogik;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class SoundData {

    private ArrayList<SoundInput> soundInputs = null;
    private static SoundData instance = new SoundData();

    private SoundData() {
    }

    // henter alle lydfiler
    public ArrayList<SoundInput> readSounds(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPref.getString("sounds",null);
        Type type = new TypeToken<ArrayList<SoundInput>>(){}.getType();
        //sharedPref.edit().clear().commit();
        return gson.fromJson(json, type);
    }

    // Singleton Pattern
    public static SoundData getInstance() {
        return instance;
    }

    // gemmer en lydfil
    public void saveSound(SoundInput sound, Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String json = sharedPref.getString("sounds", null);
        Gson gson = new Gson();

        if(json == null) {
            soundInputs = new ArrayList<>();
        } else {
            soundInputs = readSounds(context);
        }
        soundInputs.add(sound);
        json = gson.toJson(soundInputs);
        sharedPref.edit().putString("sounds", json).apply();
    }
}
