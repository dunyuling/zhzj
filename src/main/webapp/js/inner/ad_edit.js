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

    var rt_ = $("#redirectionType_").val();
    $("#redirectionType option[value='" + rt_ + "']").attr("selected", true);
    if(rt_ == "不跳转") {
        $("#innerDirectionTypeZone").hide();
        $("#externalLinkZone").hide();
    } else if(rt_ == "内部跳转") {
        $("#innerDirectionTypeZone").show();
        $("#externalLinkZone").hide();
        var irt_ = $("#innerRedirectionType_").val();
        $("#innerRedirectionType option[value='" + irt_ + "']").attr("selected",true);
    } else {
        $("#innerDirectionTypeZone").hide();
        $("#externalLinkZone").show();
    }

    var rt_ = $("#religionType_").val();
    $("#religionType option[value='" + rt_ + "']").attr("selected", true);
});
