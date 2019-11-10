# Interview for Guild Education

## Dependencies
```
brew install postgresql@10
createdb postgres
createdb guild
createuser -U postgres guild_user
```

Build the application: `./gradlew build -x test`

Deploy the application: `cf push guild -p build/libs/*.jar`