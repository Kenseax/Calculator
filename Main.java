import Operations.Operation;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IllegalStateException {

        startCalculator();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String split = scanner.nextLine();
            if (split.equalsIgnoreCase("exit")) {
                System.out.println("Программа завершена");
                break;
            }

            try {

                String[] arrayAfterSplit = split.split(" ");
                String firstElement = arrayAfterSplit[0];
                String secondElement = arrayAfterSplit[2];
                String operator = arrayAfterSplit[1];


                if (split.contains("I") || split.contains("V") || split.contains("X")) {

                    int romAfterConvert1 = ConvertNumber.romanToArabic(firstElement);
                    int romAfterConvert2 = ConvertNumber.romanToArabic(secondElement);

                    if ((arrayAfterSplit.length == 3) && (romAfterConvert1 > 0 && romAfterConvert2 >= 0) && (romAfterConvert1 < 11 && romAfterConvert2 < 11)) {

                        String result = ConvertNumber.arabicToRoman(calculateUsingFactory(romAfterConvert1, romAfterConvert2, operator));
                        System.out.println(result);
                        System.out.println("Введите следующую операцию:");

                    } else {
                        throw new IllegalStateException("Вы ввели неверные данные!");
                    }

                } else {
                    int firstArabicNum = Integer.parseInt(firstElement);
                    int secondArabicNum = Integer.parseInt(secondElement);

                    if ((arrayAfterSplit.length == 3) && (firstArabicNum > 0 && secondArabicNum >= 0) && (firstArabicNum < 11 && secondArabicNum < 11)) {
                        System.out.println(calculateUsingFactory(firstArabicNum, secondArabicNum, operator));
                        System.out.println("Введите следующую операцию:");
                    } else {
                        throw new IllegalStateException("Вы ввели неверные данные!");
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException exception) {
            throw new ArrayIndexOutOfBoundsException("Вы ввели неверные данные!");
            }

        }
    }

    private static void startCalculator() {
        System.out.println("Добро пожаловать в строчный калькулятор!");
        System.out.println("Приложение считывает из консоли арифмитические операции");
        System.out.println("Такие, как Сложение(+), Вычитание(-), Умножение(*) и Деление(/).");
        System.out.println("На вход принимаются числа от 1 до 10. Как арабские, так и римские.");
        System.out.println("Пример ввода: 6 * 2; VII - IV \n");
        System.out.println("(чтобы завершить программу введите exit) \n");
        System.out.println("Введите операцию:");
    }

    public static int calculateUsingFactory(int a, int b, String operator) {
        Operation targetOperation = OperatorFactory
                .getOperation(operator)
                .orElseThrow(() -> new IllegalArgumentException("Неверный оператор: " + operator));
        return targetOperation.apply(a, b);
    }
}