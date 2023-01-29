function goTo(pageName) {
    // const path = pageName + '.html';
    document.location.href = pageName;
}


function addToFavorite(clicked_id) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200 && this.responseText.length > 100 ) {
            alert("Авторизуйтесь для можливості добавлення рецептів в список улюблених");
        }
    };
    xhttp.open("POST", "/addToFavorite", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("recipeId=" + clicked_id);
}
