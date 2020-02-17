/*

Officer: 7935412
CaseNum: 303-2-38950611-7935412

Case 303 - The Case of the Crooked Attorney
Stage 3 - The Gates Bank

I’ve made an appointment for you at the Gates Bank to retrieve your safe deposit box from the vault.
Actually you will break into Torvalds’ one.

Crack the safe by doing the following:

	Whilst the mouse is moving:
	- Make SecretLockerKey_A equal to the value of mouseY
	- Use the 'max' function to prevent SecretLockerKey_A from falling below 1

	When the mouse button is released:
	- Increment SecretLockerKey_B by 2
	- Use the 'constrain' function to prevent SecretLockerKey_B from falling below 5 and going above 21

	When the mouse button is released:
	- Make SecretLockerKey_C equal to the value of mouseX
	- Use the 'max' function to prevent SecretLockerKey_C from falling below 2

	Whilst the mouse is being dragged:
	- Decrement SecretLockerKey_D by 3
	- Use the 'constrain' function to prevent SecretLockerKey_D from falling below 2 and going above 16

	Whilst the mouse is being dragged:
	- Make SecretLockerKey_E equal to the value of mouseY
	- Use the 'min' function to prevent SecretLockerKey_E from going above 70



This time you'll need to create the relevant event handlers yourself.

There are many possible ways of investigating this case, but you
should use ONLY the following commands:

	- The assignment operator aka. the equals sign !
	- mouseX, mouseY
	- Incrementing +=
	- Decrementing -=
	- min, max
	- constrain

*/

//declare the variables

var SecretLockerKey_A;
var SecretLockerKey_B;
var SecretLockerKey_C;
var SecretLockerKey_D;
var SecretLockerKey_E;


function preload()
{
	//IMAGES WILL BE LOADED HERE

}

function setup()
{
	createCanvas(512,512);

	//initialise the variables
	SecretLockerKey_A = 0;
	SecretLockerKey_B = 0;
	SecretLockerKey_C = 0;
	SecretLockerKey_D = 0;
	SecretLockerKey_E = 0;

}

///////////////////EVENT HANDLERS///////////////////

//Create event handlers here to open the safe ...
function mouseMoved()
{
	console.log("mouseMoved", mouseX, mouseY);
	SecretLockerKey_A=mouseY;
	SecretLockerKey_A=max(SecretLockerKey_A,1);
}

function mouseReleased()
{
	console.log("mouseReleased");
	SecretLockerKey_B+=2;
	SecretLockerKey_B=constrain(SecretLockerKey_B,5,21);
	SecretLockerKey_C=mouseX;
	SecretLockerKey_C=max(SecretLockerKey_C,2);
}

function mouseDragged()
{
	console.log("mouseDragged", mouseX, mouseY);
	SecretLockerKey_D-=3;
	SecretLockerKey_D=constrain(SecretLockerKey_D,2,16);
	SecretLockerKey_E=mouseY;
	SecretLockerKey_E=min(SecretLockerKey_E,70);
}







///////////////DO NOT CHANGE CODE BELOW THIS POINT///////////////////

function draw()
{

	//Draw the safe door
	background(70);
	noStroke();
	fill(29,110,6);
	rect(26,26,width-52,width-52);

	//Draw the combination dials
	push();
	translate(120,170);
	drawDial(140,SecretLockerKey_A, 15);
	pop();

	push();
	translate(120,380);
	drawDial(140,SecretLockerKey_B, 26);
	pop();

	push();
	translate(280,170);
	drawDial(140,SecretLockerKey_C, 13);
	pop();

	push();
	translate(280,380);
	drawDial(140,SecretLockerKey_D, 19);
	pop();

	//Draw the lever
	push();
	translate(width - 125,256);
	drawLever(SecretLockerKey_E);
	pop();


}

function drawDial(diameter,num,maxNum)
{
	//the combination lock

	var r = diameter * 0.5;
	var p = r * 0.6;

	stroke(0);
	fill(255,255,200);
	ellipse(0,0,diameter,diameter);
	fill(100);
	noStroke();
	ellipse(0,0,diameter*0.66,diameter*0.66);
	fill(150,0,0);
	triangle(
		-p * 0.4,-r-p,
		p * 0.4,-r-p,
		0,-r-p/5
	);

	noStroke();

	push();
	var inc = 360/maxNum;

	rotate(radians(-num * inc));
	for(var i = 0; i < maxNum; i++)
	{
		push();
		rotate(radians(i * inc));
		stroke(0);
		line(0,-r*0.66,0,-(r-10));
		noStroke();
		fill(0);
		text(i,0,-(r-10));
		pop();
	}

	pop();
}

function drawLever(rot)
{
	push();
	rotate(radians(-rot))
	stroke(0);
	fill(100);
	rect(-10,0,20,100);
	ellipse(0,0,50,50);
	ellipse(0,100,35,35);
	pop();
}
