package com.amazonaws;

import android.util.Log;

import com.amazonaws.mobile.AWSMobileClient;
import com.amazonaws.mobile.user.IdentityManager;
import com.amazonaws.mobileconnectors.cognito.Dataset;

/**
 * Simple class for user settings.
 */
public class UserSettings {
    private static final String LOG_TAG = UserSettings.class.getSimpleName();

    // dataset name to store user settings
    private static final String USER_SETTINGS_DATASET_NAME = "user_settings";

    // key names in dataset
    private static final String USER_SETTINGS_KEY_TITLE_TEXT_COLOR = "title_text_color";
    private static final String USER_SETTINGS_KEY_TITLE_BAR_COLOR = "title_bar_color";
    private static final String USER_SETTINGS_KEY_BACKGROUND_COLOR = "background_color";

    // default color
    private int titleTextColor = 0xFFFFFFFF; // white
    private int titleBarColor = 0xFFF58535; // orange
    private int backgroudColor = 0xFFFFFFFF; // white

    private static UserSettings instance;

    /**
     * Sets the text color in title bar.
     *
     * @param titleTextColor text color in 0xaabbccdd format
     */
    public void setTitleTextColor(final int titleTextColor) {
        this.titleTextColor = titleTextColor;
    }

    /**
     * Sets the background color of title bar.
     *
     * @param color background color of title bar in 0xaabbccdd format
     */
    public void setTitleBarColor(int color) {
        this.titleBarColor = color;
    }

    /**
     * Sets the background color of the main screen.
     *
     * @param color background color of the main screen in 0xaabbccdd format
     */
    public void setBackgroundColor(int color) {
        this.backgroudColor = color;
    }

    /**
     * Gets the text color in title bar.
     *
     * @return text color in title bar
     */
    public int getTitleTextColor() {
        return titleTextColor;
    }

    /**
     * Gets the background color of title bar.
     *
     * @return background color of title bar
     */
    public int getTitleBarColor() {
        return titleBarColor;
    }

    /**
     * Gets the background color of the main screen
     *
     * @return background color of the main screen
     */
    public int getBackgroudColor() {
        return backgroudColor;
    }


    /**
     * Loads user settings from local dataset into memory.
     */
    public void loadFromDataset() {
        Dataset dataset = getDataset();
        final String dataTextColor = dataset.get(USER_SETTINGS_KEY_TITLE_TEXT_COLOR);
        if (dataTextColor != null) {
            titleTextColor = Integer.valueOf(dataTextColor);
        }
        final String dataTitleBarColor = dataset.get(USER_SETTINGS_KEY_TITLE_BAR_COLOR);
        if (dataTitleBarColor != null) {
            titleBarColor = Integer.valueOf(dataTitleBarColor);
        }
        final String dataBackgroundColor = dataset.get(USER_SETTINGS_KEY_BACKGROUND_COLOR);
        if (dataBackgroundColor != null) {
            backgroudColor = Integer.valueOf(dataBackgroundColor);
        }
    }

    /**
     * Saves in memory user settings to local dataset.
     */
    public void saveToDataset() {
        Dataset dataset = getDataset();
        dataset.put(USER_SETTINGS_KEY_TITLE_TEXT_COLOR, String.valueOf(titleTextColor));
        dataset.put(USER_SETTINGS_KEY_TITLE_BAR_COLOR, String.valueOf(titleBarColor));
        dataset.put(USER_SETTINGS_KEY_BACKGROUND_COLOR, String.valueOf(backgroudColor));
    }

    /**
     * Gets the Cognito dataset that stores user settings.
     *
     * @return Cognito dataset
     */
    public Dataset getDataset() {
        return AWSMobileClient.defaultMobileClient()
                .getSyncManager()
                .openOrCreateDataset(USER_SETTINGS_DATASET_NAME);
    }

    /**
     * Gets a singleton of user settings
     *
     * @return user settings
     */
    public static UserSettings getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new UserSettings();
        final IdentityManager identityManager = AWSMobileClient.defaultMobileClient()
                .getIdentityManager();
        identityManager.addSignInStateChangeListener(
                new IdentityManager.SignInStateChangeListener() {
                    @Override
                    public void onUserSignedIn() {
                        Log.d(LOG_TAG, "load from dataset on user sign in");
                        instance.loadFromDataset();
                    }

                    @Override
                    public void onUserSignedOut() {
                        Log.d(LOG_TAG, "wipe user data after sign out");
                        AWSMobileClient.defaultMobileClient().getSyncManager().wipeData();
                        instance.saveToDataset();
                    }
                });
        return instance;
    }
}
