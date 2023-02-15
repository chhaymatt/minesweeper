# Minesweeper <!-- omit in toc -->

> Matthew Chhay's Minesweeper

## Preview <!-- omit in toc -->

![Preview of Matthew Chhay's Minesweeper](https://i.imgur.com/56890Dc.png)

## Table of Contents <!-- omit in toc -->

-   [Introduction](#introduction)
-   [Features](#features)
-   [Technologies Used](#technologies-used)
-   [Screenshots](#screenshots)
-   [MVP](#mvp)
-   [Going beyond the MVP](#going-beyond-the-mvp)
-   [Setup](#setup)
    -   [For viewing locally and further development](#for-viewing-locally-and-further-development)
-   [Tools Used](#tools-used)
-   [Project Status](#project-status)
-   [Room for Improvement](#room-for-improvement)

## Introduction

A recreated version of the game Minesweeper to be played in the console built using Java.

## Features

-   Play Minesweeper in (x and y coordinates - left-right, down-up)
-   Replay Minesweeper after winning or losing

## Technologies Used

-   Java

## Screenshots

![Start Screen](https://i.imgur.com/kPvPiiN.png)
![First guess](https://i.imgur.com/hPOXtiD.png)
![Lose Minesweeper](https://i.imgur.com/f6H0BeS.png)

## MVP

-   Recreated a simplified version of Minesweeper to be played in the console
-   Minesweeper randomly generates 10 mines in a 10 by 10 grid
-   User enters a command to reveal the location of a tile
-   Minesweeper displays how many mines that surround a tile between 0 and 8
-   Minesweeper ends if a user hits a tile containing a mine and the user loses
-   Minesweeper ends if a user clears all non-mine tiles and the user wins
-   Minesweeper renders the grid to the console after each user input

## Going beyond the MVP

-   Added text colours to console output for the different number of mines

## Setup

### For viewing locally and further development

1. Git clone this repo `git clone git@github.com:chhaymatt/minesweeper.git`
2. Run Main.java in an IDE
3. Play Minesweeper in the console

## Tools Used

-   Eclipse IDE
-   [Console Colours](https://gist.github.com/drjoliv/be081ba5b0b9d7a22da7f2d406b7fe04#:~:text=drjoliv/-,Console%20Colors,-Created%205%20years)

## Project Status

Project is ongoing

## Room for Improvement

-   Adopt more object orientated programming
-   A configuration.json file to configure number of mines and grid size
-   If a user selects an empty tile, the surrounding empty tiles should appear
-   Flag potential mines
