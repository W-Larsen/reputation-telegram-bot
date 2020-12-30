# Reputation telegram bot

App version: 1.0

**Reputation telegram bot** is a simple bot for Telegram for calculating user reputation.

The main implementation - [Dawg Reputation Bot](https://t.me/dawgReputationBot).

## Project management

This project uses [Zube](https://github.com/marketplace/zube) extension for GitHub for tracking new features and improvements
and simple [GitHub board](https://github.com/W-Larsen/reputation-telegram-bot/projects/1) for tracking bugs.

## How it works

**The main goal:** every time when one of the users replies to the user's message with a key word (e.g. "+" or "-"), then that user's reputation goes up or down.

### List of bot commands 

#### Main commands

* ``toprep`` - Show first 10 users with highest reputation;
* ``minrep`` - Show last 10 users with minimal reputation;
* ``myrep`` - Show reputation of current user.

#### Advanced
* ``health_check`` - Get health status.

## Tech Stack

* Language: [OpenJDK 11](https://adoptopenjdk.net/)
* Framework: [Spring Boot](https://spring.io/projects/spring-boot)
* Build tool: [Gradle](https://gradle.org/)
* Database: [PostgreSQL](https://www.postgresql.org/)
* Database migration: [Liquibase](https://www.liquibase.org/)
* Logging: [Slf4j(Lombok)](https://projectlombok.org/api/lombok/extern/slf4j/Slf4j.html)
* IDE: [IntelliJ IDEA](https://www.jetbrains.com/idea/)

## Getting Started 

### Prerequisites

* IDE e.g. [IntelliJ IDEA](https://www.jetbrains.com/idea/)
* [Gradle](https://gradle.org/install/) (version 6.3+)
* [Docker Desktop](https://www.docker.com/products/docker-desktop)
* [PostgreSQL](https://www.postgresql.org/download/) and a database desktop admin tool e.g. [Dbeaver](https://dbeaver.io/)

### Setup

* Set **Main class** to: com.telegram.rtb.TelegramReputationBotInit

#### Setup IntelliJ IDE

* Set Environment Variables
  * Menu bar -> Run -> Edit configuration -> Environment variables
* Install Project Lombok plugin
  * `Preferences` -> `Plugins` -> `Marketplace` -> search **Lombok** and install -> restart IDE
  * `Preferences` -> `Build, Execution, Deployment` -> `Compiler` -> `Annotation Processors` -> pick `Enable annotation processing`

#### Required Environment Variables
* ``BOT_TOKEN`` - Telegram bot token;
* ``BOT_USERNAME`` - Telegram bot username (only text without @);
* ``JDBC_DATABASE_URL`` - Database url;
* ``JDBC_DATABASE_USERNAME`` - Database username;
* ``JDBC_DATABASE_PASSWORD`` - Database password;
* ``JDBC_DATABASE_NAME`` - Database name;
* ``APP_URL`` - Application url (e.g. for local - ``http://localhost:8080``);
* ``PING_CRON`` - Cron expression (e.g. ``0 0/10 * * * *`` - each 10 minutes);
* ``IGNORE_USER_IDS`` - List of telegram user ids to ignore to calculate reputation, can be empty.

#### Setup database 

To run local database via docker execute the following command from shell inside app directory: ``docker-compose up -d``

#### Run migration scripts locally

To run migration execute the following command from shell inside app directory: ``gradlew update``.

**This is required the first time you launch the application!** Next times launch is required to update.

## Contacts

* Email: valik.kornienko97@gmail.com 
