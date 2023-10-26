function setup() {
  var canvas = document.getElementById('myCanvas');
  var context = canvas.getContext('2d');
  var slider1 = document.getElementById('slider1');
  slider1.value = -25;

  function draw() {
canvas.width = canvas.width;
// use the sliders to get the angles
var tParam = slider1.value*0.01;

function moveToTx(loc,Tx)
{var res=vec2.create(); vec2.transformMat3(res,loc,Tx); context.moveTo(res[0],res[1]);}

function lineToTx(loc,Tx)
{var res=vec2.create(); vec2.transformMat3(res,loc,Tx); context.lineTo(res[0],res[1]);}

function drawObject(color,Tx) {
    context.beginPath();
    context.fillStyle = color;
    moveToTx([-.05,-.05],Tx);
    lineToTx([-.05,.05],Tx);
    lineToTx([.05,.05],Tx);
    lineToTx([.05,-.05],Tx);
    context.closePath();
    context.fill();
}

function drawAxes100unit(color,Tx) {
    context.strokeStyle=color;
    context.beginPath();
    // Axes
    moveToTx([120,0],Tx);lineToTx([0,0],Tx);lineToTx([0,120],Tx);
    // Arrowheads
    moveToTx([110,5],Tx);lineToTx([120,0],Tx);lineToTx([110,-5],Tx);
    moveToTx([5,110],Tx);lineToTx([0,120],Tx);lineToTx([-5,110],Tx);
    // X-label
    moveToTx([130,0],Tx);lineToTx([140,10],Tx);
    moveToTx([130,10],Tx);lineToTx([140,0],Tx);
    context.stroke();
}

function drawAxes1unit(color,Tx) {
    context.strokeStyle=color;
    context.beginPath();
    // Axes
    moveToTx([1.20,0],Tx);lineToTx([0,0],Tx);lineToTx([0,1.20],Tx);
    // Arrowheads
    moveToTx([1.10,.05],Tx);lineToTx([1.20,0],Tx);lineToTx([1.10,-.05],Tx);
    moveToTx([.05,1.10],Tx);lineToTx([0,1.20],Tx);lineToTx([-.05,1.10],Tx);
    // X-label
    moveToTx([1.30,0],Tx);lineToTx([1.40,.10],Tx);
    moveToTx([1.30,.10],Tx);lineToTx([1.40,0],Tx);
    context.stroke();
}


var Hermite = function(t) {
  var x = t;
  var y = (-1 * (t-3) * (t-3) * (t-3)) + 1; 
    return [x,y];
}

var HermiteDerivative = function(t) {
    return [
      6*t*t-6*t,
      3*t*t-4*t+1,
      -6*t*t+6*t,
      3*t*t-2*t
    ];
}

// This can generate both the function C(t) and the derivative C'(t),
// depending on the basis passed in
function Cubic(basis,P,t){
  var b = basis(t);
//    var result=v3.mulScalar(P[0],b[0]);
//    v3.add(v3.mulScalar(P[1],b[1]),result,result);
//    v3.add(v3.mulScalar(P[2],b[2]),result,result);
//    v3.add(v3.mulScalar(P[3],b[3]),result,result);
//    return result;
}
    
  var C0 = function(t) {
      var x = t;
      var y = t*t;
      return [x,y];
  }

  

  var C1e = function(t) { // C2 continuity at t=1
      var x = t;
      var y = t*t-2*(t-1)*(t-1)*(t-1);
      return [x,y];
  }

  var C1 = C1e;
    
var Ccomp = function(t) {
      if(t<1) {
        return C0(t);
      }else if (t >= 1 & t < 2){
      return C1(t);
      } else {
        return Hermite(t);
      }
}

  function drawTrajectory(t_begin,t_end,intervals,C,Tx,color) {
    context.strokeStyle=color;
    context.beginPath();
      moveToTx(C(t_begin),Tx);
      for(var i=1;i<=intervals;i++){
          var t=((intervals-i)/intervals)*t_begin+(i/intervals)*t_end;
          lineToTx(C(t),Tx);
      }
      context.stroke();
}

// make sure you understand these    


var Tblue_to_canvas = mat3.create();
mat3.fromTranslation(Tblue_to_canvas,[50,350]);
mat3.scale(Tblue_to_canvas,Tblue_to_canvas,[150,-150]); // Flip the Y-axis

  var C = function(t_) {return Ccomp(tParam);};

  drawTrajectory(0.0,1.0,100,C0,Tblue_to_canvas,"green");
drawTrajectory(1.0,2.0,100,C1,Tblue_to_canvas,"purple");
  drawTrajectory(2,4,100,Hermite,Tblue_to_canvas,"darkblue");

var Tgreen_to_blue = mat3.create();
mat3.fromTranslation(Tgreen_to_blue,C(tParam));
var Tgreen_to_canvas = mat3.create();
mat3.multiply(Tgreen_to_canvas, Tblue_to_canvas, Tgreen_to_blue);
drawObject("black",Tgreen_to_canvas);
  }

  
  slider1.addEventListener("input",draw);
  draw();
}
window.onload = setup;


