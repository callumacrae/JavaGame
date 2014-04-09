# Features added for Milestone 3

## Konami code

A cheatmode can be activated by using the Konami code (up, up, down, down, left, right, left, right, b, a), which will turn the character into a ninja pig, make the character able to jump higher, affect how points are scored (you can just give yourself points by pressing p, too), and make the character kill the aliens when he runs into them. The main challenge involved here was keeping track of which keys had been pressed, and comparing them to the konami code. I could have used a load of if statements, but that wouldn't have been very maintainable.

## High score

A high score is tracked. It's a single user high score, and displays at the top of the screen. The challenge was reading and writing from a file, which I had not done before.

## Parallax background

A final small feature I added was a parallax background, which is where the background mirrors the players movement. It's small, but involved some maths to stop the side of the background from coming onto the screen and displaying a black box.