$(document).ready(() => {
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
               location.replace("/menu?token=" + data+"&userName="+body.id)
            }
        });
        postcheck.fail(function () {
            alert("La demande de connexion à échoué")
        });

    });

    $('#johnConnexion').click(function (event){
        $('#identifiant').val("johndupont44")
        $('#password').val("JohnDupont1234%")
    })

    $('#magalieConnexion').click(function (event){
        $('#identifiant').val("magaliedoe44")
        $('#password').val("MagagaBest1234%%")
    })

})