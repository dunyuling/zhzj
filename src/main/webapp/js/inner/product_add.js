/**
 * Created by pro on 17-3-21.
 */
$(function () {
    $("button[name^=product_slide_add]").click(function () {
        var intro_slide_append = $("div[name=slide_append]").html();
        // alert(intro_slide_append);
        var children_length = $("#slide_zone div[class='form-group']").length + 1;
        // alert(children_length);
        var input = $(intro_slide_append).find("input[name=product_intro]");
        // alert(input);

        $("#slide_zone").append(intro_slide_append);
    });

    var intro = $("#product_intro_1").text();
    if(intro != null) {
        $("#container").val(intro);
    }
});