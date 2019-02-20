package com;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class fetchData extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://api.coinmarketcap.com/v1/ticker/");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            JsonReader jsonReader = new JsonReader(inputStreamReader);
            jsonReader.beginArray();

            while (jsonReader.hasNext()) {
                jsonReader.beginObject();
                String id = "";
                Double price_usd;

                while (jsonReader.hasNext()) {
                    String name = jsonReader.nextName();
                    switch (name) {
                        case "id":
                            id = jsonReader.nextString();
                            break;
                        case "price_usd":
                            price_usd = jsonReader.nextDouble();
                            MainActivity.setIdAndPriceValue(id, price_usd);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }

                jsonReader.endObject();
            }
            Log.d("The price value is", MainActivity.getIdAndPriceValue().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
