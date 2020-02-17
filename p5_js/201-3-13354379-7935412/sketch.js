/*
201 - The case of Judge Hopper
Stage 4 - The warehouse

Officer: 7935412
CaseNum: 201-3-13354379-7935412

As you enter the ALGOL warehouse you are struck by the most horrendous stench - it’s not the fish. Lying amongst piles of fish carcasses you find the body of Judge Hopper. 
Gathering yourself together, you tie a handkerchief around your nose and mouth and quickly set about recording the evidence.

Draw around the Judge’s body ...


*/

var img;

function preload()
{
    img = loadImage('scene.png');
}

function setup()
{
    createCanvas(img.width,img.height);
}

function draw()
{

    image(img,0,0);
    stroke(255, 0, 0);
    strokeWeight(3);
    noFill();

    // write the code to draw around the Judge's body below
	beginShape();
	vertex(580,204);
	vertex(626,362);
	vertex(388,416);
	vertex(274,439);
	vertex(245,422);
	vertex(210,361);
	vertex(398,219);
	vertex(408,48);
	vertex(433,47);
	endShape(CLOSE);

}
