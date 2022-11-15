const userId = document.getElementById("id");
const userPw = document.getElementById("pw");


function login(){
let sendData = {
            "userid" : userId.value,
            "userpw" : userPw.value,
}
$.ajax({
            url      : "/userlogin",
            data     : sendData,
            type     : "GET",
            success : function(result) {
                $('#load').hide();
                if(result.loginResult == "1"){
                    location.href = "/servicezone";
                }else if(result.loginResult == "0"){
                    swal({
                        text: "계정 정보가 일치하지 않습니다.",
                        icon: "error"
                    });
                }
            },
            error:function(request,status,error){
                $('#load').hide();
                swal({
                    text: "로그인 도중 서버에 문제가 발생했습니다.",
                    icon: "warning" //"info,success,warning,error" 중 택1
                });
            }
        });
}