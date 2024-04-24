import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Task3 {
    public static void main(String[] args) {
        List<String> testsList = new ArrayList<>();// создаем коллекции для файлов с данными
        List<String> valuesList = new ArrayList<>();
        int count = 0;
        if (args.length < 3 ) {
            System.out.println("Неверное количество аргументов"); // проверяем количество аргументов
        } else {
            while (count < args.length - 1) { //заполняем коллекции из файлов
                for (String arg : args
                ) {
                    if (count == 0) {
                        fillCollection(arg, valuesList);
                    } else {
                        fillCollection(arg, testsList);
                    }
                    count++;
                }
            }
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(args[2])); // открываем поток для записи
                String value = ""; // временная переменная для заполнения тестов с совпадающим id
                boolean noRun = false; // переключатель для тестов, которые не запускались или чье значение отсутствует
                String spaces = ""; // счетчик пробелов для форматирования документа
                for (int i = 0; i < testsList.size(); i++) { //проходимся по коллекции с тестами
                    if (testsList.get(i).matches(".*tests.*")) {
                        testsList.set(i, testsList.get(i).replace("tests", "report"));// меняем шапку
                    }
                    if (testsList.get(i).matches("(.*\"values\".*)")) { // отслеживаем пробелы по массивам
                        spaces = spaces.concat("  ");
                    }
                    if (matchesID(testsList.get(i))) {  // если текущий элемент коллекции  содержит id теста, то достаем его в целочисленную переменную
                        int testId = getID(testsList.get(i));
                        for (int j = 0; j < valuesList.size(); j++) {
                            if (matchesID(valuesList.get(j))) { // делаем тоже самое с элементом коллекции со значениями
                                int valueId = getID(valuesList.get(j));
                                if (testId == valueId) {
                                    value = valuesList.get(j + 1); // следующая строка в данном случае всегда содержит результат выполнения проверки
                                    noRun = false; // поскольку  id проверки есть в выполненных тестах, помечаем, что запускалась
                                    break;
                                } else
                                    noRun = true; // если id не был найден, значит проверка не запускалась - ставим маркер
                            }
                        }
                    }
                    if (testsList.get(i).contains("]")) { //если массив закрывается - убираем лишние пробелы в итоговом файле
                        if (spaces.length() > 2) {
                            spaces = spaces.substring(0, spaces.length() - 2); //точечное удаление лишних пробелов
                        } else {
                            spaces = spaces.trim(); // полное удаление лишних пробелов
                        }
                    }
                    if (testsList.get(i).matches("(.*\"value\".*)") && !noRun) { // заполняем и форматируем значения в итоговом файле
                        if (testsList.get(i).contains(",")) {
                            testsList.set(i, spaces.concat(value.concat(",")));
                        } else {
                            testsList.set(i, spaces.concat(value));
                        }
                    }
                    writer.write(testsList.get(i)); //пишем элемент коллекции с тестами в файл
                    writer.newLine(); // добавляем новую строку в файле
                }
                writer.close(); //закрываем поток
            } catch (Exception e) {
                e.printStackTrace();
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
    public static int getID(String elementWithID){         //парсим элемент содержащий id
        String[] array = elementWithID.split(":");
        array[array.length-1] = array[array.length-1].replace(",","");
        return Integer.parseInt(array[array.length-1].trim());
    }
      public static boolean matchesID(String element) { //  если строка содержит id элемента, готовим ее к тому, чтобы распарсить
          return element.matches("(.*\"id\".*)");
      }
}
