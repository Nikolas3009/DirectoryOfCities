package sbvi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static sbvi.CityUtils.*;

public class Main {
    /**
     * Считает количество городов в регионах
     * @param cities список городов
     * @return мапу со значениями ("Region", Count)
     */
    public static Map<String, Integer> citiesCounter(ArrayList<City> cities) {
        Map<String, Integer> map = new HashMap<>();

        for (City city : cities) {
            if (map.containsKey(city.getRegion())) { // в мапе уже есть регион
                map.replace(city.getRegion(), map.get(city.getRegion()) + 1);
            }
            else { // регион встречается впервые
                map.put(city.getRegion(), 1);
            }
        }

        return map;
    }

    public static void main(String[] args) {
        ArrayList<City> cities = readDataBase("city_ru.csv");

        Map<String, Integer> map = citiesCounter(cities);
        for (Object key : map.keySet()) {
            System.out.println(key + " - " + map.get(key));
        }
    }
}
