package com.example.example_4.classes.Handlers.Orientation;

import java.util.ArrayList;
import java.util.List;

public class OrientationPublisher implements Orientatiable {

    private boolean landscape;
    private static OrientationPublisher instance = null;
    private List<Orientatiable> orientatiables = new ArrayList<>();

    public static OrientationPublisher getInstance() {
        if (instance == null){
            instance = new OrientationPublisher();
        }
        return instance;
    }

    @Override
    public void updateOrientation(boolean orientation) {
        notify(orientation);
    }

    public void subscribe(Orientatiable orientatiable) {
        orientatiables.add(orientatiable);
        updateOrientation(landscape);
    }

    public void unsubscribe(Orientatiable orientatiable) {
        orientatiables.remove(orientatiable);
    }

    public void notify(boolean orientation) {
        landscape = orientation;
        for (Orientatiable orientatiable : orientatiables) {
            orientatiable.updateOrientation(orientation);
        }
    }

}
