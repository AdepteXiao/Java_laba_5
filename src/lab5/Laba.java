package lab5;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;


/**
 * Класс для запуска программы
 */
public class Laba {

  public static void main(String[] args) throws NegativeFloatException, NegativeIntException {
    UI myUI = new UI();

    myUI.menu();
  }

}


/**
 * Интерфейс, реализующий нумерацию вариантов выбора для switch()
 */
interface menuEnum {

  int PRINT_FURNITURE = 1,
      ADD_VOID_FURNITURE = 2,
      ADD_FILLED_FURNITURE = 3,
      DELETE_FURNITURE = 4,
      EDIT_FURNITURE = 5,
      SORT_FURNITURE = 6,
      EXIT = 7;

  int CHANGE_NAME = 1,
      CHANGE_COUNTRY = 2,
      CHANGE_YEAR = 3,
      CHANGE_PRICE = 4,
      EXIT_TO_MENU = 5;

  int SORT_BY_NAME = 1,
      SORT_BY_COUNTRY = 2,
      SORT_BY_YEAR = 3,
      SORT_BY_PRICE = 4;
}

/**
 * Основной класс интерфейса
 */
class UI implements menuEnum {


  /**
   * Поток вывода, поддерживающий русские символы
   */
  private final PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

  /**
   * Экземпляр класса содержащего методы ввода разных типов
   */
  private final Inputer inp = new Inputer();

  /**
   * Основной список людей
   */
  private final ArrayList<Furniture> furnitureList = new ArrayList<>();


  /**
   * Метод основного меню работы
   */
  public void menu(){
    this.furnitureList.add(new Furniture());
    this.furnitureList.add(new Furniture());
    this.furnitureList.add(new Furniture());

    int choice;
    do {
      this.out.println("""
          1. Вывести список мебели
          2. Добавить незаполненную мебель к списку
          3. Заполнить мебель
          4. Удалить мебель по индексу
          5. Отредактировать данные мебели по индексу
          6. Отсортировать список мебели
          7. Завершить программу
          """);

      choice = inp.getInt();
      try {
        switch (choice) {
          case PRINT_FURNITURE -> this.printFurnitures();
          case ADD_VOID_FURNITURE -> this.addVoidFurniture();
          case ADD_FILLED_FURNITURE -> this.addParamFurniture();
          case DELETE_FURNITURE -> this.deleteFurniture();
          case EDIT_FURNITURE -> editFurniture();
          case SORT_FURNITURE -> sortFurnitureList();
          default -> {
            if (choice != EXIT) {
              this.out.println("Некорректный ввод");
            }
          }
        }
      } catch (NegativeIntException | NegativeFloatException | FurnitureParamException | IndexErrorException exc) {
        out.println("Ошибка ввода: \n" + exc.getMessage());
      } catch (AssertionError exc) {
        out.println(exc.getMessage());
      }

    } while (choice != EXIT);
  }

  /**
   * Метод, реализующий сортировку по выбранному в нем полю
   */
  private void sortFurnitureList() {
    this.out.println("""
        1. Сортировка по году прозводства
        2. Сортировка по цене
        3. Сортировка по стране производителе
        4. Сортировка по названию
        """);
    int choice = inp.getInt();
    switch (choice) {
      case SORT_BY_NAME -> furnitureList.sort(Comparator.comparing(Furniture::getName));
      case SORT_BY_COUNTRY -> furnitureList.sort(Comparator.comparing(Furniture::getCountry));
      case SORT_BY_YEAR -> furnitureList.sort(Comparator.comparing(Furniture::getProdYear));
      case SORT_BY_PRICE -> furnitureList.sort(Comparator.comparing(Furniture::getPrice));

      default -> System.out.println("Неизвестный параметр");
    }

  }

  /**
   * Метод удаления мебели из списка по индексу
   * @throws IndexErrorException - исключение при неверном вводе индекса
   * @throws AssertionError - исключение при попытке удаления мебели из пустого массива
   */
  private void deleteFurniture() throws IndexErrorException, AssertionError{
    assert !furnitureList.isEmpty() : "Список мебели пуст";
    out.println("Введите индекс мебели в списке:");
    int index = inp.getInt();
    if (index < 1 || index > furnitureList.size()) {
      throw new IndexErrorException("Индекс указан неверно: ", index);
    } else {
      this.out.println(furnitureList.get(index - 1).getName() + " удален(а) из списка");
      furnitureList.remove(index - 1);
    }
  }

  /**
   * Метод добавления мебели с задаваемыми параметрами
   * @throws FurnitureParamException - исключение при неверном вводе полей мебели
   */
  private void addParamFurniture() throws FurnitureParamException {

    String name, country;
    float price;
    int prodYear;

    out.println("Введите название:");
    name = inp.getString();

    out.println("Введите страну:");
    country = inp.getString();

    out.println("Введите год:");
    prodYear = inp.getInt();

    out.println("Введите цену:");
    price = inp.getFloat();

    try {
      furnitureList.add(new Furniture(price,
          prodYear, name, country));
    } catch (NegativeIntException | NegativeFloatException exc) {
      throw new FurnitureParamException(
          "Какое-то из полей заполнено неверно", exc);
    }
  }

  /**
   * Метод добавления человека с незаполненными полями
   */
  private void addVoidFurniture() {
    furnitureList.add(new Furniture());
  }

  /**
   * Метод редактирования любых полей человека
   * @throws NegativeFloatException - Исключение при неверном вводе цены
   * @throws NegativeIntException -  Исключение при неверном вводе года производства
   * @throws AssertionError - Исключение при попытке изменения мебели в пустом массиве
   * @throws IndexErrorException - Исключение при неверном вводе индекса
   */
  private void editFurniture()
      throws NegativeFloatException, NegativeIntException, AssertionError, IndexErrorException {
    assert !furnitureList.isEmpty() : "Список мебели пуст";
    out.println("Введите индекс мебели в списке:");
    int index = inp.getInt();
    if (index < 1 || index > furnitureList.size()) {
      throw new IndexErrorException("Индекс указан неверно: ", index);
    }
    Furniture furnitureToEdit = this.furnitureList.get(index - 1);
    int choice;
    do {
      this.out.println("""
          1. Изменить название
          2. Изменить страну производитель
          3. Изменить год производства
          4. Изменить цену
          5. Выйти в меню
          """);
      choice = this.inp.getInt();
      switch (choice) {

        case CHANGE_NAME -> changeNameHandler(furnitureToEdit);
        case CHANGE_COUNTRY -> changeCountryHandler(furnitureToEdit);
        case CHANGE_YEAR -> changeProdYearHandler(furnitureToEdit);
        case CHANGE_PRICE -> changePriceHandler(furnitureToEdit);
        case EXIT_TO_MENU -> this.out.println("Редактирование завершено");
        default -> this.out.println("Некорректный ввод");
      }

    } while (choice != EXIT_TO_MENU);
  }

  /**
   * Метод изменяющий название выбранной мебели
   *
   * @param furnitureToEdit Изменяемый экземпляр мебели
   */
  private void changeNameHandler(Furniture furnitureToEdit){
    this.out.println("Введите название:");
    furnitureToEdit.setName(this.inp.getString());
  }

  /**
   * Метод изменяющий страну выбранной мебели
   *
   * @param furnitureToEdit Изменяемый экземпляр мебели
   */
  private void changeCountryHandler(Furniture furnitureToEdit) {
    this.out.println("Введите страну производителя:");
    furnitureToEdit.setCountry(this.inp.getString());
  }


  /**
   * Метод изменяющий год производства выбранной мебели
   *
   * @throws NegativeIntException - Исключение при неверном вводе года произвдства
   * @param furnitureToEdit Изменяемый экземпляр мебели
   */
  private void changeProdYearHandler(Furniture furnitureToEdit)
      throws NegativeIntException {
    this.out.println("Введите год производства:");
    furnitureToEdit.setProdYear(this.inp.getInt());
  }

  /**
   * Метод изменяющий цену выбранной мебели
   *
   * @throws NegativeFloatException - исключение при неверном вводе цены
   * @param furnitureToEdit Изменяемый экземпляр мебели
   */
  private void changePriceHandler(Furniture furnitureToEdit) throws NegativeFloatException {
    this.out.println("Введите цену:");
    furnitureToEdit.setPrice(this.inp.getFloat());
  }

  /**
   * Метод вывода всего списка мебели
   */
  private void printFurnitures() {
    int num = 1;

    for (Furniture furniture : this.furnitureList) {
      this.out.printf("Мебель №%d\n", num);
      this.out.println(furniture);

      num++;
    }
  }
}