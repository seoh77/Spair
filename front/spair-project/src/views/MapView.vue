<template>
    <div id="map-view">
        <h2>스포츠시설 검색</h2>
        <div class="search_container">
            <input type="text" class="searchInput">
            <button class="searchBtn">검색</button>
        </div>
        <div id="map"></div>
    </div>
</template>

<script setup>
    import { ref, onMounted } from 'vue'

    let map = null

    onMounted(() => {
        if (window.kakao && window.kakao.maps) {
            createMap()
        } else {
            // 실제 지도를 그리는 Javascript API를 불러오기
            const script = document.createElement('script')
            script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${import.meta.env.VITE_KAKAO_JS_KEY}`
            document.head.appendChild(script)
            script.onload = () => kakao.maps.load(createMap)
        }
    });

    // 지도를 띄우는 코드
    const createMap = () => {
        const container = document.getElementById('map')
        const options = {
            center: new kakao.maps.LatLng(33.450701, 126.570667),
            level: 5
        }

        map = new kakao.maps.Map(container, options)
    }
</script>

<style scoped>
    #map-view {
        width: 80vw;
        margin-top: 30px ;
        display: flex;
        flex-direction: column;
    }

    #map-view h2 {
        font-family: 'Tenada';
        font-size: 2rem;
    }

    .search_container {
        width: 100%;
        height: 30px;
        display: flex;
        justify-content: space-between;
        margin-bottom: 30px;
    }

    .searchInput {
        width: 90%;
        height: 100%;
        border: 1px solid black;
        border-radius: 7px;
        padding-left: 10px;
    }

    .searchBtn {
        width: 7%;
        height: 115%;
        background-color: var(--secondary-color);
        border: 1px solid var(--secondary-color);
        border-radius: 5px;
        font-size: 1.3rem;
    }

    #map {
        width: 100%;
        height: 600px;
        background-color: rgb(202, 201, 201);
        margin-bottom: 50px
    }
</style>