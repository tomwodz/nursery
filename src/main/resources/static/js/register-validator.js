function validate() {
    var login = document.getElementById("login");
    var password = document.getElementById("password");
    var password2 = document.getElementById("password2");
    var name = document.getElementById("name");
    var surname = document.getElementById("surname");
    var email = document.getElementById("email");
    var phoneNumber = document.getElementById("phoneNumber");
    var street = document.getElementById("street");
    var zipCode = document.getElementById("zipCode");
    var city = document.getElementById("city");
    var info = document.getElementById("info");

    var passwordRegex = /^.{3,50}$/;
    var loginRegex = /^.{3,50}$/;
    var nameRegex = /^.{3,50}$/;
    var surnameRegex = /^.{3,50}$/;
    var emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,6}$/;
    var phoneNumberRegex = /[0-9]{3}-[0-9]{3}-[0-9]{3}/;
    var streetRegex = /^.{3,50}$/;
    var zipCodeRegex = /[0-9]{2}-[0-9]{3}/;
    var cityRegex = /^.{3,50}$/;

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
        infoResult = infoResult + "Złe hasło.\t";
        password.style.background = "#6A5ACD";
        password.style.color = "#FFFFFF";
        result = false;
    } else {
        password.style.background = "#FAFAD2";
        password.style.color = "#000000";
    }

    if (!passwordRegex.test(password2.value)) {
        infoResult = infoResult + "Hasła nie są takie same.\t";
        password2.style.background = "#6A5ACD";
        password2.style.color = "#FFFFFF";
        result = false;
    } else {
        password2.style.background = "#FAFAD2";
        password2.style.color = "#000000";
    }

    if (!nameRegex.test(name.value)) {
        infoResult = infoResult + "Złe imię.\t";
        name.style.background = "#6A5ACD";
        name.style.color = "#FFFFFF";
        result = false;
    } else {
        name.style.background = "#FAFAD2";
        name.style.color = "#000000";
    }

    if (!surnameRegex.test(surname.value)) {
        infoResult = infoResult + "Złe nazwisko.\t";
        surname.style.background = "#6A5ACD";
        surname.style.color = "#FFFFFF";
        result = false;
    } else {
        surname.style.background = "#FAFAD2";
        surname.style.color = "#000000";
    }

    if (!emailRegex.test(email.value)) {
        infoResult = infoResult + "Zły e-mail.\t";
        email.style.background = "#6A5ACD";
        email.style.color = "#FFFFFF";
        result = false;
    } else {
        email.style.background = "#FAFAD2";
        email.style.color = "#000000";
    }

    if (!phoneNumberRegex.test(phoneNumber.value)) {
        infoResult = infoResult + "Zły numer telefonu.\t";
        phoneNumber.style.background = "#6A5ACD";
        phoneNumber.style.color = "#FFFFFF";
        result = false;
    } else {
        phoneNumber.style.background = "#FAFAD2";
        phoneNumber.style.color = "#000000";
    }

    if (!streetRegex.test(street.value)) {
        infoResult = infoResult + "Zła ulica.\t";
        street.style.background = "#6A5ACD";
        street.style.color = "#FFFFFF";
        result = false;
    } else {
        street.style.background = "#FAFAD2";
        street.style.color = "#000000";
    }

    if (!zipCodeRegex.test(zipCode.value)) {
        infoResult = infoResult + "Zły kod pocztowy.\t";
        zipCode.style.background = "#6A5ACD";
        zipCode.style.color = "#FFFFFF";
        result = false;
    } else {
        zipCode.style.background = "#FAFAD2";
        zipCode.style.color = "#000000";
    }

    if (!cityRegex.test(city.value)) {
        infoResult = infoResult + "Złe miasto.";
        city.style.background = "#6A5ACD";
        city.style.color = "#FFFFFF";
        result = false;
    } else {
        city.style.background = "#FAFAD2";
        city.style.color = "#000000";
    }

    info.innerHTML = infoResult;
    return result;
}