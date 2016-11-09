package multithreadedGeometrie.geometricCalculus.model.matrixoperations;

import exceptions.InfiniteResultsException;
import exceptions.NoResultException;
import singlethreadedGeometrie.geometricCalc.model.Matrix;
import singlethreadedGeometrie.geometricCalc.model.Vector;

/**
 * Created by PhilippKroll on 08.11.2016.
 */
public class MatrixSolve {
    public Vector solve(Matrix m, Vector v) throws NoResultException,InfiniteResultsException{
            int nm = m.length();
            int nv = v.length();
            if (nm != nv) {
                throw new IllegalArgumentException();
            }

		/*
		 * if the determinante equals zero, there is an infinite amount of
		 * solutions or no result to this lgs, in this case we can stop here
		 */
            if ((new MatrixDeterminant()).determinant(m) == 0) {
                int r = (new MatrixRank()).rank(m);
			/*given the fact, that this solves only systems of equasion, where m is quadratic there is only
			 * the two following ways to interprete the determinantes value*/
                if (r < nm) {
                    throw new NoResultException();
                } else {
                    throw new InfiniteResultsException();
                }
            }

		/*
		 * if the equasionsystem has only one element left,the methode has to
		 * stop the recursion here
		 */
            if (nm == 1) {
			/*
			 * if there is a true statement, there is an infinite amount of
			 * solutions again. not sure, if this is needed -> checked the
			 * determinante earlier
			 */
                if (m.get(0, 0) == 0 && m.get(0, 0) != v.get(0)) {
                    throw new NoResultException();
                }
			/*
			 * if there is a wrong statement, there is no result for this
			 * equasionsystem
			 */
                if (m.get(0, 0) == v.get(0) && v.get(0) == 0) {
                    throw new InfiniteResultsException();
                }
			/* otherwise return the solution */
                Vector result = new Vector(1);
                result.set(0, v.get(0) / m.get(0, 0));
                return result;
            }

		/*
		 * count zeros, if the first row has one, swap it until the content of
		 * the first element is not equal to zero
		 */
            for (int i = 1; i < nm; i++) {
                if (m.get(0, 0) == 0) {
                    m.swapRows(0, i);
                    v.swapRows(0, i);
                }
            }

		/* try to subtract rows so, that in the first column are only zeros */
            for (int i = 1; i < nm; i++) {
                double c1 = m.get(0, 0);
                double c2 = m.get(i, 0);
                if (c2 != 0 && c1 != 0) {
                    for (int j = 0; j < nm; j++) {
                        m.set(i, j, m.get(i, j) * c1);
                        m.set(0, j, m.get(0, j) * c2);
                    }
                    v.set(i, v.get(i) * c1);
                    v.set(0, v.get(0) * c2);
                    m.subtractRows(0, i);
                    v.subtractRows(0, i);
                    for (int j = 0; j < nm; j++) {
                        if (c2 != 0) {
                            m.set(0, j, m.get(0, j) / c2);
                        }
                    }
                    if (c2 != 0) {
                        v.set(0, v.get(0) / c2);
                    }
                }
            }
		/* create the new and smaller instance of this problem */
            Matrix mk = m.getSubmatrix(1, 1, nm - 1);
            Vector vk = v.subVector(1, nm - 1);
            Vector littleResult = null;
            try {
                littleResult = solve(mk, vk);
            } catch (Exception e) {
                e.printStackTrace();
            }

		/* compute the current problem, with help of the returened values */
            Vector result = new Vector(nm);
            int i = 0;
            while (i < littleResult.length()) {
                result.set(i + 1, littleResult.get(i));
                i++;
            }
		/* compute the first value of the actual row */
            i = 1;
            while (i < nm) {
                double c = m.get(0, i);
                m.set(0, i, c * result.get(i));
                if(m.get(0, i) < 0){
                    v.set(0, v.get(0) - m.get(0, i));
                } else {
                    v.set(0, v.get(0) - m.get(0, i));
                }
                i++;
            }
		/* set the last computed value in the resultvector */
            if (m.get(0, 0) != 0) {
                result.set(0, v.get(0) / m.get(0, 0));
            }
            return result;
    }
}
