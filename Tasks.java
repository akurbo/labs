package сиаод;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Tasks {

    public static void main(String[] args) {

        // Первый блок задач

        System.out.println("Задача 1:");

        int[] mas = {3, 6, 2, 3};

        System.out.println("Периметр = " + zadacha1(mas));


        System.out.println("\nЗадача 2:");

        int len = 10, min = 0, max = 999;
        int[] mass = new int[len];
        Random rand = new Random();
        for (int i = 0; i < len; i++) {
            mass[i] = min + rand.nextInt(max - min + 1);
        }

        System.out.println("Максимально число = " + zadacha2(mass, len));


        System.out.println("\nЗадача 3:\n");

        int m = 10, n = 10, min_limit = 0, max_limit = 100;
        int[][] array = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = min_limit + rand.nextInt(max_limit - min_limit + 1);
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }

        zadacha3(array, m, n);

        // Задача про шарики

        int [][] points = {{-10,6},{-2,8},{-6,-1},{7,12}};
        System.out.println("\nЗадача про шарики и стрелы:");
        System.out.println(balloons(points));

        // Второй блок задач

        System.out.println("\nВторой блок задач. Задачи со строками");
        System.out.println("\nЗадача 1:");
        System.out.println(task1("abe", "acd"));

        System.out.println("\nЗадача 2:");
        System.out.println(task2("babad"));

        System.out.println("\nЗадача 3:");
        System.out.println(task3("abcabcabc"));


        // Задача про монеты

        System.out.println("\nЗадача про стопки монет:");
        int [] piles = {9,8,7,6,5,1,2,3,4};
        System.out.println(coins(piles));
    }


    public static int zadacha1(int[] mas) {
        int a, b, c;
        int per = 0;
        for (int i = 0; i < mas.length - 2; i++) {
            a = mas[i];
            for (int j = i + 1; j < mas.length - 1; j++) {
                b = mas[j];
                for (int k = j + 1; k < mas.length; k++) {
                    c = mas[k];
                    if ((a + b > c) && (a + c > b) && (b + c > a) && (a + b + c > per)) {
                        per = a + b + c;
                    }
                }
            }
        }
        return per;
    }

    public static String zadacha2(int[]mass, int len) {
        boolean check = true;
        int swap;
        if(len < 1){
            return "В массиве нет чисел";
        }
        String str1, str2, str3, ans = "";
        int num1, num2;
        while(check){
            check=false;
            for (int i = mass.length - 1; i > 0; i--) {
                str1 = Integer.toString(mass[i]) + Integer.toString(mass[i - 1]);
                str2 = Integer.toString(mass[i - 1]) + Integer.toString(mass[i]);
                num1 = Integer.parseInt(str1);
                num2 = Integer.parseInt(str2);
                if (num1 > num2) {
                    swap = mass[i];
                    mass[i] = mass[i - 1];
                    mass[i - 1] = swap;
                    check=true;
                }
            }
        }
        for (int num : mass) {
            str3 = Integer.toString(num);
            ans += str3;
        }
       return ans;
    }


    public static void zadacha3(int [][] array, int m, int n) {
        int swap;
        boolean check = true;

        // сортировка главной диагонали

        while (check){
            check = false;
            int i = 0;
            while (i < n - 1 && i < m - 1){
                if (array[i][i] > array[i + 1][i + 1]) {
                    swap = array[i][i];
                    array[i][i] = array[i + 1][i + 1];
                    array[i + 1][i + 1] = swap;
                    check = true;
                }
                i++;
            }
        }

        // сортировка под главной диагональю

        check = true;
        while (check){
            check = false;
            for(int i = m - 2; i > 0; i--) {
                int j = 0;
                while (j < n - 1 && j < m - 1){
                    if (array[i][j] > array[i + 1][j + 1]) {
                        swap = array[i][j];
                        array[i][j] = array[i + 1][j + 1];
                        array[i + 1][j + 1] = swap;
                        check = true;
                    }
                    j++;
                }
            }
        }

        // сортировка над главной диагональю

        check = true;
        while (check){
            check = false;
            for(int i = n - 2; i > 0; i--) {
                int j = 0;
                while (j < n - 1 && j < m - 1){
                    if (array[j][i] > array[j + 1][i + 1]) {
                        swap = array[j][i];
                        array[j][i] = array[j + 1][i + 1];
                        array[j + 1][i + 1] = swap;
                        check = true;
                    }
                    j++;
                }
            }
        }

        System.out.println("\nОтсортированная матрица\n");

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }
    }


    public static int balloons (int [][] points){
        ArrayList<String> array = new ArrayList<>();
        for (int[] point : points) {
            array.add(point[0] + "," + (point[1]));
        }
            for (int i = 0; i < array.size() - 1; i++) {
                for (int j = i + 1; j < array.size();) {
                    int index1 = array.get(i).indexOf(",");
                    int x1 = Integer.parseInt(array.get(i).substring(0, index1));
                    int x2 = Integer.parseInt(array.get(i).substring(index1 + 1));
                    int index2 = array.get(j).indexOf(",");
                    int x3 = Integer.parseInt(array.get(j).substring(0, index2));
                    int x4 = Integer.parseInt(array.get(j).substring(index2 + 1));
                    if ((x1 <= x4 && x4 <= x2) || (x1 <= x3 && x3 <= x2)) {
                        array.add((Math.max(x1, x3)) + "," + Math.min(x2, x4));
                        array.remove(i);
                        array.remove(j - 1);
                        i = 0;
                        j = i + 1;
                    } else {
                        j++;
                    }
                }
            }
        return array.size();
    }

    public static boolean task1(String s1, String s2){
        if(s1.length() != s2.length()){
            System.out.println("Длины строк должны быть равны");
        }
        char [] str1 = s1.toCharArray();
        char [] str2 = s2.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        System.out.println(str1);
        System.out.println(str2);
        return checkStrings(str1, str2) || checkStrings(str2, str1);
    }

    public static boolean checkStrings(char [] str1, char [] str2){
        int counter = 0;
        for(int i = 0; i < str1.length; i++){
            if(str1[i] >= str2[i]){
                counter++;
            }
        }
        return counter == str1.length;
    }

    public static String task2(String s){
        String substring = "";
        int maxLen = 0;
        if(s.contains(" ")){
            return "В строке не должно быть пробелов";
        }
        for(int i = 0; i < s.length(); i++) {
            char letter = s.charAt(i);
            int index = s.indexOf(letter, i + 1);
            if (index == -1) {
                continue;
            }
            while (index != -1){
                String check = s.substring(i, index + 1);
                String reversed = "";

                for(int j = check.length() - 1; j >= 0; j--){
                    reversed += check.charAt(j);
                }

                if(check.equals(reversed) && check.length() > maxLen){
                    maxLen = check.length();
                    substring = check;
                }
                index = s.indexOf(letter, index + 1);
            }
        }
        return substring;
    }

    public static int task3(String text){
        ArrayList<String> substrings = new ArrayList<>();
        if(text.contains(" ")){
            System.out.println("В строке не должно быть пробелов");
            return 0;
        }
        for(int i = 0; i < text.length(); i++) {
            char letter = text.charAt(i);
            int index = text.indexOf(letter, i + 1);
            if (index == -1) {
                continue;
            }

            while (index + index - i - 1 < text.length() && index != -1){
                String left = text.substring(i, index);
                String right = text.substring(index, index + index - i);
                if (left.equals(right) && !substrings.contains(left + right)) {
                    substrings.add(left + right);
                }
                index = text.indexOf(letter, index + 1);
            }
        }
        System.out.println(substrings);
        return substrings.size();
    }

    public static int coins (int [] piles){
        int sum = 0;

        if(piles.length == 0){
            System.out.println("На столе нет стопок монет");
            return 0;
        }
        if(piles.length % 3 != 0){
            System.out.println("На столе должно быть 3n стопок монет");
            return 0;
        }

        // Для достижения максимума каждый раз мы выбираем две стопки с наибольшим количеством монет и одну с наименьшим.
        // Тогда нам нужно просмотреть 2 * (piles.length / 3) наибольших стопок, из которых каждую вторую мы забираем себе

        int count = 2 * (piles.length / 3);
        Arrays.sort(piles);
        for (int i = piles.length - 2; i >= piles.length - count - 1; i-=2){
            sum += piles[i];
        }
        return sum;
    }
}
