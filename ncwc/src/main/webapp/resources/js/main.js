var map;
var userBind = function () {
    var userName = $("#UserName").val();
    var password = $("#Password").val();
    var openid = getUrlParam("openid");

    $.post("../user/bind", {'userName': userName, 'password': password, 'openid': openid}, function (data) {
        if ("SUCCESS" === data) {
            var div_alert = $("#user-bind-alert");
            div_alert.addClass("hidden")
            div_alert.find("span").html("恭喜你，绑定成功！请点击【返回】进入微信公用平台...");
            div_alert.removeClass("alert-danger")
            div_alert.addClass("alert-success");
            div_alert.removeClass("hidden")
        } else {
            var div_alert = $("#user-bind-alert");
            div_alert.addClass("hidden")
            div_alert.removeClass("alert-success")
            div_alert.addClass("alert-danger");
            div_alert.removeClass("hidden")

            div_alert.find("span").html("对不起，绑定失败，请重新输入...");
        }
    });

    return false;
}

var getUrlParam = function (name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]);
    return null;
}