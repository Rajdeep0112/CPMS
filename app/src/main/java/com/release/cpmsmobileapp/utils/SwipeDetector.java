package com.release.cpmsmobileapp.utils;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;

public class SwipeDetector implements View.OnTouchListener {

    private static final int MIN_DISTANCE = 50;
    private float downX;
    private float downY;
    private final OnSwipeListener onSwipeListener;

    public SwipeDetector(OnSwipeListener listener) {
        this.onSwipeListener = listener;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                return true;
            case MotionEvent.ACTION_UP:
                float upX = event.getX();
                float upY = event.getY();
                float deltaX = downX - upX;
                float deltaY = downY - upY;

                // Detect horizontal swipes
                if (Math.abs(deltaX) > Math.abs(deltaY) && Math.abs(deltaX) > MIN_DISTANCE) {
                    if (deltaX > 0) {
                        // Right to left swipe (left swipe)
                        onSwipeListener.onSwipeLeft();
                    } else {
                        // Left to right swipe (right swipe)
                        onSwipeListener.onSwipeRight();
                    }
                    return true;
                }
                break;
        }
        return false;
    }

    public interface OnSwipeListener {
        void onSwipeLeft();

        void onSwipeRight();
    }
}
