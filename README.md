### Документация Backend-части сервиса для создания коммерческих предложений

# Логика работы с пользователями

### Эндпоинты

- **GET /api/users/{userId}/mycompanies**
    - Получить список компаний пользователя по его ID.
    - Параметры:
        - userId: Long (Путь)
    - Ответ:
        - Успешно: 200 OK со списком компаний
        - Ошибка: 400 Bad Request с сообщением об ошибке

- **PUT /api/users/moderate/{reviewId}**
    - Модерировать отзыв.
    - Параметры:
        - reviewId: Long (Путь)
        - userId: Long (Запрос)
    - Ответ:
        - Успешно: 200 OK с сообщением о модерации отзыва
        - Ошибка: 400 Bad Request с сообщением об ошибке


# Логика работы с компаниями

### Эндпоинты

- **POST /api/companies/new**
    - Создать новую компанию.
    - Тело запроса: CompanyEntity
    - Параметры:
        - userId: Long (Запрос)
    - Ответ:
        - Успех: 200 OK с созданной компанией
        - Ошибка: 400 Bad Request с сообщением об ошибке

- **GET /api/companies/all**
    - Получить все компании.
    - Ответ:
        - Успех: 200 OK со списком компаний
        - Ошибка: 400 Bad Request с сообщением об ошибке

- **GET /api/companies/{companyId}/users**
    - Получить список пользователей компании по её ID.
    - Параметры:
        - companyId: Long (Путь)
    - Ответ:
        - Успех: 200 OK с списком пользователей
        - Ошибка: 400 Bad Request с сообщением об ошибке

- **PUT /api/companies**
    - Обновить информацию о компании.
    - Тело запроса: CompanyEntity
    - Параметры:
        - companyId: Long (Запрос)
    - Ответ:
        - Успех: 200 OK с обновленной информацией о компании
        - Ошибка: 400 Bad Request с сообщением об ошибке

- **DELETE /api/companies/{companyId}/{userId}**
    - Удалить компанию по её ID.
    - Параметры:
        - companyId: Long (Путь)
        - userId: Long (Путь)
    - Ответ:
        - Успех: 200 OK с сообщением об успешном удалении
        - Ошибка: 400 Bad Request с сообщением об ошибке

- **PUT /api/companies/add/{userId}**
    - Добавить пользователя к компании.
    - Параметры:
        - userId: Long (Путь)
        - companyId: Long (Запрос)
    - Ответ:
        - Успех: 200 OK с сообщением об успешном добавлении пользователя
        - Ошибка: 400 Bad Request с сообщением об ошибке

- **GET /api/companies**
    - Получить информацию о компании по её ID.
    - Параметры:
        - companyId: Long (Запрос)
    - Ответ:
        - Успех: 200 OK с информацией о компании
        - Ошибка: 400 Bad Request с сообщением об ошибке

- **GET /api/companies/{companyId}/reviews**
    - Получить отзывы о компании по её ID.
    - Параметры:
        - companyId: Long (Путь)
    - Ответ:
        - Успех: 200 OK со списком отзывов
        - Ошибка: 400 Bad Request с сообщением об ошибке


# Логика работы с отзывами

### Эндпоинты

- **POST /api/reviews/new/{companyId}**
    - Создать новый отзыв для компании.
    - Тело запроса: ReviewDTO
    - Параметры:
        - companyId: Long (Путь)
        - userId: Long (Запрос)
    - Ответ:
        - Успех: 200 OK с созданным отзывом
        - Ошибка: 400 Bad Request с сообщением об ошибке

- **GET /api/reviews/all**
    - Получить все отзывы.
    - Ответ:
        - Успех: 200 OK с списком отзывов
        - Ошибка: 400 Bad Request с сообщением об ошибке

- **DELETE /api/reviews/{reviewId}/{companyId}**
    - Удалить отзыв по его ID для определенной компании.
    - Параметры:
        - reviewId: Long (Путь)
        - companyId: Long (Путь)
    - Ответ:
        - Успех: 200 OK с сообщением об успешном удалении
        - Ошибка: 400 Bad Request с сообщением об ошибке

- **GET /api/reviews/{reviewId}**
    - Получить отзыв по его ID.
    - Параметры:
        - reviewId: Long (Путь)
    - Ответ:
        - Успех: 200 OK с отзывом
        - Ошибка: 400 Bad Request с сообщением об ошибке

# Логика работы с категориями

### Эндпоинты

- **POST /api/categories/new**
    - Создать новую категорию.
    - Тело запроса: CategoryEntity
    - Ответ:
        - Успех: 200 OK с созданной категорией
        - Ошибка: 400 Bad Request с сообщением об ошибке

- **GET /api/categories/all**
    - Получить все категории.
    - Ответ:
        - Успех: 200 OK с списком категорий
        - Ошибка: 400 Bad Request с сообщением об ошибке

- **GET /api/categories/{categoryId}**
    - Получить категорию по её ID.
    - Параметры:
        - categoryId: Long (Путь)
    - Ответ:
        - Успех: 200 OK с категорией
        - Ошибка: 400 Bad Request с сообщением об ошибке

- **DELETE /api/categories/{categoryId}**
    - Удалить категорию по её ID.
    - Параметры:
        - categoryId: Long (Путь)
    - Ответ:
        - Успех: 200 OK с сообщением об успешном удалении
        - Ошибка: 400 Bad Request с сообщением об ошибке

# Логика работы с подкатегориями

### Эндпоинты 

- **POST /api/subcategories/new**
    - Создать новую подкатегорию.
    - Тело запроса: SubcategoryEntity
    - Ответ:
        - Успех: 200 OK с созданной подкатегорией
        - Ошибка: 400 Bad Request с сообщением об ошибке

- **GET /api/subcategories/all**
    - Получить все подкатегории.
    - Ответ:
        - Успех: 200 OK с списком подкатегорий
        - Ошибка: 400 Bad Request с сообщением об ошибке

- **GET /api/subcategories/{subcategoryId}**
    - Получить подкатегорию по её ID.
    - Параметры:
        - subcategoryId: Long (Путь)
    - Ответ:
        - Успех: 200 OK с подкатегорией
        - Ошибка: 400 Bad Request с сообщением об ошибке

- **DELETE /api/subcategories/{subcategoryId}**
    - Удалить подкатегорию по её ID.
    - Параметры:
        - subcategoryId: Long (Путь)
    - Ответ:
        - Успех: 200 OK с сообщением об успешном удалении
        - Ошибка: 400 Bad Request с сообщением об ошибке