package nadav.tasher.handasaim.architecture.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import nadav.tasher.handasaim.R;
import nadav.tasher.handasaim.architecture.appcore.components.Schedule;
import nadav.tasher.lightool.communication.network.Requester;
import okhttp3.MultipartBody;
import okhttp3.Request;

public class PreferenceManager {

    private Context context;

    private KeyManager keyManager;
    private CoreManager coreManager;
    private ServicesManager servicesManager;
    private UserManager userManager;

    public PreferenceManager(Context context) {
        this.context = context;
        this.keyManager = new KeyManager(context, context.getSharedPreferences(context.getResources().getString(R.string.preferences_keys), Context.MODE_MULTI_PROCESS));
        this.coreManager = new CoreManager(context, context.getSharedPreferences(context.getResources().getString(R.string.preferences_core), Context.MODE_MULTI_PROCESS));
        this.servicesManager = new ServicesManager(context, context.getSharedPreferences(context.getResources().getString(R.string.preferences_services), Context.MODE_MULTI_PROCESS));
        this.userManager = new UserManager(context, context.getSharedPreferences(context.getResources().getString(R.string.preferences_user), Context.MODE_MULTI_PROCESS));
    }

    public CoreManager getCoreManager() {
        return coreManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public ServicesManager getServicesManager() {
        return servicesManager;
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public static class Manager {
        private SharedPreferences preferences;
        private Context context;

        public Manager(Context context, SharedPreferences preferences) {
            this.preferences = preferences;
            this.context = context;
        }

        private String fromResource(int res) {
            return context.getResources().getString(res);
        }

        private boolean get(int res, boolean defaultValue) {
            return preferences.getBoolean(fromResource(res), defaultValue);
        }

        private String get(int res, String defaultValue) {
            return preferences.getString(fromResource(res), defaultValue);
        }

        private int get(int res, int defaultValue) {
            return preferences.getInt(fromResource(res), defaultValue);
        }

        private float get(int res, float defaultValue) {
            return preferences.getFloat(fromResource(res), defaultValue);
        }

        private void set(int res, boolean value) {
            preferences.edit().putBoolean(fromResource(res), value).apply();
        }

        private void set(int res, String value) {
            preferences.edit().putString(fromResource(res), value).apply();
        }

        private void set(int res, int value) {
            preferences.edit().putInt(fromResource(res), value).apply();
        }

        private void set(int res, float value) {
            preferences.edit().putFloat(fromResource(res), value).apply();
        }

        public static class PublicManager extends Manager {

            public PublicManager(Context context, SharedPreferences preferences) {
                super(context, preferences);
            }

            public boolean get(int res, boolean defaultValue) {
                return super.get(res, defaultValue);
            }

            public int get(int res, int defaultValue) {
                return super.get(res, defaultValue);
            }

            public String get(int res, String defaultValue) {
                return super.get(res, defaultValue);
            }

            public float get(int res, float defaultValue) {
                return super.get(res, defaultValue);
            }

            public void set(int res, String value) {
                super.set(res, value);
            }

            public void set(int res, int value) {
                super.set(res, value);
            }

            public void set(int res, float value) {
                super.set(res, value);
            }

            public void set(int res, boolean value) {
                super.set(res, value);
            }
        }
    }

    public class KeyManager extends Manager {

        public KeyManager(Context context, SharedPreferences preferences) {
            super(context, preferences);
        }

        public String getLoadedKey(int unlockType) {
            return super.get(unlockType, "No Key Loaded");
        }

        public boolean isKeyLoaded(int unlockType) {
            return super.get(unlockType, null) != null;
        }

        public void loadKey(final String key) {
            new Requester(new Request.Builder().url(context.getResources().getString(R.string.provider_external_keys)).post(new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("exchange", key).build()), new Requester.Callback() {
                @Override
                public void onCall(okhttp3.Response response) {
                    try {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                try {
                                    //                                String responseString=response.body().string();
                                    //                                Log.i("Key",responseString);
                                    JSONObject o = new JSONObject(response.body().string());
                                    if (o.getBoolean(context.getString(R.string.key_response_parameter_approved))) {
                                        installKey(key, o.getInt(context.getString(R.string.key_response_parameter_type)));
                                    } else {
                                        Toast.makeText(context, "Key does not exist, or already used", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException | IOException e) {
                                    e.printStackTrace();
                                    Toast.makeText(context, "Key verification failed.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(context, "Key verification failed.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(context, "Key verification failed.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(context, "Key verification failed.", Toast.LENGTH_SHORT).show();
                    }
                }
            }).execute();
        }

        private void installKey(String key, int type) {
            switch (type) {
                case -1:
                    super.set(R.string.preferences_keys_type_beta, key);
                    Toast.makeText(context, "Beta Mode Enabled.", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    super.set(R.string.preferences_keys_type_news, key);
                    Toast.makeText(context, "News Splash Disabled.", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    super.set(R.string.preferences_keys_type_teachers, key);
                    Toast.makeText(context, "Teacher Mode Enabled.", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }

    public class CoreManager extends Manager {

        public CoreManager(Context context, SharedPreferences preferences) {
            super(context, preferences);
        }

        public String getMode() {
            return super.get(R.string.preferences_core_mode, super.fromResource(R.string.core_mode_student));
        }

        public void setMode(int modeRes) {
            super.set(R.string.preferences_core_mode, super.fromResource(modeRes));
        }

        public void renewSchedule(int currentIndex) {
            try {
                JSONArray schedules = new JSONArray(super.get(R.string.preferences_core_json_array, new JSONArray().toString()));
                JSONObject toMove = schedules.getJSONObject(currentIndex);
                JSONArray newSchedules = new JSONArray();
                newSchedules.put(toMove);
                schedules.remove(currentIndex);
                for (int i = 0; i < schedules.length(); i++) {
                    newSchedules.put(schedules.getJSONObject(i));
                }
                super.set(R.string.preferences_core_json_array, newSchedules.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public Schedule getSchedule(int index) {
            try {
                JSONArray schedules = new JSONArray(super.get(R.string.preferences_core_json_array, new JSONArray().toString()));
                return (index < schedules.length()) ? Schedule.Builder.fromJSON(schedules.getJSONObject(index)).build() : null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public ArrayList<Schedule> getSchedules() {
            try {
                ArrayList<Schedule> schedules = new ArrayList<>();
                JSONArray schedulesJSON = new JSONArray(super.get(R.string.preferences_core_json_array, new JSONArray().toString()));
                for (int i = 0; i < schedulesJSON.length(); i++) {
                    schedules.add(Schedule.Builder.fromJSON(schedulesJSON.getJSONObject(i)).build());
                }
                return schedules;
            } catch (Exception e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }

        public void addSchedule(Schedule schedule) {
            try {
                JSONArray schedules = new JSONArray(super.get(R.string.preferences_core_json_array, new JSONArray().toString()));
                JSONArray newSchedules = new JSONArray();
                newSchedules.put(schedule.toJSON());
                for (int i = 0; i < schedules.length(); i++) {
                    newSchedules.put(schedules.get(i));
                }
                // Cleanup
                for (int i = context.getResources().getInteger(R.integer.max_storage_schedule); i < newSchedules.length(); i++) {
                    newSchedules.remove(i);
                }
                super.set(R.string.preferences_core_json_array, newSchedules.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void clearSchedules() {
            try {
                JSONArray schedules = new JSONArray(super.get(R.string.preferences_core_json_array, new JSONArray().toString()));
                // Keep the first schedule
                for (int i = 1; i < schedules.length(); i++) {
                    schedules.remove(i);
                }
                super.set(R.string.preferences_core_json_array, schedules.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public class ServicesManager extends Manager {

        public ServicesManager(Context context, SharedPreferences preferences) {
            super(context, preferences);
        }

        public boolean getPushDisplayedAlready(String id) {
            // Check If The Push Was Displayed Already.
            String jsonString = super.get(R.string.preferences_services_push_received_pushes, new JSONArray().toString());
            try {
                JSONArray jsonArray = new JSONArray(jsonString);
                boolean displayed = false;
                for (int i = 0; (i < jsonArray.length()) && !displayed; i++) {
                    if (jsonArray.getString(i).equals(id)) displayed = true;
                }
                return displayed;
            } catch (JSONException e) {
                e.printStackTrace();
                // If There's An Error, Don't Tell The App To Display The Push - This Will Annoy The User.
                return true;
            }
        }

        public void setPushDisplayedAlready(String id) {
            if (!getPushDisplayedAlready(id)) {
                String jsonString = super.get(R.string.preferences_services_push_received_pushes, new JSONArray().toString());
                JSONArray jsonArray;
                try {
                    jsonArray = new JSONArray(jsonString);
                } catch (JSONException e) {
                    e.printStackTrace();
                    // If There's An Error, Rewrite The Database.
                    jsonArray = new JSONArray();
                }
                jsonArray.put(id);
                super.set(R.string.preferences_services_push_received_pushes, jsonArray.toString());
            }
        }

        public boolean getScheduleNotifiedAlready(String link) {
            return super.get(R.string.preferences_services_refresh_file_link, "").equals(link);
        }

        public void setScheduleNotifiedAlready(String link) {
            if (!getScheduleNotifiedAlready(link)) {
                super.set(R.string.preferences_services_refresh_file_link, link);
            }
        }

        public void setChannel(int channel) {
            super.set(R.string.preferences_services_push_channel, channel);
        }

        public int getChannel(int defaultValue) {
            return super.get(R.string.preferences_services_push_channel, defaultValue);
        }
    }

    public class UserManager extends Manager.PublicManager {

        public UserManager(Context context, SharedPreferences preferences) {
            super(context, preferences);
        }
    }
}
