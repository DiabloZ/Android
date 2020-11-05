package com.example.example_3.classes.Handlers.Orientation;

import java.util.ArrayList;
import java.util.List;

public class OrientationPublisher implements Orientatinable{

    private boolean landscape;
    private static OrientationPublisher instance = null;
    private List<Orientatinable> orientatinables = new ArrayList<>();

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

    public void subscribe(Orientatinable orientatinable) {
        orientatinables.add(orientatinable);
        updateOrientation(landscape);
    }

    public void unsubscribe(Orientatinable orientatinable) {
        orientatinables.remove(orientatinable);
    }

    public void notify(boolean orientation) {
        landscape = orientation;
        for (Orientatinable orientatinable : orientatinables) {
            orientatinable.updateOrientation(orientation);
        }
    }

}
