web: java -Dserver.port=$PORT -jar build/libs/*.jar
java -javaagent:newrelic/newrelic.jar -jar reputation-telegram-bot-1.0-SNAPSHOT.jar
release: ./gradlew update