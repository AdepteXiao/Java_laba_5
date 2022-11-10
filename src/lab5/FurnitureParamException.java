package lab5;

/**
 * Класс исключения, возникающего при некорректном создании экземпляра
 * через параметризированный конструктор
 */
public class FurnitureParamException extends Exception{
  /**
   * Консттруктор исключения
   *
   * @param exception сообщение для исключения
   * @param reason название исключения
   */
  public FurnitureParamException(String exception, Exception reason){
    super (exception, reason);
  }
}
