var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

// 지도를 생성합니다
//마커 클러스터러 사용하기
var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
  center : new kakao.maps.LatLng(36.566826, 127.9786567), // 지도의 중심좌표
  level : 12 // 지도의 확대 레벨
});

var rectangle = new kakao.maps.Rectangle({
});
var circle = new kakao.maps.Circle({
});
var polygon = new kakao.maps.Polygon({
});

// 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places();

// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({zIndex:1});

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

// 주소로 좌표를 검색합니다
function searchPlaces() {

// 마커 배열 초기화
for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }

// 주소검색
var keyword = document.getElementById('keyword').value;

    if (keyword == "" || keyword == null) {
        alert('주소를 입력해주세요!');
    }else{
    geocoder.addressSearch(keyword, function(result, status) {

        // 정상적으로 검색이 완료됐으면
         if (status === kakao.maps.services.Status.OK) {

            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

            // 결과값으로 받은 위치를 마커로 표시합니다
                marker = new kakao.maps.Marker({
                map: map,
                position: coords
            });

            // 마커가 지도 위에 표시되도록 설정합니다
            marker.setMap(map);

            // 생성된 마커를 배열에 추가합니다
            markers.push(marker);

            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            map.setCenter(coords);
        }else{
            alert('주소가 올바르지 않습니다 정확한 주소를 입력해주세요.');
        }
    });
}
}

// 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places();

// 키워드로 장소를 검색합니다
function sss(){
var keyword = document.getElementById('keyword').value;
ps.keywordSearch(keyword, placesSearchCB);
}

// 키워드 검색 완료 시 호출되는 콜백함수 입니다
function placesSearchCB (data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        var bounds = new kakao.maps.LatLngBounds();

        for (var i=0; i<data.length; i++) {
            bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
        }

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
        map.setBounds(bounds);
    }
}

// 내위치이동
function currentLocation() {
	// HTML5의 geolocation으로 사용할 수 있는지 확인합니다
	if (navigator.geolocation) {

		// GeoLocation을 이용해서 접속 위치를 얻어옵니다
		navigator.geolocation.getCurrentPosition(function(position) {

			var lat = position.coords.latitude, // 위도
			    lon = position.coords.longitude; // 경도

			var locPosition = new kakao.maps.LatLng(lat, lon); // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
			var message = '<div style="padding:5px;">현위치</div>'; // 인포윈도우에 표시될 내용입니다

			// 화면이동 표시합니다
			map.setCenter(locPosition);
		});
	} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다

		var locPosition = new kakao.maps.LatLng(37.4812845080678, 126.952713197762),
			message = '현재 위치를 알 수 없어 기본 위치로 이동합니다.'

		currentLatLon['lat'] = 33.450701
		currentLatLon['lon'] = 126.570667

		map.setCenter(locPosition);
	}
	return true
}

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
    for ( var i = 0; i < markers.length; i++ ) {
        markers[i].setMap(null);
    }
    markers = [];
}

// 지도 확대, 축소 컨트롤에서 확대 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
function zoomIn() {
    map.setLevel(map.getLevel() - 1);
}

// 지도 확대, 축소 컨트롤에서 축소 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
function zoomOut() {
    map.setLevel(map.getLevel() + 1);
}

var options = { // Drawing Manager를 생성할 때 사용할 옵션입니다
    map: map, // Drawing Manager로 그리기 요소를 그릴 map 객체입니다
    drawingMode: [ // Drawing Manager로 제공할 그리기 요소 모드입니다
        kakao.maps.Drawing.OverlayType.RECTANGLE,
        kakao.maps.Drawing.OverlayType.CIRCLE,
        kakao.maps.Drawing.OverlayType.POLYGON
    ],
    // 사용자에게 제공할 그리기 가이드 툴팁입니다
    // 사용자에게 도형을 그릴때, 드래그할때, 수정할때 가이드 툴팁을 표시하도록 설정합니다
    guideTooltip: ['draw', 'drag', 'edit'],
    rectangleOptions: {
        draggable: true,
        removable: true,
        editable: true,
        strokeColor: '#39f', // 외곽선 색
        fillColor: '#39f', // 채우기 색
        fillOpacity: 0.5 // 채우기색 투명도
    },
    circleOptions: {
        draggable: true,
        removable: true,
        editable: true,
        strokeColor: '#39f',
        fillColor: '#39f',
        fillOpacity: 0.5
    },
    polygonOptions: {
        draggable: true,
        removable: true,
        editable: true,
        strokeColor: '#39f',
        fillColor: '#39f',
        fillOpacity: 0.5,
        hintStrokeStyle: 'dash',
        hintStrokeOpacity: 0.5
    }
};

// 위에 작성한 옵션으로 Drawing Manager를 생성합니다
var manager = new kakao.maps.Drawing.DrawingManager(options);

function removeOverlays(overlays) {

	console.log("====>" +  overlays.length);
    var len = overlays.length, i = 0;

    for (; i < len; i++) {
        overlays[i].setMap(null);
    }

    overlays = [];
}

// undo, redo 버튼의 disabled 속성을 설정하기 위해 엘리먼트를 변수에 설정합니다
var undoBtn = document.getElementById('undo');
var redoBtn = document.getElementById('redo');

// Drawing Manager 객체에 state_changed 이벤트를 등록합니다
// state_changed 이벤트는 그리기 요소의 생성/수정/이동/삭제 동작
// 또는 Drawing Manager의 undo, redo 메소드가 실행됐을 때 발생합니다
manager.addListener('state_changed', function() {
    serviceszone = "";
	var data = manager.getData();
        //data = {marker: Array(0), polyline: Array(0), rectangle: Array(0), circle: Array(0), polygon: Array(0)}

        if(data.rectangle.length > 0){
        	printRectangle(data[kakao.maps.drawing.OverlayType.RECTANGLE]);
        }
        else if(data.circle.length > 0){
        	printCircle(data[kakao.maps.drawing.OverlayType.CIRCLE]);
        }
        else if(data.polygon.length > 0){
        	printPolygon(data[kakao.maps.drawing.OverlayType.POLYGON]);
        }
        var obj = manager.getOverlays();

});
var ckexhibit = "";
var ckexhibit1 = "";
var serviceszone = "";
// Drawing Manager에서 가져온 데이터 중 사각형을 아래 지도에 표시하는 함수입니다
function printRectangle(rects) {
    var len = rects.length, i = 0;
	var message = "사각형";
	console.log(message + ":" + JSON.stringify(rects));
    console.log("시작점: " + JSON.stringify(rects[0].sPoint.y) + "|" + JSON.stringify(rects[0].sPoint.x));
    console.log("끝난점: " + JSON.stringify(rects[0].ePoint.y) + "|" + JSON.stringify(rects[0].ePoint.x));
    serviceszone = JSON.stringify(rects[0].sPoint.y) + "," + JSON.stringify(rects[0].sPoint.x) + "&" + JSON.stringify(rects[0].ePoint.y) + "," + JSON.stringify(rects[0].ePoint.x);
    checkexhibit(message);
    ckexhibit = rects[0].sPoint;
    ckexhibit1 = rects[0].ePoint;
    findexrectangle();
}
// Drawing Manager에서 가져온 데이터 중 원을 아래 지도에 표시하는 함수입니다
function printCircle(circles) {
    var len = circles.length, i = 0;
	var message = "원";
	console.log(message + ":" + JSON.stringify(circles));
    console.log("중앙점: " + JSON.stringify(circles[0].center))
    console.log("반지름: " + JSON.stringify(circles[0].radius))
    serviceszone = JSON.stringify(circles[0].center.y)+","+ +JSON.stringify(circles[0].center.x)+"&"+JSON.stringify(circles[0].radius);

    ckexhibit = circles[0].center;
    insidecircle(circles[0].radius);
}
// Drawing Manager에서 가져온 데이터 중 다각형을 아래 지도에 표시하는 함수입니다
function printPolygon(polygons) {
    var len = polygons.length, i = 0;
	var message = "다각형";
	console.log(message + ":" + JSON.stringify(polygons));
	console.log("좌표값 : " + JSON.stringify(polygons[0].points))
	console.log("길이 : " + JSON.stringify(polygons[0].points.length))
	for(let i=0; i<JSON.stringify(polygons[0].points.length); i++){
       serviceszone = serviceszone + JSON.stringify(polygons[0].points[i].y)+"," +JSON.stringify(polygons[0].points[i].x) + "&"
	}
	checkexhibit(message);
    ckexhibit = polygons[0].points;
    console.log(ckexhibit);
    findexplogon();
}

var zonetype = "";
// 버튼 클릭 시 호출되는 핸들러 입니다
function selectOverlay(type) {
    // 그리기 중이면 그리기를 취소합니다
    manager.cancel();
//    clusterer.clear();
    rectangle.setMap(null);
    polygon.setMap(null);
    circle.setMap(null);

    if(type == "RECTANGLE"){
        document.getElementById('reimg').src = "/img/srinsert/RentangleBlue.png";
        document.getElementById('ciimg').src = "/img/srinsert/Circle.png";
        document.getElementById('plimg').src = "/img/srinsert/Polygon.png";
    }else if(type == "CIRCLE"){
        document.getElementById('reimg').src = "/img/srinsert/Rentangle.png";
        document.getElementById('ciimg').src = "/img/srinsert/CircleBlue.png";
        document.getElementById('plimg').src = "/img/srinsert/Polygon.png";
    }else if(type == "POLYGON"){
        document.getElementById('reimg').src = "/img/srinsert/Rentangle.png";
        document.getElementById('ciimg').src = "/img/srinsert/Circle.png";
        document.getElementById('plimg').src = "/img/srinsert/PolygonBlue.png";
    }

    // 모양버튼 클릭시 type 담기
    zonetype = type;
    // 좌표값 초기화
    serviceszone = "";
    // 클릭한 그리기 요소 타입을 선택합니다
    manager.select(kakao.maps.Drawing.OverlayType[type]);
}

const address = document.getElementById('address');
const address1 = document.getElementById('address1');
const zonename = document.getElementById('zonename');
const zoneex = document.getElementById('zoneex');
const deletezonepk = document.getElementById('pknum');
var marker1 = "";

var a = 0;
//저장하기
function savezone(){
marker1 = document.getElementById('marker1').src;
a = 0;
if(serviceszone == null || serviceszone == ""){
    swal({
          text: "서비스존 영역을 설정해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(address.value == null || address.value == ""){
    swal({
          text: "설정영역 주소를 입력해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(address1.value == null || address1.value == ""){
    swal({
          text: "설정영역 상세주소를 입력해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(zonename.value == null || zonename.value == ""){
     swal({
           text: "서비스존 이름을 입력해주세요.",
           icon: "info" //"info,success,warning,error" 중 택1
           });
}else if(zoneex.value == null || zoneex.value == ""){
      swal({
            text: "서비스존 설명을 입력해주세요.",
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
}else if(marker1 == null || marker1 == ""){
     swal({
           text: "마커를 선택해주세요.",
           icon: "info" //"info,success,warning,error" 중 택1
          });
}else{
swal({
	    title : "저장하시겠습니까?",
    	icon  : "info",
    	closeOnClickOutside : false
}).then(function(){
	$('#load').show();
    // 파일여부 확인
    var videotype1 = ""; var videotype2 = ""; var imgtype1 = ""; var imgtype2 = "";
    var imgtype3 = ""; var imgtype4 = ""; var imgtype5 = ""; var imgtype6 = "";
    if(realUploadvideo1.files[0] != null ){
    videotype1 = realUploadvideo1.files[0].name}
    if(realUploadvideo2.files[0] != null ){
    videotype2 = realUploadvideo2.files[0].name}
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
                "address" : address.value,
                "address1" : address1.value,
                "zonename" : zonename.value,
                "zoneex" : zoneex.value,
                "zonetype" : zonetype,
                "serviceszone" : serviceszone,
                "inv1" : videotype1,
                "inv2" : videotype2,
                "ini1" : imgtype1,
                "ini2" : imgtype2,
                "ini3" : imgtype3,
                "ini4" : imgtype4,
                "ini5" : imgtype5,
                "ini6" : imgtype6,
                "a" : a,
                "marker1" : marker1
            };
    $.ajax({
        url : "/savezone",
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
                    url: "/upload",
                    data: formData,
                    processData: false,
                    contentType: false,
                    cache: false,
                    success: function (data) {
                        $('#load').hide();
                       location.href = "/servicezone";
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

//출시요청
function savezone1(){
marker1 = document.getElementById('marker1').src;
a = 1;
if(serviceszone == null || serviceszone == ""){
    swal({
          text: "서비스존 영역을 설정해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(address.value == null || address.value == ""){
    swal({
          text: "설정영역 주소를 입력해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(address1.value == null || address1.value == ""){
    swal({
          text: "설정영역 상세주소를 입력해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(zonename.value == null || zonename.value == ""){
     swal({
           text: "서비스존 이름을 입력해주세요.",
           icon: "info" //"info,success,warning,error" 중 택1
           });
}else if(zoneex.value == null || zoneex.value == ""){
      swal({
            text: "서비스존 설명을 입력해주세요.",
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
}else if(marker1 == null || marker1 == ""){
      swal({
            text: "마커를 선택해주세요.",
            icon: "info" //"info,success,warning,error" 중 택1
           });
}else{
swal({
	    title : "출시요청을 하시겠습니까?",
    	icon  : "info",
    	closeOnClickOutside : false
}).then(function(){
	$('#load').show();
    // 파일여부 확인
    var videotype1 = ""; var videotype2 = ""; var imgtype1 = ""; var imgtype2 = "";
    var imgtype3 = ""; var imgtype4 = ""; var imgtype5 = ""; var imgtype6 = "";
    if(realUploadvideo1.files[0] != null ){
    videotype1 = realUploadvideo1.files[0].name}
    if(realUploadvideo2.files[0] != null ){
    videotype2 = realUploadvideo2.files[0].name}
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
                "address" : address.value,
                "address1" : address1.value,
                "zonename" : zonename.value,
                "zoneex" : zoneex.value,
                "zonetype" : zonetype,
                "serviceszone" : serviceszone,
                "inv1" : videotype1,
                "inv2" : videotype2,
                "ini1" : imgtype1,
                "ini2" : imgtype2,
                "ini3" : imgtype3,
                "ini4" : imgtype4,
                "ini5" : imgtype5,
                "ini6" : imgtype6,
                "a" : a,
                "marker1" : marker1
            };
    $.ajax({
        url : "/savezone",
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
                    url: "/upload",
                    data: formData,
                    processData: false,
                    contentType: false,
                    cache: false,
                    success: function (data) {
                        $('#load').hide();
                       location.href = "/servicezone";
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

//위치보기
function selectzone(pk){
document.getElementById('menu_wrap1').style.display = "none";
let sendData = {
            "pk" : pk
        };
$.ajax({
    url : "/searchzone",
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

//내용보기
function selectzoneview(pk){
document.getElementById('menu_wrap1').style.display = "block";
let sendData = {
            "pk" : pk
        };
$.ajax({
    url : "/searchzoneview",
    data : sendData,
    type : "POST",
    success : function(result){
            let [s1, s2] =  result.center.split(',');
            // 좌표 포지션 생성
            var newPosition = new kakao.maps.LatLng(s1, s2)
            // 이동
            map.setLevel(2, {anchor: newPosition});
            map.setCenter(newPosition);
//            for ( var i = 0; i < markers.length; i++ ) {
//                    markers[i].setMap(null);
//                };

            if (result.type == "0"){
                serviceszone = result.sp +"&"+ result.ep ;
                console.log(serviceszone);
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
            serviceszone = result.ce +"&"+ result.ra ;
            console.log(serviceszone);

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
                serviceszone = result.data;
                console.log(serviceszone);

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
            document.getElementById('zonetext').innerText = result.zonename;
            document.getElementById('zonetext1').innerText = result.ex;
            document.getElementById('viewvideo').src = "/file?fileName="+result.date+"/"+result.video1;
            document.getElementById('viewimg').src = "/file?fileName="+result.date+"/"+result.img1;
            document.getElementById('pknum').innerText = result.pk;
//            console.log(document.getElementById('pknum').innerText);
//            document.getElementById('marker1').src = result.marker;
            document.getElementById('zoombtn1').style.display = "none";
            document.getElementById('zoombtn2').style.display = "none";
            document.getElementById('zoombtn3').style.display = "block";
            document.getElementById('zoombtn4').style.display = "block";


            if(result.zonetype == "0"){
                zonetype = "RECTANGLE"
            }else if(result.zonetype == "1"){
                zonetype = "CIRCLE"
            }else if(result.zonetype == "2"){
                zonetype = "POLYGON"
            }
    },
    error: function (e) {
    }
    });
}

//서비스존 삭제
function deletezone(){
swal({
  title: "서비스존 삭제",
  text: "삭제시 해당 서비스존 전시/시설물도 삭제됩니다.",
  icon: "warning",
  closeOnClickOutside : false,
  buttons : ["취소", "삭제"],
  dangerMode: true
}).then((result) => {
  if (result) {
        let sendData = {
            "pk" : document.getElementById('pknum').innerText
        };
        $.ajax({
            url : "/deletezone",
            data : sendData,
            type : "POST",
            success : function(result){
                swal({
                      title : "삭제되었습니다.",
                      closeOnClickOutside : false,
                      icon: "success",
                    }).then(function(){
                        location.href = "/servicezone";
                    });
            },
            error: function (e) {
            }
        });
  }
});

}

//수정페이지 이동
function editgo(){
var pk = document.getElementById('pknum').innerText;
console.log("여기");
let sendData = {
            "pk" : pk
}
$.ajax({
            url      : "/editgo",
            data     : sendData,
            type     : "GET",
            success : function(result) {
                location.href= "/editgo1";
            },
            error:function(request,status,error){
                swal({
                    text: "이동중 서버에 문제가 발생했습니다.",
                    icon: "warning" //"info,success,warning,error" 중 택1
                });
            }
        });
}

//서비스존 수정
function editzone(){
var type = document.getElementById('type').innerText;
if(type == "0"){zonetype = "RECTANGLE";}else if(type == "1"){zonetype = "CIRCLE";}else if(type == "2"){zonetype = "POLYGON";}
marker1 = document.getElementById('marker1').src;
const pkzonenum = document.getElementById('pknum').innerText;
const viewaddress = document.getElementById('address');
const viewaddress1 = document.getElementById('address1');
const viewzonename = document.getElementById('zonename');
const viewzoneex = document.getElementById('zoneex');
const inv1 = document.getElementById('inv1');
const inv2 = document.getElementById('inv2');
const img1 = document.getElementById('img1');
const img2 = document.getElementById('img2');
const img3 = document.getElementById('img3');
const img4 = document.getElementById('img4');
const img5 = document.getElementById('img5');
const img6 = document.getElementById('img6');

var srcbox = [inv1.src ,inv2.src, img1.src,img2.src, img3.src, img4.src, img5.src, img6.src];
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
console.log(srcbox);
if(serviceszone == null || serviceszone == ""){
    swal({
          text: "서비스존 영역을 설정해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(viewaddress.value == null || viewaddress.value == ""){
    swal({
          text: "설정영역 주소를 입력해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(viewaddress1.value == null || viewaddress1.value == ""){
    swal({
          text: "설정영역 상세주소를 입력해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(viewzonename.value == null || viewzonename.value == ""){
    swal({
          text: "서비스존 이름을 입력해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(viewzoneex.value == null || viewzoneex.value == ""){
    swal({
          text: "서비스존 설명을 입력해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(marker1 == null || marker1 == ""){
    swal({
          text: "마커를 선택해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else{
swal({
  title: "서비스존 수정",
  text: "해당 서비스존을 수정하시겠습니까?",
  icon: "info",
  closeOnClickOutside : false,
  buttons : ["취소", "수정"],
}).then((result) => {
  if (result) {
	$('#load').show();
    // 파일여부 확인
    var videotype1 = ""; var videotype2 = ""; var imgtype1 = ""; var imgtype2 = "";
    var imgtype3 = ""; var imgtype4 = ""; var imgtype5 = ""; var imgtype6 = "";
    if(realUploadvideo1.files[0] != null ){
    videotype1 = realUploadvideo1.files[0].name
    }else{videotype1 = srcbox[0]}
    if(realUploadvideo2.files[0] != null ){
    videotype2 = realUploadvideo2.files[0].name
    }else{videotype2 = srcbox[1]}
    if(realUpload1.files[0] != null){
    imgtype1 = realUpload1.files[0].name
    }else{imgtype1 = srcbox[2]}
    if(realUpload2.files[0] != null){
    imgtype2 = realUpload2.files[0].name
    }else{imgtype2 = srcbox[3]}
    if(realUpload3.files[0] != null){
    imgtype3 = realUpload3.files[0].name
    }else{imgtype3 = srcbox[4]}
    if(realUpload4.files[0] != null){
    imgtype4 = realUpload4.files[0].name
    }else{imgtype4 = srcbox[5]}
    if(realUpload5.files[0] != null){
    imgtype5 = realUpload5.files[0].name
    }else{imgtype5 = srcbox[6]}
    if(realUpload6.files[0] != null){
    imgtype6 = realUpload6.files[0].name
    }else{imgtype6 = srcbox[7]}
    const a = 0;
    let sendData = {
                "address" : viewaddress.value,
                "address1" : viewaddress1.value,
                "zonename" : viewzonename.value,
                "zoneex" : viewzoneex.value,
                "zonetype" : zonetype,
                "serviceszone" : serviceszone,
                "inv1" : videotype1,
                "inv2" : videotype2,
                "ini1" : imgtype1,
                "ini2" : imgtype2,
                "ini3" : imgtype3,
                "ini4" : imgtype4,
                "ini5" : imgtype5,
                "ini6" : imgtype6,
                "a" : a,
                "pkzonenum": pkzonenum,
                "marker1" : marker1
            };
    $.ajax({
        url : "/editzone",
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
                   location.href = "/servicezone";
            }else{
                $.ajax({
                        type: "POST",
                        enctype: 'multipart/form-data',
                        url: "/upload",
                        data: formData,
                        processData: false,
                        contentType: false,
                        cache: false,
                        success: function (data) {
                            $('#load').hide();
                           location.href = "/servicezone";
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

  }
});
}
}

//서비스존 수정 출시
function editzone1(){
var type = document.getElementById('type').innerText;
if(type == "0"){zonetype = "RECTANGLE";}else if(type == "1"){zonetype = "CIRCLE";}else if(type == "2"){zonetype = "POLYGON";}
marker1 = document.getElementById('marker1').src;
const pkzonenum = document.getElementById('pknum').innerText;
const viewaddress = document.getElementById('address');
const viewaddress1 = document.getElementById('address1');
const viewzonename = document.getElementById('zonename');
const viewzoneex = document.getElementById('zoneex');
const inv1 = document.getElementById('inv1');
const inv2 = document.getElementById('inv2');
const img1 = document.getElementById('img1');
const img2 = document.getElementById('img2');
const img3 = document.getElementById('img3');
const img4 = document.getElementById('img4');
const img5 = document.getElementById('img5');
const img6 = document.getElementById('img6');

var srcbox = [inv1.src ,inv2.src, img1.src,img2.src, img3.src, img4.src, img5.src, img6.src];
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
console.log(srcbox);
if(serviceszone == null || serviceszone == ""){
    swal({
          text: "서비스존 영역을 설정해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(viewaddress.value == null || viewaddress.value == ""){
    swal({
          text: "설정영역 주소를 입력해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(viewaddress1.value == null || viewaddress1.value == ""){
    swal({
          text: "설정영역 상세주소를 입력해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(viewzonename.value == null || viewzonename.value == ""){
    swal({
          text: "서비스존 이름을 입력해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(viewzoneex.value == null || viewzoneex.value == ""){
    swal({
          text: "서비스존 설명을 입력해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(marker1 == null || marker1 == ""){
    swal({
          text: "마커를 선택해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else{
swal({
  title: "서비스존 출시요청",
  text: "해당 서비스존을 출시요청 하시겠습니까?",
  icon: "info",
  closeOnClickOutside : false,
  buttons : ["취소", "요청"],
}).then((result) => {
  if (result) {
	$('#load').show();
    // 파일여부 확인
    var videotype1 = ""; var videotype2 = ""; var imgtype1 = ""; var imgtype2 = "";
    var imgtype3 = ""; var imgtype4 = ""; var imgtype5 = ""; var imgtype6 = "";
    if(realUploadvideo1.files[0] != null ){
    videotype1 = realUploadvideo1.files[0].name
    }else{videotype1 = srcbox[0]}
    if(realUploadvideo2.files[0] != null ){
    videotype2 = realUploadvideo2.files[0].name
    }else{videotype2 = srcbox[1]}
    if(realUpload1.files[0] != null){
    imgtype1 = realUpload1.files[0].name
    }else{imgtype1 = srcbox[2]}
    if(realUpload2.files[0] != null){
    imgtype2 = realUpload2.files[0].name
    }else{imgtype2 = srcbox[3]}
    if(realUpload3.files[0] != null){
    imgtype3 = realUpload3.files[0].name
    }else{imgtype3 = srcbox[4]}
    if(realUpload4.files[0] != null){
    imgtype4 = realUpload4.files[0].name
    }else{imgtype4 = srcbox[5]}
    if(realUpload5.files[0] != null){
    imgtype5 = realUpload5.files[0].name
    }else{imgtype5 = srcbox[6]}
    if(realUpload6.files[0] != null){
    imgtype6 = realUpload6.files[0].name
    }else{imgtype6 = srcbox[7]}
    const a = 1;
    let sendData = {
                "address" : viewaddress.value,
                "address1" : viewaddress1.value,
                "zonename" : viewzonename.value,
                "zoneex" : viewzoneex.value,
                "zonetype" : zonetype,
                "serviceszone" : serviceszone,
                "inv1" : videotype1,
                "inv2" : videotype2,
                "ini1" : imgtype1,
                "ini2" : imgtype2,
                "ini3" : imgtype3,
                "ini4" : imgtype4,
                "ini5" : imgtype5,
                "ini6" : imgtype6,
                "a" : a,
                "pkzonenum": pkzonenum,
                "marker1" : marker1
            };
    $.ajax({
        url : "/editzone",
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
                   location.href = "/servicezone";
            }else{
                $.ajax({
                        type: "POST",
                        enctype: 'multipart/form-data',
                        url: "/upload",
                        data: formData,
                        processData: false,
                        contentType: false,
                        cache: false,
                        success: function (data) {
                            $('#load').hide();
                           location.href = "/servicezone";
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

  }
});
}
}

//서비스존 수정 출시취소
function editzone2(){
var type = document.getElementById('type').innerText;
if(type == "0"){zonetype = "RECTANGLE";}else if(type == "1"){zonetype = "CIRCLE";}else if(type == "2"){zonetype = "POLYGON";}
marker1 = document.getElementById('marker1').src;
const pkzonenum = document.getElementById('pknum').innerText;
const viewaddress = document.getElementById('address');
const viewaddress1 = document.getElementById('address1');
const viewzonename = document.getElementById('zonename');
const viewzoneex = document.getElementById('zoneex');
const inv1 = document.getElementById('inv1');
const inv2 = document.getElementById('inv2');
const img1 = document.getElementById('img1');
const img2 = document.getElementById('img2');
const img3 = document.getElementById('img3');
const img4 = document.getElementById('img4');
const img5 = document.getElementById('img5');
const img6 = document.getElementById('img6');

var srcbox = [inv1.src ,inv2.src, img1.src,img2.src, img3.src, img4.src, img5.src, img6.src];
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
console.log(srcbox);
if(serviceszone == null || serviceszone == ""){
    swal({
          text: "서비스존 영역을 설정해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(viewaddress.value == null || viewaddress.value == ""){
    swal({
          text: "설정영역 주소를 입력해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(viewaddress1.value == null || viewaddress1.value == ""){
    swal({
          text: "설정영역 상세주소를 입력해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(viewzonename.value == null || viewzonename.value == ""){
    swal({
          text: "서비스존 이름을 입력해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(viewzoneex.value == null || viewzoneex.value == ""){
    swal({
          text: "서비스존 설명을 입력해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else if(marker1 == null || marker1 == ""){
    swal({
          text: "마커를 선택해주세요.",
          icon: "info" //"info,success,warning,error" 중 택1
          });
}else{
swal({
  title: "서비스존 출시취소",
  text: "해당 서비스존을 출시취소 하시겠습니까?",
  icon: "info",
  closeOnClickOutside : false,
  buttons : ["취소", "확인"],
}).then((result) => {
  if (result) {
	$('#load').show();
    // 파일여부 확인
    var videotype1 = ""; var videotype2 = ""; var imgtype1 = ""; var imgtype2 = "";
    var imgtype3 = ""; var imgtype4 = ""; var imgtype5 = ""; var imgtype6 = "";
    if(realUploadvideo1.files[0] != null ){
    videotype1 = realUploadvideo1.files[0].name
    }else{videotype1 = srcbox[0]}
    if(realUploadvideo2.files[0] != null ){
    videotype2 = realUploadvideo2.files[0].name
    }else{videotype2 = srcbox[1]}
    if(realUpload1.files[0] != null){
    imgtype1 = realUpload1.files[0].name
    }else{imgtype1 = srcbox[2]}
    if(realUpload2.files[0] != null){
    imgtype2 = realUpload2.files[0].name
    }else{imgtype2 = srcbox[3]}
    if(realUpload3.files[0] != null){
    imgtype3 = realUpload3.files[0].name
    }else{imgtype3 = srcbox[4]}
    if(realUpload4.files[0] != null){
    imgtype4 = realUpload4.files[0].name
    }else{imgtype4 = srcbox[5]}
    if(realUpload5.files[0] != null){
    imgtype5 = realUpload5.files[0].name
    }else{imgtype5 = srcbox[6]}
    if(realUpload6.files[0] != null){
    imgtype6 = realUpload6.files[0].name
    }else{imgtype6 = srcbox[7]}
    const a = 0;
    let sendData = {
                "address" : viewaddress.value,
                "address1" : viewaddress1.value,
                "zonename" : viewzonename.value,
                "zoneex" : viewzoneex.value,
                "zonetype" : zonetype,
                "serviceszone" : serviceszone,
                "inv1" : videotype1,
                "inv2" : videotype2,
                "ini1" : imgtype1,
                "ini2" : imgtype2,
                "ini3" : imgtype3,
                "ini4" : imgtype4,
                "ini5" : imgtype5,
                "ini6" : imgtype6,
                "a" : a,
                "pkzonenum": pkzonenum,
                "marker1" : marker1
            };
    $.ajax({
        url : "/editzone",
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
                   location.href = "/servicezone";
            }else{
                $.ajax({
                        type: "POST",
                        enctype: 'multipart/form-data',
                        url: "/upload",
                        data: formData,
                        processData: false,
                        contentType: false,
                        cache: false,
                        success: function (data) {
                            $('#load').hide();
                           location.href = "/servicezone";
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

  }
});
}
}

//서비스존 내 전시/시설물 확인
function checkexhibit(type){
    console.log(type);


}

// 다각형 확인 함수
function insideploygon(point, vs) {

    var x = point[0],
        y = point[1];

    var inside = false;

    for (var i = 0, j = vs.length - 1; i < vs.length; j = i++) {
        var xi = vs[i].y,
            yi = vs[i].x
        var xj = vs[j].y,
            yj = vs[j].x;

        var intersect = ((yi > y) != (yj > y)) &&
            (x < (xj - xi) * (y - yi) / (yj - yi) + xi);
        if (intersect) inside = !inside;
    }

    return inside;
}

// 다각형 전시/시설물 좌표값 가져오기
function findexplogon(){
$.ajax({
    url : "/findexpoint",
    type : "POST",
    success : function(result){
            $(".id1").remove();
        for(var i=0; i<result.point.length; i++){
            var [s1, s2]  = result.point[i].split(',');
            if(insideploygon([s1,s2],ckexhibit) == true){
                $("#exbox")
               .append("<span class='id1'>" + result.name[i] + "<br></span>");
            }
        }
    },
    error: function (e) {
    }
});

}

// 원 확인 함수
function insidecircle(radius){
var line = new kakao.maps.Polyline();
$.ajax({
    url : "/findexpoint",
    type : "POST",
    success : function(result){
            $(".id1").remove();
        for(var i=0; i<result.point.length; i++){
            var [s1, s2]  = result.point[i].split(',');
            var path = [
                        new kakao.maps.LatLng(s1,s2),
                        new kakao.maps.LatLng(ckexhibit.y,ckexhibit.x)
                        ]
            line.setPath(path);
            var dist = line.getLength();
            if( dist <= radius){
                $("#exbox")
               .append("<span class='id1'>" + result.name[i] + "<br></span>");
            }
        }
    },
    error: function (e) {
    }
});
}

// 사각형 확인 함수
function findexrectangle(){
$.ajax({
    url : "/findexpoint",
    type : "POST",
    success : function(result){
            $(".id1").remove();

        var sw = new kakao.maps.LatLng(ckexhibit.y, ckexhibit.x),
            ne = new kakao.maps.LatLng(ckexhibit1.y, ckexhibit1.x),
            lb = new kakao.maps.LatLngBounds(sw, ne);
        for(var i=0; i<result.point.length; i++){
            var [s1, s2]  = result.point[i].split(',');

            var l1 = new kakao.maps.LatLng(s1, s2);

            if(lb.contain(l1) == true){
                $("#exbox")
               .append("<span class='id1'>" + result.name[i] + "<br></span>");
            };
        }
    },
    error: function (e) {
    }
});

}

var sspp1 = "";
var sspp2 = "";
var eepp1 = "";
var eepp2 = "";
var pol11 = [];

// edit 사각형 확인 함수
function findexrectangle1(){
$.ajax({
    url : "/findexpoint",
    type : "POST",
    success : function(result){
            $(".id1").remove();
        var sw = new kakao.maps.LatLng(sspp1, sspp2),
            ne = new kakao.maps.LatLng(eepp1, eepp2),
            lb = new kakao.maps.LatLngBounds(sw, ne);
        for(var i=0; i<result.point.length; i++){
            var [s1, s2]  = result.point[i].split(',');

            var l1 = new kakao.maps.LatLng(s1, s2);

            if(lb.contain(l1) == true){
                $("#exbox")
               .append("<span class='id1'>" + result.name[i] + "<br></span>");
            };
        }
    },
    error: function (e) {
    }
});

}

// edit 원 확인 함수
function insidecircle1(radius){
var line = new kakao.maps.Polyline();
$.ajax({
    url : "/findexpoint",
    type : "POST",
    success : function(result){
            $(".id1").remove();
        for(var i=0; i<result.point.length; i++){
            var [s1, s2]  = result.point[i].split(',');
            var path = [
                        new kakao.maps.LatLng(s1,s2),
                        new kakao.maps.LatLng(sspp1,sspp2)
                        ]
            line.setPath(path);
            var dist = line.getLength();
            if( dist <= radius){
                $("#exbox")
               .append("<span class='id1'>" + result.name[i] + "<br></span>");
            }
        }
    },
    error: function (e) {
    }
});
}

// edit 다각형 확인 함수
function insideploygon1(point, vs) {

    var x = point[0],
        y = point[1];

    var inside = false;

    for (var i = 0, j = vs.length - 1; i < vs.length; j = i++) {
        var xi = vs[i].Ma,
            yi = vs[i].La
        var xj = vs[j].Ma,
            yj = vs[j].La;

        var intersect = ((yi > y) != (yj > y)) &&
            (x < (xj - xi) * (y - yi) / (yj - yi) + xi);
        if (intersect) inside = !inside;
    }

    return inside;
}

// edit 다각형 전시/시설물 좌표값 가져오기
function findexplogon1(){
$.ajax({
    url : "/findexpoint",
    type : "POST",
    success : function(result){
            $(".id1").remove();
        for(var i=0; i<result.point.length; i++){
            var [s1, s2]  = result.point[i].split(',');
            if(insideploygon1([s1,s2],pol11) == true){
                $("#exbox")
               .append("<span class='id1'>" + result.name[i] + "<br></span>");
            }
        }
    },
    error: function (e) {
    }
});

}

