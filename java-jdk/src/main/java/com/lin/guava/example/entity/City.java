package com.lin.guava.example.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-17 14:31
 * @Description:
 **/
@Getter
@Setter
@ToString
public class City {

  private String cityName;
  private Integer population;
  private Double averageRainfall;

  public City() {
  }

  public City(String cityName, Integer population, Double averageRainfall) {
    this.cityName = cityName;
    this.population = population;
    this.averageRainfall = averageRainfall;
  }
}
