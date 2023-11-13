/*
 * Copyright (C) 2012 Steven Luo
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

package thercn.terminal;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import jackpal.androidterm.emulatorview.ColorScheme;
import jackpal.androidterm.emulatorview.EmulatorView;
import jackpal.androidterm.emulatorview.TermSession;
import thercn.terminal.Utils.TermSettings;

public class TermView extends EmulatorView {
	float scale = 1;
	int size;
    public TermView(Context context, TermSession session, DisplayMetrics metrics) {
        super(context, session, metrics);
    }

    public void updatePrefs(TermSettings settings, ColorScheme scheme) {
        if (scheme == null) {
            scheme = new ColorScheme(settings.getColorScheme());
        }
		size = settings.getFontSize();
        setTextSize(settings.getFontSize());
        setUseCookedIME(settings.useCookedIME());
        setColorScheme(scheme);
        setBackKeyCharacter(settings.getBackKeyCharacter());
        setAltSendsEsc(settings.getAltSendsEscFlag());
        setControlKeyCode(settings.getControlKeyCode());
        setFnKeyCode(settings.getFnKeyCode());
        setTermType(settings.getTermType());
        setMouseTracking(settings.getMouseTrackingFlag());
		final ScaleGestureDetector scaleGestureDetector = new ScaleGestureDetector(getContext(),new ScaleListener());
		setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					scaleGestureDetector.onTouchEvent(event);
					return false;
				}
			});
		
		
    }
	
	private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			scale *= detector.getScaleFactor();
			scale = Math.max(0.1f, Math.min(scale, 5.0f));  // 限制缩放范围，例如0.1到5倍
			setTextSize((int) (scale * size));  // 根据需要调整字体大小，这里假设初始字体大小为14sp
			return true;
		}
	}
    public void updatePrefs(TermSettings settings) {
        updatePrefs(settings, null);
    }

    @Override
    public String toString() {
        return getClass().toString() + '(' + getTermSession() + ')';
    }
}
