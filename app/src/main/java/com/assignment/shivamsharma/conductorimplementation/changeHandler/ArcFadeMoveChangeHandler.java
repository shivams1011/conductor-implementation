package com.assignment.shivamsharma.conductorimplementation.changeHandler;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.transition.ChangeBounds;
import android.transition.ChangeClipBounds;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.changehandler.SharedElementTransitionChangeHandler;
import com.bluelinelabs.conductor.internal.TransitionUtils;

import java.util.ArrayList;
import java.util.Collections;

public class ArcFadeMoveChangeHandler extends SharedElementTransitionChangeHandler {

    private ArrayList<String> sharedElementName = new ArrayList<>();

    public ArcFadeMoveChangeHandler(String... transactionname) {
        Collections.addAll(sharedElementName, transactionname);
    }

    public ArcFadeMoveChangeHandler() {
    }


    @Override
    public void configureSharedElements(@NonNull ViewGroup container, @Nullable View from, @Nullable View to, boolean isPush) {

        for (String name: sharedElementName) {
            addSharedElement(name);
        }
    }

    @Nullable
    @Override
    public Transition getExitTransition(@NonNull ViewGroup container, @Nullable View from, @Nullable View to, boolean isPush) {
        return new Fade(Fade.OUT);
    }

    @Nullable
    @Override
    public Transition getSharedElementTransition(@NonNull ViewGroup container, @Nullable final View from, @Nullable View to, boolean isPush) {

        Transition transition = new TransitionSet().addTransition(new ChangeBounds()).addTransition(new ChangeClipBounds())
                .addTransition(new ChangeTransform());
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                if (from != null){

                    for (String name: sharedElementName) {
                        View nameView = TransitionUtils.findNamedView(from, name);
                        if (nameView !=null){
                            nameView.setVisibility(View.INVISIBLE);
                        }
                    }
                }

            }

            @Override
            public void onTransitionEnd(Transition transition) {

            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
        return transition;
    }

    @Nullable
    @Override
    public Transition getEnterTransition(@NonNull ViewGroup container, @Nullable View from, @Nullable View to, boolean isPush) {
        return new Fade(Fade.IN);
    }

    @Override
    public boolean allowTransitionOverlap(boolean isPush) {
        return false;
    }
}
