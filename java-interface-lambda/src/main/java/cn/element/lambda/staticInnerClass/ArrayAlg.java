package cn.element.lambda.staticInnerClass;

public class ArrayAlg {
    
    public static class Pair {
        private final double first;
        private final double second;

        public Pair(double first, double second) {
            this.first = first;
            this.second = second;
        }

        public double getFirst() {
            return first;
        }

        public double getSecond() {
            return second;
        }
    }
    
    public static Pair minmax(double[] values) {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;

        for (double value : values) {
            if (min > value) {
                min = value;
            }
            
            if (max < value) {
                max = value;
            }
        }
        
        return new Pair(min, max);
    }
    
    
    

}
