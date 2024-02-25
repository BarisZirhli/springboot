$(document).ready(function () {
    var scaleCurve = mojs.easing.path('M0,100 L25,99.9999983 C26.2328835,75.0708847 19.7847843,0 100,0');
    var buttons = document.getElementsByClassName('button');

    // Her bir button için animasyonları oluştur
    for (var i = 0; i < buttons.length; ++i) {
        var el = buttons[i];

        // Mo.js timeline objesi
        var timeline = new mojs.Timeline();

        tween1 = new mojs.Burst({
            parent: el,
            radius: {0: 85},
            angle: {0: 45},
            y: -10,
            count: 10,
            radius: 90,
            children: {
                shape: 'circle',
                radius: 20,
                fill: ['red', 'green'],
                strokeWidth: 20,
                duration: 500,
            }
        });

        tween2 = new mojs.Tween({
            duration: 900,
            onUpdate: function (progress) {
                var scaleProgress = scaleCurve(progress);
                el.style.WebkitTransform = el.style.transform = 'scale3d(' + scaleProgress + ',' + scaleProgress + ',1)';
            }
        });
        
        tween3 = new mojs.Burst({
            parent: el,
            radius: {0: 75},
            angle: {0: -45},
            y: -10,
            count: 10,
            radius: 70,
            children: {
                shape: 'circle',
                radius: 15,
                fill: ['green', 'red'],
                strokeWidth: 15,
                duration: 400,
            }
        });

        // Tween'leri timeline'a ekle
        timeline.add(tween1, tween2, tween3);

        // Button'a tıklama olayı ekle
        $(el).click(function () {
            var currentTimeline = $(this).data('timeline');
            if ($(this).hasClass('active')) {
                currentTimeline.pause();
                $(this).removeClass('active');
            } else {
                currentTimeline.play();
                $(this).addClass('active');
            }
        });

        
        $(el).data('timeline', timeline);
        
       
    }
});
