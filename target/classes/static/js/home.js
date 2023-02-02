function getUserRole() {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", "/getUserRoles", false ); // false for synchronous request
    xmlHttp.send( null );
    return xmlHttp.responseText;
}
console.log(getUserRole());
if (getUserRole().includes('ADMIN')){
    let a = document.getElementById("goAdminPage");
    a.classList.remove("hidden")
}
if (getUserRole().includes('MODERATOR')){
    let a = document.getElementById("goModerPage");
    a.classList.remove("hidden")
}

