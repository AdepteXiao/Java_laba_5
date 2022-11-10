package lab5;

/**
 * Класс исключения, возникающего при присвоении
 * отрицательного числа полю int
 */
public class NegativeIntException extends Exception{
  /**
   * Конструктор исключения
   *
   * @param exception сообщение исключения
   */
  public NegativeIntException(String exception, int numb) {
    super(exception + numb);
  }
}
