package lab5;

/**
 * Класс исключения, возникающего  при присвоении
 * отрицательного числа полю float
 */
public class NegativeFloatException extends Exception{
  /**
   * Конструктор исключения
   *
   * @param exception сообщение исключения
   */
  public NegativeFloatException(String exception, float numb) {
    super (exception + numb);
  }
}
