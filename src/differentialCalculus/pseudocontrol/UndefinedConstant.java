package differentialCalculus.pseudocontrol;

/**
 * Created by PhilippKroll on 09.08.2016.
 */
public class UndefinedConstant extends Function {

    public UndefinedConstant(){
        super(null,null);
    }

    public Constant getConstant(){
        return new Constant(Double.NaN);
    }

    public double compute(double v){
        return Double.NaN;
    }

    public Function derivative(){
        return null;
    }

    public Function integrate(){
        return null;
    }

    public Function clone(){
        return new UndefinedConstant();
    }

}
