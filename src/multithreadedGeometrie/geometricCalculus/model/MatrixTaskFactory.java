package multithreadedGeometrie.geometricCalculus.model;

import multithreadedGeometrie.geometricCalculus.model.matrixoperations.*;
import singlethreadedGeometrie.geometricCalc.model.Matrix;
import singlethreadedGeometrie.geometricCalc.model.Vector;
import utils.Tupel;

import java.util.concurrent.Callable;

/**
 * Created by PhilippKroll on 08.11.2016.
 */
public class MatrixTaskFactory {

    /**
     * creates a task, which implements the callable interface, which can be worked out by a thread.
     * be carefull with usage -> the result must be typecasted into the correct class for further usage!
     * @param m1 first matrix
     * @param m2 second matrix
     * @return a task, which performs a matrix matrix multiplication -> m1*m2
     */
    public Callable<Object> createMatrixMatrixMultiply(Matrix m1, Matrix m2){
        if(m1 == null || m2 == null || m1.length() != m2.length()){
            throw new IllegalArgumentException();
        }

        Callable<Object> task = new Callable<Object>() {
            public Object call(){
                Tupel<Exception,Matrix> t = new Tupel<>();
                try{
                    t.first = null;
                    t.second = (new MatrixMatrixMultiply()).multiply(m1,m2);
                } catch (Exception e){
                    t.first = e;
                    t.second = null;
                }
                return t;
            }
        };

        return task;
    }

    /**
     * creates a task, which performs a matrix vector multiplication.
     * be carefull with usage -> the result must be typecasted into the correct class for further usage!
     * @param m the matrix
     * @param v the vector
     * @return a task, which can be worked out by a thread, returning an object which is a vector
     */
    public Callable<Object> createMatrixVectorMultiplication(Matrix m, Vector v){
        if(!checkArg(m,v)){
            throw new IllegalArgumentException();
        }

        Callable<Object> task = new Callable<Object>() {
            public Object call(){
                Tupel<Exception,Vector> t = new Tupel<>();
                try{
                    t.first = null;
                    t.second = (new MatrixVectorMultiply()).multiply(m,v);
                } catch (Exception e){
                    t.first = e;
                    t.second = null;
                }
                return t;
            }
        };

        return task;
    }

    /**
     * computes the determinant of the given matrix
     * @param m the matrix
     * @return a Double value representing the determinant of the matrix m
     */
    public Callable<Object> createMatrixDeterminant(Matrix m){
        if(!checkArg(m)){
            throw new IllegalArgumentException();
        }

        Callable<Object> task = new Callable<Object>() {

            public Object call(){
                Tupel<Exception,Double> t = new Tupel<>();
                try{
                    t.first = null;
                    t.second = (new MatrixDeterminant()).determinant(m);
                } catch (Exception e){
                    t.first = e;
                    t.second = null;
                }
                return t;
            }
        };

        return task;
    }

    /**
     * creates a task, which performs a scale action on the given matrix. the matrix should be used, if the returned value has the content of "true"!
     * otherwise the outcome of a read or write action leads to unpredictable results!
     * @param m the matrix
     * @param s the scalar
     * @return true, if the computation has terminated and the matrix's content can be used for further computations
     */
    public Callable<Object> createMatrixScale(Matrix m,double s){
        if(!checkArg(m)){
            throw new IllegalArgumentException();
        }

        Callable<Object> task = new Callable<Object>() {
            public Object call(){
                (new MatrixScale()).scale(m,s);
                return new Boolean(true);
            }
        };

        return task;
    }

    /**
     * returns a tupel\<Exception,Integer\> containing the exception, if one was thrown along the computation and the rank of the given matrix
     * @param m the matrix
     * @return a tupel with: first -> a exception, if one was thrown, otherwise null; second -> the rank as an Integer, or null, if a exception was thrown
     */
    public Callable<Object> createMatrixRankTask(Matrix m) {

        if (!checkArg(m)) {
            throw new IllegalArgumentException();
        }

        Callable<Object> task = new Callable<Object>() {
            public Object call() {
                Tupel<Exception,Integer> t = new Tupel<>();
                t = new Tupel<>();
                try {
                    t.first = null;
                    t.second = (new MatrixRank()).rank(m);
                } catch (Exception e) {
                    t.first = e;
                    t.second = null;
                }
                return t;
            }
        };
          return task;
    }

    private boolean checkArg(Matrix m1,Matrix m2){
        if(m1 == null || m2 == null || m1.length() != m2.length()){
            return false;
        }
        return true;
    }

    private boolean checkArg(Matrix m){
        if(m == null){
            return false;
        }
        return true;
    }

    private boolean checkArg(Matrix m,Vector v){
        if(m == null || v == null || m.length() != v.length()){
            return false;
        }
        return true;
    }
}
