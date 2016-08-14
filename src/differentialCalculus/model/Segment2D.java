package differentialCalculus.model;
import differentialCalculus.pseudocontrol.*;

/**
 * Created by PhilippKroll on 07.08.2016.
 */
public class Segment2D implements Computable{
    Function f;
    public Segment2D(int m,int c){
        f = new Add(new Multiply(new Constant(m),new Variable()),new Constant(c));
    }

    public double compute(double x){
        return f.compute(x);
    }
}
