# Interview for Guild Education - many hours later...

## Dependencies
- Frontend built with React + Material UI
- Backend built with Spring/Kotlin
- Database in PostgreSQL

### Postgres setup
```
brew install postgresql@10
createdb postgres
createdb guild
createuser -U postgres guild_user
```

## Tests

Running the journey tests, within the `acceptance` folder, run `npx cypress open` (ensure frontend/backend are running)

Running the javascript tests, within the `react` folder, run `npm test`

Running the API tests, run `./gradlew build`

## Running the app

Frontend: within the `react` folder, run `npm start`

Backend: `./gradlew bootrun`