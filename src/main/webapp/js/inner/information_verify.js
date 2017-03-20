$(function () {

    $("#denyReasonZone").hide();

    $("#verifyStatus").change(function () {
        var vs = $("#verifyStatus").find("option:selected").text();
        if(vs == "审核未通过") {
            $("#denyReasonZone").show();
        } else {
            $("#denyReasonZone").hide();
        }
    });


    var vs_ = $("#verifyStatus_").val();
    // alert("vs_: " + vs_);
    $("#verifyStatus option[value='" + vs_ + "']").attr("selected", true);
    if(vs_ == "审核未通过") {
        $("#denyReasonZone").show();
    } else {
        $("#denyReasonZone").hide();
    }
    //TODO 提交时相应的验证

});
