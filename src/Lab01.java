import java.util.InputMismatchException;
import java.util.Scanner;

public class Lab01 {

    public static void main(String[] args) {
        try {
            System.out.println("输入产生式个数:");
            Scanner scN = new Scanner(System.in);
            int n = scN.nextInt();

            if (n > 0) {
                Grammar grammar = new Grammar(n);

                // 输入 产生式
                Scanner scExpr = new Scanner(System.in);
                for(int i = 0; i < n; i++) {
                    System.out.println("输入第" + (i+1) + "个产生式");
                    String[] sp = scExpr.next().split(":");
                    grammar.setN(i, sp[0].toCharArray(), sp[1].toCharArray());
                }

                grammar.judge();
                grammar.printResult();
            }
            else
                System.out.println("非法输入");

        } catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
            System.out.println("非法输入！");
        }
    }

}
