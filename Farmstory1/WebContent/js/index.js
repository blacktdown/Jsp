/**
 * index 시작 스크립트 
 */
$(function(){

    $('.slider > ul').bxSlider({
        slideWidth: 980,
        pager: false,
        controls: false,
        auto: true
    });

    $('#tabs').tabs();

});