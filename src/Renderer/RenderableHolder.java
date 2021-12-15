package Renderer;

import Logic.LogicController;
import UIpanel.VisualFX.FishCaughtFX;
import UIpanel.fishing.FishingPanel;
import map.Map;

import java.util.ArrayList;
import java.util.Comparator;

public class RenderableHolder {
    private static final RenderableHolder instance = new RenderableHolder();

    private final ArrayList<IRenderable> elements;
    private final Comparator<IRenderable> layerComparator;

    public RenderableHolder() {

        elements = new ArrayList<IRenderable>();
        layerComparator = (IRenderable o1, IRenderable o2) -> {
            if (o1.getLayer() > o2.getLayer()) return 1;
            else if (o1.getLayer() == o2.getLayer()) return 0;
            return -1;
        };
    }


    public static RenderableHolder getInstance() {
        return instance;
    }

    public void add(IRenderable entity) {
        elements.add(entity);
        elements.sort(layerComparator);
        System.out.println(elements);
    }

    public void resetElements(){
        elements.clear();
        FishingPanel fishingPanel = ResourcesLoader.fishingPanel;
        FishCaughtFX fishCaughtFX = ResourcesLoader.fishCaughtFX;

        RenderableHolder.getInstance().add(fishCaughtFX);
        RenderableHolder.getInstance().add(fishingPanel);
    }

    public void update() {
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).isDestroyed()) {
                elements.remove(i);
            }

        }
    }

    public ArrayList<IRenderable> getElements() {
        return elements;
    }

}
