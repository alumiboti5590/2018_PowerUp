# 2018 FIRST Power Up Alumiboti Robot Code

Team 5590's 2017 FRC robot code for the Power Up robot. The code is written in Java and is based off of [WPILib's Java control system][control-system].

The code is divided into several packages, each responsible for a different aspect of the robot function. This README explains the function of each package, some of the variable naming conventions used, and setup instructions. Additional information about each specific class can be found in that class's java file.

Note that terminal commands include the `$`, but should not be included int the command. They simply show that they are a terminal command and not programming code. Exclude the `$` when you run it.

# Table of Contents
- [2018 FIRST Power Up Alumiboti Robot Code](#2018-first-power-up-alumiboti-robot-code)
  * [Basic Command Line Commands](#basic-command-line-commands)
  * [Importing Code Into Eclipse](#importing-code-into-eclipse)
    + [Cloning the Repository](#cloning-the-repository)
    + [Importing Into Eclipse](#importing-into-eclipse)
  * [Version Control with `git` and GitHub](#version-control-with-git-and-github)
    + [A Brief Overview of `git`](#a-brief-overview-of-git)
    + [Getting an Overview of Your Situation](#getting-an-overview-of-your-situation)
    + [Pulling the Most Recent Code](#pulling-the-most-recent-code)
    + [Making a Change](#making-a-change)
    + [Adding the files to GitHub](#adding-the-files-to-github)
    + [Code Review & Pull Request](#code-review--pull-request)
   

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

`git` is a version control tool that let's a team of people work on the same code at the same time without conflicting with each other (most times). GitHub is a company that hosts `git` repository - code projects - for those teams and gives them more tools to review and work with their code.

### A Brief Overview of `git`

The purpose of `git` is to make a giant code timeline so that we can easily see the code we've written overtime and to jump between different points in time, and to work on our own code without disrupting others.

`git` breaks the project down into **branches**. Each task to complete for the project will get its own branch, as well as having a main project branch, known as the **master branch**, which contains the most up-to-date competition code. If Alice is writing the code for the `Drivetrain` subsystem, she will place her code in the `drivetrain-subsystem` branch. At the same time, Bob is working on his `Climbing` subsystem in the `climbing-subsystem` branch. This allows both of them to work on their respective code and tasks without interfering with each other. Once they are done they can merge their new code in their respective branches into the `master` branch, so that everyone else can see and use it. 

![git Tree Diagram](http://codekarate.com/sites/default/files/posts/git/git-intro-4-git-features-branch.png)

In the diagram above, Alice and Bob's branches would be *Feature Branches*, meaning they are adding new code to a project. If something went wrong with the existing code in `master`, then someone could open up a *Bug Fix Branch* to fix the problem, and then merge it back into the `master` branch.

### Getting an Overview of Your Situation

Before you use `git`, you must be in a directory that has a `git` project inside of it. For us, that would be the `2018_PowerUp` directory, so run `pwd` and make sure you are in that folder.

To get a high level status of your current situation in `git`, meaning what files have been changed, do you have any code ready to push up to GitHub, etc...we can use the `git status` command. An example of the command and its result is below.

```bash
$ git status
On branch master
Your branch is up-to-date with 'origin/master'.
```

That tells us that we are currently on the main `master` branch, and that we don't have any local changes to give to GitHub. Before we write any code, let's make sure that we have the most up to date code.

### Pulling the Most Recent Code

To get the most recent code from GitHub, we should make sure that we are on the `master` branch and then we can **pull** the code. **Pulling** code is the `git` way of saying that we will take whatever code is on the online `master` branch and will download it to our computer, inserting it into our code.

First we will change to the `master` branch, so that we have the most recent changes and base any of our code off of it. `checkout` means to change to another branch if it exists.

```bash
$ git checkout master
Already on 'master'
Your branch is up-to-date with 'origin/master'.
```

We will then want to `git pull` the changes to download anything we might not have:

```bash
$ git pull
Already up-to-date.
```

The message above says that we don't have anything new to download, which is okay. If there was something to download, then `git` would download it and give a summary of what was changed using red and green symbols.

Now we have the most up to date code! Let's change something!

### Making a Change

So now that we are up to date with the code, let's make a change and add a `DummySubsystem` class that has a few `Command`s to operate it. We will first need to create a new branch, telling `git` that we are going to work on some new code for the project. To do that, run the following command. **When you do the following, please change the `dummy-subsystem` branch names!!!**

In the same way we used `git checkout` above, by adding a `-b <branch name>` to the command, we can create a new branch, like so:

```bash
$ git checkout -b dummy-subsystem
Switched to a new branch 'dummy-subsystem'
```

We now have our own little timeline to make whatever changes we need without affecting the rest of the project!

Let's make a new subsystem called `DummySubsystem.java` in the appropriate location via Eclipse...

Once we make that file, we can then run `git status` from earlier to see that it captured our changes.

```bash
$ git status
On branch dummy-subsystem
Untracked files:
  (use "git add <file>..." to include in what will be committed)

	src/org/usfirst/frc/team5590/robot/subsystems/DummySubsystem.java

nothing added to commit but untracked files present (use "git add" to track)
```

*Untracked files* are ones that `git` has never seen or does not recognize, because they are probably new. *Tracked files* are ones that may have just been updated. 

### Adding the files to GitHub

We can now start the process of **committing** these files to `git`. A commit is like adding another entry to the timeline. There are three stages a file can be in once edited; either *modified*, *staged*, or *committed*. A *modified* file is something that was edited but `git` has not yet prepared it to be included in the timeline. A *staged* file has been edited and added to a potential new timeline entry. Once *committed*, those files included in the new timeline entry are put into the repository, first locally on your computer, and then you can **push** them to GitHub. A diagram of how this works is shown below:

![Git stage diagram](http://codingdomain.com/git/partial-commits/git-staging-area.png)

To add our `DummySubsystem.java` file, we will first add it to be *staged*, meaning `git` will add it to the next timeline entry.

```bash
$ git add src/org/usfirst/frc/team5590/robot/subsystems/DummySubsystem.java
$ git add <any other files you want to commit>
```

If it worked correctly, it will not print anything out. Don't forget that the *Tab* key autocompletes in the terminal if you don't want to type all of that out!

Once we have added the file(s) we want in this timeline entry, we can **commit** it. Where a branch should be made for a totally new feature/system, a commit should be made whenever a substantial amount of code that actual does something is created, such as for each Command for that subsystem is made, or when new functions and methods are added/updated.

To commit the files we added to *staging* earlier, we can use `git commit`. Whatever you put in the quotes below should be a concise and accurate message of whatever this timeline entry describes or changes.

```bash
$ git commit -m "Added basic DummySubsystem functionality"
Added basic DummySubsystem functionality
 1 file changed, 34 insertions(+)
 create mode 100644 src/org/usfirst/frc/team5590/robot/subsystems/DummySubsystem.java
```

Yay! We now created a new timeline entry! Time to add it to GitHub! To do that, we must **push**, or upload, the code to this repository.

```bash
$ git push origin dummy-subsystem
```

If you receive any errors on that command, you may have to run `git pull` again, if someone made a change to that branch since you last pushed.

You should now see your branch on GitHub at the repository URL if it worked! Go check it out! Now let's get someone else to look at it so we can merge it into `master`.

### Code Review & Pull Request

The last stage of adding code to the `master` branch timeline is for someone else on the team to review it. This has a few advantages:

1. It let's everyone have some of the ownership over a certain part of code, instead of just one person. This means more people can fix it if something goes wrong.
2. It let's everyone see each other's coding styles and ensures good practices and documentation.
3. Two eyes are better than one when it comes to checking for errors and mishaps.
4. It makes it easier to diagnose and fix problems before they *really* become a problem.

To make a Pull Request, go to the GitHub repository online, and on the left hand side of the page, select the dropdown that says *Branch: _____* and select your branch.

You can then hit the button that says *New pull request* that appears right next to that dropdown. It will then bring you to a new page with a form. For the title, leave a summary of what your code does in the Pull Request. In the comment box, go a bit more into detail into its functionality and what you added.

On the right hand side, select the *Reviewers* tag, which will open up a multi-select dropdown menu. Select a mentor's username, and they will review it with you as well have another team member look over it.

Then hit the green *Create Pull Request* button and wait for someone else on the team to review your code!

[control-system]: https://wpilib.screenstepslive.com/s/currentCS
[WPI-setup]: https://wpilib.screenstepslive.com/s/currentCS/m/getting_started/l/599679-installing-eclipse-c-java
[git-install]: https://git-scm.com/download/win
