import java.util.Scanner;

public class Calc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main result = new Main();
        System.out.println("Введите выражение: ");
        String expression = scanner.nextLine();         // Ввод выражения
        expression = expression.replaceAll("\\s", ""); // Убираем пробелы
        String Otvet = result.calc(expression);         // Вызываем метод Calc
        System.out.println("Ответ: " + Otvet);          // Выводим ответ
    }
}

class Main{
    public static String calc(String input) {
        boolean rim = false;                            //Число не римское, для проверки
        String[] arifOper = {"+", "-", "/", "*"};       // Для определения есть ли нужный оператор в выражении
        int arifmZnak = 0;                              // Для определения какой арифметический знак в вырожении
        Main rimExamination = new Main();               // Вводим для проверки и перевода из рим в араб
        Main arabToRim = new Main();                    // Для перевода ответа в римские
        String exeption = "Сработало исключение Главное";       // Исключение
        int result = 0;

        for (int i = 0; i < arifOper.length; i++) {     // выясняем есть ли в выражении нужный оператор и какой он
            if (input.contains(arifOper[i])) {
                arifmZnak = i;
                break;
            }
        }

        String[] chisla = input.split("[+\\-*/]");      //делим выражение на 2 числа и узнаем если есть нужный оператор
        if (chisla.length !=2) {                              // если нужный оператор есть и он один, то числа должно быть два
            exeption = "Сработало исключение. Не выполнено условие двух чисел или нет нужного оператора";
            return exeption;
        }
        Integer number1 = 0;
        Integer number2 = 0;
        try {
            number1 = Integer.valueOf(chisla[0]);               // проверка на арабское.
            number2 = Integer.valueOf(chisla[1]);
        } catch (NumberFormatException ex) {
            try {
                number1 = rimExamination.rimToArab(chisla[0]);      // проверка на римское
                number2 = rimExamination.rimToArab(chisla[1]);

                rim = true;
            } catch (NumberFormatException e) {
                return exeption;
            }
        }

        if (number1 < 1 || number1 > 10 || number2 < 1 || number2 > 10) {                 // проверяем числа подходят по условию или нет
            exeption = "Сработало исключение. Не выполнено условие: числа должны быть от 1 до 10";
            return exeption;
        }

        String operator = arifOper[arifmZnak];                                             // Выполняем арифметические действия
        switch (operator) {
            case "+" -> result = number1 + number2;
            case "-" -> result = number1 - number2;
            case "*" -> result = number1 * number2;
            case "/" -> result = number1 / number2;
        }

        String output;

        if (rim) {                                                                          //Проверяем на отрицательнойсть римского ответа
            if (result < 1) {
                exeption = "Сработало исключение. Так как в римской системе нет отрицательных чисел";
                return exeption;
            } else {
                output = arabToRim.arabToRim(result);
            }
        } else {
            output = Integer.toString(result);
        }
        return output;
    }



    Integer rimToArab(String rimskInput){                            // Переводим римский ввод в арабский
        String[] RimChislaArray = new String[] {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
                "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
                "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
                "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
                "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
                "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
                "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
                "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
                "XCVIII", "XCIX", "C"};
        int result = 0;
        for (int i = 0; i <RimChislaArray.length; i++) {                // ищем порядковый номер римской цифры
            if (rimskInput.equals(RimChislaArray[i])) {
                return i;                                               //возвращаем арабскую цифру равную порядковому номеру
            }
        }

        return result;
    }

    String arabToRim(int arabInput){                                  // Перевод араб в рим
        String[] RimChislaArray = new String[] {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
                "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
                "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
                "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
                "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
                "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
                "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
                "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
                "XCVIII", "XCIX", "C"};
        String result = "";
        result = RimChislaArray[arabInput];             // ищем римскую цифру под порядковым номеров арабской цифры
        return result;
    }
}




