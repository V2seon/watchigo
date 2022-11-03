//동영상 업로드
const realUploadvideo1 = document.getElementById('realUploadvideo1');
const uploadvideo1 = document.getElementById('uploadvideo1');
const inv1 = document.getElementById('inv1');

uploadvideo1.addEventListener('click', () => realUploadvideo1.click());

realUploadvideo1.addEventListener("change", function(){
    const file = realUploadvideo1.files[0];
    const videourl = URL.createObjectURL(file);
    inv1.setAttribute("src", videourl);
    document.getElementById('delbtn1').style.display='block';
})

const realUploadvideo2 = document.getElementById('realUploadvideo2');
const uploadvideo2 = document.getElementById('uploadvideo2');
const inv2 = document.getElementById('inv2');

uploadvideo2.addEventListener('click', () => realUploadvideo2.click());

realUploadvideo2.addEventListener("change", function(){
    const file1 = realUploadvideo2.files[0];
    const videourl1 = URL.createObjectURL(file1);
    inv2.setAttribute("src", videourl1);
    document.getElementById('delbtn2').style.display='block';
})


// 이미지 업로드
const realUpload1 = document.getElementById('upimg1');
const upload1 = document.getElementById('uploadimg1');
upload1.addEventListener('click', () => realUpload1.click());
function img1(input){
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
          document.getElementById('img1').src = window.URL.createObjectURL(input.files[0]);
          document.getElementById('delbtn3').style.display='block';
        };
        reader.readAsDataURL(input.files[0]);
      }
}

const realUpload2 = document.getElementById('upimg2');
const upload2 = document.getElementById('uploadimg2');
upload2.addEventListener('click', () => realUpload2.click());
function img2(input){
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
          document.getElementById('img2').src = window.URL.createObjectURL(input.files[0]);
        };
        reader.readAsDataURL(input.files[0]);
      }
}

const realUpload3 = document.getElementById('upimg3');
const upload3 = document.getElementById('uploadimg3');
upload3.addEventListener('click', () => realUpload3.click());
function img3(input){
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
          document.getElementById('img3').src = window.URL.createObjectURL(input.files[0]);
        };
        reader.readAsDataURL(input.files[0]);
      }
}

const realUpload4 = document.getElementById('upimg4');
const upload4 = document.getElementById('uploadimg4');
upload4.addEventListener('click', () => realUpload4.click());
function img4(input){
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
          document.getElementById('img4').src = window.URL.createObjectURL(input.files[0]);
        };
        reader.readAsDataURL(input.files[0]);
      }
}

const realUpload5 = document.getElementById('upimg5');
const upload5 = document.getElementById('uploadimg5');
upload5.addEventListener('click', () => realUpload5.click());
function img5(input){
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
          document.getElementById('img5').src = window.URL.createObjectURL(input.files[0]);
        };
        reader.readAsDataURL(input.files[0]);
      }
}

const realUpload6 = document.getElementById('upimg6');
const upload6 = document.getElementById('uploadimg6');
upload6.addEventListener('click', () => realUpload6.click());
function img6(input){
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
          document.getElementById('img6').src = window.URL.createObjectURL(input.files[0]);
        };
        reader.readAsDataURL(input.files[0]);
      }
}


// 동영상 삭제
function delete1(){
console.log("삭제");
}
