# ui-test-framework (3-нұсқа – practicetestautomation.com)

Талаптар: JDK 17+, Maven, Chrome/Firefox, Allure CLI.

    mvn clean test                          # config.properties баптауымен
    mvn clean test -Dbrowser=firefox        # браузерді ауыстыру
    mvn clean test -Dheadless=true
    allure serve target/allure-results      # есепті ашу

Параллель/тізбекті: src/test/resources/testng.xml ішінде
parallel="methods" thread-count="2"  ↔  parallel="none".
Скриншот тексеру: тесттегі күтілетін мәнді уақытша қате етіп өзгертіп, қайта іске қосыңыз.
