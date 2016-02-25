package com.mt.chart.animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;

/**
 * Created by XieJiaHua on 2016/3/3.
 * Company:PeanutRun
 * Email:lylwo317@gmail.com
 */
public class ChartAnimator {

    protected float mPhaseY = 1f;

    private ValueAnimator.AnimatorUpdateListener mListener;

    private ObjectAnimator animatorY;

    public ChartAnimator(ValueAnimator.AnimatorUpdateListener updateListener) {
        mListener = updateListener;
        animatorY = ObjectAnimator.ofFloat(this, "phaseY", 0f, 1f);
    }

    public void animateY(int durationMillis) {

        if (android.os.Build.VERSION.SDK_INT < 11)
            return;
        animatorY.setDuration(durationMillis);
        animatorY.addUpdateListener(mListener);
        animatorY.start();
    }

    public void cancelAnimateY(){
        animatorY.cancel();
    }

    /**
     * This gets the y-phase that is used to animate the values.
     *
     * @return
     */
    public float getPhaseY() {
        return mPhaseY;
    }

    /**
     * This modifys the y-phase that is used to animate the values.
     *
     * @param phase
     */
    public void setPhaseY(float phase) {
        mPhaseY = phase;
    }

}
