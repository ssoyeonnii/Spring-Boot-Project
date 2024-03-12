let i = parseInt(document.getElementById('idxValue').getAttribute('data-idx'));

$.ajax({
    url: "/main/hotPlaceJson",
    type: "POST",
    success: function (data) {
        const body = $("#body");
        let tag ="";
        if(data.length>0){

        }
        tag += `
                            <div class="container pb-5 text-center mt-5 my-auto">
                                <h1 id="title" style="font-size: 75px; font-weight: bold">${data[i].main_TITLE}</h1>
                            </div>
                            <div class="container" style="border: 1px solid #ddd; border-radius: 8px; padding: 20px; font-size: larger; background: white">
                                <div class="container mb-5" style="text-align: center; display: flex; justify-content: center; align-items: center;">
                                    <img src="${data[i].main_IMG_NORMAL}" style="width: 900px; height: 400px;">
                                </div>
                                <div class="container" style="padding-left: 100px; padding-right: 100px">
                                    <div class="pb-3">
                                        <label for="addr" class="rounded-2 mb-2"><img src="../sources/addr.png" style="width: 20px; height: 20px" class="mx-2">주소</label>
                                        <p id="addr" style="text-indent: 30px;">${data[i].addr1}</p>
                                    </div>
                                    <div class="pb-3">
                                        <label for="itemcntnts" class="rounded-2 mb-2"><img src="../sources/check.png" style="width: 20px; height: 20px" class="mx-2">소개</label>
                                        <p id="itemcntnts" style="padding-left: 30px;">${data[i].itemcntnts}</p>
                                    </div>
                                    <div class="pb-3">
                                        <label for="rprsntv_MENU" class="rounded-2 mb-2"><img src="../sources/cart.png" style="width: 20px; height: 20px" class="mx-2">메뉴</label>
                                        <p id="rprsntv_MENU" style="text-indent: 30px;">${data[i].rprsntv_MENU}</p>
                                    </div>
                                    <div class="pb-3">
                                        <label for="cntct_TEL" class="rounded-2 mb-2"><img src="../sources/tel.png" style="width: 20px; height: 20px" class="mx-2">문의</label>
                                        <p id="cntct_TEL" style="text-indent: 30px;">${data[i].cntct_TEL}</p>
                                    </div>
                                    <div class="pb-3">
                                        <label for="usage_DAY_WEEK_AND_TIME" class="rounded-2 mb-2"><img src="../sources/time.png" style="width: 20px; height: 20px" class="mx-2">운영시간</label>
                                        <p id="usage_DAY_WEEK_AND_TIME" style="text-indent: 30px;">${data[i].usage_DAY_WEEK_AND_TIME}</p>
                                    </div>
                                </div>
                            </div>`;
        body.append(tag);
        myMap(data);
        addMarker(data);
    },
    error: function () {
        alert("통신 중 오류가 발생했습니다.");
    }
});
function myMap(data) {
    var mapOptions = {
        center: new google.maps.LatLng(data[i].lat,data[i].lng),
        zoom: 15
    };

    map = new google.maps.Map(
        document.getElementById("googleMap"),
        mapOptions);

}
function addMarker(data) {
    var marker = new google.maps.Marker({
        position: new google.maps.LatLng(data[i].lat, data[i].lng),
        map: map,
        title: data[i].main_TITLE
    });

    markers.push(marker);
}

$(document).ready(function () {
    document.getElementById('showFormBtn').addEventListener('click', function () {
        var formContainer = document.getElementById('reviewFormContainer');
        formContainer.classList.toggle('hide');
    });
})



$.ajax({
    url: "/review/list",
    type: "GET",
    data: { reviewStoreIdx: i },
    success: function (reviewLists) {
        const reviewList = $("#reviewList");
        let reviewTag = "";

        if (reviewLists.length > 0) {
            for (let j = 0; j < reviewLists.length; j++) {
                reviewTag += `<div class="border-bottom p-3">
                    <div class="mb-3">
                        <img src="../sources/person.png" style="width: 25px; height: 25px; margin-bottom: 10px;">
                        <span style="font-size: 25px; padding-left: 5px;">${reviewLists[j].reviewUserId}</span>
                        <span style="padding-left: 150px;">${reviewLists[j].reviewCreateDate}</span>
                    </div>
                    <div style="padding-left: 30px; padding-bottom: 10px;">
                        <span>${reviewLists[j].reviewContent}</span>
                    </div>
                    <div style="padding: 50px;">`;

                // 이미지 파일 경로 사용
                const imagePaths = reviewLists[j].reviewImagePaths;
                if (imagePaths && imagePaths.length > 0) {
                    for (let k = 0; k < imagePaths.length; k++) {
                        // 이미지를 동적으로 추가
                        reviewTag += `<img src="${imagePaths[k].fileReviewStoredfileName}" style="width: 300px; height:300px;" class="mt-3">`;
                    }
                }

                reviewTag += `</div></div>`;
            }
        }
        else{
            reviewTag += `<h4 class="mt-3 text-center">작성된 리뷰가 없습니다.</h4>`;
        }
        reviewList.append(reviewTag);
    },
    error: function () {
        alert("리뷰리스트 로딩 중 오류가 발생했습니다.");
    },
});