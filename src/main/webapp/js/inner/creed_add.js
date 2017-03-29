$(function () {
    alert("sss");
    var content = $("#content").text();
    if (content != null) {
        $("#container").val(content);
    }

    var rt_ = $("#religionType_").val();
    $("#religionType option[value='" + rt_ + "']").attr("selected", true);
});