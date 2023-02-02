FROM openjdk:11
ADD target/cooking_recipes-0.8.0.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]
