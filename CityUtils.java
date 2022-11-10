package sbvi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class CityUtils {
    /**
     * Считывает базу данных из файла и заносит в список
     * @param filePath путь к файлу
     * @return заполненный список
     */
    public static ArrayList<City> readDataBase(String filePath) {
        ArrayList<City> cityData = new ArrayList<>();

        try {
            Scanner fileScanner = new Scanner(new File(filePath));
            while (fileScanner.hasNext()) {
                cityData.add(parseLine(fileScanner.nextLine()));
            }
            fileScanner.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        return  cityData;
    }

    /**
     * Считывет поля объекта из строки
     * @param line входящая строка
     * @return считанный объект
     */
    private static City parseLine(String line) {
        Scanner scan = new Scanner(line);
        scan.useDelimiter(";");

        int id = scan.nextInt();
        String name = scan.next();
        String region = scan.next();
        String district = scan.next();
        int population = scan.nextInt();
        String foundation = null;
        if (scan.hasNext()) {
            foundation = scan.next();
        }
        scan.close();

        City scanCity = new City(id, name, region, district, population, foundation);
        return scanCity;
    }

    /**
     * Сортировка по имени
     * @param cities список городов
     */
    public static void nameSort(ArrayList<City> cities){
        cities.sort(new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
    }

    /**
     * Сортировка по округу и имени
     * @param cities список городов
     */
    public static void districtSort(ArrayList<City> cities) {
        cities.sort(Comparator.comparing(City::getDistrict).thenComparing(City::getName));
    }

    /**
     * Преобразует список городов в массив (по численности населения)
     * @param cities список городов
     * @return полученный массив
     */
    public static int[] listToArray(ArrayList<City> cities) {
        int[] citiesArray = new int[cities.size()];

        for (int i = 0; i < cities.size(); i++) {
            citiesArray[i] = cities.get(i).getPopulation();
        }

        return citiesArray;
    }

    /**
     * Находит город с макс населением
     * @param cities список городов
     * @return id объекта
     */
    public static int searchMax(int[] cities) {
        int maxPopulation = cities[0];
        int maxID = 0;

        for (int i = 1; i < cities.length; i++) {
            if (cities[i] > maxPopulation) {
                maxPopulation = cities[i];
                maxID = i;
            }
        }

        return maxID;
    }

    /**
     * Выводит базу данных в консоль
     * @param cities база
     */
    public static void print(ArrayList<City> cities) {
        cities.forEach(System.out::println);
    }
}
