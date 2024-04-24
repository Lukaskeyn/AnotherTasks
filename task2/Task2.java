import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    public static void main(String[] args) {
        List<String> file1List = new ArrayList<>(); //создаем все необходимые переменные
        List<String> file2List = new ArrayList<>();
        int count = 0;
        double radius, tX, tY, oX, oY;
        while (count < args.length) {
                for (String arg : args //читаем путь из аргументов
                ) {
                    if (count == 0) {
                        fillCollection(arg, file1List); // заполняем коллекцию вводными первого файла
                    } else {
                        fillCollection(arg, file2List); // заполняем коллекцию вводными второго файла
                    }
                    count++;
                }
            }
        if (file2List.isEmpty()) { //условие на 0
            System.out.println("Вы не ввели координат точки");
        } else {
            if (file2List.size() > 100) {
                System.out.println("Количество точек превышает 100");// условие на > 100
            } else {
                String[] coordinates = file1List.get(0).split(" ");// достаем координаты из первой строки
                oX = Double.parseDouble(coordinates[0]); // инициализиурем координаты центра окружности
                oY = Double.parseDouble(coordinates[1]);
                radius = Double.parseDouble(file1List.get(1)); // инициализируем и преобразуем радиус
                for (String dot : file2List                            //проходимся по каждой точке
                ) {
                    String[] temp = dot.split(" ");
                    tX = Double.parseDouble(temp[0]); // инициализиурем координаты точки
                    tY = Double.parseDouble(temp[1]);
                    double distance = Math.sqrt((Math.pow(tX - oX, 2)) + Math.pow(tY - oY, 2)); //вычисляем расстояние
                    if (radius > distance) {          //сравниваем с радиусом
                        System.out.println(1);
                    } else if (radius == distance) {
                        System.out.println(0);
                    } else {
                        System.out.println(2);
                    }
                }
            }
        }
    }
    public static void fillCollection( String arg, List<String> collection ){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(arg));
            String str;
            while ((str = reader.readLine()) != null) {          // читаем файл со значениями
                collection.add(str); //
            }
            reader.close();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
