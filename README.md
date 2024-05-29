README
Обзор
Этот репозиторий содержит автоматизированные тесты для сайта по продаже бассейнов. Тесты охватывают различные аспекты приложения, включая функциональное тестирование, тестирование удобства использования и тестирование на уровне кода. Тесты реализованы с использованием фреймворков Selenium, Postman и Rest Assured и выполняются локально, а также после развертывания на тестовом сервере.

Инструменты и технологии
Selenium: Используется для автоматизации взаимодействий с веб-браузером.
Postman: Используется для тестирования API.
Rest Assured: Используется для тестирования API.
Gradle: Используется как инструмент сборки и управления зависимостями.

Типы тестов
Функциональное тестирование
Функциональные тесты обеспечивают корректную работу приложения в соответствии с заданными требованиями. Эти тесты охватывают:
Аутентификацию пользователей (вход, регистрация)
Список продуктов и поиск
Управление корзиной (добавление, обновление и удаление продуктов)
Процесс оформления заказа
Процесс  заказа консультации
Процесс управления администратором заказов, пользователями и продуктами на сайте
Тестирование удобства использования
Тесты удобства использования фокусируются на удобстве и общем пользовательском опыте работы с приложением. Эти тесты включают:
Проверку форм и сообщения об ошибках
Адаптивность и производительность приложения
Тестирование белого ящика
Тесты белого ящика выполняются с учетом внутренней структуры приложения. Эти тесты включают:
Проверку логики обработки данных
Анализ покрытия кода
Тестирование серого ящика
Тестирование серого ящика выполняется после развертывания приложения на тестовом сервере. Эти тесты включают:
Интеграционные тесты для проверки взаимодействия различных компонентов
Сквозные тесты для симуляции реальных пользовательских сценариев
Тестирование безопасности для выявления потенциальных уязвимостей
Выполнение тестов
Локальная среда
Предварительные требования:
JDK 11 
Gradle
ChromeDriver (для Selenium)
Настройка:
Клонируйте репозиторий: git clone https://github.com/Rydzaki/Final_project_Pool
Перейдите в директорию проекта: cd ./Final_project_Pool
Установите зависимости: 
dependencies {
   implementation 'org.testng:testng:7.10.2'

   implementation 'io.rest-assured:rest-assured:5.4.0'
   implementation 'com.google.code.gson:gson:2.10.1'
   implementation 'com.fatboyindustrial.gson-javatime-serialisers:gson-javatime-serialisers:1.1.0'

   implementation 'org.seleniumhq.selenium:selenium-java:4.21.0'
   implementation 'ch.qos.logback:logback-classic:1.5.6'

   compileOnly 'org.projectlombok:lombok:1.18.24'
   annotationProcessor 'org.projectlombok:lombok:1.18.24'
   testCompileOnly 'org.projectlombok:lombok:1.18.24'
   testAnnotationProcessor 'org.projectlombok:lombok:1.18.24'
}


Запуск тестов:
Выполните все тесты: gradle test
Контакты
Если у вас есть вопросы или предложения, пожалуйста, свяжитесь с нами по адресу aushakov123544@gmail.com








README Overview This repository contains automated tests for a pool sales website. The tests cover various aspects of the application, including functional testing, usability testing, and code-level testing. The tests are implemented using Selenium, Postman, and Rest Assured frameworks and are executed both locally and after deployment on a test server.
Tools and Technologies
Selenium: Used for automating web browser interactions.
Postman: Used for API testing.
Rest Assured: Used for API testing.
Gradle: Used as a build and dependency management tool.
Types of Tests
Functional Testing
Functional tests ensure the application works correctly according to the specified requirements. These tests cover:
User authentication (login, registration)
Product listing and search
Cart management (adding, updating, and removing products)
Order checkout process
Consultation booking process
Admin management of orders, users, and products on the site
Usability Testing
Usability tests focus on the ease of use and overall user experience with the application. These tests include:
Form validation and error messages
Application responsiveness and performance
White-Box Testing
White-box tests are performed with knowledge of the internal structure of the application. These tests include:
Data processing logic verification
Code coverage analysis
Gray-Box Testing
Gray-box testing is performed after deploying the application on a test server. These tests include:
Integration tests to verify the interaction of different components
End-to-end tests to simulate real user scenarios
Security testing to identify potential vulnerabilities
Test Execution
Local Environment
Prerequisites:
JDK 11
Gradle
ChromeDriver (for Selenium)
Setup:
Clone the repository: git clone <repository-url>
Navigate to the project directory: cd <project-directory>
Install dependencies: gradle clean build
Running Tests:
Execute all tests: gradle test
License This project is licensed under the MIT License - see the LICENSE file for details.
Contact If you have any questions or suggestions, please contact us at anton_ushakov@test.com.











