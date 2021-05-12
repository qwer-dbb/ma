
function checkForm(){
    // alert("aaa")
    // return false;
    $("input[name='password']").blur();
    $("input[name='password2']").blur();
    $("input[name='password3']").blur();
    var flag=true;

    $("#userForm font").each(function (index,item) {

        if ($(item).html()!=""){
            flag=false;
        }
    })
    return flag;
}
$(document).ready(function () {
    $("input[name='password3']").blur(function () {
        var password3=$(this).val();
        if(password3==""){
            $(this).next("font").html("请二次输入新密码");
            return;
        }else {
            var password2=$("input[name='password2']").val()
            if (password3!=password2){
                $(this).next("font").html("二次输入新密码不一致");
            }else {
                $(this).next("font").html("");
            }
        }
    })
    $("input[name='password2']").blur(function () {
        var password2=$(this).val();
        if(password2==""){
            $(this).next("font").html("请输入密码");
        }else {
            $(this).next("font").html("");
        }
    })

    $("input[name='password']").blur(function () {
        var password=$(this).val();
        if (password==""){
            $(this).next("font").html("原始密码不能为空")
            return;
        }else {
            $(this).next("font").html("")
        }
        $.ajax({
            "url":"checkPwd",
            "method":"POST",
            "data":"password="+password,
            "async":false,
            "dataType":"json",
            "success":function (obj) {
                if(obj.login == "false"){
                    alert("登录已超时，请重新登录");
                    location.href=$("#path")+"/login.jsp";
                }else if (obj.result =="false"){
                    $("input[name='password']").next("font").html("原始密码输入有误");
                }else {
                    $("input[name='password']").next("font").html("");
                }
            },
            "error":function () {
                alert("服务器繁忙")
            }
        })
    })
})





