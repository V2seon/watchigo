function home(){
location.href="/"
}
function home2(){
location.href="/Introduction"
}
function home3(){
location.href="/Payment"
}
function home4(){
location.href="/Process"
}
function home5(){
location.href="/WATCHIGO"
}
function home6(){
location.href="/Partners"
}
function home7(){
location.href="/AR_Factory"
}
function home8(){
location.href="/Register"
}
function home9(){
location.href="/Customer_Service"
}
function home11(){
location.href="/upload"
}
function home10(){
location.href="/userup"
}

function log(){
if(document.getElementById('ppp').innerText == "0" ){
    document.getElementById('loginbox').style.display = "block";
    document.getElementById('ppp').innerText = "1";
}else if(document.getElementById('ppp').innerText == "1" ){
    document.getElementById('loginbox').style.display = "none";
    document.getElementById('ppp').innerText = "0";
}
}

function login(){
const userId = document.getElementById("inid");
const userPw = document.getElementById("inpw");
if(userId.value == null || userId.value == ""){
    swal({
           text: "아이디를 입력해주세요.",
           icon: "info" //"info,success,warning,error" 중 택1
           });
}else if(userPw.value == null || userPw.value == ""){
    swal({
           text: "비밀번호를 입력해주세요.",
           icon: "info" //"info,success,warning,error" 중 택1
           });
}else{
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
}