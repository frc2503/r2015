# r2015 [![Join the chat at https://gitter.im/frc2503/r2015](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/frc2503/r2015?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Our code for the 2015 season and following off-season.

This code was run on our robot at the Lake Superior Regional and is currently being worked-on for reference and practice.

This README is intended as a complicated set of instructions for this repository, which will likely apply to future robot code projects.

## Contributing

To contribute to this GitHub account, you must first have the code cloned on your workstation!
If you have yet to install the FRC development suite, you can do this by following the [2015 FRC Control System][FRC Control System] steps.
Since this robot program is written in Java, specifically follow the directions for "FRC Java Programming;" there isn't much need for the C++ or LabVIEW stuff since we don't use it.

Once you've gotten everything cloned, and set up, clone the repository into a directory somewhere.
You can just clone this into your "My Documents" folder if you're on Windows, or the equivalent if you're on a Mac.
It is recommended to create a dedicated "Development" folder, even with "Git", "GitHub", and "frc2503" nested subdirectories, to keep everything nice and neat.

```sh
$ cd <directory>
$ git clone https://github.com/frc2503/r2015.git r2015
```

The above command will use Git (provided that everything is set up properly) to clone down the robot code into a folder (in the current directory) called `r2015`.

From Eclipse, you can then import the project as an "Existing Project" in the "Filesystem", and you're set to go.

To get your code into the `master` branch, first create a branch, commit to that branch, and create a Pull Request.
If this Pull Request is in response to an Issue, then reference that like "resolves #<number>", so that GitHub will automagically close that Issue with the Pull Request, once it's merged.

You can, of course, commit directly to `master`, but generally it clutters things and makes things more confusing.

## Programming

In general, try to keep your code as neat as possible.
Don't focus on getting the results, then stopping; constantly iterate on your design.
If you can, make it dynamic.
Handle all potential Exceptions, and make things both bulletproof and intelligent.
*This* is what leads you to having a competitive program.

[FRC Control System]: https://wpilib.screenstepslive.com/s/4485
