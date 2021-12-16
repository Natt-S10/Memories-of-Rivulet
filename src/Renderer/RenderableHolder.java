package Renderer;

import Logic.LogicController;
import InGamePanel.MoneyStatus.MoneyBar;
import InGamePanel.VisualFX.FishCaughtFX;
import InGamePanel.Fishing.FishingPanel;

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
        MoneyBar moneyBar = ResourcesLoader.moneyBar;

        RenderableHolder.getInstance().add(fishCaughtFX);
        RenderableHolder.getInstance().add(fishingPanel);
        RenderableHolder.getInstance().add(moneyBar);
    }

    public void update() {
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).isDestroyed()) {
                elements.remove(i);
            }
        }
    }

    public void doBGM(){
        switch (LogicController.getInstance().getGameState()){
            case WALK, BAITING, AFTERFISHING, FISHRAISING, FISHING -> AudioAsset.playBGM(true);
            default -> AudioAsset.playBGM(false);
        }
    }

    public ArrayList<IRenderable> getElements() {
        return elements;
    }

}
