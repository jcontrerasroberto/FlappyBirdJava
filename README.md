# Flappy Bird JAVA
Click in the image to watch a demo of the game, special thanks to @angelgabriel1020

[![Demo](https://raw.githubusercontent.com/jcontrerasroberto/FlappyBirdJava/master/Captures/menu.png)](https://drive.google.com/file/d/1yGvt_zAGwoILMri2Hxh-lxYM6Ap3RC_Y/view "Demo")


This is a Flappy Bird clone coded in Java with some interesting features:

- Skin change

- Single mode

- Scores tables

- Multiplayer mode

Flappy Bird Java was a university project where I practiced things like: 

- Serialization

- File Handling

- Props

- 2D Components

- Images

- Threads

- Timers

- RMI

- Database conection

![alt text](https://raw.githubusercontent.com/jcontrerasroberto/FlappyBirdJava/master/Captures/game.png)

## How to play

Clone this repo

```git
git clone https://github.com/jcontrerasroberto/FlappyBirdJava.git
```

Create the database, you can simply use the next line in the terminal. The SQL file is in Server dir

```bash
mysql -u user -p FlappyBird < FlappyBird.sql
```

### Server side

Compile the Server. Go to the path Server/src

```bash
javac FlappyBirdServer.java
```

Run the Server

```bash
java -cp mysql-connector-java-5.1.49.jar:. FlappyBirdServer
```

### Client side

In the Client/src path

Compile the Client

```bash
javac FlappyBird.java
```

Run the Client

```bash
java FlappyBird
```

Now you can play!



# To Do

- [ ] Translate comments

- [ ] Create documentation


