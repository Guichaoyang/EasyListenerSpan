package com.example.easylistenerspan;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.text.style.ReplacementSpan;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

public class EasySpan extends ReplacementSpan {

// text=������������Եģ�TextView�������Ч������Ҫ��������������¼��õġ� start=0end=38 x=0.0 top = 0 y = 45bottom=57
// text=������������Եģ�TextView�������Ч������Ҫ��������������¼��õġ� start=0end=38 x=0.0 top = 0 y = 45bottom=57
// text=������������Եģ�TextView�������Ч������Ҫ��������������¼��õġ� start=0end=38 x=0.0 top = 0 y = 45bottom=57

	
	
	private Context mContext ; 
	private TextView mView ;
	public EasySpan(Context context , TextView view) {
		this.mContext = context ; 
		this.mView = view ;
	}
	
	@Override
	public void draw(Canvas canvas, CharSequence text, int start, int end,
			float x, int top, int y, int bottom, Paint paint) {
		canvas.save();
		canvas.drawText( text.toString(), x - startX, y, paint);
		canvas.restore();
		int measureText = (int) paint.measureText(text, 0, text.length());
		if (valueAnimator == null ) {
			initAnimator(measureText - mView.getWidth()); 
		}
	}
	
	private int startX = 0 ;
	private final static int DEFULT_DURATION = 2000 ;
	private int duration = DEFULT_DURATION ;

	@Override
	public int getSize(Paint paint, CharSequence text, int start, int end,
			FontMetricsInt fm) {
		return 40;
	}
	
	private void initAnimator(int width ){
		 	valueAnimator = ValueAnimator.ofInt(0, width , 0 );
		 	valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					startX = (Integer) animation.getAnimatedValue() ;
					mView.invalidate();
				}
			});
		 	valueAnimator.setInterpolator(new LinearInterpolator());
		 	valueAnimator.setDuration(duration); 
		 	valueAnimator.addListener(new AnimatorListenerAdapter() {
		 		@Override
				public void onAnimationRepeat(Animator animation) {
					if (mEasySpanListener != null ) {
						mEasySpanListener.over(); 
					}
				}
			});
		 	valueAnimator.setRepeatCount(Integer.MAX_VALUE);
		 	valueAnimator.start(); 
		
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	private EasySpanListener mEasySpanListener ;
	private ValueAnimator valueAnimator;
	public void setEasySpanListener(EasySpanListener mEasySpanListener){
		this.mEasySpanListener = mEasySpanListener ;
	}
	
	public interface EasySpanListener {
		public void over();
	}

}
