const ip =window.location.host.replace(":8080","")

function setUp(){
    document.getElementById("register-button").onclick=function () {window.location=`http://${ip}:8080/register.html`};
    document.getElementById("logo").onclick=function () {window.location=`http://${ip}:8080/`};
}

async function login() {
    var email = document.getElementById("email");
    var password = document.getElementById("password");
    // var url = "http://localhost:8080/user?email=" + email.value + "&password=" + password.value;
    var url = `http://${ip}:8080/user?email=${email.value}&password=${password.value}`;
    var response = await fetch(url, { method: "GET" })
    if (response.status == 400) {
        alert(await response.text());
        password.value = "";
    } else {
        sessionStorage.setItem("token", response.headers.get("Authorization"))
        sessionStorage.setItem("username", await response.text())
        // window.location='http://localhost:8080/index.html';
        window.location=`http://${ip}:8080/index.html`;
    }
}