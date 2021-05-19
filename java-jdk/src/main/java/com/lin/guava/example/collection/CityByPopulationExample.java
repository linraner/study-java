package com.lin.guava.example.collection;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import com.lin.guava.example.entity.City;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-17 14:33
 * @Description: Ordering排序
 **/
public class CityByPopulationExample implements Comparator<City> {

  @Override
  public int compare(City o1, City o2) {
    return Ints.compare(o1.getPopulation(), o2.getPopulation());
  }

  public static void main(String[] args) {
    CityByPopulationExample cityByPopulation = new CityByPopulationExample();
    CityByRainfall cityByRainfall = new CityByRainfall();
    // 根据第二个参数排序
    City city1 = new City("Beijing", 100000, 55.0);
    City city2 = new City("Shanghai", 100000, 45.0);
    City city3 = new City("ShenZhen", 100000, 33.8);
    List<City> cities = Lists.newArrayList(city1, city2, city3);

    /**
     * 单参数排序
     */
    // 排序反转
    Ordering<City> firstOrdering = Ordering.from(cityByRainfall).reverse();
    Collections.sort(cities, firstOrdering);
    Iterator<City> cityByRainfallIterator = cities.iterator();
    while (cityByRainfallIterator.hasNext()) {
      System.out.println(cityByRainfallIterator.next().getCityName());
    }
    System.out.println("I was evil dividing line");
    /**
     * 多参数排序
     */
    Ordering<City> secondaryOrdering = Ordering.
        from(cityByPopulation).compound(cityByRainfall);
    Collections.sort(cities, secondaryOrdering);
    Iterator<City> cityIterator = cities.iterator();
    while (cityIterator.hasNext()) {
      System.out.println(cityIterator.next().getCityName());
    }
    /**
     * 取得最小最大值
     */
    Ordering<City> ordering = Ordering.from(cityByRainfall);
    // 降雨量最高的2个城市
    List<City> topTwo = ordering.greatestOf(cities, 2);
    Iterator<City> topTwoIterator = topTwo.iterator();
    while (topTwoIterator.hasNext()) {
      System.out.println("降雨量最高城市" + topTwoIterator.next().getCityName());
    }
    // 降雨量最低的一个城市
    List<City> bottomOne = ordering.leastOf(cities, 1);
    Iterator<City> bottomOneIterator = bottomOne.iterator();
    while (bottomOneIterator.hasNext()) {
      System.out.println("降雨量最低的城市" + bottomOneIterator.next().getCityName());
    }
  }
}
