function goTo(pageName) {
    // const path = pageName + '.html';
    document.location.href = pageName;
}


function addToFavorite(clicked_id) {
    let XMLHttp = new XMLHttpRequest();
    XMLHttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200 && this.responseText.length > 100 ) {
            alert("Авторизуйтесь для можливості добавлення рецептів в список улюблених");
        }
    };
    XMLHttp.open("POST", "/addToFavorite", true);
    XMLHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    XMLHttp.send("recipeId=" + clicked_id);
}

function getRecipe(ingredients) {
    let XMLHttp = new XMLHttpRequest();
    XMLHttp.open("POST", "/search", false);
    XMLHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    XMLHttp.send("search=" + ingredients);
    return XMLHttp.responseText;
}


var list = [];

function fillList() {
    list = [];
    var op = document.getElementById('ingredients-list').options;

    for (var i = 0; i < op.length; i++) {
        list.push(op[i].text);
    }
}

function addToList() {
    var ingredient = document.getElementById('search-line').value;
    var s = document.getElementById('ingredients-list');
    var op = document.getElementById('ingredients-list').options;
    var isInList = false;

    if (ingredient != '') {
        fillList();
        for (var i = 0; i < list.length; i++) {
            if (list[i].toLowerCase() == ingredient.toLowerCase()) {
                isInList = true;
                break;
            }
            else {
                isInList = false;
            }
        }

        if (isInList == false) {
            list.push(ingredient);
            getRecipe(list);
            var option = document.createElement("option");
            option.text = ingredient;
            option.value = ingredient;
            s.appendChild(option);
            document.getElementById('search-line').value = '';
        } else {
            alert(`${ingredient} вже є в списку`);
            return;
        }
    }
    else {
        alert('Введіть назву інгрідієнта');
        return;
    }
}

function removeFromList() {
    var sel = document.getElementById('ingredients-list').options.selectedIndex;

    if (sel == -1) {
        alert('Виберіть інгрідієнт із списку');
    } else {
        document.getElementById('ingredients-list').options[sel] = null;
    }
}

function showPassword() {
    var checkbox = document.getElementById('show-password');
    var passwordInput1 = document.getElementById('password');
    var passwordInput2 = document.getElementById('repeatPassword');

    if (checkbox.checked) {
        passwordInput1.type = 'text';
        passwordInput2.type = 'text';
    } else {
        passwordInput1.type = 'password';
        passwordInput2.type = 'password';
    }
}
