# Task Mod для Minecraft 1.21.8

Мод для Minecraft с интеграцией Spring Boot backend для обработки игровых сообщений.

## 📋 Описание

Этот проект представляет собой монорепозиторий, содержащий три интегрированных компонента:

- **minecraft-mod** - Fabric мод для Minecraft 1.21.8 с клиентским UI
- **spring-backend** - Spring Boot REST API сервер с PostgreSQL
- **shared-dto** - Общие DTO классы для обмена данными

## 🎮 Возможности

- **Отправка сообщений в игре**: Нажмите клавишу `M` для открытия экрана отправки сообщений
- **Многоязычная поддержка**: Интерфейс на русском и английском языках
- **Интеграция с backend**: Сообщения автоматически сохраняются в базу данных через REST API
- **Protobuf сериализация**: Эффективная передача данных между клиентом и сервером

## 🛠️ Технологии

### Minecraft Mod
- **Minecraft**: 1.21.8
- **Fabric Loader**: 0.17.3
- **Fabric API**: 0.136.0+1.21.8
- **Fabric Loom**: 1.11-SNAPSHOT
- **Java**: 21
- **Protobuf**: 4.29.2
- **Lombok**: 1.18.36

### Spring Backend
- **Spring Boot**: 3.4.1
- **PostgreSQL**: База данных для хранения
- **JPA/Hibernate**: ORM
- **Java**: 21
- **Lombok**: 1.18.36

### Инструменты сборки
- **Gradle**: 8.14
- **Gradle Wrapper**: Включён в проект

## 📦 Структура проекта

```
task-mod-1.21.8/
├── minecraft-mod/           # Fabric мод
│   ├── src/
│   │   ├── main/java/       # Общий и серверный код
│   │   ├── client/java/     # Клиентский код (UI, рендеринг)
│   │   ├── main/proto/      # Protobuf схемы
│   │   └── main/resources/  # Ресурсы мода (языки, иконки)
│   └── build.gradle
├── spring-backend/          # Spring Boot сервер
│   ├── src/main/java/
│   └── build.gradle
├── shared-dto/              # Общие DTO
│   ├── src/main/java/
│   └── build.gradle
├── gradle/                  # Gradle wrapper
├── build.gradle             # Корневой build script
├── settings.gradle
└── README.md
```

## 🚀 Быстрый старт

### Предварительные требования

- **Java 21**
- **PostgreSQL** (для backend)
- **Minecraft 1.21.8** (для запуска мода)

### Установка

1. **Клонируйте репозиторий**
   ```bash
   git clone <repository-url>
   cd task-mod-1.21.8
   ```

2. **Настройте базу данных PostgreSQL**
   ```sql
   CREATE DATABASE taskmod;
   ```

3. **Настройте параметры подключения** (опционально)

   Создайте `.env` и задайте параметры подключения к базе данных, если необходимо.

## 🔨 Сборка проекта

### Сборка всего проекта
```bash
./gradlew build
```

### Сборка отдельных модулей

**Minecraft мод:**
```bash
./gradlew :minecraft-mod:build
```

**Spring Backend:**
```bash
./gradlew :spring-backend:build
```

**Shared DTO:**
```bash
./gradlew :shared-dto:build
```

### Очистка
```bash
./gradlew clean
```

## ▶️ Запуск

### Запуск Spring Backend
```bash
./gradlew :spring-backend:bootRun
```

Backend будет доступен по адресу: `http://localhost:8080`

### Запуск Minecraft клиента с модом
```bash
./gradlew :minecraft-mod:runClient
```

### Запуск Minecraft сервера с модом
```bash
./gradlew :minecraft-mod:runServer
```

## 🎯 Использование

1. **Запустите Spring Backend**
2. **Запустите Minecraft с модом**
3. **В игре нажмите клавишу `M`**
4. **Введите сообщение и нажмите "Отправить"**
5. **Сообщение будет сохранено в базу данных**

## 🌐 Локализация

Мод поддерживает следующие языки:

- **Английский** (`en_us.json`)
- **Русский** (`ru_ru.json`)

Языковые файлы находятся в:
```
minecraft-mod/src/main/resources/assets/task-mod/lang/
```

### Добавление нового языка

1. Создайте файл `<locale>.json` в папке `lang/`
2. Скопируйте структуру из `en_us.json`
3. Переведите все значения на нужный язык
