/*
The case of the Python Syndicate
Stage 3


Officer: 7935412
CaseNum: 301-2-87348809-7935412

Right kid let’s work out which of our ‘friends’ is connected to the syndicate.

- An object for Rocky kray has been declared and initialised
- Modify the x and y parameters of each image command using the x and y
properties from the Rocky kray object so the images remain at their correct
positions on the board.
- To do this you will need to combine add and subtract operators with the
relevant property for each parameter



*/

var photoBoard;
var anna_karpinski_image;
var bones_karpinski_image;
var robbie_kray_image;
var countess_hamilton_image;
var rocky_kray_image;
var lina_lovelace_image;

var rocky_kray_object;




function preload()
{
	photoBoard = loadImage('photoBoard.png');
	anna_karpinski_image = loadImage("karpinskiWoman.png");
	bones_karpinski_image = loadImage("karpinskiDog.png");
	robbie_kray_image = loadImage("krayBrothers2.png");
	countess_hamilton_image = loadImage("countessHamilton.png");
	rocky_kray_image = loadImage("krayBrothers1.png");
	lina_lovelace_image = loadImage("lina.png");

}

function setup()
{
	createCanvas(photoBoard.width, photoBoard.height);
	rocky_kray_object = {
		x: 408,
		y: 309,
		image: rocky_kray_image
	};
}

function draw()
{
	image(photoBoard, 0, 0);

	//And update these image commands with your x and y coordinates.
	image(rocky_kray_object.image, rocky_kray_object.x, rocky_kray_object.y);

	image(anna_karpinski_image,  rocky_kray_object.x-293, rocky_kray_object.y-269);
	image(bones_karpinski_image, rocky_kray_object.x, rocky_kray_object.y-269);
	image(robbie_kray_image, rocky_kray_object.x+293, rocky_kray_object.y-269);
	image(countess_hamilton_image, rocky_kray_object.x-293, rocky_kray_object.y);
	image(lina_lovelace_image, rocky_kray_object.x+293, rocky_kray_object.y);

}