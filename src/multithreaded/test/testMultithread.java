package multithreaded.test;

import geometricCalculus.GeometricCalculator;
import geometricCalculus.model.Matrix;
import multithreaded.control.MatrixCalculator;
import multithreaded.control.Monitor;
import utils.Tupel;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by PhilippKroll on 08.11.2016.
 */
public class testMultithread {
    public static void main(String[] args){
        Matrix m1 = new Matrix(3);
        m1.set(0,0,2);
        m1.set(1,1,2);
        m1.set(2,2,2);
        m1.set(0,1,2);
        Matrix m2 = new Matrix(3);
        m2.set(0,0,2);
        m2.set(1,1,2);
        m2.set(2,2,2);
        m2.set(0,1,2);
        MatrixCalculator mc = new MatrixCalculator();
        Tupel<Class,Future<Object>> f = mc.multiply(m1,m2);

        long start,end;

        start = System.currentTimeMillis();
        for(int j = 0;j < 10;j++){
            for (int i = 0;i < 1000000;i++){
                mc.multiply(m1,m2);
            }
        }
        end = System.currentTimeMillis();
        System.out.println("thread: "+(end - start));

        GeometricCalculator gc = new GeometricCalculator();

        start = System.currentTimeMillis();
        for (int i = 0;i < 1000000;i++){
            gc.multiply(m1,m2);
        }
        end = System.currentTimeMillis();
        System.out.println("ohne: "+2*5*(end - start));

        System.out.println(f.first);
        try{
            System.out.println(f.second == null);
            System.out.println(f.second.get());
        } catch (Exception e){
            e.printStackTrace();
            Monitor.getInstance().terminate();
        }
        Monitor.getInstance().terminate();
    }
}
