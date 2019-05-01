package com.nullgeodesic.washingtonhhh.service;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class PersistenceService {

    private static final String TAG = PersistenceService.class.getSimpleName();
    private static final String FILENAME_V1 = "wh3_v1";

    public static void storeV1(final Context context, final String data) {
        try (final FileOutputStream outputStream = context.openFileOutput(FILENAME_V1, Context.MODE_PRIVATE)) {
            outputStream.write(data.getBytes());
            Log.v(TAG, "data stored");
        } catch (Exception e) {
            Log.v(TAG, "error saving data: " + e.getMessage());
        }
    }

    public static String retrieveV1(final Context context) {
        try (final FileInputStream inputStream = context.openFileInput(FILENAME_V1);
             final BufferedReader buf = new BufferedReader(new InputStreamReader(inputStream))) {
            final StringBuilder builder = new StringBuilder();
            String line = buf.readLine();
            while (line != null) {
                builder.append(line);
                line = buf.readLine();
            }
            Log.v(TAG, "data retrieved");
            return builder.toString();
        } catch (Exception e) {
            Log.v(TAG, "error retrieving data: " + e.getMessage());
            return null;
        }
    }
}
