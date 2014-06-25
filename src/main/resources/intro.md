# Web Component example with Vaadin

Web Components is a major effort to solve issue among JS hackers that has been dealt well in e.g. Java world for ages: modularization. Browser support is getting better and with projects like [polymer](http://www.polymer-project.org) using this emerging technology has [already started](http://component.kitchen).

Creating Vaadin wrappers for WebComponents is pretty analogues to creating wrappers for JS components: **easy**. This is an example integration that creates an easy to use server side Java API for [a Web Component presenting a chess board](https://github.com/laat/chess-board). In case you want to achieve something similar, check out the sources of this example from [github](https://github.com/mstahv/vaadin-webcomponent-chessboard). A more advanced solution would be to create GWT wrappers as well, but in case you only work with the stable server side API, this wont make a difference.

