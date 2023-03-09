$(document).ready(() => {

    $('#nav-menu, #doneBack').click(function (event){
        event.preventDefault();
        location.replace("/menu?token=" + token+"&userName="+userName)
    })
    $('#nav-newD, #newD').click(function (event) {
        event.preventDefault();
        location.replace("/formNewDeclaration?token=" + token+"&userName="+userName)
    });
    $('#nav-myD, #myD').click(function (event) {
        event.preventDefault();
        location.replace("/myDeclarations?token=" + token+"&userName="+userName)
    });
    $('#deconnexion').click(function (event) {
        event.preventDefault();
        location.replace("/home")
    });
})