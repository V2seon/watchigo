//동영상 업로드
const viewrealUploadvideo1 = document.getElementById('viewrealUploadvideo1');
const viewuploadvideo1 = document.getElementById('viewuploadvideo1');
const viewinv1 = document.getElementById('viewinv1');
const viewover1 = function(){
document.getElementById('viewdelete1').style.display='block';
}
const viewout1 = function(){
document.getElementById('viewdelete1').style.display='none';
}

function viewvideo1(){
if(viewrealUploadvideo1.files[0] == null){
    viewrealUploadvideo1.click();
    viewrealUploadvideo1.addEventListener("change", function(){
        const viewfile = viewrealUploadvideo1.files[0];
        const viewvideourl = URL.createObjectURL(viewfile);
        document.getElementById('viewdelbtn1').style.display='block';
        viewinv1.setAttribute("src", viewvideourl);
        viewuploadvideo1.addEventListener('mouseover',viewover1);
        viewuploadvideo1.addEventListener('mouseout',viewout1);
    })
}
}

const viewrealUploadvideo2 = document.getElementById('viewrealUploadvideo2');
const viewuploadvideo2 = document.getElementById('viewuploadvideo2');
const viewinv2 = document.getElementById('viewinv2');
const viewover2 = function(){
document.getElementById('viewdelete2').style.display='block';
}
const viewout2 = function(){
document.getElementById('viewdelete2').style.display='none';
}

function viewvideo2(){
    if(viewrealUploadvideo2.files[0] == null){
        viewrealUploadvideo2.click();
        viewrealUploadvideo2.addEventListener("change", function(){
            const viewfile1 = viewrealUploadvideo2.files[0];
            const viewvideourl1 = URL.createObjectURL(viewfile1);
            document.getElementById('viewdelbtn2').style.display='block';
            viewinv2.setAttribute("src", viewvideourl1);
            viewuploadvideo2.addEventListener('mouseover',viewover2);
            viewuploadvideo2.addEventListener('mouseout',viewout2);
        })
    }
}


// 이미지 업로드
const viewrealUpload1 = document.getElementById('viewupimg1');
const viewupload1 = document.getElementById('viewuploadimg1');
const viewini1 = document.getElementById('viewimg1');

const viewover3 = function(){
document.getElementById('viewdelete3').style.display='block';
}
const viewout3 = function(){
document.getElementById('viewdelete3').style.display='none';
}
function viewimg1(){
    if (viewrealUpload1.files[0] == null) {
        viewrealUpload1.click();
        viewrealUpload1.addEventListener("change", function(){
            const viewfile2 = viewrealUpload1.files[0];
            const viewimgurl2 = URL.createObjectURL(viewfile2);
            document.getElementById('viewdelbtn3').style.display='block';
            viewini1.setAttribute("src", viewimgurl2);
            viewupload1.addEventListener('mouseover',viewover3);
            viewupload1.addEventListener('mouseout',viewout3);
        })
    }
}

const viewrealUpload2 = document.getElementById('viewupimg2');
const viewupload2 = document.getElementById('viewuploadimg2');
const viewini2 = document.getElementById('viewimg2');

const viewover4 = function(){
document.getElementById('viewdelete4').style.display='block';
}
const viewout4 = function(){
document.getElementById('viewdelete4').style.display='none';
}
function viewimg2(){
    if (viewrealUpload2.files[0] == null) {
        viewrealUpload2.click();
        viewrealUpload2.addEventListener("change", function(){
            const viewfile3 = viewrealUpload2.files[0];
            const viewimgurl3 = URL.createObjectURL(viewfile3);
            document.getElementById('viewdelbtn4').style.display='block';
            viewini2.setAttribute("src", viewimgurl3);
            viewupload2.addEventListener('mouseover',viewover4);
            viewupload2.addEventListener('mouseout',viewout4);
        })
    }
}

const viewrealUpload3 = document.getElementById('viewupimg3');
const viewupload3 = document.getElementById('viewuploadimg3');
const viewini3 = document.getElementById('viewimg3');

const viewover5 = function(){
document.getElementById('viewdelete5').style.display='block';
}
const viewout5 = function(){
document.getElementById('viewdelete5').style.display='none';
}
function viewimg3(){
    if (viewrealUpload3.files[0] == null) {
        viewrealUpload3.click();
        viewrealUpload3.addEventListener("change", function(){
            const viewfile4 = viewrealUpload3.files[0];
            const viewimgurl4 = URL.createObjectURL(viewfile4);
            document.getElementById('viewdelbtn5').style.display='block';
            viewini3.setAttribute("src", viewimgurl4);
            viewupload3.addEventListener('mouseover',viewover5);
            viewupload3.addEventListener('mouseout',viewout5);
        })
    }
}

const viewrealUpload4 = document.getElementById('viewupimg4');
const viewupload4 = document.getElementById('viewuploadimg4');
const viewini4 = document.getElementById('viewimg4');

const viewover6 = function(){
document.getElementById('viewdelete6').style.display='block';
}
const viewout6 = function(){
document.getElementById('viewdelete6').style.display='none';
}
function viewimg4(){
    if (viewrealUpload4.files[0] == null) {
        viewrealUpload4.click();
        viewrealUpload4.addEventListener("change", function(){
            const viewfile5 = viewrealUpload4.files[0];
            const viewimgurl5 = URL.createObjectURL(viewfile5);
            document.getElementById('viewdelbtn6').style.display='block';
            viewini4.setAttribute("src", viewimgurl5);
            viewupload4.addEventListener('mouseover',viewover6);
            viewupload4.addEventListener('mouseout',viewout6);
        })
    }
}

const viewrealUpload5 = document.getElementById('viewupimg5');
const viewupload5 = document.getElementById('viewuploadimg5');
const viewini5 = document.getElementById('viewimg5');

const viewover7 = function(){
document.getElementById('viewdelete7').style.display='block';
}
const viewout7 = function(){
document.getElementById('viewdelete7').style.display='none';
}
function viewimg5(){
    if (viewrealUpload5.files[0] == null) {
        viewrealUpload5.click();
        viewrealUpload5.addEventListener("change", function(){
            const viewfile6 = viewrealUpload5.files[0];
            const viewimgurl6 = URL.createObjectURL(viewfile6);
            document.getElementById('viewdelbtn7').style.display='block';
            viewini5.setAttribute("src", viewimgurl6);
            viewupload5.addEventListener('mouseover',viewover7);
            viewupload5.addEventListener('mouseout',viewout7);
        })
    }
}

const viewrealUpload6 = document.getElementById('viewupimg6');
const viewupload6 = document.getElementById('viewuploadimg6');
const viewini6 = document.getElementById('viewimg6');

const viewover8 = function(){
document.getElementById('viewdelete8').style.display='block';
}
const viewout8 = function(){
document.getElementById('viewdelete8').style.display='none';
}
function viewimg6(){
    if (viewrealUpload6.files[0] == null) {
        viewrealUpload6.click();
        viewrealUpload6.addEventListener("change", function(){
            const viewfile7 = viewrealUpload6.files[0];
            const viewimgurl7 = URL.createObjectURL(viewfile7);
            document.getElementById('viewdelbtn8').style.display='block';
            viewini6.setAttribute("src", viewimgurl7);
            viewupload6.addEventListener('mouseover',viewover8);
            viewupload6.addEventListener('mouseout',viewout8);
        })
    }
}


// 동영상 삭제
function viewdelete1(){
document.getElementById('viewrealUploadvideo1').value ="";
viewinv1.src = "/img/plus.mp4";
document.getElementById('viewdelbtn1').style.display='none';
document.getElementById('viewdelete1').style.display='none';
viewuploadvideo1.removeEventListener('mouseover',viewover1);
viewuploadvideo1.removeEventListener('mouseout',viewout1);
}

function viewdelete2(){
document.getElementById('viewrealUploadvideo2').value ="";
viewinv2.src = "/img/plus.mp4";
document.getElementById('viewdelbtn2').style.display='none';
document.getElementById('viewdelete2').style.display='none';
viewuploadvideo2.removeEventListener('mouseover',viewover2);
viewuploadvideo2.removeEventListener('mouseout',viewout2);
}

// 이미지 삭제
function viewdelete3(){
document.getElementById('viewupimg1').value ="";
viewini1.src = "/img/plus.png";
document.getElementById('viewdelbtn3').style.display='none';
document.getElementById('viewdelete3').style.display='none';
viewupload1.removeEventListener('mouseover',viewover3);
viewupload1.removeEventListener('mouseout',viewout3);
}

function viewdelete4(){
document.getElementById('viewupimg2').value ="";
viewini2.src = "/img/plus.png";
document.getElementById('viewdelbtn4').style.display='none';
document.getElementById('viewdelete4').style.display='none';
viewupload2.removeEventListener('mouseover',viewover4);
viewupload2.removeEventListener('mouseout',viewout4);
}

function viewdelete5(){
document.getElementById('viewupimg3').value ="";
viewini3.src = "/img/plus.png";
document.getElementById('viewdelbtn5').style.display='none';
document.getElementById('viewdelete5').style.display='none';
viewupload3.removeEventListener('mouseover',viewover5);
viewupload3.removeEventListener('mouseout',viewout5);
}

function viewdelete6(){
document.getElementById('viewupimg4').value ="";
viewini4.src = "/img/plus.png";
document.getElementById('viewdelbtn6').style.display='none';
document.getElementById('viewdelete6').style.display='none';
viewupload4.removeEventListener('mouseover',viewover6);
viewupload4.removeEventListener('mouseout',viewout6);
}

function viewdelete7(){
document.getElementById('viewupimg5').value ="";
viewini5.src = "/img/plus.png";
document.getElementById('viewdelbtn7').style.display='none';
document.getElementById('viewdelete7').style.display='none';
viewupload5.removeEventListener('mouseover',viewover7);
viewupload5.removeEventListener('mouseout',viewout7);
}

function viewdelete8(){
document.getElementById('viewupimg6').value ="";
viewini6.src = "/img/plus.png";
document.getElementById('viewdelbtn8').style.display='none';
document.getElementById('viewdelete8').style.display='none';
viewupload6.removeEventListener('mouseover',viewover8);
viewupload6.removeEventListener('mouseout',viewout8);
}

const viewrealUploadvideo3 = document.getElementById('viewrealUploadvideo3');
const viewuploadvideo3 = document.getElementById('viewuploadvideo3');
const viewinv3 = document.getElementById('viewinv3');
const viewover9 = function(){
document.getElementById('delete9').style.display='block';
}
const viewout9 = function(){
document.getElementById('delete9').style.display='none';
}

function viewvideo3(){
    if(realUploadvideo3.files[0] == null){
        realUploadvideo3.click();
        realUploadvideo3.addEventListener("change", function(){
            const file9 = realUploadvideo3.files[0];
            const videourl9 = URL.createObjectURL(file9);
            document.getElementById('delbtn9').style.display='block';
            inv3.setAttribute("src", videourl9);
            uploadvideo3.addEventListener('mouseover',over9);
            uploadvideo3.addEventListener('mouseout',out9);
        })
    }
}

function viewdelete9(){
document.getElementById('realUploadvideo3').value ="";
inv3.src = "/img/plus.mp4";
document.getElementById('delbtn9').style.display='none';
document.getElementById('delete9').style.display='none';
uploadvideo3.removeEventListener('mouseover',over9);
uploadvideo3.removeEventListener('mouseout',out9);
}