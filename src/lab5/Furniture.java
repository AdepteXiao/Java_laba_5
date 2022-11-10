package com.lab2;

import java.text.DecimalFormat;

/**
 * Класс мебели
 */
public class Furniture {

  /**
   * Для приведения чисел к нужному формату
   */
  private final DecimalFormat decimalFormat = new DecimalFormat("#.###");
  /**
   * Цена мебели
   */
  private float price;
  /**
   * Год производства мебели
   */
  private int prodYear;
  /**
   * Название мебели
   */
  private String name;
  /**
   * Страна производитель мебели
   */
  private String country;

  /**
   * Конструктор создающий пустой экземпляр класса
   */
  public Furniture() {
    this.country = "Неизвестна";
    this.name = "Без названия";
    this.prodYear = 0;
    this.price = 0;
  }

  /**
   * Конструктор создающий заполненный экземпляр класса
   *
   * @param price    Цена мебели
   * @param prodYear Год производства мебели
   * @param name     Название мебели
   * @param country  Страна производитель мебели
   */
  public Furniture(float price, int prodYear, String name, String country) {
    if (price > 0 &&
        prodYear > 0 &&
        prodYear < 2023 &&
        (name != null) &&
        (country != null)) {
      this.price = price;
      this.prodYear = prodYear;
      this.country = country;
      this.name = name;
    } else {
      this.country = "Неизвестна";
      this.name = "Без названия";
    }
  }

  /**
   * Метод получения цены мебели
   *
   * @return private float price - цена мебели
   */
  public float getPrice() {
    return price;
  }

  /**
   * Метод установки значения цены мебели
   *
   * @param price - цена мебели
   */
  public void setPrice(float price) {
    if (price > 0) {
      this.price = price;
    }
  }

  /**
   * Метод получения года производства мебели
   *
   * @return private int prodYear - год производства мебели
   */
  public int getProdYear() {
    return prodYear;
  }

  /**
   * Метод установки значения года производства мебели
   *
   * @param prodYear - год производства мебели
   */
  public void setProdYear(int prodYear) {
    if (prodYear > 0 &&
        prodYear < 2023) {
      this.prodYear = prodYear;
    }
  }

  /**
   * Метод получения название мебели
   *
   * @return private String name - название мебели
   */
  public String getName() {
    return name;
  }

  /**
   * Метод установки значения названия мебели
   *
   * @param name - название мебели
   */
  public void setName(String name) {
    if (!(name == null || name.equals(""))) {
      this.name = name;
    }
  }

  /**
   * Метод получения страны - производителя мебели
   *
   * @return private String country - страна - производитель мебели
   */
  public String getCountry() {
    return country;
  }

  /**
   * Метод установки значения страны - производителя мебели
   *
   * @param country страна - производитель мебели
   */
  public void setCountry(String country) {
    if (!(country == null || country.equals(""))) {
      this.country = country;
    }
  }

  /**
   * Метод получения месячной оплаты при взятии мебели в рассрочку под 5% годовых на 12 месяцев
   *
   * @return месячная оплата при взятии мебели в рассрочку под 5% годовых на 12 месяцев
   */
  public float getMonthlyPayment() {
    final float MONTHLY_PAYMENT = 1.05f;
    final int MONTHS = 12;
    return (price * MONTHLY_PAYMENT) / MONTHS;
  }

  /**
   * Метод строкового представления класса
   */
  @Override
  public String toString() {
    return String.format("""
            %s
            Цена: %s
            Страна производитель: %s
            Год производства %d
            Месячная оплата при рассрочке на год: %s
            """,
        name,
        decimalFormat.format(price),
        country,
        prodYear,
        decimalFormat.format(getMonthlyPayment()));
  }
}
