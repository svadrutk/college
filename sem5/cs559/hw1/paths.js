function setup() { "use strict";
  var canvas = document.getElementById('myCanvas');
  var slider1 = document.getElementById('slider1');
  slider1.value = 1;
  var slider2 = document.getElementById('slider2');
  slider2.value = 2;
  var slider3 = document.getElementById('slider3');
  slider3.value = 3;
  
  var counter = 1; // counter for the animation
  function draw() {
    var context = canvas.getContext('2d');
    canvas.width = canvas.width;

    counter += 1;
    console.log("yes");
    
    // note that this only changes the y
    // X just stays the same
    // the coordinate systems will move
    function linkage(color) {
      
      context.beginPath();
      context.fillStyle = color;
      context.moveTo(0,0);
      context.lineTo(10,5);
      context.lineTo(90,5);
      context.lineTo(100,0);
      context.lineTo(90,-5);
      context.lineTo(10,-5);
      context.closePath();
      context.fill();
      
    }
    
    
    context.clearRect(0,0,canvas.width,canvas.height);
    // make sure you understand these
    context.translate(50,150);
    context.save();
    context.translate(100,0);
    context.save();
    context.rotate(counter * slider1.value * 0.01);
    linkage("green");
    context.translate(100,0);
    context.save();
    context.rotate(counter * slider2.value * 0.01);
    context.scale(0.5,1);
    linkage("red");
    context.restore();
    context.save();
    context.rotate(counter * slider3.value * 0.01);
    context.scale(0.5,1);
    linkage("orange");
    context.restore();
    context.restore();
    context.save();
    
    context.restore();  
    context.restore();   
    window.requestAnimationFrame(draw);
  }
//   slider1.addEventListener("input",draw);
//   slider2.addEventListener("input",draw);
//   slider3.addEventListener("input",draw);
  window.requestAnimationFrame(draw);
  draw();
}
window.onload = setup;

