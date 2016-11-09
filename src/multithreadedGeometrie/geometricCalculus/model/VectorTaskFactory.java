package multithreadedGeometrie.geometricCalculus.model;

import multithreadedGeometrie.geometricCalculus.model.vectoroperations.*;
import singlethreadedGeometrie.geometricCalc.model.Vector;
import utils.Tupel;

import java.util.concurrent.Callable;

/**
 * Created by PhilippKroll on 08.11.2016.
 */
public class VectorTaskFactory {

    /**
     * adds v2 to v1
     * @param v1 first vector
     * @param v2 second vector
     * @return a new vector, representing v2 + v1
     */
    public Callable<Object> createVectorVectorAddition(Vector v1, Vector v2){
        Callable<Object> task = new Callable<Object>() {
            public Object call(){
                Tupel<Exception,Vector> t = new Tupel<>();
                try{
                    t.first = null;
                    t.second = (new VectorAddition().add(v1,v2));
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
     * subtracts v2 from v1
     * @param v1 first vector
     * @param v2 second vector
     * @return a new vector, representing v2 - v1
     */
    public Callable<Object> createVectorVectorSubtraction(Vector v1,Vector v2){
        Callable<Object> task = new Callable<Object>() {
            public Object call(){
                Tupel<Exception,Vector> t = new Tupel<>();
                try{
                    t.first = null;
                    t.second = (new VectorSubtraction().subtract(v1,v2));
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
     * scales the vector v1 by the factor s, be aware of the fact, that further operations on v1 should only be applied after the
     *  computation has finished!
     * @param v1 the vector
     * @param s the scalar
     * @return true, when the computation finished
     */
    public Callable<Object> createVectorScale(Vector v1,double s){
        Callable<Object> task = new Callable<Object>() {
            public Object call(){
                Tupel<Exception,Boolean> t = new Tupel<>();
                try{
                    t.first = null;
                    (new VectorScale()).scale(v1,s);
                    t.second = true;
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
     * this method creates the crossproduct of two vectors in the 3d vectorroom: this method only works for
     * 3 dimensional vectors!
     * @param v1
     * @param v2
     * @return a new vector, representing the vector, which stands in a 90° angle on v1 and v2
     */
    public Callable<Object> createVectorVectorCrossproduct(Vector v1,Vector v2){
        Callable<Object> task = new Callable<Object>() {

            public Object call(){
                Tupel<Exception,Vector> t = new Tupel<>();
                try{
                    t.first = null;
                    t.second = (new VectorCrossproduct()).crossProduct(v1,v2);
                } catch (IllegalArgumentException ia){
                    t.first = ia;
                    t.second = null;
                }
                return t;
            }

        };
        return task;
    }

    /**
     * computes the scalarproduct of v1 and v2
     * @param v1 the first vector
     * @param v2 the second vector
     * @return a tupel<exception,double> containing a exception in first, if one was thrown, otherwise null; and on second the result for the scalarproduct
     */
    public Callable<Object> createVectorVectorScalarproduct(Vector v1,Vector v2){
        if(!checkArg(v1,v2)){
            throw new IllegalArgumentException();
        }

        Callable<Object> task = new Callable<Object>() {

            public Object call(){
                Tupel<Exception,Double> t = new Tupel<>();
                double sp = (new VectorScalarproduct()).scalarproduct(v1,v2);
                t.first = null;
                t.second = sp;
                return t;
            }
        };

        return task;
    }

    /**
     * this method produces a callable object, which computes the angle between two vectors
     * @param v1 first vector
     * @param v2 second vector
     * @return a callable object, which computes the angle between two vectore upon invoke of call()
     */
    public Callable<Object> createVectorVectorAngle(Vector v1,Vector v2){

        Callable<Object> task = new Callable<Object>() {

            private Tupel<Exception,Double> t;

            public Object call(){
                if(!checkArg(v1,v2)){
                    throw new IllegalArgumentException();
                }
                t = new Tupel<>();
                try{
                    double angle = (new VectorAngle()).angle(v1,v2);
                    t.first = null;
                    t.second = angle;
                } catch (Exception ex){
                    t.first = ex;
                    t.second = null;
                }
                return t;
            }
        };

        return task;
    }

    private boolean checkArg(Vector v1,Vector v2){
        if(v1 == null || v2 == null || v1.length() != v2.length()){
            return false;
        }
        return true;
    }

    private boolean checkArg(Vector v1){
        if(v1 == null){
            return false;
        }
        return true;
    }
}
