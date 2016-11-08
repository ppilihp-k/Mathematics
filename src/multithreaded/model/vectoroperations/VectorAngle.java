package multithreaded.model.vectoroperations;

import geometricCalculus.model.Vector;

/**
 * Created by PhilippKroll on 08.11.2016.
 */
public class VectorAngle {
    public double angle(Vector v1,Vector v2){
        int n1 = v1.length();
        int n2 = v2.length();
        if(n1 != n2 || n1 > 3 || n2 > 3){
            throw new IllegalArgumentException();
        }
        double scp = (new VectorScalarproduct()).scalarproduct(v1,v2);
        double lp = v1.vectorLength() * v2.vectorLength();
        if(scp != 0){
            return Math.acos(scp/lp);
        }
        return 0;
    }
}
