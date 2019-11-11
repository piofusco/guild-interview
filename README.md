# Interview for Guild Education



## Dependencies

### Postgres
```
brew install postgresql@10
createdb postgres
createdb guild
createuser -U postgres guild_user
```



## Tests

Running

Build the application: `./gradlew build -x test`

## Deploy

Deploy the application: `cf push guild -p build/libs/*.jar`