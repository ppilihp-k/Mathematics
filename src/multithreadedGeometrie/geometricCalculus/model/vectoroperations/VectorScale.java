package multithreadedGeometrie.geometricCalculus.model.vectoroperations;

import singlethreadedGeometrie.geometricCalc.model.Vector;
import utils.Tupel;

/**
 * Created by PhilippKroll on 08.11.2016.
 */
public class VectorScale {
    public void scale(Vector v, double s){
        int n = v.length();
        for (int i = 0; i < n; i++) {
            double d = v.get(i) * s;
            v.set(i, d);
        }
    }

    public void scale(Tupel<Vector,Double> ... t){
        for (Tupel<Vector,Double> tupel:t){
            scale(tupel.first,tupel.second);
        }
    }
}
