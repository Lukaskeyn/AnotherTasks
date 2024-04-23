import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    public static void main(String[] args) {
        List<String> file1List = new ArrayList<>(); //создаем все необходимые переменные
        List<String> file2List = new ArrayList<>();
        int count = 0;
        int radius, tX,tY,oX,oY;
        try {
            while (count < args.length) { //читаем путь из аргументов
                for (String arg:args
                     ) {
                    BufferedReader readFile = new BufferedReader(new FileReader(arg));//читаем файлы
                    String str;
                    if( count == 0) {
                        while ((str = readFile.readLine()) != null) {
                            file1List.add(str); // заполняем коллекцию вводными первого файла
                        }
                    }
                    else {
                        while ((str = readFile.readLine()) != null) {
                            file2List.add(str); // заполняем коллекцию вводными второго файла
                        }
                    }

                    readFile.close();//закрываем поток
                    count++;
                }


            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        String[] coordinates = file1List.get(0).split(" ");// достаем координаты из первой строки
        oX = Integer.parseInt(coordinates[0]); // инициализиурем координаты центра окружности
        oY = Integer.parseInt(coordinates[1]);
        radius = Integer.parseInt(file1List.get(1)); // инициализируем радиус
        for (String dot: file2List                            //проходимся по каждой точке
             ) {
          String[] temp = dot.split(" ");
          tX =Integer.parseInt(temp[0]);
          tY =Integer.parseInt(temp[0]);
          int distance = (int) ((Math.pow(tX - oX,2)) + (Math.pow(tY - oY,2))); //вычисляем расстояние
            if(radius > distance) {          //сравниваем с радиусом
                System.out.println(1);
            }
            else if(radius == distance) {
                System.out.println(0);
            }
            else {
                System.out.println(2);
            }
        }
    }
}
