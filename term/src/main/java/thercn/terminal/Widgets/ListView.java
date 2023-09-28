package thercn.terminal.Widgets;

import android.content.Context;
import android.util.AttributeSet;

public class ListView extends android.widget.ListView {

	public ListView(Context context) {
		super(context);
	}

	public ListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		heightMeasureSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

}

