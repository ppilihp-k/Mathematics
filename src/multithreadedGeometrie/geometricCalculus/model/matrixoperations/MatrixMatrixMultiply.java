package multithreadedGeometrie.geometricCalculus.model.matrixoperations;


import singlethreadedGeometrie.geometricCalc.model.Matrix;

/**
 * Created by PhilippKroll on 08.11.2016.
 */
public class MatrixMatrixMultiply {
    public Matrix multiply(Matrix m1, Matrix m2) {
        int nm1 = m1.length();
        int nm2 = m2.length();
        if (nm1 != nm2)
            throw new IllegalArgumentException();
        Matrix m3 = new Matrix(nm1);
        double c = 0;
        for (int row = 0; row < nm1; row++) {
            for (int col = 0; col < nm1; col++) {
                c = 0;
                for (int i = 0; i < nm1; i++) {
                    c = m3.get(row, col);
                    c += (m1.get(row, i) * m2.get(i, col));
                    m3.set(row, col, c);
                }
            }
        }
        return m3;
    }
}
