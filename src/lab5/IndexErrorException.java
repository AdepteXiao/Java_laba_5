package lab5;

/**
 * Класс исключения, возникающего при указании неверного индекса
 */
public class IndexErrorException extends Exception{
  /**
   * Конструктор исключения
   *
   * @param exception сообщение исключения
   */
  public IndexErrorException(String exception, int numb) {
    super(exception + numb);
  }
}
