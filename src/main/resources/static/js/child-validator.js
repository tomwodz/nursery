function validate() {
    var name = document.getElementById("name");
    var surname = document.getElementById("surname");
    var info = document.getElementById("info");

    var nameRegex = /^.{3,50}$/;
    var surnameRegex = /^.{3,50}$/;

    var result = true;
    var infoResult = "";


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
        infoResult = infoResult + "Złe nazwisko.";
        surname.style.background = "#6A5ACD";
        surname.style.color = "#FFFFFF";
        result = false;
    } else {
        surname.style.background = "#FAFAD2";
        surname.style.color = "#000000";
    }

    info.innerHTML = infoResult;
    return result;
}