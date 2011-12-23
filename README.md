BUMMEL
======

The final product should be and application that will provide a logical and electrical circuit boards editor, firstly for educational use, maybe in future will contain commercial features.

The name is acronym where the first part is the nick-names of the project creators (**b**0xer, **u**ko, **m**cangel), and the second part stands for **M**iracle **E**lectronics **L**ab.

A Bit of Story
--------------

This project started is Lviv National University of Ivan Franko based on the educational purposes. At the moment our main concern is to make a nice development workflow, so new students will be able to work on something interesting. But we'll enjoy open-source cooperation for sure, as this project has important goals and final product should be very cool.

Goals
-----

  1. Should work everywhere, no meter what operation system you're (*or your school is*) using.
  2. Should provide functionality for logical and electrical circuit composing and some helpful analysis.
  3. Should be intuitive, provide nice design and sleek functionality.
  4. In future we want it give other developers some public API, so they'll be able to write plugins for our app.

First of all we should achieve something that can be used in our University instead of crappy **Electronics Workbench** app.


Deployment
==========

Language/Platform
-----------------

This project is written in Java 1.6, using NetBeans Platform 7.0.1. So for development, we recommend to download latest stable NetBeans IDE form it's [download page](http://netbeans.org/downloads/index.html). Please note, NetBeans Platform SDK (that you need to compile the project) is included only in **Java SE** **Java EE** and "**all**" bundles, thou you should be comfortable using **Java SE** bundle.

Branches
--------

We are using **feature-driven** branches which means that **master** branch should contain code that at least compiles, and if we are adding some new feature or bug-fix we put the development code in the separate branch and we can't ensure that it will work fine or it will work at all.

Modules
=======

  * **BasicElements** - an interface & abstract class for an circuit's element.
  * **BrandingModule** - module used for some branding stuff and menu setup (*some crap, should be revised*).
  * **Engine** - code that contains whole circuit logic.
  * **Jgraph** - contains Jgraph sourcecode. The purpose of using source code and not the `.jar` is because we've edited it a bit.
  * **LogicElements** - an implementation of circuit elements as a logic ones.
  * **ProjectModel** - some model of an app project (*circuit data etc…*).
  * **VisualEditor** - the editor window itself + elements palette.