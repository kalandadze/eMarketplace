function register(){
    var password=document.getElementById("password");
    var passwordCofirm=document.getElementById("confirm_password");
    if(password!=passwordCofirm){
        alert("failed to confirm the password");
        password.textContent="";
        passwordCofirm.textContent="";
    }else{
        sendRegisterRequest()
    }
}
async function sendRegisterRequest(){
    var username=document.getElementById("username");
    var email=document.getElementById("email");
    var date=document.getElementById("date");
    var password=document.getElementById("password");
    var passwordCofirm=document.getElementById("confirm_password");

}