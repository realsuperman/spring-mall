let categories;
let bestCategoryIndex = 0;
let bestCategoryRange;

$(document).ready(function(){
    categories = getCategories(null);
    bestCategoryRange = categories.length;

    bestSeller(0);
})

function bestSeller(categoryIdx){
    if(categoryIdx >= bestCategoryRange){
        categoryIdx = 0;
    }else if(categoryIdx < 0){
        categoryIdx = bestCategoryRange - 1;
    }

    bestCategoryIndex = categoryIdx;

    let bestCategoryId = categories[bestCategoryIndex].key.split(";")[0];
    let categoryName = categories[bestCategoryIndex].key.split(";")[1];

    $.ajax({
        type:"GET",
        url:"itemJson?categoryId="+bestCategoryId,
        dataType:"json",
        success: function(response){
            var str = "";

            $('#bestSeller').empty();

            str +=
                '<div class="col-lg-3 product__item__text">' +
                '<h5 style="display:inline">' + categoryName + '</h5> 의 인기상품' +
                '</div>' +
                '<i onClick="bestSeller(bestCategoryIndex+1)" id = "previousButton" class = "fa-solid fa-caret-left fa-5x"/>' +
                '<div id = "bestSellerBody" class = "col-lg-12">' +
                '<div class="row">'
            for (let i = 0; i < response.length; i++) {
                console.log(response[i].itemImagePath.split(';')[0]);
                str +=

                    '<div class="col-lg-3 col-md-6 col-sm-6">' +
                        '<div class="product__item">' +
                            '<a href=itemDetail?itemId=' + response[i].itemId + '>' +
                                '<img class="product__item__pic set-bg" src ="' + downPrefix + response[i].itemImagePath.split(';')[0] + downSuffix + '">' +
                            '</a>' +
                            '<div class="product__item__text">' +
                                '<h6>'+response[i].itemName + '</h6>' +
                                '<a href="itemDetail?itemId=' + response[i].itemId + '" className="add-cart">상품 상세보기</a>' +
                                '<h5>'+response[i].itemPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')+'원</h5>' +
                            '</div>' +
                        '</div>' +
                    '</div>'
            }
            str +=
                '</div>' +
                '</div>' +
                '<i onClick="bestSeller(bestCategoryIndex-1)" id ="nextButton" class = "fa-solid fa-caret-right fa-5x"/>'
            console.log($('#bestSeller'))
            $('#bestSeller').append(str);
        }
    })
}