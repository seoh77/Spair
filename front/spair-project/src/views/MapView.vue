<template>
    <div id="map-view">
        <h2>스포츠시설 검색</h2>
        <div class="search_container">
            <input type="text" class="searchInput">
            <button class="searchBtn">검색</button>
        </div>
        <KakaoMap width="100%" height="600px" :lat="33.450701" :lng="126.570667" />
    </div>
</template>

<script setup>
    import { ref, onMounted } from 'vue'
    import { KakaoMap, KakaoMapMarker } from 'vue3-kakao-maps'

    const coordinate = {
        lat: 33.450701,
        lng: 126.570667
    };
</script>

<style scoped>
    #map-view {
        width: 80vw;
        margin-top: 30px ;
        display: flex;
        flex-direction: column;
        padding-bottom: 60px;
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
</style>