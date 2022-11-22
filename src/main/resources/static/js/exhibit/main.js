// 위치보기
function selectexzone(pk){
let sendData = {
            "pk" : pk
        };
$.ajax({
    url : "/searchzoneview1",
    data : sendData,
    type : "POST",
    success : function(result){
            let [s1, s2] =  result.center.split(',');
            // 좌표 포지션 생성
            var newPosition = new kakao.maps.LatLng(s1, s2)
            // 이동
            map.setLevel(2, {anchor: newPosition});
            map.setCenter(newPosition);
            for ( var i = 0; i < markers.length; i++ ) {
                    markers[i].setMap(null);
                };

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
                   strokeColor: '#FF3DE5', // 선의 색깔입니다
                   strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
                   strokeStyle: 'shortdashdot', // 선의 스타일입니다
                   fillColor: '#FF8AEF', // 채우기 색깔입니다
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
                strokeColor: '#75B8FA', // 선의 색깔입니다
                strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
                strokeStyle: 'dashed', // 선의 스타일 입니다
                fillColor: '#CFE7FF', // 채우기 색깔입니다
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
        document.getElementById("tt11").readOnly = false;
        document.getElementById("tt11").style.backgroundColor = "#FFFFFF";
    }else{
        document.getElementById("tt11").readOnly = true;
        document.getElementById("tt11").style.backgroundColor = "#8C8C8C";
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

// 엔터키
function enterkey1(){
    if(window.event.keyCode == 13){
        searching1();
    }
}

function test1(){
const tes =  document.getElementsByName("printtype");

var tt = 3;
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
