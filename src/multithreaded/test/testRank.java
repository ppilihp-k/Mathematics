package multithreaded.test;

import geometricCalculus.model.Matrix;
import multithreaded.control.Monitor;
import multithreaded.model.MatrixTaskFactory;
import utils.Tupel;

import java.util.concurrent.Callable;

/**
 * Created by PhilippKroll on 08.11.2016.
 */
public class testRank {
    public static void main(String[] arg){
        MatrixTaskFactory tf = new MatrixTaskFactory();
        Matrix m = new Matrix(3);
        m.set(0,0,1);
        m.set(0,1,1);
        m.set(0,2,1);
        m.set(1,1,1);
        m.set(1,2,1);
        m.set(2,2,1);
        Callable<Object> task = tf.createMatrixRankTask(m);
        try{
            int rank = ((Tupel<Exception,Integer>)Monitor.getInstance().addTask(task).get()).second;
            System.out.println(rank);
        } catch (Exception e){

        }
    }
}
