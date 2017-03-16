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


    //TODO 提交时相应的验证
    /*$("#submit").click(function() {
        var rt = $("#redirectionType").find("option:selected").text();
        var el = $("#externalLink").val();


        if(rt == "外部跳转") {

        }
    });*/
});
