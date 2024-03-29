# S05T02N01 Rodriguez Jose API Documentation

## Overview

> The dice game is played with two dice. If the result of the sum of the two dice is 7, the round is won; otherwise, it is lost. A player can see a list of all the rolls they have made and their success rate.
>
> To be able to play the game and make a roll, a user must register with a non-repeating name. Upon creation, they are assigned a unique numeric identifier and a registration date. If the user so wishes, they can choose not to add a name and will be called "ANONYMOUS". There can be more than one "ANONYMOUS" player. Each player can see a list of all the rolls they have made, with the value of each die and whether the round was won or lost. In addition, they can know their success rate for all the rolls they have made.
>
> It is not possible to delete a specific game, but it is possible to delete the entire list of rolls for a player.
>
> The software must allow listing all the players in the system, the success rate of each player, and the average success rate of all players in the system.
>
> The software must adhere to the main design patterns.

> **NOTES**

 The following construction details must be taken into account:

 >URLs:
- POST: /players: creates a player.
- PUT /players: modifies the player's name.
- POST /players/{id}/games/: a specific player makes a dice roll.
- DELETE /players/{id}/games: deletes the rolls of the player.
- GET /players/: returns the list of all the players in the system with their average success rate.
- GET /players/{id}/games: returns the list of games for a player.
- GET /players/ranking: returns the average ranking of all the players in the system. That is, the average success rate.
- GET /players/ranking/loser: returns the player with the worst success rate.
- GET /players/ranking/winner: returns the player with the best success rate.

>Level 1

Add security: include JWT authentication for all access to the microservice URLs.

>Level 2

Add unit, component, and integration tests to the project with libraries jUnit, AssertJ, or Hamcrest.
Add Mocks to the project testing (Mockito) and Contract Tests (https://docs.pact.io/)

>Level 3

Design and modify the project to diversify persistence so that it uses two database schemas at the same time, MySQL and Mongo.
## API Version

1.0.0

## Base URL

The API is accessible at `https://S05T02N01RodriguezJose`

## Endpoints

### Players

#### Create Player

- **POST** `/api/v1/players/players`
- Summary: Creates a new player.
- Request Body: `PlayerDTO` schema.
- Responses:
    - `200 OK`: Returns the created `PlayerDTO`.

#### Update Player Name

- **PUT** `/api/v1/players/players`
- Summary: Updates an existing player's name.
- Query Parameters:
    - `id` (integer, required): The ID of the player.
    - `name` (string, required): The new name of the player.
- Responses:
    - `200 OK`: Returns the updated `PlayerDTO`.

#### Get All Players

- **GET** `/api/v1/players/players`
- Summary: Retrieves all players with their average success rate.
- Responses:
    - `200 OK`: Returns an array of `PlayerDTO`.

### Games

#### Add Game

- **POST** `/api/v1/players/players/{id}/games`
- Summary: Adds a new game result for a player.
- Path Parameters:
    - `id` (integer, required): The ID of the player.
- Request Body: `GameDTO` schema.
- Responses:
    - `200 OK`: Returns a confirmation message.

#### Delete Games

- **DELETE** `/api/v1/players/players/{id}/games`
- Summary: Deletes all games for a player.
- Path Parameters:
    - `id` (integer, required): The ID of the player.
- Responses:
    - `200 OK`: Returns a confirmation message.

#### Get Games By Player ID

- **GET** `/api/v1/players/players/{id}/games`
- Summary: Retrieves all games for a specific player.
- Path Parameters:
    - `id` (integer, required): The ID of the player.
- Responses:
    - `200 OK`: Returns an array of `GameDTO`.

### Rankings

#### Get Average Success Rate

- **GET** `/api/v1/players/players/ranking`
- Summary: Retrieves the average success rate across all players.
- Responses:
    - `200 OK`: Returns the average success rate as a double.

#### Get Loser Player

- **GET** `/api/v1/players/players/ranking/loser`
- Summary: Retrieves the player with the lowest success rate.
- Responses:
    - `200 OK`: Returns the `PlayerDTO` of the player with the lowest success rate.

#### Get Winner Player

- **GET** `/api/v1/players/players/ranking/winner`
- Summary: Retrieves the player with the highest success rate.
- Responses:
    - `200 OK`: Returns the `PlayerDTO` of the player with the highest success rate.

## Schemas

### PlayerDTO

- Represents a player.
- Properties:
    - `id`: integer, unique identifier.
    - `name`: string, name of the player.
    - `registrationDate`: date-time, when the player was registered.
    - `avgSuccessRate`: double, the player's average success rate.

### GameDTO

- Represents a game result.
- Properties:
    - `id`: string, unique identifier.
    - `playerId`: integer, ID of the player.
    - `diceValue1`: integer, value of the first dice.
    - `diceValue2`: integer, value of the second dice.
    - `won`: boolean, whether the game was won or not.



