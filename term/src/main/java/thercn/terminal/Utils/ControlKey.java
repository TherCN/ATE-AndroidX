package thercn.terminal.Utils;
import android.view.View;
import android.widget.Button;
import thercn.terminal.R;
import thercn.terminal.TerminalActivity;
import android.view.KeyEvent;
import jackpal.androidterm.emulatorview.TermSession;

public class ControlKey {
    public TerminalActivity mActivity;
	public TermSession session;
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
					mActivity.getCurrentEmulatorView().onKeyDown(KeyEvent.KEYCODE_ALT_LEFT,
																 new KeyEvent(0x0, KeyEvent.KEYCODE_ALT_LEFT));
				}
			});
			
		tab.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
					mActivity.getCurrentEmulatorView().onKeyDown(KeyEvent.KEYCODE_TAB,
																 new KeyEvent(0x0, KeyEvent.KEYCODE_TAB));
				}
			});
			
		esc.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
					mActivity.getCurrentEmulatorView().onKeyDown(KeyEvent.KEYCODE_ESCAPE,
																 new KeyEvent(0x0, KeyEvent.KEYCODE_ESCAPE));
				}
			});
		
		pgup.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
					mActivity.getCurrentEmulatorView().onKeyDown(KeyEvent.KEYCODE_PAGE_UP,
																 new KeyEvent(0x0, KeyEvent.KEYCODE_PAGE_UP));
				}
			});
			
		pgdn.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
					mActivity.getCurrentEmulatorView().onKeyDown(KeyEvent.KEYCODE_PAGE_DOWN,
																 new KeyEvent(0x0, KeyEvent.KEYCODE_PAGE_DOWN));
				}
			});
			
		left.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
					mActivity.getCurrentEmulatorView().onKeyDown(KeyEvent.KEYCODE_DPAD_LEFT,
																 new KeyEvent(0x0, KeyEvent.KEYCODE_DPAD_LEFT));
				}
			});
			
		right.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
					mActivity.getCurrentEmulatorView().onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT,
																 new KeyEvent(0x0, KeyEvent.KEYCODE_DPAD_RIGHT));
				}
			});
			
		up.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
					mActivity.getCurrentEmulatorView().onKeyDown(KeyEvent.KEYCODE_DPAD_UP,
																 new KeyEvent(0x0, KeyEvent.KEYCODE_DPAD_UP));
				}
			});
			
		down.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
					mActivity.getCurrentEmulatorView().onKeyDown(KeyEvent.KEYCODE_DPAD_DOWN,
																 new KeyEvent(0x0, KeyEvent.KEYCODE_DPAD_DOWN));
				}
			});
			
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

	private void doSendControlKey() {
        mActivity.getCurrentEmulatorView().sendControlKey();
    }

    private void doSendFnKey() {
        mActivity.getCurrentEmulatorView().sendFnKey();
    } 

}
