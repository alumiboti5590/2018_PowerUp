# 2018 FIRST Power Up Alumiboti Robot Code

Team 5590's 2017 FRC robot code for the Power Up robot. The code is written in Java and is based off of [WPILib's Java control system][control-system].

The code is divided into several packages, each responsible for a different aspect of the robot function. This README explains the function of each package, some of the variable naming conventions used, and setup instructions. Additional information about each specific class can be found in that class's java file.

Note that terminal commands include the `$`, but should not be included int the command. They simply show that they are a terminal command and not programming code. Exclude the `$` when you run it.

## Basic Command Line Commands

If needed, some basic command line and Git Bash instructions are below. A **directory** is a folder. Anything in `<>` should be seen as something *you* need to fill in for the command to run, so `cd <directory>` would become `cd Desktop` to change to the desktop.

* `pwd`: Show your current directory.
* `ls`: List everything (files and folders) in the current directory.
* `cd <directory>`: Change to a desired directory `directory` from your current directory. If you want to go to the parent directory, use `cd ..`.
* `man <command>`: Show the help text for command `command`, in case you forget or need to learn it. Also shows all of the helpful options.

## Importing Code Into Eclipse

To import this code into Eclipse, you will need to follow [this setup link][WPI-setup] first to install Eclipse, the WPI libraries, and get your environment set up. Also, it will be necessary to install [`git`][git-install] if on Windows.

### Cloning the Repository

Open up the Terminal (on Mac or Linux) or Git Bash on Windows, and change into a directory of your choice. Once there, run the following command to clone the repository from GitHub to your local computer.

```bash
$ git clone https://github.com/sjcirobotics/2018_PowerUp.git
```

or, if you prefer to use SSH, you can use this command.

```bash
$ git clone git@github.com:sjcirobotics/2018_PowerUp.git
```

It will be helpful to run the following two commands after cloning to know where it was cloned to.

```bash
$ cd 2018_PowerUp
$ pwd
/Users/dan/robotics/2018_PowerUp  # Copy this line
```

### Importing Into Eclipse

Open up Eclipse, and select the **File** menu button in the top left corner. Select **Import** and then open the dropdown for **General**, highlight **Existing Projects Into Workspace** and click the *Next* button.

Paste or write the *root* location where you cloned the repository above. This should be the value that was printed when you ran the `pwd` command after cloning and changing into the project directory. You can also **Browse** for the root directory if you need to.

Select *Next* until it gives you the option to finish, and then simply select *Finish*. It should now appear in the *Package Explorer* in Eclipse.

## Version Control with `git` and GitHub



[control-system]: https://wpilib.screenstepslive.com/s/currentCS
[WPI-setup]: https://wpilib.screenstepslive.com/s/currentCS/m/getting_started/l/599679-installing-eclipse-c-java
[git-install]: https://git-scm.com/download/win