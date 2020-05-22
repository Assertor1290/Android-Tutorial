package com.example.constraintlayoutdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    //Utility class for creating a default transition that automatically
    //fades, moves, and resizes views during a scene change.
    //It extends TransitionSet class
    AutoTransition autoTransition;
    //This class allows you to define programmatically a set of
    //constraints to be used with ConstraintLayout
    ConstraintSet constraintSet;
    ConstraintLayout constraintLayout;

    ImageView imageView;
    ConstraintLayout.LayoutParams layoutParams;
    ValueAnimator valueAnimator;

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Constraint set object.(to avoid null reference)
        constraintSet = new ConstraintSet();
        //Constructs an AutoTransition object
        autoTransition = new AutoTransition();
        constraintLayout = findViewById(R.id.constraintLayout);

        imageView=findViewById(R.id.rocketIcon);
        valueAnimator = animatePointer(TimeUnit.SECONDS.toMillis(5));
//        valueAnimator.start();

        btn=findViewById(R.id.button);
        // to wait until the layout is laid out before attempting the transition.
        constraintLayout.post(new Runnable() {
            @Override
            public void run() {
                //Copy the constraints from a layout.
                constraintSet.clone(getApplicationContext(), R.layout.activity_final);

                //Setting a non-negative duration on a TransitionSet causes all
                //of the child transitions (current and future) to inherit this duration.
                autoTransition.setDuration(2000);
                //This class manages the set of transitions that fire when there is a change of Scene.
                //Calling this method causes TransitionManager to capture current values
                //in the scene root and then post a request to run a transition on the next frame.
                //At that time, the new values in the scene root will be captured and
                //changes will be animated.
                TransitionManager.beginDelayedTransition(constraintLayout, autoTransition);
                //Apply the constraints to a ConstraintLayout.
                constraintSet.applyTo(constraintLayout);

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueAnimator.start();
            }
        });
    }

    private ValueAnimator animatePointer(long toMillis) {
        ValueAnimator anim = ValueAnimator.ofInt(0, 359);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                layoutParams= (ConstraintLayout.LayoutParams) imageView.getLayoutParams();
                layoutParams.circleAngle=val;
                imageView.setLayoutParams(layoutParams);
            }
        });
        anim.setDuration(toMillis);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatMode(ValueAnimator.RESTART);
        anim.setRepeatCount(ValueAnimator.INFINITE);
        return anim;
    }
}
