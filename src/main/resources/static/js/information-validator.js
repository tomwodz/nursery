function validate() {
    var title = document.getElementById("title");
    var content = document.getElementById("contentinformation");
    var info = document.getElementById("info");

    var titleRegex = /^.{3,255}$/;
    var contentRegex = /^.{3,255}$/;

    var result = true;
    var infoResult = "";


    if (!titleRegex.test(title.value)) {
        infoResult = infoResult + "Zły tytuł (min. 3 znaki).\t";
        title.style.background = "#6A5ACD";
        title.style.color = "#FFFFFF";
        result = false;
    } else {
        title.style.background = "#FAFAD2";
        title.style.color = "#000000";
    }

    if (!contentRegex.test(content.value)) {
        infoResult = infoResult + "Zła treść (min. 3 znaki).";
        content.style.background = "#6A5ACD";
        content.style.color = "#FFFFFF";
        result = false;
    } else {
        content.style.background = "#FAFAD2";
        content.style.color = "#000000";
    }

    info.innerHTML = infoResult;
    return result;
}