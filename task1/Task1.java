public class Task1 {
    public static void main(String[] args)  {
        if (args.length < 2 ) {
            System.out.println("Неверное количество аргументов"); // проверяем количество аргументов
        } else {
            int n, m;                   //задаем необходимые переменные
            int startElement = 0;
            boolean isEnd = false;
            StringBuilder result = new StringBuilder();  // в эту переменную запишем результат
            try {
                n = Integer.parseInt(args[0]); //читаем значения длины массива и шага
                m = Integer.parseInt(args[1]);
                int[] circleArray = new int[n]; // создание массива размером n
                for (int i = 0; i < circleArray.length; i++) { // заполнение массива числами от 1 до n
                    circleArray[i] = i + 1;
                }
                while (!isEnd) { // запускаем цикл до момента, пока начальный элемент кругового массива не станет равен последнему элементу временного
                    int[] tempArray = new int[m]; //создаем временный массив по размеру шага
                    for (int i = 0; i < m; i++) {  //разбиваем на интервалы по размеру шага
                        tempArray[i] = circleArray[startElement];
                        if (i != tempArray.length - 1) { // если итерация не равна конечному элементу кругового массива - переходим к следующему элементу
                            startElement++;
                            if (startElement == circleArray.length) { // переходим на новый круг, дабы вернуться к началу кругового массива
                                startElement = 0;
                            }
                        }
                        if (tempArray[tempArray.length - 1] == circleArray[0]) { //если конечный элемент интервала равен первому элементу кругового массива - заканчиваем цикл
                            isEnd = true;
                        }
                    }
                    result.append(tempArray[0]); // добавляем первые значения интервалов в наш путь
                }
                System.out.println(result); // выводим результат
            }
            catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
}
