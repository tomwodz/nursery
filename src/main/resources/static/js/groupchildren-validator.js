function validate() {
    var name = document.getElementById("name");
    var info = document.getElementById("info");

    var nameRegex = /^.{3,20}$/;

    var result = true;
    var infoResult = "";


    if (!nameRegex.test(name.value)) {
        infoResult = infoResult + "Zła nazwa (min. 3 znaki).";
        name.style.background = "#6A5ACD";
        name.style.color = "#FFFFFF";
        result = false;
    } else {
        name.style.background = "#FAFAD2";
        name.style.color = "#000000";
    }

    info.innerHTML = infoResult;
    return result;
}