package multithreaded.model.matrixoperations;

import geometricCalculus.model.Matrix;

/**
 * Created by PhilippKroll on 08.11.2016.
 */
public class MatrixRank {
    public int rank(Matrix m){
        if (m.length() == 1) {
            if (m.get(0, 0) != 0) {
                return 1;
            } else {
                return 0;
            }
        }
        int n = m.length();
        for (int i = 1; i < n; i++) {
            if (m.get(0, 0) == 0) {
                m.swapRows(0, i);
            } else {
                break;
            }
        }
        for (int i = 1; i < n; i++) {
            double c1 = m.get(0, 0);
            double c2 = m.get(i, 0);
            if (c1 < 0 && c2 < 0 || c1 < 0 && c2 > 0) {
                c1 *= (-1);
            }
            for (int j = 0; j < n; j++) {
                if (c1 != 0 && c2 != 0) {
                    m.set(0, j, m.get(0, j) * c2);
                    m.set(i, j, m.get(i, j) * c1);
                }
            }
            if (m.get(i, 0) != 0) {
                m.subtractRows(0, i);
            }
            for (int j = 0; j < n; j++) {
                if (c2 != 0) {
                    m.set(0, j, m.get(0, j) / c2);
                } else {
                    break;
                }
            }
        }
        Matrix mk = m.getSubmatrix(1, 1, n - 1);
        for (int i = 0; i < n; i++) {
            if (m.get(0, i) != 0) {
                return rank(mk) + 1;
            }
        }
        return rank(mk);
    }
}
