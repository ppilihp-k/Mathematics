package multithreadedGeometrie.geometricCalculus.model.matrixoperations;


import singlethreadedGeometrie.geometricCalc.model.Matrix;

/**
 * Created by PhilippKroll on 08.11.2016.
 */
public class MatrixScale {
    public void scale(Matrix m, double s) {
        int n = m.length();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double d = m.get(i, j) * s;
                m.set(i, j, d);
            }
        }
    }
}
