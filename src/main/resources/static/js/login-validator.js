function validate() {
    var login = document.getElementById("login");
    var password = document.getElementById("password");
    var info = document.getElementById("info");

    var passwordRegex = /^.{3,50}$/;
    var loginRegex = /^.{3,50}$/;

    var result = true;
    var infoResult = "";


    if (!loginRegex.test(login.value)) {
        infoResult = infoResult + "Zły login.\t";
        login.style.background = "#6A5ACD";
        login.style.color = "#FFFFFF";
        result = false;
    } else {
        login.style.background = "#FAFAD2";
        login.style.color = "#000000";
    }

    if (!passwordRegex.test(password.value)) {
        infoResult = infoResult + "Złe hasło.";
        password.style.background = "#6A5ACD";
        password.style.color = "#FFFFFF";
        result = false;
    } else {
        password.style.background = "#FAFAD2";
        password.style.color = "#000000";
    }

    info.innerHTML = infoResult;
    return result;
}