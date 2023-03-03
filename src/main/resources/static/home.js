$(document).ready(() => {
    /*
    "johndupont44"
    "JohnDupont1234%"
    */


    if (errorMsg !== "") {
        alert(errorMsg)
    }

    $('#form').submit(function (event) {
        event.preventDefault();
        const body = {
            id: $('#identifiant').val(),
            pass: $('#password').val(),
        };
        const postcheck = $.post("/checkConnexion", body);
        postcheck.done(function (data) {
            if (data == "null") {
                alert("Identifiant ou mot de pass incorrecte");
            } else {
                location.replace("/formNewDeclaration?token=" + data+"&userName="+body.id)
            }
        });
        postcheck.fail(function () {
            alert("La demande de connexion à échoué")
        });

    });
})