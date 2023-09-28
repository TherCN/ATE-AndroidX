package thercn.terminal.Utils;
import android.view.View;
import android.widget.Button;
import thercn.terminal.R;
import thercn.terminal.TerminalActivity;
import android.view.KeyEvent;

public class ControlKey {
    public TerminalActivity mActivity;
	
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
		tab.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
					mActivity.getCurrentEmulatorView().onKeyDown(KeyEvent.KEYCODE_TAB,
							new KeyEvent(0x0,KeyEvent.KEYCODE_TAB));
				}
			});
		//TODO 更多模拟按键待实现
	}
	
	private void doSendControlKey() {
        mActivity.getCurrentEmulatorView().sendControlKey();
    }

    private void doSendFnKey() {
        mActivity.getCurrentEmulatorView().sendFnKey();
    } 
    
}
