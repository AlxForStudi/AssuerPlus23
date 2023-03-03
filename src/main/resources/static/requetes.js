let form = document.getElementById('form');

form.addEventListener("submit", (event)=>{
    alert("TEST");
    const xhr = new XMLHttpRequest();
    const url = '/checkConnexion';
    xhr.open("POST",url);
    xhr.setRequestHeader("Content-Type","application/x-");
    const body = JSON.stringify({
        id:"identifiant",
        psss:"password",
    });

    xhr.onload = ()=>{
        if (xhr.readyState == 4 && xhr.status ==201){
            alert(JSON.parse(xhr.responseText));
        }else{
            alert("error : " + xhr.status);
        }
    };
    xhr.send(body);
})