/*
The case of the Python Syndicate
Stage 4

Officer: 7935412
CaseNum: 301-3-38627106-7935412

To really crack the Python Syndicate we need to go in deep. I want to understand
all the connections. I have the data but it’s a mess and I need you to sort it out.

Organise each syndicate member into an object. I’ve done one for you as an example.
Be sure to replicate the naming conventions for your own objects.
Use image command together with the objects you created to organise the mugshots.

- Once you have done this you can comment out or delete the old variables.

*/

var photoBoard;
var annaKarpinskiImage;
var countessHamiltonImage;
var pawelKarpinskiImage;
var linaLovelaceImage;
var bonesKarpinskiImage;
var cecilKarpinskiImage;

var linaLovelaceObject;


//declare your new objects below


var annaKarpinskiXCoordinate = 115;
var annaKarpinskiYCoordinate = 40;
var countessHamiltonXCoordinate = 408;
var countessHamiltonYCoordinate = 40;
var pawelKarpinskiXCoordinate = 701;
var pawelKarpinskiYCoordinate = 40;
var bonesKarpinskiXCoordinate = 408;
var bonesKarpinskiYCoordinate = 309;
var cecilKarpinskiXCoordinate = 701;
var cecilKarpinskiYCoordinate = 309;


function preload()
{
	photoBoard = loadImage('photoBoard.png');
	annaKarpinskiImage = loadImage("karpinskiWoman.png");
	countessHamiltonImage = loadImage("countessHamilton.png");
	pawelKarpinskiImage = loadImage("karpinskiBros2.png");
	linaLovelaceImage = loadImage("lina.png");
	bonesKarpinskiImage = loadImage("karpinskiDog.png");
	cecilKarpinskiImage = loadImage("karpinskiBros1.png");

}

function setup()
{
	createCanvas(photoBoard.width, photoBoard.height);
	linaLovelaceObject = {
		x: 115,
		y: 309,
		image: linaLovelaceImage
	};



	//define your new objects below
	annaKarpinskiObject={x:115,y:40,image:annaKarpinskiImage};
	countessHamiltonObject={x:408,y:40,image:countessHamiltonImage};
	pawelKarpinskiObject={x:701,y:40,image:pawelKarpinskiImage};
	bonesKarpinskiObject={x:408,y:309,image:bonesKarpinskiImage};
	cecilKarpinskiObject={x:701,y:309,image:cecilKarpinskiImage};
}

function draw()
{
	image(photoBoard, 0, 0);

	//And update these image commands with your x and y coordinates.
	image(annaKarpinskiObject.image, annaKarpinskiObject.x, annaKarpinskiObject.y);
	image(countessHamiltonObject.image, countessHamiltonObject.x, countessHamiltonObject.y);
	image(pawelKarpinskiObject.image, pawelKarpinskiObject.x, pawelKarpinskiObject.y);
	image(linaLovelaceObject.image, linaLovelaceObject.x, linaLovelaceObject.y);
	image(bonesKarpinskiObject.image, bonesKarpinskiObject.x, bonesKarpinskiObject.y);
	image(cecilKarpinskiObject.image, cecilKarpinskiObject.x, cecilKarpinskiObject.y);


}