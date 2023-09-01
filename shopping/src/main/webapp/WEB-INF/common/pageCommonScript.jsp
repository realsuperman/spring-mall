<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
    var pageSize = 16; // 한개의 페이지당 나오는 row수
    var totalRow = 0; // 전체 로우 수 (해당값은 구현하는쪽에서 이 값을 넣어줘야함 전체 row의 수임)
    var pagesPerGroup = 5; // 페이지 개수
    var currentGroup = 0; // 현재 페이지 그룹 -> 검색이나 조회시 초기화 필요

    $(document).ready(function() {
        /*
            동적으로 html 태그를 그릴 경우 이런식으로 이벤트 위임으로 해결해야함
            만약 nextPage나 prevPage 버튼 눌렀을 때 해당 그룹의 첫 페이지를 요청하게 하고 싶은 경우 메소드 변경필요
        */
        $(document).on("click", "#nextPageButton", function() {
            if((currentGroup+1)*pagesPerGroup*pageSize<=totalRow){
                currentGroup++;
                displayPageNumbers();
            }
        });


        $(document).on("click", "#previousPageButton", function() {
            if (currentGroup > 0) {
                currentGroup--;
                displayPageNumbers();
            }
        });
    });

    function displayPageNumbers() { // 페이지네이션을 그리는 함수(검색이나 조회시 호출하면 됨)
        let pagination = $(".pagination");
        pagination.empty();

        // Previous 아이템 추가
        pagination.append('<li class="page-item"><a class="page-link" id="previousPageButton">Previous</a></li>');

        let startPage = currentGroup * pagesPerGroup + 1;
        let currentPageCnt = ( (currentGroup * pagesPerGroup + 1)*pageSize) - pageSize;

        let i = 0;
        while(i++<5 && currentPageCnt<totalRow){
            // 사용하는쪽에서 moveAnotherPage 메소드를 구현해야함 해당 메소드에는 클릭한 페이지 번호를 넘겨줌 (자세한건 stockAndStat 참조)
            pagination.append('<li class="page-item"><a class="page-link" onclick="moveAnotherPage(' + startPage + ')">' + startPage + '</a></li>');
            startPage++;
            currentPageCnt += pageSize;
        }

        // Next 아이템 추가
        pagination.append('<li class="page-item"><a class="page-link" id="nextPageButton">Next</a></li>');
    }

</script>