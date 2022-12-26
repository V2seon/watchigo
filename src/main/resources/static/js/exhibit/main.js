// 지도를 클릭한 위치에 표출할 마커입니다

// 지도에 마커를 표시합니다

var zonepk = 0;
// 위치보기
function selectexzone(pk){
var marker = new kakao.maps.Marker({
});
zonepk = pk;
document.getElementById("pknum").innerText = pk;
var boxlen = document.getElementsByClassName('choicebtn').length;
for(let a=0; a<boxlen; a++){
    document.getElementsByClassName('choicebtn')[a].checked = false;
}
var ttt = pk+'btn';
if(document.getElementById(ttt)){
document.getElementById(ttt).checked = true;
}
for(let a=0; a<boxlen; a++){
 if(document.getElementsByClassName('choicebtn')[a].checked == true){
     document.getElementsByClassName('ckimg')[a].src =  "/img/exinsert/check.png";
 }else if(document.getElementsByClassName('choicebtn')[a].checked == false){
    document.getElementsByClassName('ckimg')[a].src =  "/img/exinsert/cknone.png";
 }
}

var boxlen1 = document.getElementsByClassName('choicebtn1').length;
for(let a=0; a<boxlen1; a++){
    document.getElementsByClassName('choicebtn1')[a].checked = false;
}
var ttt1 = pk+'btn1';
document.getElementById(ttt1).checked = true;
for(let a=0; a<boxlen1; a++){
 if(document.getElementsByClassName('choicebtn1')[a].checked == true){
     document.getElementsByClassName('ckimg1')[a].src =  "/img/exinsert/check.png";
 }else if(document.getElementsByClassName('choicebtn1')[a].checked == false){
    document.getElementsByClassName('ckimg1')[a].src =  "/img/exinsert/cknone.png";
 }
}

let sendData = {
            "pk" : pk
        };
$.ajax({
    url : "/searchzoneview1",
    data : sendData,
    type : "POST",
    success : function(result){
    for ( var i = 0; i < marker.length; i++ ) {
           marker[i].setMap(null);
       };
            let [s1, s2] =  result.center.split(',');
            // 좌표 포지션 생성
            var newPosition = new kakao.maps.LatLng(s1, s2)
            // 이동
            map.setLevel(2, {anchor: newPosition});
            map.setCenter(newPosition);
            swal({
            	    title : "서비스존 내의 전시/시설물의 위치를 클릭해주세요.",
                	icon  : "info",
                	closeOnClickOutside : false
            }).then(function(){
              	kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
                    marker.setMap(map);
                    // 클릭한 위도, 경도 정보를 가져옵니다
                    var latlng = mouseEvent.latLng;

                    // 마커 위치를 클릭한 위치로 옮깁니다
                    marker.setPosition(latlng);

                    document.getElementById("ypoint").value = latlng.getLat();
                    document.getElementById("xpoint").value = latlng.getLng();
                });
            });
            if (result.type == "0"){
                rectangle.setMap(null);
                polygon.setMap(null);
                circle.setMap(null);

               let [sp1, sp2] =  result.sp.split(',');
               let [ep1, ep2] =  result.ep.split(',');

               var sw = new kakao.maps.LatLng(sp1, sp2),
                   ne = new kakao.maps.LatLng(ep1, ep2);

               // 사각형을 구성하는 영역정보를 생성합니다
               // 사각형을 생성할 때 영역정보는 LatLngBounds 객체로 넘겨줘야 합니다
               var rectangleBounds = new kakao.maps.LatLngBounds(sw, ne);

               // 지도에 표시할 사각형을 생성합니다
               rectangle = new kakao.maps.Rectangle({
                   bounds: rectangleBounds, // 그려질 사각형의 영역정보입니다
                   strokeWeight: 4, // 선의 두께입니다
                   strokeColor: '#39DE2A', // 선의 색깔입니다
                   strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
                   strokeStyle: 'longdash', // 선의 스타일입니다
                   fillColor: '#A2FF99', // 채우기 색깔입니다
                   fillOpacity: 0.8 // 채우기 불투명도 입니다
               });

               // 지도에 사각형을 표시합니다
               rectangle.setMap(map);
            }
            else if(result.type == "1"){
            // 지도에 표시할 원을 생성합니다
            rectangle.setMap(null);
            circle.setMap(null);
            polygon.setMap(null);

            let [ce1, ce2] =  result.ce.split(',');

            circle = new kakao.maps.Circle({
                center : new kakao.maps.LatLng(ce1, ce2),  // 원의 중심좌표 입니다
                radius: result.ra, // 미터 단위의 원의 반지름입니다
                strokeWeight: 5, // 선의 두께입니다
                strokeColor: '#39DE2A', // 선의 색깔입니다
                strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
                strokeStyle: 'longdash', // 선의 스타일 입니다
                fillColor: '#A2FF99', // 채우기 색깔입니다
                fillOpacity: 0.7  // 채우기 불투명도 입니다
            });

            // 지도에 원을 표시합니다
            circle.setMap(map);
            }
            else if(result.type == "2"){
                rectangle.setMap(null);
                circle.setMap(null);
                polygon.setMap(null);

                let p1 = result.data.split('&');
                var polygonPath = [];
                for(var i=0; i<(p1.length)-1; i++){
                    let [p, s] = p1[i].split(',');
                    polygonPath[i] = new kakao.maps.LatLng(p,s)
                }

                // 지도에 표시할 다각형을 생성합니다
                polygon = new kakao.maps.Polygon({
                    path:polygonPath, // 그려질 다각형의 좌표 배열입니다
                    strokeWeight: 3, // 선의 두께입니다
                    strokeColor: '#39DE2A', // 선의 색깔입니다
                    strokeOpacity: 0.8, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
                    strokeStyle: 'longdash', // 선의 스타일입니다
                    fillColor: '#A2FF99', // 채우기 색깔입니다
                    fillOpacity: 0.7 // 채우기 불투명도 입니다
                });

                // 지도에 다각형을 표시합니다
                polygon.setMap(map);
            }
    },
    error: function (e) {
    }
    });
}

// 분류 락
function lock(){

    var yourTestSelect = document.getElementById("lock");

// select element에서 선택된 option의 value가 저장됩니다.lock
    var selectedValue = yourTestSelect.options[yourTestSelect.selectedIndex].value;
    if(selectedValue == "기타"){
        document.getElementById("typename").readOnly = false;
        document.getElementById("typename").style.backgroundColor = "#FFFFFF";
    }else{
        document.getElementById("typename").readOnly = true;
        document.getElementById("typename").style.backgroundColor = "#E0E0E0";
    }

}

// 페이징
function paging(pageValue){
    const myPageQuery = new URLSearchParams(location.search);

    console.log(pageValue);

    var titleText = myPageQuery.get('titleText');
    var selectKey = myPageQuery.get('selectKey');

    $("#load").show();

    //문자열 "null" 이 들어가는것 방지하기 위해 값이 null 이라면 공백 문자열 대입
    if(titleText == null){
        titleText = "";
    }

    // 대입 끝

    //url 주소 바꾸기
    const params = {
        page: pageValue,
        selectKey: selectKey,
        titleText: titleText
    }
    const queryString = new URLSearchParams(params).toString();
    const replaceUri = location.pathname + '?' + queryString;
    history.pushState(null, '', replaceUri);
    //url 주소 바꾸기 끝


    var querydata = { "page" : pageValue, "selectKey":selectKey, "titleText":titleText};



    $.ajax({
        url: "/exhibit_search",
        data: querydata,
        type:"POST",
    }).done(function (fragment) {
        $("#see").replaceWith(fragment);
        console.log(fragment);
        $("#load").hide();

    });

    // $.ajax({
    //     url: "/afc/afc01Paging",
    //     data: querydata,
    //     type:"POST",
    // }).done(function (fragment) {
    //     $("#pagination").replaceWith(fragment);
    // });
}

// 검색
function searching1(){

    var titleText = $('#titleText1').val();
    var selectKey = $('#selectKey1').val();

    const params = {
        page: 0,
        selectKey: selectKey,
        titleText: titleText
    }

    const queryString = new URLSearchParams(params).toString();

    const replaceUri = location.pathname + '?' + queryString;

    history.pushState(null, '', replaceUri);

    //값 가져오기 (페이지네이션)
    const myPageQuery = new URLSearchParams(location.search);

    var querydata = { "page" : myPageQuery.get('page'), "selectKey":myPageQuery.get('selectKey'),"titleText":myPageQuery.get('titleText')};

    $.ajax({
        url: "/exhibit_search",
        data: querydata,
        type:"POST",
    }).done(function (fragment) {
        $("#see").replaceWith(fragment);
        console.log(fragment);
    });

}

function searching2(){

    var titleText = $('#titleText').val();
    var selectKey = $('#selectKey').val();

    const params = {
        page: 0,
        selectKey: selectKey,
        titleText: titleText
    }

    const queryString = new URLSearchParams(params).toString();

    const replaceUri = location.pathname + '?' + queryString;

    history.pushState(null, '', replaceUri);

    //값 가져오기 (페이지네이션)
    const myPageQuery = new URLSearchParams(location.search);

    var querydata = { "page" : myPageQuery.get('page'), "selectKey":myPageQuery.get('selectKey'),"titleText":myPageQuery.get('titleText')};

    $.ajax({
        url: "/exhibit_search1",
        data: querydata,
        type:"POST",
    }).done(function (fragment) {
        $("#viewzonebox1").replaceWith(fragment);
        console.log(fragment);
    });

}

// 엔터키
function enterkey2(){
    if(window.event.keyCode == 13){
        searching2();
    }
}
// 엔터키
function enterkey1(){
    if(window.event.keyCode == 13){
        searching1();
    }
}


function searching(){

    var titleText = $('#titleText').val();
    var selectKey = $('#selectKey').val();

    const params = {
        page: 0,
        selectKey: selectKey,
        titleText: titleText
    }

    const queryString = new URLSearchParams(params).toString();

    const replaceUri = location.pathname + '?' + queryString;

    history.pushState(null, '', replaceUri);

    //값 가져오기 (페이지네이션)
    const myPageQuery = new URLSearchParams(location.search);

    var querydata = { "page" : myPageQuery.get('page'), "selectKey":myPageQuery.get('selectKey'),"titleText":myPageQuery.get('titleText')};

    $.ajax({
        url: "/exhibit_search",
        data: querydata,
        type:"POST",
    }).done(function (fragment) {
        $("#see").replaceWith(fragment);
        console.log(fragment);
    });

}

function enterkey(){
    if(window.event.keyCode == 13){
        searching();
    }
}

// 정보 표현 방식
var tt = 0;
function test1(){
const tes =  document.getElementsByName("printtype");

for(var i=0; i<tes.length; i++){
    if(tes[i].checked){
    tt = tes[i].value;
    }
}
if(tt == 0){
document.getElementById("ptckbox").style.borderRadius = "8px 8px 0px 0px";
document.getElementById("ptckbox").style.backgroundColor = "#FFFFFF";
document.getElementById("ptckbox1").style.backgroundColor = "transparent";
document.getElementById("ptckbox").style.color = "#1474E5";
document.getElementById("ptckbox1").style.color = "#FFFFFF";

document.getElementById("gpssebox").style.display='block';
document.getElementById("vedsebox").style.display='none';
}else if(tt == 1){
document.getElementById("ptckbox").style.borderRadius = "8px 8px 0px 0px";
document.getElementById("ptckbox").style.backgroundColor = "transparent";
document.getElementById("ptckbox1").style.backgroundColor = "#FFFFFF";
document.getElementById("ptckbox1").style.color = "#1474E5";
document.getElementById("ptckbox").style.color = "#FFFFFF";

document.getElementById("gpssebox").style.display='none';
document.getElementById("vedsebox").style.display='block';
}
}

var mainicon = "";
var armarker = "";

// 전시/시설물 저장
let choice1 = document.getElementById("lock");
function saveexhibit(){
mainicon = document.getElementById("mainicon").src;
armarker = document.getElementById("armarker").src;

const typename = document.getElementById("typename").value;
var expoint = document.getElementById("ypoint").value +","+ document.getElementById("xpoint").value;
// 파일여부 확인
console.log(expoint)
if(zonepk == 0){
    swal({
          text: "전시/시설물을 등록할 서비스존을 선택해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(choice1.options[choice1.selectedIndex].value == "전체"){
    swal({
          text: "전시/시설물을 분류를 선택해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(document.getElementById("exhibitname").value == null || document.getElementById("exhibitname").value == ""){
    swal({
          text: "전시/시설물을 이름을 입력해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(document.getElementById("exhibitex").value == null || document.getElementById("exhibitex").value == ""){
    swal({
          text: "전시/시설물을 설명을 입력해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(realUploadvideo1.files[0] == null && realUploadvideo2.files[0] == null){
    swal({
          text: "동영상을 등록해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(realUpload1.files[0] == null && realUpload2.files[0] == null && realUpload3.files[0] == null && realUpload4.files[0] == null && realUpload5.files[0] == null && realUpload6.files[0] == null){
    swal({
          text: "이미지를 등록해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(mainicon == null || mainicon == ""){
    swal({
          text: "대표 아이콘을 선택해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(armarker == null || armarker == ""){
    swal({
          text: "AR 마커를 선택해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(expoint == ","){
    swal({
          text: "전시/시설물 위치를 선택해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else{
swal({
	    title : "저장하시겠습니까?",
    	icon  : "info",
    	closeOnClickOutside : false
}).then(function(){
$('#load').show();
var videotype1 = ""; var videotype2 = ""; var videotype3 = "";
var imgtype1 = ""; var imgtype2 = ""; var imgtype3 = "";
var imgtype4 = ""; var imgtype5 = ""; var imgtype6 = "";

if(realUploadvideo1.files[0] != null ){
videotype1 = realUploadvideo1.files[0].name}
if(realUploadvideo2.files[0] != null ){
videotype2 = realUploadvideo2.files[0].name}
if(realUploadvideo3.files[0] != null ){
videotype3 = realUploadvideo3.files[0].name}
if(realUpload1.files[0] != null){
imgtype1 = realUpload1.files[0].name}
if(realUpload2.files[0] != null){
imgtype2 = realUpload2.files[0].name}
if(realUpload3.files[0] != null){
imgtype3 = realUpload3.files[0].name}
if(realUpload4.files[0] != null){
imgtype4 = realUpload4.files[0].name}
if(realUpload5.files[0] != null){
imgtype5 = realUpload5.files[0].name}
if(realUpload6.files[0] != null){
imgtype6 = realUpload6.files[0].name}
let sendData = {
            "inv1" : videotype1,
            "inv2" : videotype2,
            "inv3" : videotype3,
            "ini1" : imgtype1,
            "ini2" : imgtype2,
            "ini3" : imgtype3,
            "ini4" : imgtype4,
            "ini5" : imgtype5,
            "ini6" : imgtype6,
            "printtype" : tt,
            "typename" : typename,
            "zonepk" : zonepk,
            "extype" : choice1.options[choice1.selectedIndex].value,
            "exhibitname" : document.getElementById("exhibitname").value,
            "exhibitex" : document.getElementById("exhibitex").value,
            "expoint" : expoint,
            "mainicon" : mainicon,
            "armarker" : armarker
        };
$.ajax({
    url : "/saveexhibit",
    data : sendData,
    type : "POST",
    success : function(result){
        var formData = new FormData();
        if(realUploadvideo1.files[0] != null){
            formData.append('files', realUploadvideo1.files[0]);
        }
        if(realUploadvideo2.files[0] != null){
            formData.append('files', realUploadvideo2.files[0]);
        }
        if(realUploadvideo3.files[0] != null){
            formData.append('files', realUploadvideo3.files[0]);
        }
        if(realUpload1.files[0] != null){
            formData.append('files', realUpload1.files[0]);
        }
        if(realUpload2.files[0] != null){
            formData.append('files', realUpload2.files[0]);
        }
        if(realUpload3.files[0] != null){
            formData.append('files', realUpload3.files[0]);
        }
        if(realUpload4.files[0] != null){
            formData.append('files', realUpload4.files[0]);
        }
        if(realUpload5.files[0] != null){
            formData.append('files', realUpload5.files[0]);
        }
        if(realUpload6.files[0] != null){
            formData.append('files', realUpload6.files[0]);
        }
        $.ajax({
                type: "POST",
                enctype: 'multipart/form-data',
                url: "/upload1",
                data: formData,
                processData: false,
                contentType: false,
                cache: false,
                success: function (data) {
                    $('#load').hide();
                   location.href = "/exhibit";
                },
                error: function (e) {

                    swal({
                             text: "사진 업로드 실패",
                             icon: "warning" //"info,success,warning,error" 중 택1
                         });
                }
            });

    },
    error:function(request,status,error){
    }
});
});
}
}

// 전시/시설물 위치보기
function selectexhibit(seq){


}

// 전시/시설물 내용보기
function selectexhibitview(seq){
document.getElementById('menu_wrap1').style.display = "block";
document.getElementById('menu_wrap2').style.display = "none";

let sendData = {
            "seq" : seq
        };
$.ajax({
    url : "/searchexhibitview",
    data : sendData,
    type : "POST",
    success : function(result){
            rectangle.setMap(null);
            polygon.setMap(null);
            circle.setMap(null);
            let [s1, s2] =  result.center.split(',');
            // 좌표 포지션 생성
            var newPosition = new kakao.maps.LatLng(s1, s2)
            // 이동
            map.setLevel(2, {anchor: newPosition});
            map.setCenter(newPosition);

//            document.getElementById('exhibitex').value = result.ex;
//            document.getElementById('exhibitname').value = result.name;
//            document.getElementById('ypoint').value = s1;
//            document.getElementById('xpoint').value = s2;
//            let se = document.getElementById('lock');
//
//            for(let i=0; i<se.options.length; i++){
//                if(se.options[i].value == result.type){
//                    se.options[i].selected = true;
//                }
//            }
//            lock();
            document.getElementById('zonetext').innerText = result.zonename;
            document.getElementById('zonetext1').innerText = result.ex;
            document.getElementById('pknum').innerText = result.pk;
            document.getElementById('viewvideo').src = "/file1?fileName="+result.date+"/"+result.video1;
            document.getElementById('viewimg').src = "/file1?fileName="+result.date+"/"+result.img1;
            document.getElementById('marker1').src = result.mainicon;
            document.getElementById('marker2').src = result.armarker;
//            document.getElementById('pknum').innerText = result.pk;
//            console.log(document.getElementById('pknum').innerText);
//            document.getElementById('marker1').src = result.marker;
            document.getElementById('zoombtn1').style.display = "none";
            document.getElementById('zoombtn2').style.display = "none";
            document.getElementById('zoombtn3').style.display = "block";
            document.getElementById('zoombtn4').style.display = "block";

//            if(result.printtype == "0"){
//                document.getElementById('gps').checked = true;
//            }else if(result.printtype == "1"){
//                document.getElementById('ved').checked = true;
//            }
//
//            test1();
//
//            var boxlen = document.getElementsByClassName('choicebtn').length;
//            for(let a=0; a<boxlen; a++){
//                document.getElementsByClassName('choicebtn')[a].checked = false;
//            }

//            document.getElementById(result.zonepk).checked = true;

//            document.getElementById('mainicon').src = result.mainicon;
//            document.getElementById('armarker').src = result.armarker;
//
//            document.getElementById('pknum').innerText = result.zonepk;
//            document.getElementById('seqnum').innerText = result.seq;
    },
    error: function (e) {
    }
    });
}

// 전시/시설물 삭제
function deleteexhibit(){
swal({
  title: "전시/시설물 삭제",
  text: "해당 전시/시설물을 삭제하시겠습니까?",
  icon: "warning",
  closeOnClickOutside : false,
  buttons : ["취소", "삭제"],
  dangerMode: true
}).then((result) => {
  if (result) {
        let sendData = {
            "pk" : document.getElementById('seqnum').innerText
        };
        $.ajax({
            url : "/deleteexhibit",
            data : sendData,
            type : "POST",
            success : function(result){
                swal({
                      title : "삭제되었습니다.",
                      closeOnClickOutside : false,
                      icon: "success",
                    }).then(function(){
                        location.href = "/exhibit";
                    });
            },
            error: function (e) {
            }
        });
  }
});
}

// 전시/시설물 수정
function editexhibit(){
mainicon = document.getElementById("mainicon").src;
armarker = document.getElementById("armarker").src;

zonepk = document.getElementById("pknum").innerText;
const seq = document.getElementById("seqnum").innerText;
const typename = document.getElementById("typename").value;
var expoint = document.getElementById("ypoint").value +","+ document.getElementById("xpoint").value;

const inv1 = document.getElementById('inv1');
const inv2 = document.getElementById('inv2');
const inv3 = document.getElementById('inv3');
const img1 = document.getElementById('img1');
const img2 = document.getElementById('img2');
const img3 = document.getElementById('img3');
const img4 = document.getElementById('img4');
const img5 = document.getElementById('img5');
const img6 = document.getElementById('img6');
var srcbox = [inv1.src ,inv2.src,inv3.src, img1.src,img2.src, img3.src, img4.src, img5.src, img6.src];
for(var i=0; i<srcbox.length; i++){
var [ee,rr,dd,ff,cc] = srcbox[i].split('/');
if(cc != null){
    console.log(cc);
    if(cc == "srinsert"){
        srcbox[i] = "";
    }else{
        srcbox[i] = cc;
    }
}else{
srcbox[i] = "";
}}

if(zonepk == 0){
    swal({
          text: "전시/시설물을 등록할 서비스존을 선택해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(choice1.options[choice1.selectedIndex].value == "전체"){
    swal({
          text: "전시/시설물을 분류를 선택해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(document.getElementById("exhibitname").value == null || document.getElementById("exhibitname").value == ""){
    swal({
          text: "전시/시설물을 이름을 입력해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(document.getElementById("exhibitex").value == null || document.getElementById("exhibitex").value == ""){
    swal({
          text: "전시/시설물을 설명을 입력해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(mainicon == null || mainicon == ""){
    swal({
          text: "대표 아이콘을 선택해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(armarker == null || armarker == ""){
    swal({
          text: "AR 마커를 선택해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(expoint == ","){
    swal({
          text: "전시/시설물 위치를 선택해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else{
swal({
  title: "전시/시설물 수정",
  text: "해당 전시/시설물을 수정하시겠습니까?",
  icon: "warning",
  closeOnClickOutside : false,
  buttons : ["취소", "수정"],
}).then((result) =>{
$('#load').show();

// 파일여부 확인
var videotype1 = ""; var videotype2 = ""; var videotype3 = "";
var imgtype1 = ""; var imgtype2 = ""; var imgtype3 = "";
var imgtype4 = ""; var imgtype5 = ""; var imgtype6 = "";

if(realUploadvideo1.files[0] != null ){
videotype1 = realUploadvideo1.files[0].name
}else{videotype1 = srcbox[0]}
if(realUploadvideo2.files[0] != null ){
videotype2 = realUploadvideo2.files[0].name
}else{videotype2 = srcbox[1]}
if(realUploadvideo3.files[0] != null ){
videotype3 = realUploadvideo3.files[0].name
}else{videotype3 = srcbox[2]}
if(realUpload1.files[0] != null){
imgtype1 = realUpload1.files[0].name
}else{imgtype1 = srcbox[3]}
if(realUpload2.files[0] != null){
imgtype2 = realUpload2.files[0].name
}else{imgtype2 = srcbox[4]}
if(realUpload3.files[0] != null){
imgtype3 = realUpload3.files[0].name
}else{imgtype3 = srcbox[5]}
if(realUpload4.files[0] != null){
imgtype4 = realUpload4.files[0].name
}else{imgtype4 = srcbox[6]}
if(realUpload5.files[0] != null){
imgtype5 = realUpload5.files[0].name
}else{imgtype5 = srcbox[7]}
if(realUpload6.files[0] != null){
imgtype6 = realUpload6.files[0].name
}else{imgtype6 = srcbox[8]}
let sendData = {
            "inv1" : videotype1,
            "inv2" : videotype2,
            "inv3" : videotype3,
            "ini1" : imgtype1,
            "ini2" : imgtype2,
            "ini3" : imgtype3,
            "ini4" : imgtype4,
            "ini5" : imgtype5,
            "ini6" : imgtype6,
            "printtype" : tt,
            "typename" : typename,
            "zonepk" : zonepk,
            "extype" : choice1.options[choice1.selectedIndex].value,
            "exhibitname" : document.getElementById("exhibitname").value,
            "exhibitex" : document.getElementById("exhibitex").value,
            "expoint" : expoint,
            "seqnum" : seq,
            "mainicon" : mainicon,
            "armarker" : armarker
        };
$.ajax({
    url : "/editexhibit",
    data : sendData,
    type : "POST",
    success : function(result){
        var formData = new FormData();
        if(realUploadvideo1.files[0] != null){
            formData.append('files', realUploadvideo1.files[0]);
        }
        if(realUploadvideo2.files[0] != null){
            formData.append('files', realUploadvideo2.files[0]);
        }
        if(realUploadvideo3.files[0] != null){
            formData.append('files', realUploadvideo3.files[0]);
        }
        if(realUpload1.files[0] != null){
            formData.append('files', realUpload1.files[0]);
        }
        if(realUpload2.files[0] != null){
            formData.append('files', realUpload2.files[0]);
        }
        if(realUpload3.files[0] != null){
            formData.append('files', realUpload3.files[0]);
        }
        if(realUpload4.files[0] != null){
            formData.append('files', realUpload4.files[0]);
        }
        if(realUpload5.files[0] != null){
            formData.append('files', realUpload5.files[0]);
        }
        if(realUpload6.files[0] != null){
            formData.append('files', realUpload6.files[0]);
        }
        if(realUploadvideo1.files[0] == null && realUploadvideo2.files[0] == null && realUpload1.files[0] == null && realUpload2.files[0] == null && realUpload3.files[0] == null && realUpload4.files[0] == null && realUpload5.files[0] == null && realUpload6.files[0] == null){
            location.href = "/exhibit";
        }else{
            $.ajax({
                    type: "POST",
                    enctype: 'multipart/form-data',
                    url: "/upload1",
                    data: formData,
                    processData: false,
                    contentType: false,
                    cache: false,
                    success: function (data) {
                        $('#load').hide();
                       location.href = "/exhibit";
                    },
                    error: function (e) {
                        swal({
                                 text: "사진 업로드 실패",
                                 icon: "warning" //"info,success,warning,error" 중 택1
                             });
                    }
                });
        }


    },
    error:function(request,status,error){
    }
});
});
}
}

// 전시/시설물이 등록된 서비스존 리스트 보기
function search(pk){
    const myPageQuery = new URLSearchParams(location.search);

    var titleText = myPageQuery.get('titleText');
    var selectKey = myPageQuery.get('selectKey');

    $("#load").show();

    //문자열 "null" 이 들어가는것 방지하기 위해 값이 null 이라면 공백 문자열 대입
    if(titleText == null){
        titleText = "";
    }

    // 대입 끝
    var pageValue = 0;
    //url 주소 바꾸기
    const params = {
        page: pageValue,
        selectKey: selectKey,
        titleText: titleText
    }
    const queryString = new URLSearchParams(params).toString();
    const replaceUri = location.pathname + '?' + queryString;
    history.pushState(null, '', replaceUri);
    //url 주소 바꾸기 끝

    var querydata = { "page" : pageValue, "selectKey":selectKey, "titleText":titleText, "pk":pk};

    $.ajax({
        url: "/exhibit_search2",
        data: querydata,
        type:"POST",
    }).done(function (fragment) {
        $("#bb1").replaceWith(fragment);
        console.log(fragment);
        $("#load").hide();

    });

    // $.ajax({
    //     url: "/afc/afc01Paging",
    //     data: querydata,
    //     type:"POST",
    // }).done(function (fragment) {
    //     $("#pagination").replaceWith(fragment);
    // });
}

//수정페이지 이동
function editgo(){
var pk = document.getElementById('pknum').innerText;
let sendData = {
            "pk" : pk
}
$.ajax({
            url      : "/exeditgo",
            data     : sendData,
            type     : "GET",
            success : function(result) {
                location.href= "/exeditgo1";
            },
            error:function(request,status,error){
                swal({
                    text: "이동중 서버에 문제가 발생했습니다.",
                    icon: "warning" //"info,success,warning,error" 중 택1
                });
            }
        });
}