package com.lab2;

import java.util.Objects;
import java.util.Scanner;



/**
 * Класс предназначенный для получения значений с клавиатуры
 */
public class Inputer {

  private final Scanner sc = new Scanner(System.in); // Считыватель текста с клавиатуры

  /**
   * Метод получения целочисленного значения
   *
   * @return -1 если ввод некорректен, число типа int при корректном вводе
   */
  public int getInt() {
    int res;
    try {
      res = Integer.parseInt(this.sc.nextLine());
    } catch (Exception e) {
      res = -1;
    }
    return res;
  }

  /**
   * Метод получения дробного значения
   *
   * @return -1 если ввод некорректен, число типа float при корректном вводе
   */
  public float getFloat() {
    float  res;
    try {
      res = Float.parseFloat(sc.nextLine().replace(",", "."));
    } catch (Exception e) {
      res = -1f;
    }
    return res;
  }

  /**
   * Метод получения строки
   *
   * @return null если ввод некорректен, строку при корректном вводе
   */
  public String getString() {
    String res;
    res = sc.nextLine();
    if (Objects.equals(res, "") || Objects.equals(res, "\n")) return null;
    return res;
  }

}
