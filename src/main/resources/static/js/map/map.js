// 마커를 담을 배열입니다
var markers = [];

var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

// 지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

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

			// 마커와 인포윈도우를 표시합니다
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
        kakao.maps.Drawing.OverlayType.MARKER,
        kakao.maps.Drawing.OverlayType.POLYLINE,
        kakao.maps.Drawing.OverlayType.RECTANGLE,
        kakao.maps.Drawing.OverlayType.CIRCLE,
        kakao.maps.Drawing.OverlayType.POLYGON
    ],
    // 사용자에게 제공할 그리기 가이드 툴팁입니다
    // 사용자에게 도형을 그릴때, 드래그할때, 수정할때 가이드 툴팁을 표시하도록 설정합니다
    guideTooltip: ['draw', 'drag', 'edit'],
    markerOptions: { // 마커 옵션입니다
        draggable: true, // 마커를 그리고 나서 드래그 가능하게 합니다
        removable: true // 마커를 삭제 할 수 있도록 x 버튼이 표시됩니다
    },
    polylineOptions: { // 선 옵션입니다
        draggable: true, // 그린 후 드래그가 가능하도록 설정합니다
        removable: true, // 그린 후 삭제 할 수 있도록 x 버튼이 표시됩니다
        editable: true, // 그린 후 수정할 수 있도록 설정합니다
        strokeColor: '#39f', // 선 색
        hintStrokeStyle: 'dash', // 그리중 마우스를 따라다니는 보조선의 선 스타일
        hintStrokeOpacity: 0.5  // 그리중 마우스를 따라다니는 보조선의 투명도
    },
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

//manager.addListener('drawstart', function(data) {
//
//	var mapobj = manager.getOverlays();
//
//    	removeOverlays(mapobj.rectangle);
//    	removeOverlays(mapobj.circle);
//    	removeOverlays(mapobj.polygon);
//});

function removeOverlays(overlays) {

	console.log("====>" +  overlays.length);
    var len = overlays.length, i = 0;

    for (; i < len; i++) {
        overlays[i].setMap(null);
    }

    overlays = [];
}

//manager.addListener('drawend', function(data) {
//    //console.log('drawend', manager.getOverlays().length);
//
//     var data = manager.getData();
//    //data = {marker: Array(0), polyline: Array(0), rectangle: Array(0), circle: Array(0), polygon: Array(0)}
//    //console.log("data : " + JSON.stringify(data));
//
//
//    if(data.rectangle.length > 0){
//    	printRectangle(data[kakao.maps.drawing.OverlayType.RECTANGLE]);
//    }
//    else if(data.circle.length > 0){
//    	printCircle(data[kakao.maps.drawing.OverlayType.CIRCLE]);
//    }
//    else if(data.polygon.length > 0){
//    	printPolygon(data[kakao.maps.drawing.OverlayType.POLYGON]);
//    }
//    var obj = manager.getOverlays();
//
//    //console.log("polygon : " + JSON.stringify(obj));
//    //removeOverlays();
//    console.log("...");
//});

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
        //console.log("data : " + JSON.stringify(data));

        console.log("수정");
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

        //console.log("polygon : " + JSON.stringify(obj));
        //removeOverlays();
        console.log("수정");

});

var serviceszone = "";
// Drawing Manager에서 가져온 데이터 중 사각형을 아래 지도에 표시하는 함수입니다
function printRectangle(rects) {
    var len = rects.length, i = 0;
	var message = "사각형";
	console.log(message + ":" + JSON.stringify(rects));
    console.log("시작점: " + JSON.stringify(rects[0].sPoint.y) + "|" + JSON.stringify(rects[0].sPoint.x));
    console.log("끝난점: " + JSON.stringify(rects[0].ePoint.y) + "|" + JSON.stringify(rects[0].ePoint.x));
    serviceszone = JSON.stringify(rects[0].sPoint.y) + "," + JSON.stringify(rects[0].sPoint.x) + "&" + JSON.stringify(rects[0].ePoint.y) + "," + JSON.stringify(rects[0].ePoint.x);
    console.log(serviceszone);
}

// Drawing Manager에서 가져온 데이터 중 원을 아래 지도에 표시하는 함수입니다
function printCircle(circles) {
    var len = circles.length, i = 0;
	var message = "원";
	console.log(message + ":" + JSON.stringify(circles));
    console.log("중앙점: " + JSON.stringify(circles[0].center))
    console.log("반지름: " + JSON.stringify(circles[0].radius))
    serviceszone = JSON.stringify(circles[0].center.y)+","+ +JSON.stringify(circles[0].center.x)+"&"+JSON.stringify(circles[0].radius);
    console.log(serviceszone);
}

// Drawing Manager에서 가져온 데이터 중 다각형을 아래 지도에 표시하는 함수입니다
function printPolygon(polygons) {
    var len = polygons.length, i = 0;
	var message = "다각형";
	console.log(message + ":" + JSON.stringify(polygons));
	console.log("좌표값 : " + JSON.stringify(polygons[0].points))
}

var zonetype = "";
// 버튼 클릭 시 호출되는 핸들러 입니다
function selectOverlay(type) {
    // 그리기 중이면 그리기를 취소합니다
    // manager.cancel();

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


// 저장하기
function savezone(){
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
}else{
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
const a = 0;
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
            "a" : a
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
                   swal({
                            text: "성공",
                            icon: "success" //"info,success,warning,error" 중 택1
                        });
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
}



