public class Main {

    public static class LambdaManager {

        public static void main(String[] args) {
            System.out.println("Software design, laboratory work 1.1");
            Operationable operation;
            operation = (x,y)->x+y;

            int result = operation.calculate(4578678, 58757);
            System.out.println(result);
        }
    }
    interface Operationable{
        int calculate(int x, int y);
    }
}