var themePage = 1;
var workPage = 1;
//var authorPage = 0; this show that works
var authorPage = 1;


function loadMoreThemes() {
    var urlPage = "/table-theme?page=" + themePage;
    
    $.ajax({
        url: urlPage
    }).done(function (data) {
        $("#moreThemes").append(data);
        themePage++;
        
        if (indexTheme == themePage){
        	$("#buttonMoreThemes").hide();
        	console.log("esconder boton tema");
        }
    })
}

function loadMoreWorks() {
    var urlPage = "/table-works?page=" + workPage;
    
    $.ajax({
        url: urlPage
    }).done(function (data) {
        $("#moreWorks").append(data);
        workPage++;
        
        if (indexWorks == workPage){
        	$("#buttonMoreWorks").hide();
        	console.log("esconder boton obra");
        }
    })
}

function loadMoreAuthor() {
    var urlPage = "/table-author?page=" + authorPage;
    
    $.ajax({
        url: urlPage
    }).done(function (data) {
        $("#moreAuthor").append(data);
        authorPage++;
        
        if (indexAuthors == authorPage){
        	$("#buttonMoreAuthor").hide();
        	console.log("esconder boton autor");
        }
    })
}