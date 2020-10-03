package com.example.example_3.classes.Handlers.FitText;

import java.util.ArrayList;
import java.util.List;

public class FitTextPublisher implements Fitable {

    private boolean fitTextOnDisplay;
    private static FitTextPublisher instance = null;
    private List<Fitable> fitables = new ArrayList<>();

    public static FitTextPublisher getInstance() {
        if (instance == null) {
            instance = new FitTextPublisher();
        }
        return instance;
    }

    @Override
    public void updateFitText(boolean fitTextOnDisplay) {
        notify(fitTextOnDisplay);
    }

    public void subscribe(Fitable fitable) {
        fitables.add(fitable);
        updateFitText(fitTextOnDisplay);
    }

    public void unsubscribe(Fitable fitable) {
        fitables.remove(fitable);
    }

    public void notify(boolean fitTextOnDisplay) {
        this.fitTextOnDisplay = fitTextOnDisplay;
        for (Fitable fitable : fitables) {
            fitable.updateFitText(fitTextOnDisplay);
        }
    }


}
