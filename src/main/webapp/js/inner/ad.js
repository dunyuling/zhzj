$(function () {

    $("#innerDirectionTypeZone").hide();
    $("#externalLinkZone").hide();

    $("#redirectionType").change(function () {
        var rt = $("#redirectionType").find("option:selected").text();
        if(rt == "不跳转") {
            $("#innerDirectionTypeZone").hide();
            $("#externalLinkZone").hide();
        } else if(rt == "内部跳转") {
            $("#innerDirectionTypeZone").show();
            $("#externalLinkZone").hide();
        } else {
            $("#innerDirectionTypeZone").hide();
            $("#externalLinkZone").show();
        }
    });
});