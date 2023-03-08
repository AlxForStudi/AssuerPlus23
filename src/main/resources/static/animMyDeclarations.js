/* List pour test => viendra du controller / Format de l'objet à concerver */
/*let list = [
    {
        number: "AVP123456JDK11",
        contract: "Megane-John",
        date: "2023-02-05",
        place: "7 rue du pas de bol, Saint accident",
        status: "Traiter",
    },
    {
        number: "AVP123456JDK22",
        contract: "Megane-John",
        date: "2023-02-05",
        place: "7 rue du pas de bol, Saint accident",
        status: "Traiter",
    },
    {
        number: "AVP123456JDK33",
        contract: "Megane-John",
        date: "2023-02-05",
        place: "7 rue du pas de bol, Saint accident",
        status: "Traiter",
    },
    {
        number: "AVP123456JDK44",
        contract: "Megane-John",
        date: "2023-02-05",
        place: "7 rue du pas de bol, Saint accident",
        status: "En cours",
    },
    {
        number: "AVP123456JDK55",
        contract: "Megane-John",
        date: "2023-02-05",
        place: "7 rue du pas de bol, Saint accident",
        status: "En cours",
    }
]*/
let sinistresList;
$(document).ready(function () {
    $('h1, h4').each(function(){
        $(this).css('display','none')
    })

    $('h1, h4').each(function(){
        $(this).show(1000)
    })
        const body = {
            token: token,
            personId: personId
        };
        const postSinistre = $.post("/getClientSinistres", body);
        postSinistre.done(function (data) {
            if (data == null) {
                alert("Vous n'etes pas autorisé à consulté la liste");
            } else {
                sinistresList = data
                loadList()
            }
        });
        postSinistre.fail(function () {
            alert("La demande de list à échoué")
        });
    });
function loadList() {
    if (sinistresList != null) {
        $('#listOff').css('display', 'none')
        $('#listOn').show()
    }
    for (let index = 0; index <= sinistresList.length - 1; index++) {
        if (sinistresList[index] != null) {
            $('#table-body').append(
                '<tr>' +
                '<td class="d-md-none text-center"><a href="#" onclick="detailsSinistre(' + index + ')">' + sinistresList[index]["number"] + '</a></td>' +
                '<td class="d-md-table-cell d-none">' + sinistresList[index]["number"] + '</td>' +
                '<td class="d-md-table-cell d-none text-center">' + sinistresList[index]["contract"] + '</td>' +
                '<td class="d-md-table-cell text-center">' + sinistresList[index]["date"] + '</td>' +
                '<td class="d-md-table-cell d-none text-center">' + sinistresList[index]["place"] + '</td>' +
                '<td class="d-md-table-cell text-center">' + sinistresList[index]["status"] + '</td>' +
                '</tr>'
            )
        }
    }
}
function detailsSinistre(number){
    $('#previousDetails').remove()
    $('#details').append(
        '<div id="previousDetails" class="col-sm-7 d-md-none p-3 ">'+
        '<h5 class="text-decoration-underline mb-3">Détails</h5>'+
        '<p class="fw-bold border-bottom">Sinistre n° : <span class="fw-normal">'+sinistresList[number]["number"]+'</span></p>' +
        '<p class="fw-bold border-bottom">Contrat : <span class="fw-normal">'+sinistresList[number]["contract"]+'</span></p>' +
        '<p class="fw-bold border-bottom">Le : <span class="fw-normal">'+sinistresList[number]["date"]+'</span></p>' +
        '<p class="fw-bold border-bottom">À : <span class="fw-normal">'+sinistresList[number]["place"]+'</span></p>' +
        '<p class="fw-bold">Statut : <span class="fw-normal">'+sinistresList[number]["status"]+'</span></p>' +
        '</div>'
    )
}

