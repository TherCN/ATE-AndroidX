package thercn.terminal.Utils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import jackpal.androidterm.emulatorview.TermSession;
import thercn.terminal.R;
import thercn.terminal.TerminalActivity;

public class ControlKey {
    TerminalActivity mActivity;
	TermSession session;
	boolean isPressed = false;
	public ControlKey(TerminalActivity activity) {
		this.mActivity = activity;

	}

	public void initControlKeys() {
		Button esc = mActivity.findViewById(R.id.esc);
		Button alt = mActivity.findViewById(R.id.alt);
		Button tab = mActivity.findViewById(R.id.tab);
		Button ctrl = mActivity.findViewById(R.id.ctrl);
		Button pgup = mActivity.findViewById(R.id.pgup);
		Button pgdn = mActivity.findViewById(R.id.pgdn);
		Button up = mActivity.findViewById(R.id.up);
		Button down = mActivity.findViewById(R.id.down);
		Button left = mActivity.findViewById(R.id.left);
		Button right = mActivity.findViewById(R.id.right);
		Button home = mActivity.findViewById(R.id.home);
		Button end = mActivity.findViewById(R.id.end);
		ctrl.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
					doSendControlKey();
				}
			});

		alt.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
					mActivity.getCurrentEmulatorView().sendAltKey();
					}
			});

		esc.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
					mActivity.getCurrentEmulatorView().onKeyDown(KeyEvent.KEYCODE_ESCAPE,
																 new KeyEvent(0x0, KeyEvent.KEYCODE_ESCAPE));
				}
			});

		setButtonEvent(pgup, KeyEvent.KEYCODE_PAGE_UP);
		setButtonEvent(pgdn, KeyEvent.KEYCODE_PAGE_DOWN);
		setButtonEvent(tab, KeyEvent.KEYCODE_TAB);
		setButtonEvent(left, KeyEvent.KEYCODE_DPAD_LEFT);
		setButtonEvent(right, KeyEvent.KEYCODE_DPAD_RIGHT);
		setButtonEvent(up, KeyEvent.KEYCODE_DPAD_UP);
		setButtonEvent(down, KeyEvent.KEYCODE_DPAD_DOWN);
		
		home.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
					mActivity.getCurrentEmulatorView().onKeyDown(KeyEvent.KEYCODE_MOVE_HOME,
																 new KeyEvent(0x0, KeyEvent.KEYCODE_MOVE_HOME));
				}
			});

		end.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
					mActivity.getCurrentEmulatorView().onKeyDown(KeyEvent.KEYCODE_MOVE_END,
																 new KeyEvent(0x0, KeyEvent.KEYCODE_MOVE_END));
				}
			});
	}
	private void setButtonEvent(Button button, final int keyId) {

		button.setOnTouchListener(new View.OnTouchListener(){

				@Override
				public boolean onTouch(View view, MotionEvent event) {
					long touchStartTime = System.currentTimeMillis();
					mActivity.getCurrentEmulatorView().onKeyDown(keyId,
																 new KeyEvent(0x0, keyId));
					switch (event.getAction()) {
						case MotionEvent.ACTION_DOWN:
							if (System.currentTimeMillis() - touchStartTime > 1000) {
								isPressed = true;
								new Thread(new Runnable() {
										@Override
										public void run() {
											while (isPressed) {
												try {
													Thread.sleep(100);
												} catch (InterruptedException e) {
													e.printStackTrace();
												}
												mActivity.getCurrentEmulatorView().onKeyDown(keyId,new KeyEvent(0x0, keyId));
											}
										}
									}).start();
							}
							break;
						case MotionEvent.ACTION_UP:
							isPressed = false;
							break;
					}
					return false;
				}
			});


	}

	private void doSendControlKey() {
        mActivity.getCurrentEmulatorView().sendControlKey();
    }

    private void doSendFnKey() {
        mActivity.getCurrentEmulatorView().sendFnKey();
    } 

}
