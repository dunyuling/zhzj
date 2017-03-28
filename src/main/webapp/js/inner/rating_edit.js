$(function () {
    var religionType_ = $("#religionType_").val();
    $("#religionType option[value='" + religionType_ + "']").attr("selected", true);

    var rt_ = $("#rt_").val();
    $("#rt option[value='" + rt_ + "']").attr("selected", true);

    var content = $("#content_id").text();
    if (content != null) {
        $("#container").val(content);
    }
});