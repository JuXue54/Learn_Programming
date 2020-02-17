/*

Officer: 7935412
CaseNum: 101-3-67668530-7935412

Case 101 - The Case of Lina Lovelace
Stage 4 - The Plaza Hotel

Okay this place is more Lina’s style. Now’s our chance to find out the root of all
of this. Lets see who is Lina meeting.

Identify Lina by drawing a red filled rectangle with a green outline.
She’s the woman in the red dress of course.

Identify the man with the monocle smoking the cigar red filled
rectangle with a green outline around him.

Identify the man reading the newspaper by drawing a yellow filled rectangle
with a red outline around him.

Identify the woman with the dog by drawing a magenta filled rectangle with a
yellow outline around her. Make sure you include the dog too.

The rectangles should cover the targets as accurately as possible without
including anything else.

There are many possible ways of investigating this case, but you
should use ONLY the following commands:

  rect()
  fill() Use only 255 or 0 for r,g,b values. Set alpha to 100 for some opacity.
	stroke() Use only 255 or 0 for r,g,b values.

*/

var img;

function preload()
{
	img = loadImage('img.jpg');
}

function setup()
{
	createCanvas(img.width,img.height);
	strokeWeight(2);
}

function draw()
{
	image(img,0,0);

	//Write your code below here ...
	stroke(0,255,0);
	fill(255,0,0);
	rect(958,150,142,296);
	
	stroke(0,255,0);
	fill(255,0,0);
	rect(10,350,330,432);
	
	stroke(255,0,0);
	fill(255,255,0);
	rect(555,223,153,290);
	
	stroke(255,255,0);
	fill(255,0,255);
	rect(712,133,230,500);


}
