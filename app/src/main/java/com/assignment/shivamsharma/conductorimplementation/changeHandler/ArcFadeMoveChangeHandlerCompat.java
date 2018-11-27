package com.assignment.shivamsharma.conductorimplementation.changeHandler;

import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.bluelinelabs.conductor.changehandler.TransitionChangeHandlerCompat;

public class ArcFadeMoveChangeHandlerCompat extends TransitionChangeHandlerCompat {

    public ArcFadeMoveChangeHandlerCompat(){
        super();
    }

    public ArcFadeMoveChangeHandlerCompat(String...transactionname) {
        super(new ArcFadeMoveChangeHandler(transactionname), new FadeChangeHandler());
    }
}
