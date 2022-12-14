function home(){
location.href="/"
}
function Intro(){
location.href="/Intro"
}
function Ask(){
location.href="/Ask"
}
function Process(){
location.href="/Process"
}
function WATCHIGO(){
location.href="/WATCHIGO"
}
function Partners(){
location.href="/Partners"
}
function AR_Factory(){
location.href="/AR_Factory"
}
function Register(){
location.href="/Register"
}
function Customer_Service(){
location.href="/Customer_Service"
}
function IP(){
location.href="/IP"
}
function upload(){
location.href="/upload"
}
function userup(){
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