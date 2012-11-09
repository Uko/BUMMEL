BUMMEL [![Build Status](https://secure.travis-ci.org/Uko/BUMMEL.png)](http://travis-ci.org/Uko/BUMMEL)
======================================================================================================

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

Language/Platform/BuildTool
-----------------

This project is written in **Java 1.7** with **NetBeans RCP 7.2** using **Maven 3**.

Branches
--------

We are using **feature-driven** branches which means that **master** branch should contain code that at least compiles, and if we are adding some new feature or bug-fix we put the development code in the separate branch and we can't ensure that it will work fine or it will work at all.

Modules
=======

  * **AdvancedLogicElements** - an implementation of circuit elements as an extended logic ones (XOR, NOR, XNOR, NAND).
  * **Latches** - an implementation of flip-flop elements (RS).
  * **LogicElements** - an implementation of circuit elements as a logic ones (AND, NOT, OR, and supply ones).
  * **ProjectModel** - some model of an application project (*circuit data etc…*):
    * interfaces & abstract classes for the circuit, an circuit's element and the element's connection
    * circuit work algorithm
  * **VisualEditor** - the editor window itself + elements palette.


Changelog
=========

v0.3
----
Migrated build tool from **Ant** to **Maven**. Upgraded Netbeans RCP version to **7.2**.

###v0.3.1
Updated module versions to match version of the project.

v0.2
----
Dedicated to architecture improvements, and major bug fixes.
Major change in UI graph library _(JGraph -> NetBeans Visual Library)_, elements graphics in svg, ext…
Separation of **model** from ViewController

###v0.2.2
Fix of Class loading during the project deserialization.
Now all module dependencies are back to normal.

Projects are saved to XML

###v0.2.1-ff
Additional flip-flops: **D**, **T** and **JK**

###v0.2.1
General bug-fixes

  * Elements with unconnected ports not working as intended: #9
  * Disconnecting a connection between ports of the one element: #19
  * Elements not refreshing state on disconnect: #20
  * Default graphics for non-defined states: #22

###v0.2.0-ff
Flip-flop update introducing **RS** flip-flop element

v0.1
----
Start-up version. Introduced basic GUI based on JGraph. Crappy.
