/*

Officer: 7935412
CaseNum: 202-3-92472785-7935412

Case 202 - The case of Bob and Daisy - stage 4

Here’s the final letter from Daisy (aka. Woz). Decode it to uncover the
final details about Woz and Job’s dastardly plan.

Discover the hidden code by commenting out all text commands except
those which produce pink filled text with black outline in Ballpointprint font.
Only comment out text commands - leave fill & stroke, push and pop commands uncommented.

There are many possible ways of investigating this case, but you
should use ONLY the following commands:

  // comments are all that are needed for this case.
  Do NOT add any new lines of code.

*/

var letterFont;

function preload()
{
	Ballpointprint = loadFont('Ballpointprint.ttf');
	Melissa = loadFont('Melissa.otf');
	Diggity = loadFont('Diggity.ttf');
	RonsFont = loadFont('RonsFont.ttf');
}

function setup()
{
	createCanvas(538,326);
	textSize(21);
}

function draw()
{
	background(255);

	fill(255,165,0);
	stroke(255,0,0);
	textFont(RonsFont);
	//text("these", 55,89);
	fill(0,255,255);
	stroke(255,0,255);
	//text("more", 61,160);
	fill(255,192,203);
	stroke(0,255,0);
	//text("for", 367,114);
	//text("are", 254,89);
	fill(0,255,255);
	stroke(0,0,0);
	textFont(Diggity);
	//text("ver", 42,202);
	fill(255,192,203);
	//text("hould", 254,114);
	textFont(Ballpointprint);
	text("I", 253,63);
	text("break", 424,114);
	text("can", 279,63);
	push();
	stroke(0,255,0);
	textFont(Diggity);
	//text("The", 216,160);
	pop();
	text("you", 13,114);
	text("guard", 316,89);
	fill(255,255,0);
	//text("go", 294,114);
	push();
	stroke(255,0,255);
	textFont(RonsFont);
	//text("out.", 105,139);
	pop();
	textFont(RonsFont);
	//text("not", 405,139);
	stroke(255,0,255);
	textFont(Diggity);
	//text("?", 200,160);
	//text("x", 55,244);
	stroke(0,255,0);
	textFont(Ballpointprint);
	//text("you", 44,63);
	push();
	fill(255,192,203);
	//text("?", 342,139);
	//text("a", 404,114);
	pop();
	textFont(RonsFont);
	//text("how", 479,139);
	stroke(0,0,0);
	textFont(Diggity);
	//text("Are", 9,63);
	//text("our", 174,139);
	fill(0,255,255);
	//text("away", 322,114);
	fill(255,192,203);
	stroke(255,0,255);
	textFont(RonsFont);
	//text("ling", 69,21);
	//text("My", 5,21);
	stroke(0,0,0);
	textFont(Ballpointprint);
	text("delay", 169,89);
	fill(0,255,255);
	textFont(Melissa);
	//text("avoiding", 46,114);
	push();
	stroke(255,0,255);
	textFont(Ballpointprint);
	/* text("s", 211,89);
	text("and", 478,114);
	text("so", 292,89); */
	pop();
	stroke(255,0,255);
	//text("You", 229,89);
	textFont(Diggity);
	//text("continual", 105,89);
	fill(255,255,0);
	stroke(255,0,0);
	textFont(RonsFont);
	//text("the", 312,160);
	fill(0,255,255);
	//text("no", 447,63);
	fill(255,192,203);
	textFont(Melissa);
	//text("so,", 230,63);
	fill(255,165,0);
	stroke(0,255,0);
	textFont(Diggity);
	//text("send", 312,63);
	fill(255,192,203);
	//text("money", 138,63);
	//text("all", 86,139);
	push();
	fill(0,255,255);
	stroke(255,0,0);
	//text("sort", 10,139);
	pop();
	stroke(0,0,255);
	//text("ignore", 9,89);
	//text("Bob,", 104,21);
	textFont(RonsFont);
	//text("I'm", 366,139);
	fill(0,255,255);
	textFont(Ballpointprint);
	//text("relationship", 205,139);
	//text("?", 123,114);
	fill(255,165,0);
	textFont(Diggity);
	//text("I", 115,160);
	fill(255,192,203);
	stroke(255,0,255);
	textFont(Ballpointprint);
	//text("ed", 356,89);
	stroke(0,0,255);
	//text("I", 396,63);
	fill(255,165,0);
	stroke(255,0,255);
	textFont(Diggity);
	//text("of", 114,63);
	//text("secrets,", 249,160);
	push();
	fill(255,255,0);
	textFont(Melissa);
	//text("ort", 90,63);
	pop();
	textFont(RonsFont);
	//text("yours,", 74,202);
	textFont(Melissa);
	//text("sh", 77,63);
	stroke(255,0,0);
	textFont(RonsFont);
	//text("we", 216,114);
	fill(255,192,203);
	stroke(0,0,0);
	textFont(Ballpointprint);
	text("safe", 294,139);
	fill(255,255,0);
	stroke(255,0,0);
	textFont(Diggity);
	//text("sure", 440,139);
	fill(255,165,0);
	stroke(0,0,0);
	textFont(Ballpointprint);
	//text("can", 126,160);
	push();
	fill(255,255,0);
	stroke(255,0,255);
	textFont(Diggity);
	//text("take", 159,160);
	pop();
	textFont(RonsFont);
	//text("Daisy", 7,244);
	push();
	stroke(0,255,0);
	//text("Are", 470,89);
	pop();
	//text("dar", 36,21);
	//text("much", 11,160);
	stroke(255,0,0);
	textFont(Ballpointprint);
	//text("silence.", 346,160);
	//text("Is", 142,139);
	fill(0,255,255);
	textFont(Diggity);
	//text("Fore", 12,202);
	fill(255,255,0);
	stroke(0,255,0);
	//text("sometimes.", 390,89);
	//text("longer", 473,63);
	fill(255,165,0);
	stroke(0,0,255);
	//text("If", 211,63);
	fill(0,255,255);
	stroke(0,255,0);
	textFont(RonsFont);
	//text("this", 48,139);
	textFont(Ballpointprint);
	//text("?", 187,63);
	push();
	fill(255,165,0);
	stroke(0,0,255);
	textFont(RonsFont);
	//text("ps", 187,114);
	//text("cash.", 351,63);
	pop();
	textFont(Diggity);
	//text("me", 95,114);
	stroke(0,0,255);
	textFont(RonsFont);
	//text("Perha", 147,114);
	fill(255,255,0);
	stroke(0,255,0);
	textFont(Melissa);
	//text("can", 422,63);
	fill(0,255,255);
	stroke(0,0,255);
	textFont(Diggity);
	//text("s", 247,114);



}
