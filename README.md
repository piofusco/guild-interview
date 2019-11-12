# Interview for Guild Education - many hours later...

Where did I end up?
- the user can submit messages and see them displayed in order they were sent
- added a pretty decent [CI pipeline](https://circleci.com/gh/piofusco/workflows/guild-interview) (CD is broken due - 
didn't have enough time to properly configure the DB in [PWS](https://guild.cfapps.io/)
- decent test coverage on both the frontend/backend + acceptance tests

What was left?
- user(s) can see who posted what message

This would have involved adding basic auth ([and maybe followed something like this](https://www.baeldung.com/spring-security-basic-authentication)) 
and creating two accounts. Then, associating messages sent to the current user logged in. I probably would have added a 
migration (take a look in the `src/main/resources/db/migration`) - something like `V2__associate_user_id_to_messages`.

- ensuring users can send messages from multiple browsers and that the front end will honor the order they were received

I really don't think this would have been that much extra work. If I accomplish the bullet point above, I am there. I 
probably would have tried something fancier in Cypress.

- fixing CD :) even half a day is a little much to get CI/CD setup with a free version of PWS. I tried my best.

- Applying more of the [App Continuum](https://www.appcontinuum.io/). Not to tout my own Koolaid, but this is a great 
means of organizing an application to put on by some brilliant minds from our Boulder office - ask Josh Mattila about 
it ;) - following this can lead to:
1. no circular dependencies
1. no high coupling between components
1. a wonderful starting ground for (incoming buzzword) micro-service architecture
1. codifying your business requirements/concepts into a `core` component
1. moving `react` to it's own component
1. moving the `api` to it's own component
1. `api` depends on `core` and `react`

Yada, yada. Happy to riff on this as much as y'all would like. Sounds like you might have some of these issues going on.

- adding [Sass](https://sass-lang.com/documentation/syntax) and [BEM-ifying](http://getbem.com/introduction/) all
of the markup. Happy to speak to this more in person, but basically BEM is a means of organizing css so things don't
get too out of hand too quickly

- rewriting the Material components with Vanilla React. I wanted this to look "not terrible", so I went with a cheaper, 
more attractive option. Normally, I favor less dependencies in favor of simplicity and not coupling oneself to a 
library. The Material stuff looks nice out of the box and saves time.

- deploying the frontend separately from the backend. With Spring, this simply means React dumps everything into 
`main/resources/public`, so when the `.jar` boots and the browser navigates to `/` it renders the react app.

## Dependencies
- Frontend built with [React](https://reactjs.org/)
- Used [Material UI](https://material-ui.com/) for fan
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

Running the [Cypress tests](https://www.cypress.io/), within the `acceptance` folder, run `npx cypress open` (ensure frontend/backend are running)

Running the javascript tests, within the `react` folder, run `npm test`

Running the API tests, run `./gradlew build`

## Running the app

Frontend: within the `react` folder, run `npm start`

Backend: `./gradlew bootrun`