//동영상 업로드
const realUploadvideo1 = document.getElementById('realUploadvideo1');
const uploadvideo1 = document.getElementById('uploadvideo1');
const inv1 = document.getElementById('inv1');

function video1(){
if(realUploadvideo1.files[0] == null){
    realUploadvideo1.click();
    realUploadvideo1.addEventListener("change", function(){
        const file = realUploadvideo1.files[0];
        const videourl = URL.createObjectURL(file);
        document.getElementById('delete1').style.display='block';
        document.getElementById('delbtn1').style.display='block';
        inv1.setAttribute("src", videourl);
        document.getElementById('uploadview').style.display='none';
    })
}
}

const realUploadvideo2 = document.getElementById('realUploadvideo2');
const uploadvideo2 = document.getElementById('uploadvideo2');
const inv2 = document.getElementById('inv2');

function video2(){
    if(realUploadvideo2.files[0] == null){
        realUploadvideo2.click();
        realUploadvideo2.addEventListener("change", function(){
            const file1 = realUploadvideo2.files[0];
            const videourl1 = URL.createObjectURL(file1);
            document.getElementById('delete2').style.display='block';
            document.getElementById('delbtn2').style.display='block';
            inv2.setAttribute("src", videourl1);
            document.getElementById('uploadview2').style.display='none';
        })
    }
}


const aiUploadvideo = document.getElementById('aiUploadvideo');
const aiinv = document.getElementById('aiinv');

function aivideo(){
    if(aiUploadvideo.files[0] == null){
        aiUploadvideo.click();
        aiUploadvideo.addEventListener("change", function(){
            const aifile = aiUploadvideo.files[0];
            const aivideourl = URL.createObjectURL(aifile);
            document.getElementById('aiVdelete').style.display='block';
            document.getElementById('aiVdelbtn').style.display='block';
            aiinv.setAttribute("src", aivideourl);
            aiinv.style.display='flex';
            document.getElementById('aiVview').style.display='none';
        })
    }
}


// 이미지 업로드
const realUpload1 = document.getElementById('upimg1');
const upload1 = document.getElementById('uploadimg1');
const ini1 = document.getElementById('img1');

function img1(){
    if (realUpload1.files[0] == null) {
        realUpload1.click();
        realUpload1.addEventListener("change", function(){
            const file2 = realUpload1.files[0];
            const imgurl2 = URL.createObjectURL(file2);
            document.getElementById('delete3').style.display='block';
            document.getElementById('delbtn3').style.display='block';
            ini1.setAttribute("src", imgurl2);
        })
    }
}

const realUpload2 = document.getElementById('upimg2');
const upload2 = document.getElementById('uploadimg2');
const ini2 = document.getElementById('img2');


function img2(){
    if (realUpload2.files[0] == null) {
        realUpload2.click();
        realUpload2.addEventListener("change", function(){
            const file3 = realUpload2.files[0];
            const imgurl3 = URL.createObjectURL(file3);
            document.getElementById('delete4').style.display='block';
            document.getElementById('delbtn4').style.display='block';
            ini2.setAttribute("src", imgurl3);
        })
    }
}

const realUpload3 = document.getElementById('upimg3');
const upload3 = document.getElementById('uploadimg3');
const ini3 = document.getElementById('img3');

function img3(){
    if (realUpload3.files[0] == null) {
        realUpload3.click();
        realUpload3.addEventListener("change", function(){
            const file4 = realUpload3.files[0];
            const imgurl4 = URL.createObjectURL(file4);
            document.getElementById('delbtn5').style.display='block';
            document.getElementById('delete5').style.display='block';
            ini3.setAttribute("src", imgurl4);
        })
    }
}

const realUpload4 = document.getElementById('upimg4');
const upload4 = document.getElementById('uploadimg4');
const ini4 = document.getElementById('img4');

function img4(){
    if (realUpload4.files[0] == null) {
        realUpload4.click();
        realUpload4.addEventListener("change", function(){
            const file5 = realUpload4.files[0];
            const imgurl5 = URL.createObjectURL(file5);
            document.getElementById('delbtn6').style.display='block';
            document.getElementById('delete6').style.display='block';
            ini4.setAttribute("src", imgurl5);
        })
    }
}

const realUpload5 = document.getElementById('upimg5');
const upload5 = document.getElementById('uploadimg5');
const ini5 = document.getElementById('img5');

function img5(){
    if (realUpload5.files[0] == null) {
        realUpload5.click();
        realUpload5.addEventListener("change", function(){
            const file6 = realUpload5.files[0];
            const imgurl6 = URL.createObjectURL(file6);
            document.getElementById('delbtn7').style.display='block';
            document.getElementById('delete7').style.display='block';
            ini5.setAttribute("src", imgurl6);
        })
    }
}

const realUpload6 = document.getElementById('upimg6');
const upload6 = document.getElementById('uploadimg6');
const ini6 = document.getElementById('img6');

function img6(){
    if (realUpload6.files[0] == null) {
        realUpload6.click();
        realUpload6.addEventListener("change", function(){
            const file7 = realUpload6.files[0];
            const imgurl7 = URL.createObjectURL(file7);
            document.getElementById('delbtn8').style.display='block';
            document.getElementById('delete8').style.display='block';
            ini6.setAttribute("src", imgurl7);
        })
    }
}


// 동영상 삭제
function delete1(){
document.getElementById('realUploadvideo1').value ="";
inv1.src = "";
document.getElementById('delbtn1').style.display='none';
document.getElementById('delete1').style.display='none';
document.getElementById('uploadview').style.display='block';
}

function delete2(){
document.getElementById('realUploadvideo2').value ="";
inv2.src = "";
document.getElementById('delbtn2').style.display='none';
document.getElementById('delete2').style.display='none';
document.getElementById('uploadview2').style.display='block';
}

function aiVdelete(){
document.getElementById('aiUploadvideo').value ="";
aiinv.src = "";
aiinv.style.display='none';
document.getElementById('aiVdelbtn').style.display='none';
document.getElementById('aiVdelete').style.display='none';
document.getElementById('aiVview').style.display='block';
document.getElementById('Vdelcheck').value ="check";
}

// 이미지 삭제
function delete3(){
document.getElementById('upimg1').value ="";
ini1.src = "/img/srinsert/upload.png";
document.getElementById('delbtn3').style.display='none';
document.getElementById('delete3').style.display='none';
}

function delete4(){
document.getElementById('upimg2').value ="";
ini2.src = "/img/srinsert/upload.png";
document.getElementById('delbtn4').style.display='none';
document.getElementById('delete4').style.display='none';
}

function delete5(){
document.getElementById('upimg3').value ="";
ini3.src = "/img/srinsert/upload.png";
document.getElementById('delbtn5').style.display='none';
document.getElementById('delete5').style.display='none';
}

function delete6(){
document.getElementById('upimg4').value ="";
ini4.src = "/img/srinsert/upload.png";
document.getElementById('delbtn6').style.display='none';
document.getElementById('delete6').style.display='none';
}

function delete7(){
document.getElementById('upimg5').value ="";
ini5.src = "/img/srinsert/upload.png";
document.getElementById('delbtn7').style.display='none';
document.getElementById('delete7').style.display='none';
}

function delete8(){
document.getElementById('upimg6').value ="";
ini6.src = "/img/srinsert/upload.png";
document.getElementById('delbtn8').style.display='none';
document.getElementById('delete8').style.display='none';
}

const realUploadvideo3 = document.getElementById('realUploadvideo3');
const uploadvideo3 = document.getElementById('uploadvideo3');
const inv3 = document.getElementById('inv3');

function video3(){
    if(realUploadvideo3.files[0] == null){
        realUploadvideo3.click();
        realUploadvideo3.addEventListener("change", function(){
            const file9 = realUploadvideo3.files[0];
            const videourl9 = URL.createObjectURL(file9);
            document.getElementById('delbtn9').style.display='block';
            document.getElementById('delete9').style.display='block';
            inv3.setAttribute("src", videourl9);
            document.getElementById('uploadview3').style.display='none';
        })
    }
}

function delete9(){
document.getElementById('realUploadvideo3').value ="";
inv3.src = "";
document.getElementById('delbtn9').style.display='none';
document.getElementById('delete9').style.display='none';
document.getElementById('uploadview3').style.display='block';
}