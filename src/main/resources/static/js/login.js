


function loginCheck() {


    $.get("/loginUser",{},function (user) {
        if(!user) {
            $("#userOption").css("display","none")
            $("#loginActive").css("display","block")
        } else {
            $("#userOption").css("display","block")
            $("#userInfoUsername").html(""+ user.username+"")
            $("#avatar").attr("src",""+user.avatarUrl+"")
            $("#loginActive").css("display","none")
        }

    })
}