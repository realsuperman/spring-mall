<%@include file="./commonScript.jsp" %>
<script>
    var mainCategory = new Map(); // 대분류
    var subCategory = new Map(); // 중분류+소분류
    var statusMap = new Map();

    $(document).ready(function(){
        executeAjax('/categories','GET',false,settingCategory);
        executeAjax('/status','GET',false,settingStatus);
    });

    function settingCategory(result){
        createMap(result.largeCategory,mainCategory,true);
        createMap(result.middleCategory,subCategory,true);
    }

    function settingStatus(result){
        createMap(result.mainStatus,statusMap,false);
    }

    function createMap(data, resultMap, isSpecialKey){
        let entries = data.match(/\d+;[^=]+=\[[^\]]+\]/g);

        entries.forEach((entry) => {
            let [keyPart, valuePart] = entry.split('=');
            let [setId, setName] = keyPart.split(';');

            let itemStrings = valuePart.slice(1, -1).split(', ');
            let itemArray = itemStrings.map((itemString) => {
                let [itemId, itemName] = itemString.split(';');
                return itemId + ';' + itemName;
            });

            if(isSpecialKey){
                resultMap.set(setId + ';' + setName, itemArray);
            }else{
                resultMap.set(setName, itemArray);
            }
        });
    }

    /**
     *  if(categoryId == null) -> largeCategory
     *  else -> 특정 카테고리의 자식들(카테고리아이디;카테고리명)형식으로 List반환
     *  return 값은 key와 value의 객체 형태
     */
    function getCategories(categoryId){
        let categories = [];
        let map = mainCategory;

        if (categoryId != null) { // 대분류 조회
            map = subCategory.get(categoryId);
            if(map==null){
                map = mainCategory.get(categoryId);
            }
        }

        map.forEach((value, key) => {
            categories.push({ key, value });
        });

        return categories;
    }

    function getStatus(statusId){
        let status = [];
        let map = statusMap.get(statusId);

        map.forEach((value, key) => {
            status.push({ key, value });
        });

        return status;
    }
</script>