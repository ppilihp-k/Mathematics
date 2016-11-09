package utils;

/**
 * Created by PhilippKroll on 04.10.2016.
 */
public class testplaneintersection {
    public static void main(String[] args){

        long start,end;
        System.out.println("--------------------------------test for 'big' values-------------------------------------------");
        System.out.println("plus(double) test:");
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000000;i++){
            double c = 999999999999999999999999999999.999999999999999999999999999999999998d + 999999999999999999999999999999.999999999999999999999999999999999998d;
        }
        end = System.currentTimeMillis();
        System.out.println("time: "+(end - start));
        System.out.println("plus(float) test:");
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000000;i++){
            float c = 999999999999999999999999999999.999999999999999999999999999999999998f + 999999999999999999999999999999.999999999999999999999999999999999998f;
        }
        end = System.currentTimeMillis();
        System.out.println("time: "+(end - start));
        System.out.println("minus(double) test:");
        for (int i = 0; i < 1000000000;i++){
            double c = 999999999999999999999999999999.999999999999999999999999999999999998d - 999999999999999999999999999999.999999999999999999999999999999999998d;
        }
        end = System.currentTimeMillis();
        System.out.println("time: "+(end - start));
        System.out.println("minus(float) test:");
        for (int i = 0; i < 1000000000;i++){
            float c = 999999999999999999999999999999.999999999999999999999999999999999998f - 999999999999999999999999999999.999999999999999999999999999999999998f;
        }
        end = System.currentTimeMillis();
        System.out.println("time: "+(end - start));
        System.out.println("multiply(double) test:");
        for (int i = 0; i < 1000000000;i++){
            double c = 999999999999999999999999999999.999999999999999999999999999999999998d * 999999999999999999999999999999.999999999999999999999999999999999998d;
        }
        end = System.currentTimeMillis();
        System.out.println("time: "+(end - start));
        System.out.println("multiply(float) test:");
        for (int i = 0; i < 1000000000;i++){
            float c = 999999999999999999999999999999.999999999999999999999999999999999998f * 999999999999999999999999999999.999999999999999999999999999999999998f;
        }
        end = System.currentTimeMillis();
        System.out.println("time: "+(end - start));
        System.out.println("divide(double) test:");
        for (int i = 0; i < 1000000000;i++){
            double c = 999999999999999999999999999999.999999999999999999999999999999999998d / 999999999999999999999999999999.999999999999999999999999999999999998d;
        }
        end = System.currentTimeMillis();
        System.out.println("time: "+(end - start));
        System.out.println("divide(float) test:");
        for (int i = 0; i < 1000000000;i++){
            float c = 999999999999999999999999999999.999999999999999999999999999999999998f / 999999999999999999999999999999.999999999999999999999999999999999998f;
        }
        end = System.currentTimeMillis();
        System.out.println("time: "+(end - start));
        System.out.println("--------------------------------test for 'small' values-------------------------------------------");
        System.out.println("plus(double) test:");
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000000;i++){
            double c = 0.000000000000000000000000000000000000000000000999999999999999d + 0.000000000000000000000000000000000000000000000999999999999999d;
        }
        end = System.currentTimeMillis();
        System.out.println("time: "+(end - start));
        System.out.println("plus(float) test:");
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000000;i++){
            float c = 0.000000000000000000000000000000000000000000000999999999999999f + 0.000000000000000000000000000000000000000000000999999999999999f;
        }
        end = System.currentTimeMillis();
        System.out.println("time: "+(end - start));
        System.out.println("minus(double) test:");
        for (int i = 0; i < 1000000000;i++){
            double c = 0.000000000000000000000000000000000000000000000999999999999999d - 0.000000000000000000000000000000000000000000000999999999999999d;
        }
        end = System.currentTimeMillis();
        System.out.println("time: "+(end - start));
        System.out.println("minus(float) test:");
        for (int i = 0; i < 1000000000;i++){
            float c = 0.000000000000000000000000000000000000000000000999999999999999f - 0.000000000000000000000000000000000000000000000999999999999999f;
        }
        end = System.currentTimeMillis();
        System.out.println("time: "+(end - start));
        System.out.println("multiply(double) test:");
        for (int i = 0; i < 1000000000;i++){
            double c = 0.000000000000000000000000000000000000000000000999999999999999d * 0.000000000000000000000000000000000000000000000999999999999999d;
        }
        end = System.currentTimeMillis();
        System.out.println("time: "+(end - start));
        System.out.println("multiply(float) test:");
        for (int i = 0; i < 1000000000;i++){
            float c = 0.000000000000000000000000000000000000000000000999999999999999f * 0.000000000000000000000000000000000000000000000999999999999999f;
        }
        end = System.currentTimeMillis();
        System.out.println("time: "+(end - start));
        System.out.println("divide(double) test:");
        for (int i = 0; i < 1000000000;i++){
            double c = 0.000000000000000000000000000000000000000000000999999999999999d / 0.000000000000000000000000000000000000000000000999999999999999d;
        }
        end = System.currentTimeMillis();
        System.out.println("time: "+(end - start));
        System.out.println("divide(float) test:");
        for (int i = 0; i < 1000000000;i++){
            float c = 0.000000000000000000000000000000000000000000000999999999999999f / 0.000000000000000000000000000000000000000000000999999999999999f;
        }
        end = System.currentTimeMillis();
        System.out.println("time: "+(end - start));
    }
}
