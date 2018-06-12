# Bard Bot 

A Discord Music Bot developed for general tasks using the Java Discord API (JDA), JDA Utilities and LavaPlayer. Created to be used in gaming servers but works in any other type of setting within the Discord application.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

```
Java Development Kit 8 (JDK 8)
Maven Dependency Management
```

### Installing

A step by step series of examples that tell you how to get a development env running

#### Create Executable .jar file
Make sure to include a token for the Discord Bot in the Main.java file. Then, in the Terminal, direct your way inside of the project folder and then run the following commands

```
$ mvn clean compile
$ mvn package
```

An executable .jar file will be created inside of the /target folder

## Deployment

### Running Locally

Make sure you have [Java](https://www.java.com/en/download/manual.jsp) and the [Heroku CLI](https://cli.heroku.com/) installed.

```sh
$ git clone https://github.com/JBobda/Bard-bot.git
$ cd GeneralDiscordBot
$ mvn package
$ cd target
$ java -jar BardBot-1.0.jar
```

Your app should now be running

### Deploying to Heroku

```
$ heroku create
$ git push heroku master
$ heroku open
```
or

[![Deploy to Heroku](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy)

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [JDA](https://github.com/DV8FromTheWorld/JDA) - Java Discord API
* [JDA Utilities](https://github.com/JDA-Applications/JDA-Utilities) - JDA Framework
* [LavaPlayer](https://github.com/sedmelluq/lavaplayer) - Audio Library

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request 

## Authors

* **Jan Bobda** 

## License

This project is licensed under the MIT License - see the [LICENSE.md](https://github.com/JBobda/GeneralDiscordBot/blob/master/LICENSE) file for details
