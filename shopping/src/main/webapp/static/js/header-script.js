$(document).ready(function(){
    let str = "";
    str +=
        '<ul class="dep1 hide">'

    let largeC = getCategories(null);
    for (let i = 0; i < largeC.length; i++) {
        str +=
            '<li>' +
            '<div class = "hoverClass">' +
            '<span>' + largeC[i].key.split(";")[1] +'</span>' +
            '<ul class = "dep2 hide">'

        middleC = largeC[i].value;
        for (let j = 0; j < middleC.length; j++) {
            str +=  '<li>' +
                '<div class = "hoverClass">' +
                '<span>' + middleC[j].split(";")[1] + '</span>' +
                '<ul class = "dep3 hide">'
            let smallC = getCategories(middleC[j]);
            for(let k = 0; k < smallC.length; k++){
                str +=
                    '<li>' +
                    '<a href = item?categoryId=' + smallC[k].value.split(";")[0] + '&page=1>' +
                    smallC[k].value.split(";")[1] +
                    '</a>' +
                    '</li>'
            }
            str +=
                '</ul>' +
                '</div>' +
                '</li>'
        }

        str +=
            '</ul>' +
            '</div>' +
            '</li>'
    }
    $('#menuBody').append(str);

    $(".hoverClass").bind("mouseenter",function(){
        $(this).children('ul').removeClass("hide");
        $(this).children('ul').addClass("show");
    })

    $(".hoverClass").bind("mouseleave",function(){
        $(this).children('ul').removeClass("show");
        $(this).children('ul').addClass("hide");
    })
})