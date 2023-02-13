function goTo(pageName) {
    // const path = pageName + '.html';
    document.location.href = pageName;
}
const userRoles = getUserRoles();
let userFavorRecipe;
updateUserFavorRecipe();

function updateUserFavorRecipe() {
    if (userRoles.includes("USER")){
        userFavorRecipe = getFavorRecipe();
    }
}

function addToFavorite(clicked_id) {
    let XMLHttp = new XMLHttpRequest();
    let button = document.getElementById(clicked_id);
    updateUserFavorRecipe();
    if (userFavorRecipe != null && userFavorRecipe.includes(clicked_id)) {
        button.classList.remove('red');
        button.classList.add('green');
        button.innerHTML = '&#10084;';
        XMLHttp.open("POST", "/deleteFromFavorite", true);
    } else {
        button.classList.remove('green');
        button.classList.add('red');
        button.innerHTML = '&#128148;';
        XMLHttp.open("POST", "/addToFavorite", true);
    }
    XMLHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    XMLHttp.send("recipeId=" + clicked_id);
}

function deleteFromFavorite(clicked_id) {
    let XMLHttp = new XMLHttpRequest();
    let button = document.getElementById(clicked_id);

    button.onclick = () => addToFavorite(id);
    button.innerHTML = '&#10084;';
    button.classList.remove('red');
    button.classList.add('green');


    XMLHttp.open("POST", "/deleteFromFavorite", true);
    XMLHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    XMLHttp.send("recipeId=" + clicked_id);
}

function getRecipe(ingredients) {
    let XMLHttp = new XMLHttpRequest();
    // XMLHttp.open("POST", "/search", false);
    // XMLHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    // XMLHttp.send("search=" + ingredients);
    let checkboxes = document.getElementsByClassName("checkbox");
    let category = [];
    for(let i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked){
            category.push(checkboxes[i].id);
        }
    }
    XMLHttp.open("GET", "/search?search=" + ingredients + "&category=" + category, false);
    XMLHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    XMLHttp.send(null);
    const json = JSON.parse(XMLHttp.responseText);
    // console.log(json[0]['name']);
    return json;
}

function  getUserRoles() {
    let XMLHttp = new XMLHttpRequest();
    XMLHttp.open("GET", "/getUserRoles", false);
    XMLHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    XMLHttp.send(null);
    return XMLHttp.responseText;
}

function  getFavorRecipe() {
    let XMLHttp = new XMLHttpRequest();
    XMLHttp.open("GET", "/getIdFavorRecipe", false);
    XMLHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    XMLHttp.send(null);
    return XMLHttp.responseText;
}


function addRecipe(json) {
    let title;
    let recipe;
    let ingredients;
    let addTooFavor;
    let getMoreInfo;
    let recipes = document.getElementById('recipes-list');
    recipes.innerHTML = '';

    for (let i = 0; i < json.length; i++) {
        recipe = document.createElement("div");
        recipe.style.cssText = 'margin: 5px; margin-bottom: 10px;'

        title = document.createElement("h3");
        title.innerHTML = json[i]['name'];

        ingredients = document.createElement("p");
        ingredients.innerHTML = json[i]['recipe'];

        getMoreInfo = document.createElement("button");
        getMoreInfo.innerHTML = 'Детальніше';
        getMoreInfo.style.cssText = 'margin-left: 10px; padding: 5px;'
        getMoreInfo.onclick = () => window.open(json[i]['url'], '_blank');
        getMoreInfo.href = json[i]['url'];

        recipes.appendChild(recipe);
        recipe.appendChild(title);
        recipe.appendChild(ingredients);
        if (userRoles.includes("USER")) {
            addTooFavor = createButton(json[i]['id']);
            recipe.appendChild(addTooFavor);
        }

        recipe.appendChild(getMoreInfo);

    }
}

function createButton(id) {

    let button = document.createElement("button");
    button.id = id;
    button.onclick = () => addToFavorite(id);
    button.style.cssText = 'margin-right: 5px;'
    if (userFavorRecipe != null && userFavorRecipe.includes(id)){
        button.classList.remove('green');
        button.classList.add('red');
        button.innerHTML = '&#128148;';
    } else {
        button.classList.remove('red');
        button.classList.add('green');
        button.innerHTML = '&#10084;';
    }
    return button;
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
            addRecipe(getRecipe(list));
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

function showPassword(page) {
    if (page == "reg") {
        var checkbox = document.getElementById('show-password');
        var passwordInput1 = document.getElementById('password');
        var passwordInput2 = document.getElementById('password-confirm');

        if (checkbox.checked) {
            passwordInput1.type = 'text';
            passwordInput2.type = 'text';
        } else {
            passwordInput1.type = 'password';
            passwordInput2.type = 'password';
        }
    } else if (page == "auth") {
        var checkbox = document.getElementById('show-password');
        var passwordInput1 = document.getElementById('password');
        
        if (checkbox.checked) {
            passwordInput1.type = 'text';
        } else {
            passwordInput1.type = 'password';
        }
    }
}
