package com.daizzyinfo.food18.utils;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;


import java.util.Locale;

public class VerifyOTPCountdownTimer {
    private long millisInFuture;
    private long countDownInterval;
    private Handler handler;
    private Runnable runnable;
    private boolean isRunning = false;
    private TextView textView;
    private long startTime; // Add startTime variable

    public VerifyOTPCountdownTimer(long millisInFuture, long countDownInterval, TextView textView) {
        this.millisInFuture = millisInFuture;
        this.countDownInterval = countDownInterval;
        this.handler = new Handler(Looper.getMainLooper());
        this.textView = textView;
        this.runnable = new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                // Update the text view with the remaining time
                textView.setText("OTP Expired in " + getTimeRemainingString()+"s");
            }
        };
        this.startTime = System.currentTimeMillis(); // Initialize startTime
    }

    public void start() {
        isRunning = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                startTime = System.currentTimeMillis();
                while (isRunning) {
                    long currentTime = System.currentTimeMillis();
                    long timeRemaining = millisInFuture - (currentTime - startTime);
                    if (timeRemaining <= 0) {
                        // Countdown finished
                        isRunning = false;
                        handler.post(new Runnable() {
                            @SuppressLint({"SetTextI18n"})
                            @Override
                            public void run() {
                                // Update the text view with a message indicating that the countdown has finished

                                Spannable word = new SpannableString("I don’t receive a code! ");
                                word.setSpan(new ForegroundColorSpan(Color.BLACK), 0, word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                textView.setText(word);
                                Spannable wordTwo = new SpannableString("Please resend");
                                wordTwo.setSpan(new ForegroundColorSpan(Color.RED), 0, wordTwo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                textView.append(wordTwo);
//                                textView.setText("Didn't receive OTP. Resend");
                            }
                        });
                        return;
                    }
                    if (timeRemaining < countDownInterval) {
                        countDownInterval = timeRemaining;
                    }
                    handler.post(runnable);
                    try {
                        Thread.sleep(countDownInterval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void cancel() {
        isRunning = false;
    }

    private String getTimeRemainingString() {
        long timeRemaining = millisInFuture - (System.currentTimeMillis() - startTime);
        long seconds = timeRemaining / 1000;
        long minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
    }
}
