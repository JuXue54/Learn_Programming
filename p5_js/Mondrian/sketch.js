function setup()
{
	//create a large square canvas
	createCanvas(800, 800);
}

function draw()
{
	//background(200);
	//set the fill colour to red
	fill(255, 0, 0);

	//set a thick stroke weight for the black lines
	strokeWeight(5);

	//draw the red rectangle 
	rect(100, 50, 600, 600);
	

	
	//draw a quad
	fill(255,0,0);
	quad(100,50,150,50,550,650,100,650);
	
	//draw a triangle
	fill(255);
	triangle(100,650,350,350,550,650);
	
	//draw a top triangle
	fill(255);
	triangle(400,425,250,200,400,50);
	
	//draw a bottom triangle
	fill(255,255,0);
	triangle(500,650,700,500,700,650);
	
	//draw a middle triangle
	fill(255,255,0);
	triangle(150,50,250,200,400,50);
	
	//draw a rect
	fill(255);
	rect(500,250,200,250);
	
	//draw a line
	line(500,250,700,500);
	
	//draw a rect 
	fill(0,0,255);
	rect(400,50,100,250);
	
	//
	fill(255,255,0);
	beginShape();
	vertex(400,300);
	vertex(500,300);
	vertex(500,500);
	vertex(450,500);
	vertex(400,425);
	endShape(CLOSE);

}