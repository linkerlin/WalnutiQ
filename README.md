# [WalnutiQ](http://walnutiq.com)

“*In another life I would make you stay so I don't have to say
that you were the one that got away.*”  

~ Katheryn Elizabeth Hudson

[![Build Status](https://travis-ci.org/quinnliu/WalnutiQ.png)](https://travis-ci.org/quinnliu/WalnutiQ)

Welcome! WalnutiQ is a human brain model simulation in Java. 
The goal of this repository is to store code that can 
simulate a human brain in real-time. A real intelligence machine 
built on biological principles will be able to solve many of the 
problems which currently plague the world and allow us to unravel
the mystery of human conciousness. It will be a long journey but
this has the potential to dramatically change the world for the 
better.

Currently, you will find code that allows you to build a partial 
human brain model, train it on input data, view its activity 
as text and visual output, and test its ability to recognize 
objects. All of the code here supports modeling of the human 
brain at a high level of abstraction while still allowing user
access to individual neuron properties. For more information please 
watch this [video playlist](http://www.youtube.com/playlist?list=PLPXsMt57rLtgddN0NQEmXP-FbF6wt2O-f) 
to become familiar with the neuroscience behind this repository.

If you are interested in becoming a researcher/developer, the 
only requirement is interest in understanding how the brain 
really works. If you have no programming experience there are
plenty of research tasks we need your help on [here](https://github.com/quinnliu/WalnutiQ/issues?labels=Research).
If you are interested in gaining programming experience by
building brain models we would be happy to google hangout with 
you or meet you in person regardless of your programming
experience! Feel free to e-mail us at quinnliu@vt.edu to get 
coffee/lunch/dinner to talk about how you can contribute.

Most importantly, this research is made possible by the 
neuroscientists and engineers at [Numenta](http://numenta.org/). 
Numenta has designed a exciting new technology that accurately models 
layers 3 & 4 of the human neocortex. They have generously released 
the pseudocode for their learning algorithms, and this repository is an 
extended implementation of their algorithms using object-oriented 
programming. Make sure to read Numenta's great explanation 
of their research in this [white paper](https://db.tt/FuQWQuwE) 
to better understand the theory behind this repository.

## Setup in Linux/Mac/Windows with Eclipse(like Microsoft Word but for writing code)
1. [Install Eclipse](http://wiki.eclipse.org/Eclipse/Installation)

2. Fork this repository and clone it locally. Then import it as a 
project into Eclipse.

3. IMPORTANT: You will notice that your folders will have "red X's". 
   To fix this right click your `src` folder then hover over "New", 
   then click "Source Folder". Then give it the "Folder name:" src. 
   You will also need to do this same process for the folders 
   `experiments` and `images`. Make sure when you type the 
   "Folder name:" you put in the folder name of 
   the folder you previously just right clicked.

4. In Eclipse, add all the libraries (.jar file) in the folder 
   `referencedLibraries/` by right-clicking your project `WalnutiQ` 
   in the package explorer `=>` Build Path `=>` Add External Archives...

5. In Eclipse, add JUnit 4 by right-clicking your project `WalnutiQ` 
   in the package explorer `=>` Add Libraries... `=>` JUnit `=>`
   Next > `=>` Finish

6. In Eclipse, also add JRE System Library by right-clicking 
   your project `WalnutiQ` in the package explorer `=>` 
   Add Libraries... `=>` JRE System Library `=>` Next > `=>` Finish
  
## Setup in Linux/Mac/Windows with Gradle(Builds our Java code)
1. Install Gradle in Linux OR Mac by typing to the command line:
   ```sh
   prompt> wget http://services.gradle.org/distributions/gradle-1.10-bin.zip
   prompt> sudo unzip -q gradle-1.10-bin.zip -d /usr/local/
   prompt> echo "export GRADLE_HOME=/usr/local/gradle-1.10" >> .profile
   prompt> echo "export PATH=$PATH:$GRADLE_HOME/bin" >> .profile
   ```
   
2. Or install Gradle with these [instructions for Windows](https://db.tt/DMF3ww2D)

3. Fork this repo and clone it locally. Navigate into the `WalnutiQ/` folder

4. Type in the command line `gradle build`. This may take a minute so no worries.
   Go to the folder `build/reports/tests/`. View the file `index.html` in 
   a browser and make sure all tests pass!

## How to Contribute

1. View an example of how some of the code is used in the file
   [NoiseInvarianceExperiment.java](./experiments/model/MARK_I/vision/NoiseInvarianceExperiment.java)

2. View an example of how the current model can be instantiated as a simplified human visual pathway
   in [HowMARK_I_FitsInToBrainAnatomy.java](./experiments/model/MARK_I/vision/HowMARK_I_FitsInToBrainAnatomy.java)

3. View our [issue tracker](https://github.com/quinnliu/WalnutiQ/issues?state=open) and create a new issue
   with a question if you are confused. Otherwise, asign a issue to yourself you would like to work on or suggest
   a new issue if you kind of know what you are doing. Happy coding!

## What each file/folder in this repository is for:
  - experiments  
      + model
          - MARK_I 
            + [vision](./experiments/model/MARK_I/vision) = experiments with partial 
              visual pathway models on a popular handwritten digit data
              set called MNIST
  - gradle = @linkerlin what is this for?
  - images
      + digits
          - [MNIST](./images/digits/MNIST) = unique files that contain training images 
            and testing images
      + main
        - java
          + [model](./images/main/java/model) = images used in testing the MARK I model
  - referencedLibraries = contains .jar files(of other people's code) needed to run WalnutiQ
  - src
      + main
        - java
          + model
            - [MARK_I](./src/main/java/model/MARK_I) = the core logic for the partial brain model. 
              Includes abstract data types for basic brain structures and learning 
              algorithms that simulate how the brain learns
              + [SDR](./src/main/java/model/MARK_I/SDR) = allows construction of different WalnutiQ
                models from command line for this repo https://github.com/quinnliu/CallWalnutiQ.
              + [connectTypes](./src/main/java/model/MARK_I/connectTypes) = classes allow the different 
                brain structures in MARK I to connect to each other in a variety of ways
            - [util](./src/main/java/model/util) = classes that enable the brain model properties
              to be viewed graphically and efficiently saved and opened  
      + test = test classes for important classes in the ```src/``` folder
        - java
          + model
            - [MARK_I](./tests/java/model/MARK_I)
              + [connectTypes](./tests/java/model/MARK_I/connectTypes)
              + [SDR](./tests/java/model/MARK_I/SDR)
            - [util](./tests/java/model/util) 
  - .gitignore = contains names of files/folders not to add to this repository
  - .project = when writing your code using Eclipse this file will allow all of
               your files to be organized in the correct folder
  - .travis.yml = tells [our custom travis testing site](https://travis-ci.org/quinnliu/WalnutiQ) 
                  what versions of Java to test the files here
  - BrainTheories.md = list of brain theories with support and simple explanation
  - LICENSE.txt = MIT liscense basically saying you can do whatever you want the code here 
                  but it would be very cool of you to do something nice with it.
  - README.md = the file you are reading right now
  - build.gradle = Groovy language code for compiling all of the code in this repository using Gradle
  - gradlew.bat = @linkerlin what is this for?

===============================================================
Please contact me at quinnliu@vt.edu if you have any questions! 