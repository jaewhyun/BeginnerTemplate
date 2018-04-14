# BeginnerTemplate

Beginner templates for the Java Mode in PDE.

Template: https://github.com/processing/processing/wiki/Tool-Basics

### Download the file
```
$ git clone https://github.com/jaewhyun/BeginnerTemplate.git
```

### Move folder to Processing Sketchbook location
```
$ cp -R BeginnerTemplate ../Documents/Processing/tools
```

When you restart the PDE, you will be able to find "A Template for Beginners" under "Tools"

Currently the Basic Template is:
```
/*
 * {title of your project}
 * by {your name}
 *
 * {description of your project}
 *
 */


void setup() {
	size({fill in});    //set window size
}
void draw(){
	background({fill in});    //set background
}
```

and the Class Template is (tab name is inserted once added):
```
/*
 * {description of current class}
 * by {your name}
 *
 */


class TabName{
	//initiate variables here

	TabName(){
		//Write your code here
	}

}
```

