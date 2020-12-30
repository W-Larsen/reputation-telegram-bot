web: java -Dserver.port=$PORT -jar build/libs/*.jar
release: ./gradlew update
java -javaagent:newrelic/newrelic.jar -jar build/libs/*.jar