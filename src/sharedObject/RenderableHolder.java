package sharedObject;

import java.util.ArrayList;
import java.util.Comparator;

public class RenderableHolder {
    private static final RenderableHolder instance = new RenderableHolder();
    private ArrayList<IRenderable> entities;
    private Comparator<IRenderable> layerComparator;

    public RenderableHolder(){
        entities = new ArrayList<IRenderable>();
        layerComparator = (IRenderable o1, IRenderable o2) -> {
            if (o1.getLayer() > o2.getLayer()) return 1;
            else if(o1.getLayer() == o2.getLayer()) return 0;
            return -1;
        };
    }


    public static RenderableHolder getInstance(){
        return instance;
    }

    public void add(IRenderable entity){
        entities.add(entity);
        entities.sort(layerComparator);
    }

    public void update(){
        for(int i = 0; i<entities.size(); i++){
            if(entities.get(i).isDestroyed()){
                entities.remove(i);
            }
        }
    }

    public ArrayList<IRenderable> getEntities(){
        return entities;
    }

}
