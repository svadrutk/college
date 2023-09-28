function setup() {
    var canvas = document.getElementById('canvas');
    var slider = document.getElementById('slider');
    slider.value = 0; 
    function draw() {
        var context = canvas.getContext('2d');
        canvas.width = canvas.width;
        var distance = slider.value;

        
        function drawHuman() {
            context.beginPath();
            context.arc(150, 70, 40, 0, 2 * Math.PI);
            context.stroke();
            context.fill();
            context.closePath();
            //
            context.beginPath();
            context.roundRect(136, 120, 30, 225, 20);
            context.fill();
            context.closePath();
            //
            context.beginPath();
            context.roundRect(172, 120, 30, 150, 20);
            context.fill();
            context.closePath();
            //
            context.beginPath();
            context.roundRect(100, 120, 30, 150, 20);
            context.fill();
            context.closePath();
            // 
            context.beginPath();
            context.beginPath();
            context.strokeStyle = 'blue';
            context.arc(187, 270, 20, 0, 2 * Math.PI);
            context.stroke();
            context.closePath();
            //
            context.beginPath();
            context.arc(115, 270, 20, 0, 2 * Math.PI);
            context.stroke();
            context.closePath();
            //
            context.beginPath();
            context.strokeStyle = 'red';
            context.moveTo(190, 275);
            context.lineTo(360, 275);
            context.stroke();
            context.closePath();

        }
        drawHuman();
        
    }
    slider.addEventListener('input', draw);
    draw();
}
window.onload = setup;