package multithreaded.model.matrixoperations;

import geometricCalculus.model.Matrix;

/**
 * Created by PhilippKroll on 08.11.2016.
 */
public class MatrixDeterminant {
    public double determinant(Matrix m){
        if (m.length() < 2) {
            return m.get(0, 0);
        }
        if (m.length() == 2) {
            return (m.get(0, 0) * m.get(1, 1) - m.get(0, 1) * m.get(1, 0));
        }
        double s = 0;
        int n = m.length();
        for (int i = 0; i < n; i++) {
            s += ((Math.pow(-1, i) * m.get(0, i) * determinant(m.discard(0, i))));
        }
        return s;
    }
}
