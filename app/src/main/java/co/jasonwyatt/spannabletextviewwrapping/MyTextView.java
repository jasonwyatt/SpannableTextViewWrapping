package co.jasonwyatt.spannabletextviewwrapping;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author jason
 */

public class MyTextView extends TextView {
    private int mMode;
    private float mSizeValue;

    public MyTextView(Context context) {
        super(context);
        init(null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray a = getResources().obtainAttributes(attrs, R.styleable.MyTextView);

        mMode = a.getInt(R.styleable.MyTextView_mode, 0);
        mSizeValue = a.getFloat(R.styleable.MyTextView_sizevalue, 1.0f);

        a.recycle();
        setText(getText().toString());
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        SpannableStringBuilder ssb = new SpannableStringBuilder(text);
        Object s = null;
        switch (mMode) {
            case 0:
                s = new TypefaceSpan("monospace");
                break;
            case 1:
                s = new SuperscriptSpan();
                break;
            case 2:
                s = new SubscriptSpan();
                break;
            case 3:
                s = new ScaleXSpan(mSizeValue);
                break;
            case 4:
                s = new AbsoluteSizeSpan((int) mSizeValue, true);
                break;
            case 5:
                s = new RelativeSizeSpan(mSizeValue);
                break;
            case 6:
                s = new ForegroundColorSpan(0xFFFF0000);
                break;
        }
        if (s != null) {
            ssb.setSpan(s, ssb.length() / 2, ssb.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        super.setText(ssb, BufferType.SPANNABLE);
    }
}
