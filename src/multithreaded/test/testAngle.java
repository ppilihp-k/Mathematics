package multithreaded.test;

import geometricCalculus.GeometricCalculator;
import geometricCalculus.model.Matrix;
import geometricCalculus.model.Vector;
import multithreaded.control.Monitor;
import multithreaded.model.MatrixTaskFactory;
import multithreaded.model.VectorTaskFactory;
import utils.Tupel;

import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.DoubleAccumulator;

/**
 * Created by PhilippKroll on 08.11.2016.
 */
public class testAngle {
    public static void main(String[] arg){
        VectorTaskFactory tf = new VectorTaskFactory();
        Vector v1 = new Vector(2);
        v1.set(0,0);
        v1.set(1,1);
        Vector v2 = new Vector(2);
        v2.set(0,5);
        v2.set(1,2);
        long start,end;

        start = System.currentTimeMillis();
        LinkedList<Future<Object>> list = new LinkedList<>();
        for (int i= 0;i< 500000;i++){
            Callable<Object> task = tf.createVectorVectorAngle(v1,v2);
            try{
                Future<Object> f = Monitor.getInstance().addTask(task);
                list.add(f);
            } catch (Exception e){

            }
        }
        end = System.currentTimeMillis();
        System.out.println("threaded:"+(end - start));

        Monitor.getInstance().terminate();

        GeometricCalculator gc = new GeometricCalculator();

        start = System.currentTimeMillis();
        for (int i= 0;i< 500000;i++){
            gc.angle(v1,v2);
        }
        end = System.currentTimeMillis();
        System.out.println("normal:"+(end - start));

    }
}
