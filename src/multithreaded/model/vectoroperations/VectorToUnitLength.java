package multithreaded.model.vectoroperations;

import geometricCalculus.model.Vector;

/**
 * Created by PhilippKroll on 08.11.2016.
 */
public class VectorToUnitLength {
    /**
     * makes the vector v a unitlength vector
     * @param v
     */
    public void toUnitLength(Vector v){
        double length = 1/v.vectorLength();
        for (int i = 0;i < v.length(); i++){
            v.set(i,v.get(i)*length);
        }
    }
}
