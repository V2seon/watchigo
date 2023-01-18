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
function Post(){
location.href="/Post"
}
function Plan(){
location.href="/Plan"
}
function Press_Release(){
location.href="/Press_Release"
}
function QA(){
location.href="/QA"
}
function FAQ(){
location.href="/FAQ"
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

function enterkey(){
if(window.event.keyCode == 13){
        login();
    }
}

function email(){
var emailid = document.getElementById('email');
document.getElementById('email2').value = emailid.options[emailid.selectedIndex].text;
}

function ckpw(){
var pw1 = document.getElementById('PW').value;
var pw2 = document.getElementById('PWck').value;
if(pw1 == null || pw1 == "" || pw2 == null || pw2 == ""){
document.getElementById('pwcktext').innerText = "비밀번호 불일치";
document.getElementById('pwcktext').style.color = "red";
}else if(pw1 == pw2){
document.getElementById('pwcktext').innerText = "비밀번호 일치";
document.getElementById('pwcktext').style.color = "blue";
}else{
document.getElementById('pwcktext').innerText = "비밀번호 불일치";
document.getElementById('pwcktext').style.color = "red";
}
}

function join(){
const id = document.getElementById('ID').value;
const pw = document.getElementById('PW').value;
const name = document.getElementById('NAME').value;
const date = document.getElementById('DATE').value;
const phone = document.getElementById('PHONE').value;
const email1 = document.getElementById('email1').value;
const email2 = document.getElementById('email2').value;
const pwcktext = document.getElementById('pwcktext').innerText;
const cknum = document.getElementById('cknum').innerText;
if(id == null || id == ""){
    swal({
          text: "아이디를 입력해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(pw == null || pw == ""){
    swal({
              text: "비밀번호를 입력해주세요.",
              icon: "info" //"info,success,warning,error" 중 택1
              });
}else if(name == null || name == ""){
    swal({
              text: "이름을 입력해주세요.",
              icon: "info" //"info,success,warning,error" 중 택1
              });
}else if(date == null || date == ""){
    swal({
              text: "생년월일을 입력해주세요.",
              icon: "info" //"info,success,warning,error" 중 택1
              });
}else if(phone == null || phone == ""){
    swal({
              text: "휴대전화를 입력해주세요.",
              icon: "info" //"info,success,warning,error" 중 택1
              });
}else if(email1 == null || email1 == ""){
    swal({
              text: "이메일을 입력해주세요.",
              icon: "info" //"info,success,warning,error" 중 택1
              });
}else if(email2 == null || email2 == ""){
    swal({
              text: "이메일을 입력해주세요.",
              icon: "info" //"info,success,warning,error" 중 택1
              });
}else if(pwcktext == "비밀번호 불일치"){
    swal({
              text: "비밀번호를 확인해주세요.",
              icon: "info" //"info,success,warning,error" 중 택1
              });
}else if(cknum == "0"){
    swal({
              text: "아이디 중복확인을 해주세요.",
              icon: "info" //"info,success,warning,error" 중 택1
              });
}else{
swal({
  title: "회원가입신청",
  text: "회원가입신청 하시겠습니까?",
  icon: "info",
  closeOnClickOutside : false,
  buttons : ["취소", "신청"]
}).then((result) => {
    if(result){
    let sendData = {
            "id" : id,
            "pw" : pw,
            "name" : name,
            "date" : date,
            "phone" : phone,
            "email" : email1+email2
            };
    $.ajax({
            url : "/join",
            data : sendData,
            type : "POST",
            success : function(result){
                swal({
                      text: "회원가입신청 되었습니다.",
                      icon: "info", //"info,success,warning,error" 중 택1
                      closeOnClickOutside : false
                      }).then((result) => {
                        location.href = "/";
                      });
            }
    });
    }
  });
}
}

function ckid(){
var id1 = document.getElementById('ID').value;
if(id1 == null || id1 == ""){
    swal({
          text: "아이디를 입력해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else{
let sendData = {
            "id" : id1
            };
$.ajax({
            url      : "/ckid",
            data     : sendData,
            type     : "GET",
            success : function(result) {
                if(result.ckid == "1"){
                    document.getElementById("ckbox1").style.display= "block";
                    document.getElementById("ckbox2").style.display= "none";
                    document.getElementById("cknum").innerText = "1";
                    swal({
                            text: "해당 아이디는 사용가능합니다.",
                            icon: "warning" //"info,success,warning,error" 중 택1
                        });
                }else if(result.ckid == "0"){
                    swal({
                        text: "해당 아이디를 사용할 수 없습니다.",
                        icon: "warning" //"info,success,warning,error" 중 택1
                    });
                }
            },
            error:function(request,status,error){
                swal({
                    text: "로그인 도중 서버에 문제가 발생했습니다.",
                    icon: "warning" //"info,success,warning,error" 중 택1
                });
            }
        });
}
}

function newid(){
document.getElementById("ckbox1").style.display= "none";
document.getElementById("ckbox2").style.display= "block";
document.getElementById("cknum").innerText = "0";
}
