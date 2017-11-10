package com.wdullaer.materialdatetimepicker.time;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.v4.content.ContextCompat;
import android.view.View;

public class BitmapView extends View {

    private Paint mPaint = new Paint();

    OverlayDetails mDetails;

    public BitmapView(Context context) {
        this(context, null);
    }

    public BitmapView(Context context, OverlayDetails details) {
        super(context);
        mDetails = details;
    }

    @Override
    public void draw(Canvas canvas) {
        if (mDetails != null) {
            Drawable drawable = ContextCompat.getDrawable(getContext(), mDetails.getDrawableResId());
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

            mPaint.setAlpha((int) (mDetails.getAlpha() * 255));

            int width = getWidth()-1, height = getHeight()-1;
            int size = mDetails.getSize();

            Rect dest = new Rect(
                    (width - size)/2,
                    (height - size)/2,
                    (width - size)/2 + size,
                    (height - size)/2 + size);
            canvas.drawBitmap(bitmap, null, dest, mPaint);
        }

        super.draw(canvas);
    }

    public static class OverlayDetails {

        @DrawableRes
        private int mDrawableResId;
        private int mSize = 256;
        private @FloatRange(from=0.0, to=1.0) float mAlpha;

        public OverlayDetails(int drawableResId, int size) {
            this(drawableResId, size, 1);
        }

        public OverlayDetails(int drawableResId, int size, @FloatRange(from=0.0, to=1.0) float alpha) {
            mDrawableResId = drawableResId;
            mSize = size;
            mAlpha = alpha;
        }

        public int getDrawableResId() {
            return mDrawableResId;
        }

        public int getSize() {
            return mSize;
        }

        public float getAlpha() {
            return mAlpha;
        }
    }

}
