package multithreaded.control;

import geometricCalculus.model.Matrix;
import utils.Tupel;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Created by PhilippKroll on 08.11.2016.
 */
public class MatrixCalculator {
    public MatrixCalculator(){
    }

    public Tupel<Class,Future<Object>> multiply(Matrix m1, Matrix m2){
        Callable<Object> task = new Callable<Object>() {
            public Matrix call(){
                if(m1 == null || m2 == null || m1.length() != m2.length()){
                    return null;
                }
                int dim = m1.length();
                Matrix m3 = new Matrix(dim);
                double c = 0;
                for (int row = 0; row < dim; row++) {
                    for (int col = 0; col < dim; col++) {
                        c = 0;
                        for (int i = 0; i < dim; i++) {
                            c = m3.get(row, col);
                            c += (m1.get(row, i) * m2.get(i, col));
                            m3.set(row, col, c);
                        }
                    }
                }
                return m3;
            }
        };
        Tupel<Class,Future<Object>> t = new Tupel<>();
        t.first = Matrix.class;
        t.second = Monitor.getInstance().addTask(task);
        return t;
    }
}
