//동영상 업로드
const realUploadvideo1 = document.getElementById('realUploadvideo1');
const uploadvideo1 = document.getElementById('uploadvideo1');
const inv1 = document.getElementById('inv1');
const over1 = function(){
document.getElementById('delete1').style.display='block';
}
const out1 = function(){
document.getElementById('delete1').style.display='none';
}

function video1(){
if(realUploadvideo1.files[0] == null){
    realUploadvideo1.click();
    realUploadvideo1.addEventListener("change", function(){
        const file = realUploadvideo1.files[0];
        const videourl = URL.createObjectURL(file);
        document.getElementById('delbtn1').style.display='block';
        inv1.setAttribute("src", videourl);
        uploadvideo1.addEventListener('mouseover',over1);
        uploadvideo1.addEventListener('mouseout',out1);
    })
}
}

const realUploadvideo2 = document.getElementById('realUploadvideo2');
const uploadvideo2 = document.getElementById('uploadvideo2');
const inv2 = document.getElementById('inv2');
const over2 = function(){
document.getElementById('delete2').style.display='block';
}
const out2 = function(){
document.getElementById('delete2').style.display='none';
}

function video2(){
    if(realUploadvideo2.files[0] == null){
        realUploadvideo2.click();
        realUploadvideo2.addEventListener("change", function(){
            const file1 = realUploadvideo2.files[0];
            const videourl1 = URL.createObjectURL(file1);
            document.getElementById('delbtn2').style.display='block';
            inv2.setAttribute("src", videourl1);
            uploadvideo2.addEventListener('mouseover',over2);
            uploadvideo2.addEventListener('mouseout',out2);
        })
    }
}


// 이미지 업로드
const realUpload1 = document.getElementById('upimg1');
const upload1 = document.getElementById('uploadimg1');
const ini1 = document.getElementById('img1');

const over3 = function(){
document.getElementById('delete3').style.display='block';
}
const out3 = function(){
document.getElementById('delete3').style.display='none';
}
function img1(){
    if (realUpload1.files[0] == null) {
        realUpload1.click();
        realUpload1.addEventListener("change", function(){
            const file2 = realUpload1.files[0];
            const imgurl2 = URL.createObjectURL(file2);
            document.getElementById('delbtn3').style.display='block';
            ini1.setAttribute("src", imgurl2);
            upload1.addEventListener('mouseover',over3);
            upload1.addEventListener('mouseout',out3);
        })
    }
}

const realUpload2 = document.getElementById('upimg2');
const upload2 = document.getElementById('uploadimg2');
const ini2 = document.getElementById('img2');

const over4 = function(){
document.getElementById('delete4').style.display='block';
}
const out4 = function(){
document.getElementById('delete4').style.display='none';
}
function img2(){
    if (realUpload2.files[0] == null) {
        realUpload2.click();
        realUpload2.addEventListener("change", function(){
            const file3 = realUpload2.files[0];
            const imgurl3 = URL.createObjectURL(file3);
            document.getElementById('delbtn4').style.display='block';
            ini2.setAttribute("src", imgurl3);
            upload2.addEventListener('mouseover',over4);
            upload2.addEventListener('mouseout',out4);
        })
    }
}

const realUpload3 = document.getElementById('upimg3');
const upload3 = document.getElementById('uploadimg3');
const ini3 = document.getElementById('img3');

const over5 = function(){
document.getElementById('delete5').style.display='block';
}
const out5 = function(){
document.getElementById('delete5').style.display='none';
}
function img3(){
    if (realUpload3.files[0] == null) {
        realUpload3.click();
        realUpload3.addEventListener("change", function(){
            const file4 = realUpload3.files[0];
            const imgurl4 = URL.createObjectURL(file4);
            document.getElementById('delbtn5').style.display='block';
            ini3.setAttribute("src", imgurl4);
            upload3.addEventListener('mouseover',over5);
            upload3.addEventListener('mouseout',out5);
        })
    }
}

const realUpload4 = document.getElementById('upimg4');
const upload4 = document.getElementById('uploadimg4');
const ini4 = document.getElementById('img4');

const over6 = function(){
document.getElementById('delete6').style.display='block';
}
const out6 = function(){
document.getElementById('delete6').style.display='none';
}
function img4(){
    if (realUpload4.files[0] == null) {
        realUpload4.click();
        realUpload4.addEventListener("change", function(){
            const file5 = realUpload4.files[0];
            const imgurl5 = URL.createObjectURL(file5);
            document.getElementById('delbtn6').style.display='block';
            ini4.setAttribute("src", imgurl5);
            upload4.addEventListener('mouseover',over6);
            upload4.addEventListener('mouseout',out6);
        })
    }
}

const realUpload5 = document.getElementById('upimg5');
const upload5 = document.getElementById('uploadimg5');
const ini5 = document.getElementById('img5');

const over7 = function(){
document.getElementById('delete7').style.display='block';
}
const out7 = function(){
document.getElementById('delete7').style.display='none';
}
function img5(){
    if (realUpload5.files[0] == null) {
        realUpload5.click();
        realUpload5.addEventListener("change", function(){
            const file6 = realUpload5.files[0];
            const imgurl6 = URL.createObjectURL(file6);
            document.getElementById('delbtn7').style.display='block';
            ini5.setAttribute("src", imgurl6);
            upload5.addEventListener('mouseover',over7);
            upload5.addEventListener('mouseout',out7);
        })
    }
}

const realUpload6 = document.getElementById('upimg6');
const upload6 = document.getElementById('uploadimg6');
const ini6 = document.getElementById('img6');

const over8 = function(){
document.getElementById('delete8').style.display='block';
}
const out8 = function(){
document.getElementById('delete8').style.display='none';
}
function img6(){
    if (realUpload6.files[0] == null) {
        realUpload6.click();
        realUpload6.addEventListener("change", function(){
            const file7 = realUpload6.files[0];
            const imgurl7 = URL.createObjectURL(file7);
            document.getElementById('delbtn8').style.display='block';
            ini6.setAttribute("src", imgurl7);
            upload6.addEventListener('mouseover',over8);
            upload6.addEventListener('mouseout',out8);
        })
    }
}


// 동영상 삭제
function delete1(){
document.getElementById('realUploadvideo1').value ="";
inv1.src = "/img/plus.mp4";
document.getElementById('delbtn1').style.display='none';
document.getElementById('delete1').style.display='none';
uploadvideo1.removeEventListener('mouseover',over1);
uploadvideo1.removeEventListener('mouseout',out1);
}

function delete2(){
document.getElementById('realUploadvideo2').value ="";
inv2.src = "/img/plus.mp4";
document.getElementById('delbtn2').style.display='none';
document.getElementById('delete2').style.display='none';
uploadvideo2.removeEventListener('mouseover',over2);
uploadvideo2.removeEventListener('mouseout',out2);
}

// 이미지 삭제
function delete3(){
document.getElementById('upimg1').value ="";
ini1.src = "/img/plus.png";
document.getElementById('delbtn3').style.display='none';
document.getElementById('delete3').style.display='none';
upload1.removeEventListener('mouseover',over3);
upload1.removeEventListener('mouseout',out3);
}

function delete4(){
document.getElementById('upimg2').value ="";
ini2.src = "/img/plus.png";
document.getElementById('delbtn4').style.display='none';
document.getElementById('delete4').style.display='none';
upload2.removeEventListener('mouseover',over4);
upload2.removeEventListener('mouseout',out4);
}

function delete5(){
document.getElementById('upimg3').value ="";
ini3.src = "/img/plus.png";
document.getElementById('delbtn5').style.display='none';
document.getElementById('delete5').style.display='none';
upload3.removeEventListener('mouseover',over5);
upload3.removeEventListener('mouseout',out5);
}

function delete6(){
document.getElementById('upimg4').value ="";
ini4.src = "/img/plus.png";
document.getElementById('delbtn6').style.display='none';
document.getElementById('delete6').style.display='none';
upload4.removeEventListener('mouseover',over6);
upload4.removeEventListener('mouseout',out6);
}

function delete7(){
document.getElementById('upimg5').value ="";
ini5.src = "/img/plus.png";
document.getElementById('delbtn7').style.display='none';
document.getElementById('delete7').style.display='none';
upload5.removeEventListener('mouseover',over7);
upload5.removeEventListener('mouseout',out7);
}

function delete8(){
document.getElementById('upimg6').value ="";
ini6.src = "/img/plus.png";
document.getElementById('delbtn8').style.display='none';
document.getElementById('delete8').style.display='none';
upload6.removeEventListener('mouseover',over8);
upload6.removeEventListener('mouseout',out8);
}