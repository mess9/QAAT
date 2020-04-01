Feature: Проверка возможностей
  Проверяем тайтл этой фичей

  @otladish
  Scenario: Тайтл равен Bell Integrator
    Given перейти на сайт 'http://www.bellintegrator.ru/'
    Then тайтл равен 'Bell Integrator'
    Then закончить работу