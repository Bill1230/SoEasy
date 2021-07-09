//輪播區
let slideNum=0;
let slideCount=$(".slides li").length;
let lastIndex=slideCount-1;

    
$(".dot li").eq(0).css("background-color","darkgreen");
$(".dot li").mouseenter(function(){
    slideNum=$(".dot li").index($(this));
    show();
})

function show(){
    $(".dot li").eq(slideNum).css("background-color","darkgreen")
    .siblings().css("background-color","transparent");

    let slidemove=0-800*slideNum;
    $("ul.slides").css("left",slidemove);
}
function ongoingLecture_show(){
    $(".dot li").eq(slideNum).css("background-color","#fff")
    .siblings().css("background-color","transparent");

    let slidemove=0-800*slideNum;
    $("ul.slides").css("left",slidemove);
}
function upcomingLecture_show(){
    $(".dot li").eq(slideNum).css("background-color","#fff")
    .siblings().css("background-color","transparent");

    let slidemove=0-800*slideNum;
    $("ul.slides").css("left",slidemove);
}

$("#ongoingLecture_prevSlide").click(function(){
    slideNum--;
    if(slideNum<0)slideNum=lastIndex;
    ongoingLecture_show();
})

$("#ongoingLecture_nextSlide").click(function(){
    slideNum++;
    if(slideNum>lastIndex)slideNum=0;
    ongoingLecture_show();
})

$("#upcomingLecture_prevSlide").click(function(){
    slideNum--;
    if(slideNum<0)slideNum=lastIndex;
    upcomingLecture_show();
})

$("#upcomingLecture_nextSlide").click(function(){
    slideNum++;
    if(slideNum>lastIndex)slideNum=0;
    upcomingLecture_show();
})

let s = setInterval(function () {
    if (slideNum == slideCount)
        slideNum = 0;
    show();
    slideNum++;
}, 5000);      

$(".wrapper").mouseenter(function () {
    clearInterval(s);
    })

$(".wrapper").mouseleave(function () {
    s = setInterval(function () {
    if (slideNum == slideCount)
        slideNum = 0;
    show();
    slideNum++;
    }, 5000);    
   })