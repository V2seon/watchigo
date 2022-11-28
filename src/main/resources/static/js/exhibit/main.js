// 지도를 클릭한 위치에 표출할 마커입니다

// 지도에 마커를 표시합니다

var zonepk = 0;
// 위치보기
function selectexzone(pk){
var marker = new kakao.maps.Marker({
});
zonepk = pk;
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
        document.getElementById("typename").style.backgroundColor = "#8C8C8C";
    }

}

// 페이징
function paging(pageValue){
    const myPageQuery = new URLSearchParams(location.search);

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
        $("#table1").replaceWith(fragment);
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
document.getElementById("gpsbox").style.display='block';
document.getElementById("vedbox").style.display='none';
}else if(tt == 1){
document.getElementById("gpsbox").style.display='none';
document.getElementById("vedbox").style.display='block';
}
}

// 전시/시설물 저장
let choice1 = document.getElementById("lock");
function saveexhibit(){
$('#load').show();
console.log(zonepk);
console.log(choice1.options[choice1.selectedIndex].value);
const typename = document.getElementById("typename").value;
console.log(document.getElementById("exhibitname").value);
console.log(document.getElementById("exhibitex").value);
var expoint = document.getElementById("ypoint").value +","+ document.getElementById("xpoint").value;
// 파일여부 확인
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
            "expoint" : expoint
        };
        console.log(sendData);
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
}

// 전시/시설물 위치보기
function selectexhibit(seq){
document.getElementById('menu_wrap1').style.display = "none";
let sendData = {
            "seq" : seq
        };
$.ajax({
    url : "/searchexhibit",
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
    },
    error: function (e) {
    }
    });
}

// 전시/시설물 내용보기
function selectexhibitview(seq){
document.getElementById('menu_wrap1').style.display = "block";
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

            document.getElementById('exhibitex').value = result.ex;
            document.getElementById('exhibitname').value = result.name;
            document.getElementById('ypoint').value = s1;
            document.getElementById('xpoint').value = s2;
            let se = document.getElementById('lock');

            for(let i=0; i<se.options.length; i++){
                if(se.options[i].value == result.type){
                    se.options[i].selected = true;
                }
            }
            lock();
            document.getElementById('typename').value = result.typename;
            document.getElementById('inv1').src = "/file1?fileName="+result.video1;
            document.getElementById('inv2').src = "/file1?fileName="+result.video2;
            document.getElementById('img1').src = "/file1?fileName="+result.img1;
            document.getElementById('img2').src = "/file1?fileName="+result.img2;
            document.getElementById('img3').src = "/file1?fileName="+result.img3;
            document.getElementById('img4').src = "/file1?fileName="+result.img4;
            document.getElementById('img5').src = "/file1?fileName="+result.img5;
            document.getElementById('img6').src = "/file1?fileName="+result.img6;

            if(result.printtype == "0"){
                document.getElementById('gps').checked = true;
            }else if(result.printtype == "1"){
                document.getElementById('ved').checked = true;
            }

            test1();

            var boxlen = document.getElementsByClassName('choicebtn').length;
            for(let a=0; a<boxlen; a++){
                document.getElementsByClassName('choicebtn')[a].checked = false;
            }
            document.getElementById(result.zonepk).checked = true;

    },
    error: function (e) {
    }
    });
}