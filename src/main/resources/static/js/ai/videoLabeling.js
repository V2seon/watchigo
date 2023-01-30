function ainext(){
    var alvseq = document.getElementById('alvseqnum').value;
    var ainextCheck = false;
    var VSsendData;

    if(alvseq==null||alvseq==""||alvseq=="null"){
        var aiinvideo = document.getElementById('aiUploadvideo');
        var aiclass = document.getElementById('classification').value;
        var ainame = document.getElementById('facilityname').value;

        if(aiinvideo.files[0] == null){ // 동영상 유무 확인
            swal({
                text: "동영상을 등록해주세요.",
                icon: "info"
            })
        }else if(aiclass == null || aiclass == ""){
            swal({
                text: "분류를 입력해주세요.",
                icon: "info"
            })
        }else if(ainame == null || ainame == ""){
            swal({
                text: "시설물의 이름을 입력해주세요.",
                icon: "info"
            })
        }else{
            swal({ // 영상분리 팝업창
                title : "영상 분리까지 최대 10분정도 소요됩니다. 완료되면 자동으로 넘어갑니다. 진행하시겠습니까?",
                icon : "info"
            }).then(function(){
                $('#load').show();
                // 동영상 저장
                var formData = new FormData();
                formData.append("aiinvideo",aiinvideo.files[0]);
                $.ajax({
                    url : "/aivideo/video_download",
                    processData:false,
                    contentType:false,
                    data : formData,
                    type : "POST",
                    success : function(result){
                        // 실패시 등록페이지로 이동
                        if(result=="null"){
                            swal({
                                text: "동영상 저장 실패",
                                icon: "warning"
                            }).then(function(){
                                location.href = "/aivideo/write";
                            });
                        }
                        VSsendData = {
                            "alvseq" : "",
                            "aiinv" : result,
                            "aiclass" : aiclass,
                            "ainame" : ainame
                        };
                        videoSplit(VSsendData);
                    }, error:function(request, status, error){
                        swal({
                            text: "동영상 저장 실패",
                            icon: "warning"
                        }).then(function(){
                            $('#load').hide();
                        });
                    }
                });
            });
        }
    }else{ // state4에서 진행
        var aiclass = document.getElementById('classification').value; // 분류명
        var ainame = document.getElementById('facilityname').value; // 시설물명

        $.ajax({
            url : "/aivideo/getVname", // 비디오데이터 가져오기
            data : {"alvseq" : alvseq},
            type : "GET",
            success : function(result){
                // 비디오저장명 가져오기
                if(result==null||result==""||result=="null"){ // 동영상 유무 확인
                    swal({
                        text: "동영상을 등록해주세요.",
                        icon: "info"
                    })
                }else if(aiclass == null || aiclass == ""){
                    swal({
                        text: "분류를 입력해주세요.",
                        icon: "info"
                    })
                }else if(ainame == null || ainame == ""){
                    swal({
                        text: "시설물의 이름을 입력해주세요.",
                        icon: "info"
                    })
                }else{
                    swal({ // 영상분리 팝업창
                        title : "영상 분리까지 최대 10분정도 소요됩니다. 완료되면 자동으로 넘어갑니다. 진행하시겠습니까?",
                        icon : "info"
                    }).then(function(){
                        $('#load').show();
                        VSsendData = {
                            "alvseq" : alvseq,
                            "aiinv" : result,
                            "aiclass" : aiclass,
                            "ainame" : ainame
                        };
                        videoSplit(VSsendData);
                    });
                }
            }, error:function(request, status, error){
                swal({
                    text: "동영상 저장 실패",
                    icon: "warning"
                }).then(function(){
                    $('#load').hide();
                });
            }
        });
    }
}
function videoSplit(VSsendData){
    // 동영상명 전송 - flask
    $.ajax({
        url : "/aivideo/video_split",
        data : VSsendData,
        type : "GET",
        success : function(result){
            if(result=="null"){
                swal({
                    text: "통신오류",
                    icon: "warning"
                }).then(function(){
                    location.href = "/aivideo/write";
                });
            }
            const get_arr = result.split("/");
            var getdata, aiimgname, aiimgcount, maincount, width, height, alvseq, aimainnum1, aimainnum2, aimainnum3, aimainnum4, aimainnum5;

            // 받아온 데이터 분리 및 input에 넣기
            for(i in get_arr){
                getdata = get_arr[i].split(":");
                if(getdata[0]=="img_name"){
                    aiimgname = getdata[1];
                    document.getElementById("img_name").value = getdata[1];
                }else if(getdata[0]=="img_count"){
                    aiimgcount = getdata[1];
                    document.getElementById("img_count").value = getdata[1];
                }else if(getdata[0]=="main_count"){
                    maincount = getdata[1];
                    document.getElementById("main_count").value = "["+getdata[1]+"]";
                    getdata = getdata[1].split(",");
                    aimainnum1 = getdata[0];
                    aimainnum2 = getdata[1];
                    aimainnum3 = getdata[2];
                    aimainnum4 = getdata[3];
                    aimainnum5 = getdata[4];
                }else if(getdata[0]=="width"){
                    width = getdata[1];
                    document.getElementById("width").value = getdata[1];
                }else if(getdata[0]=="height"){
                    height = getdata[1];
                    document.getElementById("height").value = getdata[1];
                }else if(getdata[0]=="alvseq"){
                    alvseq = getdata[1];
                }
            }
            // 이미지 불러오기
            var ai_src = "/file_dir/img/"+aiimgname+"/"+aiimgname; // *********************
            document.getElementById('aiimg1').src=ai_src+aimainnum1+'.jpg';
            document.getElementById('aiimg2').src=ai_src+aimainnum2+'.jpg';
            document.getElementById('aiimg3').src=ai_src+aimainnum3+'.jpg';
            document.getElementById('aiimg4').src=ai_src+aimainnum4+'.jpg';
            document.getElementById('aiimg5').src=ai_src+aimainnum5+'.jpg';

            document.getElementById('aiInVPage').style.display='none';
            document.getElementById('aiinvstart').style.display='none';
            document.getElementById('aiLPage').style.display='block';
            document.getElementById('aiLStart').style.display='block';
            document.getElementById('aiLProgress').style.display='flex';
            document.getElementById('aiProgress').style.display='flex';

            document.getElementById("classification").readOnly = true;
            document.getElementById("facilityname").readOnly = true;

            $('#load').hide();
        }, error:function(request, status, error){
            swal({
                text: "비디오 분할실패",
                icon: "warning"
            }).then(function(){
                location.href = "/aivideo/list";
            });
        }
    });
}

// 라벨명 영어한정
function onlyAlphabet(ele){
    ele.value = ele.value.replace(/[^\\!-z]/gi,"");
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
//// 영역 색상 지정
//var ccolor = document.getElementById("inputccchange");
//ccolor.addEventListener("change", changeColor, false);
//function changeColor(event){
//    cvs_color = event.target.value;
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
    var heights = document.getElementById('aicvs1').height;
    var xy1,xy2,xy3,xy4,temp1,temp2;
    if(startY<=currentY){xy1 = startY; xy3 = currentY;}else{xy1 = currentY; xy3 = startY}
    if(startX<=currentX){xy2 = startX; xy4 = currentX;}else{xy2 = currentX; xy4 = startX}

    xy1 = Math.floor(xy1/heights*100)/100;
    xy2 = Math.floor(xy2/widths*100)/100;
    xy3 = Math.ceil(xy3/heights*100)/100;
    xy4 = Math.ceil(xy4/widths*100)/100;

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
    }
}


// ai 라벨링
function labeling_start(){
    var xy1 = document.getElementById("box1XY").value;
    var xy2 = document.getElementById("box2XY").value;
    var xy3 = document.getElementById("box3XY").value;
    var xy4 = document.getElementById("box4XY").value;
    var xy5 = document.getElementById("box5XY").value;

    if(document.getElementById("label_name").value == null || document.getElementById("label_name").value == ""){
        swal({
            text: "라벨명을 지정해주세요.",
            icon: "info"
        })
    }else if(xy1 == null || xy2 == null || xy3 == null || xy4 == null || xy5 == null || xy1 == "" || xy2 == "" || xy3 == "" || xy4 == "" || xy5 == ""||xy1 == "null" || xy2 == "null" || xy3 == "null" || xy4 == "null" || xy5 == "null"){ // 동영상 유무 확인
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
            console.log(main_label)
            let sendData = {
                "img_name" : document.getElementById("img_name").value,
                "label_name" : document.getElementById("label_name").value,
                "img_count" : document.getElementById("img_count").value,
                "main_count" : document.getElementById("main_count").value,
                "main_label" : main_label,
                "width" : document.getElementById("width").value,
                "height" : document.getElementById("height").value
            };
            location.href = "/aivideo/list";
            $.ajax({ // 라벨링 데이터 전송 - flask
                url : "/aivideo/ai_labeling",
                data : sendData,
                type : "POST",
                success : function(result){
                    if(result=="null"){
                        swal({
                            text: "통신오류",
                            icon: "warning"
                        });
                    }else{
                        swal({
                            text: "라벨링이 완료되었습니다.",
                            icon: "info"
                        })
                    }
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


// list페이지
// 상세
function aivideo_detail(alvseq,state){
    $.ajax({
        url : "/aivideo/stateCheck",
        data : {"alvseq" : alvseq},
        type : "GET",
        success : function(result){
            if(result!=state){
                state = result;
            }
            if(state==5){
                swal({
                    text: "라벨링 준비완료 후 가능합니다.",
                    icon: "info"
                }).then(function(){
                    location.href = "/aivideo/list";
                });
            }else{
                location.href ="/aivideo/detail_page?alvseq="+alvseq+"&state="+state+"&changeCheck=false";
            }
        }, error:function(request, status, error){
        }
    });
}
// 삭제
function aivideo_delete(alvseq,state){
    $.ajax({
        url : "/aivideo/stateCheck",
        data : {"alvseq" : alvseq},
        type : "GET",
        success : function(result){
            if(result!=state){
                state = result;
            }
            if(state==0||state==3||state==4){
                swal({
                    text: "삭제하시겠습니까?",
                    icon: "info",
                    buttons: {
                        confirm: {
                            text: "YES",
                            value: true
                        },
                        cancle: {
                            text: "NO",
                            value: false
                        }
                    }
                }).then(function(result){
                    if(result){
                        // DB삭제(ald)
                        // DB삭제(alv)
                        let sendData = {
                            "alvseq" : alvseq,
                            "state" : state
                        };
                        $.ajax({
                            url : "/aivideo/aideletes",
                            data : sendData,
                            type : "GET",
                            success : function(result){
                                swal({
                                    text: "성공적으로 삭제되었습니다.",
                                    icon: "info"
                                }).then(function(){
                                    location.href = "/aivideo/list";
                                });
                            }, error:function(request, status, error){
                                swal({
                                    text: "삭제 실패",
                                    icon: "warning"
                                }).then(function(){
                                    location.href = "/aivideo/list";
                                });
                            }
                        });
                    }
                });
            }else if(state==1||state==2||state==5){
                swal({
                    text: "라벨링 진행중/준비중 또는 ai학습중에는 수정/삭제가 불가능합니다.",
                    icon: "warning"
                }).then(function(){
                    location.href = "/aivideo/list";
                });
            }
        }, error:function(request, status, error){
        }
    });
}
// 수정
function aivideo_change(alvseq,state){

var ainame = document.getElementById('facilityname').value;
    if(state==0||state==4){

        swal({
            text: "수정하시겠습니까?",
            icon: "info",
            buttons: {
                confirm: {
                    text: "YES",
                    value: true
                },
                cancle: {
                    text: "NO",
                    value: false
                }
            }
        }).then(function(result){

            let sendData = {
                "alvseq" : alvseq,
                "state" : state,
                "changeCheck" : true
            };
            location.href ="/aivideo/detail_page?alvseq="+alvseq+"&state="+state+"&changeCheck=true";
        });

        }else if(state==1||state==2||state==3){
        swal({
            text: "라벨링 진행 전에만 수정이 가능합니다.",
            icon: "warning"
        });
    }else if(state==5){
        swal({
            text: "라벨링 준비될때까지 잠시만 기다려주세요.",
            icon: "info"
        });
    }

}
// 수정저장
function aisavechange(alvseq,state){
    var aiclass = document.getElementById('classification2').value;// 분류명
    var ainame = document.getElementById('facilityname2').value;// 시설물명
    var aivideo_name;

    // 분류명, 시설물명 수정
    if(state==4){
        var Vdelcheck = document.getElementById('Vdelcheck').value;// 동영상 변경 체크
        var aiinvideo = document.getElementById('aiUploadvideo');
        // 수정 넘기기
        if(Vdelcheck=="null"){
            let sendData = {
                "alvseq" : alvseq,
                "state" : state,
                "aiclass" : aiclass,
                "ainame" : ainame,
                "boxXY" : "",
                "ailagelname" : "",
                "aiinvideo" : ""
            };
            $.ajax({
                url : "/aivideo/update_alv",
                data : sendData,
                type : "GET",
                success : function(result){
                    swal({
                        text: "성공적으로 수정되었습니다.",
                        icon: "info"
                    }).then(function(){
                        location.href ="/aivideo/detail_page?alvseq="+alvseq+"&state="+state+"&changeCheck=false";
                    });
                }, error:function(request, status, error){
                    swal({
                        text: "수정 실패",
                        icon: "warning"
                    });
                }
            });
        }else{
            // 수정전 동영상 삭제
            let sendData = {
                "alvseq" : alvseq,
                "state" : state
            };
            $.ajax({
                url : "/aivideo/Vdeletes",
                data : sendData,
                type : "GET",
                success : function(result){
                    // 동영상 저장
                    var formData = new FormData();
                    formData.append("aiinvideo",aiinvideo.files[0]);
                    $.ajax({
                        url : "/aivideo/video_download",
                        processData:false,
                        contentType:false,
                        data : formData,
                        type : "POST",
                        success : function(result){
                            aivideo_name = result; // 새로 저장 동영상명
                            let sendData = {
                                "alvseq" : alvseq,
                                "state" : state,
                                "aiclass" : aiclass,
                                "ainame" : ainame,
                                "boxXY" : "",
                                "ailagelname" : "",
                                "aivideo_name" : aivideo_name
                            };
                            $.ajax({
                                url : "/aivideo/update_alv",
                                data : sendData,
                                type : "GET",
                                success : function(result){
                                    swal({
                                        text: "성공적으로 수정되었습니다.",
                                        icon: "info"
                                    }).then(function(){
                                        location.href ="/aivideo/detail_page?alvseq="+alvseq+"&state="+state+"&changeCheck=false";
                                    });
                                }, error:function(request, status, error){
                                    swal({
                                        text: "수정 실패",
                                        icon: "warning"
                                    });
                                }
                            });
                        }, error:function(request, status, error){
                            swal({
                                text: "동영상 저장 실패",
                                icon: "warning"
                            }).then(function(){
                                $('#load').hide();
                            });
                        }
                    });
                }, error:function(request, state, error){
                    swal({
                        text: "동영상 삭제 실패",
                        icon: "warning"
                    });
                }
            });
        }
    //동영상 수정및 이전동영상 삭제
    }else if(state==0){
        var box1xy = document.getElementById('box1XY').value; // 메인박스
        var box2xy = document.getElementById('box2XY').value;
        var box3xy = document.getElementById('box3XY').value;
        var box4xy = document.getElementById('box4XY').value;
        var box5xy = document.getElementById('box5XY').value;
        console.log(box1xy);
        if(box1xy==null||box1xy==""||box1xy=="null"){
            box1xy="null";
        }
        if(box2xy==null||box2xy==""||box2xy=="null"){
            box2xy="null";
        }
        if(box3xy==null||box3xy==""||box3xy=="null"){
            box3xy="null";
        }
        if(box4xy==null||box4xy==""||box4xy=="null"){
            box4xy="null";
        }
        if(box5xy==null||box5xy==""||box5xy=="null"){
            box5xy="null";
        }
        var boxXY = box1xy+"/"+box2xy+"/"+box3xy+"/"+box4xy+"/"+box5xy;
        var ailagelname = document.getElementById('label_name2').value; // 수정라벨명
        // 수정 넘기기
        let sendData = {
            "alvseq" : alvseq,
            "state" : state,
            "aiclass" : aiclass,
            "ainame" : ainame,
            "boxXY" : boxXY,
            "ailagelname" : ailagelname,
            "aiinvideo" : ""
        };
        $.ajax({
            url : "/aivideo/update_alv",
            data : sendData,
            type : "GET",
            success : function(result){
                swal({
                    text: "성공적으로 수정되었습니다.",
                    icon: "info"
                }).then(function(){
                    location.href ="/aivideo/detail_page?alvseq="+alvseq+"&state="+state+"&changeCheck=false";
                });
            }, error:function(request, status, error){
                swal({
                    text: "수정 실패",
                    icon: "warning"
                });
            }
        });
    }
}