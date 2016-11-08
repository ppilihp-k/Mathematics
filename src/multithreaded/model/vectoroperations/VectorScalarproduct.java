package multithreaded.model.vectoroperations;

import geometricCalculus.model.Vector;

/**
 * Created by PhilippKroll on 08.11.2016.
 */
public class VectorScalarproduct {
    public double scalarproduct(Vector v1,Vector v2){
        int n1 = v1.length();
        int n2 = v2.length();
        if(n1 != n2 || n1 > 3 || n2 > 3){
            throw new IllegalArgumentException();
        }
        double s = 0;
        for (int i = 0; i < n1; i++) {
            s += (v1.get(i) * v2.get(i));
        }
        return s;
    }
}
