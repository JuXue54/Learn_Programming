/*
The case of the Python Syndicate
Stage 1

Officer: 7935412
CaseNum: 301-0-66635772-7935412

I gotta give it to you kid, you’ve made an excellent start, but now it’s time
to take things up a level. For some time I’ve suspected that there’s something
big going down in Console City.

These cases that we’ve been working are all connected somehow. I need to use
that considerable brain of yours to work it all out. Let’s start by laying out
who we know.

Place each mugshot in its designated position by doing the following:

- Create a new variable for the X and Y coordinates of each mugshot.
    - One has already been done for you.
    - Make sure you use the same style and format for the variable name.
- Find coordinates for the mugshot and initialise your variable with these
values.
- Replace the hard-coded constants in the corresponding image command so that
the mugshot appears in its designated position.

*/

var photoBoard;
var robbie_kray_image;
var lina_lovelace_image;
var pawel_karpinski_image;
var cecil_karpinski_image;
var rocky_kray_image;
var countess_hamilton_image;



//declare your new variables below
var pawel_karpinski_location_x = 701;
var pawel_karpinski_location_y = 40;

var robbie_kray_location_x=115;
var robbie_kray_location_y=40;

var lina_lovelace_location_x=408;
var lina_lovelace_location_y=40;

var cecil_karpinski_location_x=115;
var cecil_karpinski_location_y=309;

var rocky_kray_location_x=408;
var rocky_kray_location_y=309;

var countess_hamilton_location_x=701;
var countess_hamilton_location_y=309;


function preload()
{
	photoBoard = loadImage('photoBoard.png');
	robbie_kray_image = loadImage("krayBrothers2.png");
	lina_lovelace_image = loadImage("lina.png");
	pawel_karpinski_image = loadImage("karpinskiBros2.png");
	cecil_karpinski_image = loadImage("karpinskiBros1.png");
	rocky_kray_image = loadImage("krayBrothers1.png");
	countess_hamilton_image = loadImage("countessHamilton.png");

}

function setup()
{
	createCanvas(photoBoard.width, photoBoard.height);
}

function draw()
{
	image(photoBoard, 0, 0);



	//And update these image commands with your x and y coordinates.
	image(pawel_karpinski_image, pawel_karpinski_location_x, pawel_karpinski_location_y);

	image(robbie_kray_image, robbie_kray_location_x, robbie_kray_location_y);
	image(lina_lovelace_image,lina_lovelace_location_x,lina_lovelace_location_y );
	image(cecil_karpinski_image, cecil_karpinski_location_x, cecil_karpinski_location_y);
	image(rocky_kray_image, rocky_kray_location_x, rocky_kray_location_y);
	image(countess_hamilton_image, countess_hamilton_location_x, countess_hamilton_location_y);

}