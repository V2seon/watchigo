function ainext(){
    if(aiUploadvideo.files[0] == null){ // 동영상 유무 확인
        swal({
            text: "동영상을 등록해주세요.",
            icon: "info"
        })
    }else{
        swal({ // 영상분리 팝업창
            title : "영상 분리까지 최대 10분정도 소요됩니다. 완료되면 자동으로 넘어갑니다. 진행하시겠습니까?",
            icon : "info"
        }).then(function(){
            $('#load').show();
//            var aivideoname = document.getElementById('aiUploadvideo').files[0].name;


            // 동영상 저장
            var aiinvideo = document.getElementById('aiUploadvideo').files[0];
            var formData = new FormData();
            formData.append("aiinvideo",aiinvideo);
//            formData.append("aivideoname",aivideoname);

            $.ajax({
                url : "/aivideo/video_download",
                processData:false,
                contentType:false,
                data : formData,
                type : "POST",
                success : function(result){
                    console.log(result)



                    // 동영상명 전송 - flask
                    $.ajax({
                        url : "/aivideo/video_split",
//                        data : {"aiinv" : aivideoname},
                        data : {"aiinv" : result},
                        type : "GET",
                        success : function(result){
                            const get_arr = result.split("/");
                            var getdata, aiimgname, aimainnum1, aimainnum2, aimainnum3, aimainnum4, aimainnum5;
                            // var aimainnum6;

                            console.log(get_arr.length+"");
                            // 받아온 데이터 분리 및 input에 넣기
                            for(i in get_arr){
                                getdata = get_arr[i].split(":");
                                if(getdata[0]=="img_name"){
                                    aiimgname = getdata[1];
                                    document.getElementById("img_name").value = getdata[1];
                                }else if(getdata[0]=="img_count"){
                                    document.getElementById("img_count").value = getdata[1];
                                }else if(getdata[0]=="main_count"){
                                    document.getElementById("main_count").value = "["+getdata[1]+"]";
                                    getdata = getdata[1].split(",");
                                    aimainnum1 = getdata[0];
                                    aimainnum2 = getdata[1];
                                    aimainnum3 = getdata[2];
                                    aimainnum4 = getdata[3];
                                    aimainnum5 = getdata[4];
                                    // aimainnum6 = getdata[5];

                                }else if(getdata[0]=="width"){
                                    document.getElementById("width").value = getdata[1];
                                                    }else if(getdata[0]=="height"){
                                                        document.getElementById("height").value = getdata[1];
                                                    }
                                                }
                                                // 이미지 불러오기
                                                var ai_src = "/file/img/"+aiimgname+"/"+aiimgname;
                                                document.getElementById('aiimg1').src=ai_src+aimainnum1+'.jpg';
                                                document.getElementById('aiimg2').src=ai_src+aimainnum2+'.jpg';
                                                document.getElementById('aiimg3').src=ai_src+aimainnum3+'.jpg';
                                                document.getElementById('aiimg4').src=ai_src+aimainnum4+'.jpg';
                                                document.getElementById('aiimg5').src=ai_src+aimainnum5+'.jpg';
                    //                            document.getElementById('aiimg6').src=ai_src+aimainnum6+'.jpg';

                                                document.getElementById('aiInVPage').style.display='none';
                                                document.getElementById('aiinvstart').style.display='none';
                                                document.getElementById('aiLPage').style.display='block';
                                                document.getElementById('aiLStart').style.display='block';
                                                document.getElementById('aiLProgress').style.display='flex';
                                                document.getElementById('aiProgress').style.display='flex';

                                                $('#load').hide();


                    //                        },error: function(e){
                    //                            swal({
                    //                                text: "동영상 저장 실패",
                    //                                icon: "warning"
                    //                            });
                    //                        }
                    //                    });


                                    }, error:function(request, status, error){
                                        swal({
                                            text: "동영상 업로드 실패",
                                            icon: "warning"
                                        });
                                    }
                                });



                }, error:function(request, status, error){
                    swal({
                    text: "동영상 저장 실패",
                    icon: "warning"
                    });
                }
            });


        });
    }
}



// 컨버스 라벨링
var canvas;
var context;
var drag = false;
var cvs_color = "#ff0000";

// 영역지정
function ai_cvs1(){
    canvas = document.getElementById("aicvs1");

    context = canvas.getContext ("2d");
    context.lineWidth = 1;
    context.strokeStyle = cvs_color;
    canvas.addEventListener("mousedown",function(me){mDown(me)},false);
    canvas.addEventListener("mousemove",function(me){mMove(me)},false);
    canvas.addEventListener("mouseup",function(me){mUp(me)},false);
    canvas.addEventListener("mouseout",function(me){mOut(me)},false);
}
function ai_cvs2(){
    canvas = document.getElementById("aicvs2");

    context = canvas.getContext ("2d");
    context.lineWidth = 1;
    context.strokeStyle = cvs_color;
    canvas.addEventListener("mousedown",function(me){mDown(me)},false);
    canvas.addEventListener("mousemove",function(me){mMove(me)},false);
    canvas.addEventListener("mouseup",function(me){mUp(me)},false);
    canvas.addEventListener("mouseout",function(me){mOut(me)},false);
}
function ai_cvs3(){
    canvas = document.getElementById("aicvs3");

    context = canvas.getContext ("2d");
    context.lineWidth = 1;
    context.strokeStyle = cvs_color;
    canvas.addEventListener("mousedown",function(me){mDown(me)},false);
    canvas.addEventListener("mousemove",function(me){mMove(me)},false);
    canvas.addEventListener("mouseup",function(me){mUp(me)},false);
    canvas.addEventListener("mouseout",function(me){mOut(me)},false);
}
function ai_cvs4(){
    canvas = document.getElementById("aicvs4");

    context = canvas.getContext ("2d");
    context.lineWidth = 1;
    context.strokeStyle = cvs_color;
    canvas.addEventListener("mousedown",function(me){mDown(me)},false);
    canvas.addEventListener("mousemove",function(me){mMove(me)},false);
    canvas.addEventListener("mouseup",function(me){mUp(me)},false);
    canvas.addEventListener("mouseout",function(me){mOut(me)},false);
}
function ai_cvs5(){
    canvas = document.getElementById("aicvs5");

    context = canvas.getContext ("2d");
    context.lineWidth = 1;
    context.strokeStyle = cvs_color;
    canvas.addEventListener("mousedown",function(me){mDown(me)},false);
    canvas.addEventListener("mousemove",function(me){mMove(me)},false);
    canvas.addEventListener("mouseup",function(me){mUp(me)},false);
    canvas.addEventListener("mouseout",function(me){mOut(me)},false);
}
//function ai_cvs6(){
//    canvas = document.getElementById("aicvs6");
//
//    context = canvas.getContext ("2d");
//    context.lineWidth = 1;
//    context.strokeStyle = "#1919ff";
//    canvas.addEventListener("mousedown",function(me){mDown(me)},false);
//    canvas.addEventListener("mousemove",function(me){mMove(me)},false);
//    canvas.addEventListener("mouseup",function(me){mUp(me)},false);
//    canvas.addEventListener("mouseout",function(me){mOut(me)},false);
//}
// 마우스 드래그 기능
function mMove(me){
    // drag가 false일때는 return(return 아래는 실행 안함)
    if(!drag){return;}
    // 마우스를 움직일 때마다 x좌표를 nowX에 담음
    var nowX = me.offsetX;
    //마우스를 움직일 때마다 Y좌표를 nowY에 담음
    var nowY = me.offsetY;
    //실질적으로 캔버스에 그림을 그리는 부분
    canvasDraw(nowX,nowY);
    //마우스가 움직일때마다 X좌표를 stX에 담음
    stX = nowX;
    //마우스가 움직일때마다 Y좌표를 stY에 담음
    stY = nowY;
}
function mDown(me){
    startX = me.offsetX;
    startY = me.offsetY;
    stX = me.offsetX ; //눌렀을 때 현재 마우스 X좌표를 stX에 담음
    stY = me.offsetY ; //눌렀을 때 현재 마우스 Y좌표를 stY에 담음
    drag = true ; //그림 그리기는 그리는 상태로 변경
}
function mUp(me){
    endX = me.offsetX
    endY = me.offsetY
    // context.strokeRect(startX,startY,endX-startX,endY-startY)
    drag = false ; //마우스를 떼었을 때 그리기 중지
}
function mOut(me){
    drag = false ; //마우스가 캔버스 밖으로 벗어났을 때 그리기 중지
}
function canvasDraw(currentX,currentY){
    context.clearRect(0,0,context.canvas.width,context.canvas.height) //설정된 영역만큼 캔버스에서 지움
    context.strokeRect(startX,startY,currentX-startX,currentY-startY) //시작점과 끝점의 좌표 정보로 사각형을 그려준다.

    // 라벨영역저장
    var widths = document.getElementById('aicvs1').width;
    var xy1,xy2,xy3,xy4,temp1,temp2;
    if(startY<=currentY){xy1 = startY; xy3 = currentY;}else{xy1 = currentY; xy3 = startY}
    if(startX<=currentX){xy2 = startX; xy4 = currentX;}else{xy2 = currentX; xy4 = startX}

    xy1 = Math.floor(xy1*100/widths)/100;
    xy2 = Math.floor(xy2*100/widths)/100;
    xy3 = Math.ceil(xy3*100/widths)/100;
    xy4 = Math.ceil(xy4*100/widths)/100;

    box_text = "[" + Math.abs(xy1) + "," + Math.abs(xy2) + "," + Math.abs(xy3) + "," + Math.abs(xy4) + "]"

    if(canvas == document. getElementById("aicvs1")){
        document.getElementById("box1XY").value = box_text;
    }else if(canvas == document. getElementById("aicvs2")){
        document.getElementById("box2XY").value = box_text;
    }else if(canvas == document. getElementById("aicvs3")){
        document.getElementById("box3XY").value = box_text;
    }else if(canvas == document. getElementById("aicvs4")){
        document.getElementById("box4XY").value = box_text;
    }else if(canvas == document. getElementById("aicvs5")){
        document.getElementById("box5XY").value = box_text;
//    }else if(canvas == document. getElementById("aicvs6")){
//        document.getElementById("box6XY").value = box_text;
    }
}


// ai 라벨링
function labeling_start(){
    var xy1 = document.getElementById("box1XY").value;
    var xy2 = document.getElementById("box2XY").value;
    var xy3 = document.getElementById("box3XY").value;
    var xy4 = document.getElementById("box4XY").value;
    var xy5 = document.getElementById("box5XY").value;
//    var xy6 = document.getElementById("box6XY").value;
    console.log(xy1);
    console.log(xy2);
    console.log(xy3);
    console.log(xy4);
    console.log(xy5);
//    console.log(xy6);

    if(document.getElementById("label_name").value == null){
        swal({
            text: "라벨명을 지정해주세요.",
            icon: "info"
        })
//    if(document.getElementById("aiclass").value == null){
//        swal({
//            text: "분류명을 지정해주세요.",
//            icon: "info"
//        })
//    }else if(document.getElementById("ainame").value == null){
//        swal({
//            text: "시설물 이름을 지정해주세요.",
//            icon: "info"
//        })
    }else if(xy1 == null || xy2 == null || xy3 == null || xy4 == null || xy5 == null){ // 동영상 유무 확인

//    if(xy1 == null || xy2 == null || xy3 == null || xy4 == null || xy5 == null){ // 동영상 유무 확인
        swal({
            text: "모든 영역을 지정해주세요.",
            icon: "info"
        })
    }else{
        swal({ // 영상분리 팝업창
            title : "라벨링이 완료되면 자동으로 AI학습이 진행됩니다. 진행하시겠습니까?",
            icon : "info"
        }).then(function(){
            var main_label = xy1+"/"+xy2+"/"+xy3+"/"+xy4+"/"+xy5;
            let sendData = {
                "img_name" : document.getElementById("img_name").value,
                "label_name" : document.getElementById("label_name").value,
                "img_count" : document.getElementById("img_count").value,
                "main_count" : document.getElementById("main_count").value,
                "main_label" : main_label,
                "width" : document.getElementById("width").value,
                "height" : document.getElementById("height").value
            };
            $.ajax({ // 라벨링 데이터 전송 - flask
                url : "/aivideo/ai_labeling",
                data : sendData,
                type : "POST",
                success : function(result){
                    location.href = "/aivideo/list";
                }, error:function(request, status, error){
                    swal({
                        text: "라벨링 전송 실패",
                        icon: "warning"
                    });
                }
            });
        });
    }
}