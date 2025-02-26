const ip =window.location.host.replace(":8080","")
function setUp(){
    document.getElementById("logo").onclick=function () {window.location=`http://${ip}:8080`};
    document.getElementById("login-button").onclick=function () {window.location=`http://${ip}:8080/login.html`};
}

function register() {
    var password = document.getElementById("password");
    var passwordCofirm = document.getElementById("confirm_password");
    if (password.value != passwordCofirm.value) {
        password.value = "";
        passwordCofirm.value = "";
        alert("failed to confirm the password");
    } else {
        sendRegisterRequest()
    }
}
async function sendRegisterRequest() {
    var username = document.getElementById("username").value;
    var email = document.getElementById("email").value;
    var date = new Date(document.getElementById("dob").value).getTime();
    var password = document.getElementById("password").value;

    // var url = "http://localhost:8080/user?username=" + username + "&email=" + email + "&date=" + date + "&password=" + password
    var url = `http://${ip}:8080/user?username=${username}&email=${email}&date=${date}&password=${password}`;
    var response = await fetch(url, { method: "POST" })
    if(response.status!=201){
        alert(await response.text());
    }else{
        // window.location='http://localhost:8080'
        window.location=`http://${ip}:8080`;
    }
}