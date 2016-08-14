package differentialCalculus;

import differentialCalculus.pseudocontrol.*;

/**
 * Created by PhilippKroll on 07.08.2016.
 */
public class test {
    public static void main(String[] args){
        //Function f = new Exponentiate(new Constant(2),new Variable());
        //Function f = new Multiply(new Constant(2),new Multiply(new Logarithmus(new Constant(2)),new Variable()));
        //Function f = new Logarithmus(new Constant(2));
        //Function f = new Subtract(new Multiply(new Constant(2),new Exponentiate(new Variable(),new Constant(2))),new Exponentiate(new Variable(),new Constant(5)));
        //Function f = new Cosinus(new Exponentiate(new Variable(),new Constant(2)));
        //Function f = new Multiply(new Constant(2),new Multiply(new Constant(2),new Exponentiate(new Variable(),new Constant(3))));
        //Function f = new Multiply(new Constant(2),new Sinus(new Variable()));
        //Function f = new Subtract(new Add(new Constant(4),new Constant(1)),new Constant(4));
        //Function f = new Multiply(new Constant(2),new Variable());
        Function f = new Exponentiate(new Variable(),new Multiply(new Constant(2),new Variable()));

        System.out.println(f);
        System.out.println(Math.log(2)*2);
        System.out.println(f.derivative());
       System.out.println(f.integrate());
    }
}
