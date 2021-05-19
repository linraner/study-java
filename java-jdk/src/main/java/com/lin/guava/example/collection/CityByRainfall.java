package com.lin.guava.example.collection;

import com.google.common.primitives.Doubles;
import com.lin.guava.example.entity.City;
import java.util.Comparator;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-17 14:38
 * @Description:
 **/
public class CityByRainfall implements Comparator<City> {

  @Override
  public int compare(City o1, City o2) {
    return Doubles.compare(o1.getAverageRainfall(), o2.getAverageRainfall());
  }
}
