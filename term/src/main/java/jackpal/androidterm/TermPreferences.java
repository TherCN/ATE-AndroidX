/*
 * Copyright (C) 2007 The Android Open Source Project
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

package jackpal.androidterm;

import android.app.ActionBar;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.view.MenuItem;
import jackpal.androidterm.Compat.ActivityCompat;
import jackpal.androidterm.emulatorview.compat.AndroidCompat;

public class TermPreferences extends PreferenceActivity {
    private static final String ACTIONBAR_KEY = "actionbar";
    private static final String CATEGORY_SCREEN_KEY = "screen";

	public static final int NAVIGATION_MODE_STANDARD = 0;
    public static final int NAVIGATION_MODE_LIST = 1;
    public static final int NAVIGATION_MODE_TABS = 2;
    public static final int DISPLAY_USE_LOGO = 1;
    public static final int DISPLAY_SHOW_HOME = 2;
    public static final int DISPLAY_HOME_AS_UP = 4;
    public static final int DISPLAY_SHOW_TITLE = 8;
    public static final int DISPLAY_SHOW_CUSTOM = 16;

    // Provides android.R.id.home from API 11 and up
    public static final int ID_HOME = 0x0102002c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);

        // Remove the action bar pref on older platforms without an action bar
        if (AndroidCompat.SDK < 11) {
            Preference actionBarPref = findPreference(ACTIONBAR_KEY);
			PreferenceCategory screenCategory =
				(PreferenceCategory) findPreference(CATEGORY_SCREEN_KEY);
			if ((actionBarPref != null) && (screenCategory != null)) {
				screenCategory.removePreference(actionBarPref);
			}
        }

        // Display up indicator on action bar home button

		
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
			case ID_HOME:
				// Action bar home button selected
				finish();
				return true;
			default:
				return super.onOptionsItemSelected(item);
        }
    }
}
