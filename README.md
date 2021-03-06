# Radio Project

A school project created by me (Philip Segerfast) and my other three classmates [Anton Baric](https://github.com/AntonBaric), [Sebastian Wiberg](https://github.com/SebastianTheButler) and [Elias Rundqvist](https://github.com/yungnunogod).

## What was is about?
The task was to fulfill a number of user stories in any way we liked. They were all about fetching and displaying information retrieved from the [API of the Swedish Radio (Sveriges Radio API)](https://sverigesradio.se/api/documentation/v2/index.html). This included fetching channels, programs, episodes and data related to those entities, such as showing a tableau for a specific channel.
We were also expected to have a login/register feature along with saving personal favourite programs and episodes.
A search function for programs is available too. 

## Used technologies
### Backend
* Java
* Spring Boot
* Spring JPA
* Spring Security (for user management)
* Maven
* SQLite

### Frontend
* Vue.js, some Vuex, Vue Router (Javascript, Node.js)
* HTML
* CSS

### Other related tools:
* Postman
* IntelliJ IDEA
* Visual Studio Code
* SQLite Studio - mainly
* (HeidiSQL)
* (DBeaver) - Philip
* GitKraken

## Images
List all programs under a channel (not logged in)
![bild](https://user-images.githubusercontent.com/23383100/115075552-87f6a400-9efb-11eb-9259-2b013404db5f.png)
List all episodes under a program (logged in, note the favourited items)
![bild](https://user-images.githubusercontent.com/23383100/115081528-b4fb8480-9f04-11eb-9917-e2eedf307f19.png)

## How to use it
### Backend
1. Clone the repo
2. Get dependencies with Maven
3. Run Spring server
### Frontend
1. Install Node.js
2. Open `/frontend`-folder with a terminal
3. Run `npm i` to install dependencies
4. Run `npm run serve` to start Node.js (Vue CLI) server
5. Navigate to `localhost:3000` using a web browser


*Please note that this project had a very limited time limit and that there are plenty of bugs. Also note that all of the members still are in the learning stage, some more than others, and that everybody has (very) different experience and skill.*
