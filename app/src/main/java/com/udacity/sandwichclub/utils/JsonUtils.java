package com.udacity.sandwichclub.utils;
/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String ERROR = "JSONUTILS";

    /**
     * This method parses the specific JSON string into a Sandwich object
     * the sandwich object is then returned
     *
     * @param json
     * @return
     */

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        // Log.d(ERROR,json);
        try {
            List<String> array = new ArrayList<>();
            List<String> ingredients = new ArrayList<>();
            //obtain the JSONObject from the json string
            JSONObject reader = new JSONObject(json);
            //obtain inner JSONObject called 'name'
            JSONObject name = reader.getJSONObject("name");
            sandwich.setMainName(name.getString("mainName"));
            JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");
            JSONArray list_of_ingredients = reader.getJSONArray("ingredients");
            for (int i = 0; i < alsoKnownAs.length(); i++) {
                array.add(alsoKnownAs.getString(i));
            }
            for (int i = 0; i < list_of_ingredients.length(); i++) {
                ingredients.add(list_of_ingredients.getString(i));
            }
            sandwich.setAlsoKnownAs(array);
            sandwich.setPlaceOfOrigin(reader.getString("placeOfOrigin"));
            sandwich.setDescription(reader.getString("description"));
            Log.d(ERROR, reader.getString("description"));
            sandwich.setImage(reader.getString("image"));
            sandwich.setIngredients(ingredients);

        } catch (JSONException e) {
            Log.d(ERROR, e.toString());
        }
        return sandwich;
    }
}
