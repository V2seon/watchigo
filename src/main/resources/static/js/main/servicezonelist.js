function searching(){

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
        url: "/servicezone_search",
        data: querydata,
        type:"POST",
    }).done(function (fragment) {
        $("#intable").replaceWith(fragment);
        console.log(fragment);
    });

}

function enterkey(){
    if(window.event.keyCode == 13){
        searching();
    }
}

