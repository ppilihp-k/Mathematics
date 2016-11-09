package multithreadedGeometrie.geometricCalculus.model.vectoroperations;

import singlethreadedGeometrie.geometricCalc.model.Vector;

/**
 * Created by PhilippKroll on 08.11.2016.
 */
public class VectorCrossproduct {
    public Vector crossProduct(Vector v1, Vector v2){
        int n1 = v1.length();
        int n2 = v2.length();
        if(n1 != n2  || n1 != 3 || n2 != 3){
            throw new IllegalArgumentException();
        }
        Vector cp = new Vector(n1);
        cp.set(0, (v1.get(1) * v2.get(2)) - (v1.get(2) * v2.get(1)));
        cp.set(1, (v1.get(2) * v2.get(0)) - (v1.get(0) * v2.get(2)));
        cp.set(2, (v1.get(0) * v2.get(1)) - (v1.get(1) * v2.get(0)));
        return cp;
    }
}
