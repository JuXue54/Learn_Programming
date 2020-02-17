/*
The case of the Python Syndicate
Stage 2


Officer: 7935412
CaseNum: 301-1-81889515-7935412

- Word on the street is that there is a new gang in town - The Python Syndicate.
It seems my bones were correct on this one. I need you to organise the gang
around the suspected leader Countess hamilton

- The variables for Countess hamilton have been declared and
initialised.
- Modify the x and y parameters of each image command using these two variables
so the images maintain their correct positions their correct positions on the board.
- To do this you will need to combine add and subtract operators with variables
Countess hamilton for for each parameter.
- Do not create any new variables
- Do not add any additional commands


*/

var photoBoard;
var annaKarpinskiImg;
var pawelKarpinskiImg;
var countessHamiltonImg;
var bonesKarpinskiImg;
var linaLovelaceImg;
var rockyKrayImg;


var countessHamiltonLocationX = 701;
var countessHamiltonLocationY = 40;


function preload()
{
	photoBoard = loadImage('photoBoard.png');
	annaKarpinskiImg = loadImage("karpinskiWoman.png");
	pawelKarpinskiImg = loadImage("karpinskiBros2.png");
	countessHamiltonImg = loadImage("countessHamilton.png");
	bonesKarpinskiImg = loadImage("karpinskiDog.png");
	linaLovelaceImg = loadImage("lina.png");
	rockyKrayImg = loadImage("krayBrothers1.png");

}

function setup()
{
	createCanvas(photoBoard.width, photoBoard.height);
}

function draw()
{
	image(photoBoard, 0, 0);

	//And update these image commands with your x and y coordinates.
	image(countessHamiltonImg, countessHamiltonLocationX, countessHamiltonLocationY);
	image(annaKarpinskiImg, countessHamiltonLocationX-586, countessHamiltonLocationY);
	image(pawelKarpinskiImg, countessHamiltonLocationX-293, countessHamiltonLocationY);
	image(bonesKarpinskiImg, countessHamiltonLocationX-586, countessHamiltonLocationY+269);
	image(linaLovelaceImg, countessHamiltonLocationX-293, countessHamiltonLocationY+269);
	image(rockyKrayImg, countessHamiltonLocationX, countessHamiltonLocationY+269);

}