function setup() { "use strict";
  var canvas = document.getElementById('myCanvas');
  var context = canvas.getContext('2d');
  var slider1 = document.getElementById('slider1');
  slider1.value = 1;
  var slider2 = document.getElementById('slider2');
  slider2.value = 2;
  var slider3 = document.getElementById('slider3');
  slider3.value = 3;
  
  var counter = 1; // counter for the animation
  function moveToTx(x,y,Tx)
	{var res=vec2.create(); vec2.transformMat3(res,[x,y],Tx); context.moveTo(res[0],res[1]);}

	function lineToTx(x,y,Tx)
	{var res=vec2.create(); vec2.transformMat3(res,[x,y],Tx); context.lineTo(res[0],res[1]);}

  function draw() {
    canvas.width = canvas.width;

    counter += 1;
    console.log("yes");
    
    // note that this only changes the y
    // X just stays the same
    // the coordinate systems will move
    function linkage(color, Tx) { 
      context.beginPath();
      context.fillStyle = color;
      moveToTx(0,0, Tx);
      lineToTx(10,5, Tx);
      lineToTx(90,5, Tx);
      lineToTx(100,0, Tx);
      lineToTx(90,-5, Tx);
      lineToTx(10,-5, Tx);
      context.closePath();
      context.fill();  
    }
    
    
  var Tgreen_to_canvas = mat3.create();
	mat3.fromTranslation(Tgreen_to_canvas,[50,150]);
  mat3.rotate(Tgreen_to_canvas,Tgreen_to_canvas,slider1.value * 0.01 * counter);
	linkage("green",Tgreen_to_canvas);
	

	var Tred_to_green = mat3.create();
	mat3.fromTranslation(Tred_to_green,[100,0]);
	mat3.rotate(Tred_to_green,Tred_to_green,slider2.value * 0.01 * counter);
	mat3.scale(Tred_to_green,Tred_to_green,[0.5,1]);
	var Tred_to_canvas = mat3.create();
	mat3.multiply(Tred_to_canvas,Tgreen_to_canvas,Tred_to_green);
	linkage("red",Tred_to_canvas);

	var Torange_to_green = mat3.create();
	mat3.fromTranslation(Torange_to_green,[100,0]);
	mat3.rotate(Torange_to_green,Torange_to_green,slider3.value * 0.01 * counter);
	mat3.scale(Torange_to_green,Torange_to_green,[0.5,1]);
	var Torange_to_canvas = mat3.create();
	mat3.multiply(Torange_to_canvas,Tgreen_to_canvas,Torange_to_green);
	linkage("orange",Torange_to_canvas);
  
  window.requestAnimationFrame(draw);

  }

 
  window.requestAnimationFrame(draw);
  draw();
}
window.onload = setup;

