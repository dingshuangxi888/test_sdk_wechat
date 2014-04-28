var map;
var userBind = function () {
    div_alert.addClass("hidden")
    var userName = $("#UserName").val();
    var password = $("#Password").val();

    $.post("../user/bind", {'userName': userName, 'password': password}, function (data) {
        if ("SUCCESS" === data) {
            var div_alert = $("#user-bind-alert");
            div_alert.removeClass("alert-danger")
            div_alert.addClass("alert-success");
            div_alert.removeClass("hidden")

            div_alert.find("span").html("恭喜你，绑定成功！请点击【返回】进入微信公用平台...");
        }
    });

    return false;
}