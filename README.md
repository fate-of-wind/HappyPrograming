# rock-paper-scissors card game

## Introduction
It's a card game about rock-paper-scissors. Every player have 15 cards involving three kinds.
5 rock, 5 paper, and 5 scissors.
Every turn, each player should show a card and compare them.
That's a normal rule.
The winner of this turn will win the card loser showed and card he showed.
When a player loses all cards, he loses this game.

Child and student will play this game to train their brain or just for relax.
That's a game I designed in my middle school,
just making an interesting improvement of original one. 
I draw them on paper and played with my classmates.
Now I have a chance to display it through Java,
that's amazing.   

## User story
- As a user, I want to be able to start a game (finished)
- As a user, I want to be able to see number of each kind of card I own currently 
- As a user, I want to be able to add Cards to Hand automatically 
- As a user, I want to be able to add player2's past shown Card to Player2PastShown class and its arraylist
- As a user, I want to be able to select a card on hand and show it 
- As a user, I want to be able to compare my card to opponent's card 
- As a user, I want to be able to play with an opponent who can actually show card 
- As a user, I want to be able to have ability to see number of other player's hand card (can choose open or not)
- As a user, I want to be able to remove Cards from Hand automatically 
- As a user, I want to be able to select difficulty level of other player
- As a user, I want to be able to save state of hand in turn to file
- As a user, I want to be able to load state of hand in turn from file
- As a user, I want to switch the mode of Past Player2 Shown Card panel 
- As a user, when I select the quit option from the application menu, I want to be reminded to save state
 of hand in turn to file and have the option to do so or not.
- As a user, when I start the application, I want to be given the option to load state of hand in turn
from file

## Phase 4: Task 2 
I test and design a robust class in model package.
Card Class.
There is a method named compare which require Card Type parameter before that.

Now it throws an InvalidCard Exception when it receives Card parameter other than Rock, Paper, and Scissor.

I test both two condition, one for the case where the exception is expected
 and another where the exception is not expected.
 
## Phase 4: Task 3
- I use many repeated association between class, I can refactor it by simplify
those associations. Use two or one class to cover those data instead of three classes: Card,
DetailCardNum, Hand.

- I use improve the store data structure.

- Coupling problem seems contain in my project, I need to reduce those coupling.
Maybe use Observer Pattern to implement that.