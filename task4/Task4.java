import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Task4 {
    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        int count = 0;
        while (count < args.length) { // заполняем  данными из файла
            for (String arg : args
            ) {
                fillCollection(arg, arrayList);
            }
            count++;
        }
        int result = findMinSteps(arrayList.get(0), arrayList); // присваиваем значение первого элемента, с которым будем сравнивать все остальные вариации
        for (int i = 1; i < arrayList.size(); i++) {
            int tempResult = findMinSteps(arrayList.get(i), arrayList);// пишем в переменную результат шагов каждой итерации
            if (tempResult < result) { // если находим меньшее число шагов, чем при приведении к первому числу - делаем его минимальным результатом
                result = tempResult;
            }
        }
        System.out.println(result);
    }

    public static int findMinSteps(int number, List<Integer> arrayList) {
        int steps = 0;
        for (int element : arrayList
        ) {
            if (element > number) {
                while (element != number) {
                    element--;
                    steps++;
                }
            } else if (element < number) {
                while (element != number) {
                    element++;
                    steps++;
                }
            }
        }
        return steps;
    }

    public static void fillCollection(String arg, List<Integer> collection) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(arg));
            String str;
            while ((str = reader.readLine()) != null) {          // читаем файл со значениями
                collection.add(Integer.parseInt(str)); //
            }
            reader.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}