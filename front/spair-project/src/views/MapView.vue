<template>
    <div id="map-view">
        <h2>게시글이 등록된 스포츠센터</h2>
        <KakaoMap width="100%" height="600px" :lat="37.501712" :lng="127.039603" :markerList="markerList" @onLoadKakaoMap="onLoadKakaoMap"/>
    </div>
</template>

<script setup>
    import axios from 'axios';
    import { ref, onMounted } from 'vue'
    import { KakaoMap, KakaoMapMarker } from 'vue3-kakao-maps'

    const map = ref()
    const markerList = ref([])

    onMounted(async() => {
        const response = await axios.get('http://localhost:8080/api/board')

        const boardList = response.data 

        boardList.map((board) => {
            markerList.value.push({
                "key" : board.sportsCenter.centerId,
                "lat" : board.sportsCenter.latitude,
                "lng" : board.sportsCenter.longitude
            })
        })
    })

    const onLoadKakaoMap = (mapRef) => {
        map.value = mapRef

        kakao.maps.event.addListener(map.value, 'click', function(mouseEvent) {
            // 클릭한 위치의 위도, 경도 값 가져오기
            const latlng = mouseEvent.latLng
            const lat = latlng.getLat()
            const lng = latlng.getLng()

            // 클릭한 위치의 반경 10m 이내에 있는 스포츠 센터 게시글 출력
            for(let i=0; i<markerList.value.length; i++) {
                const distance = getDistanceFromLatLonInMeters(lat, lng, markerList.value[i].lat, markerList.value[i].lng) 

                if(distance <= 10) {
                    console.log(markerList.value[i].key)        // centerId 출력 -> 추후 해당 값을 param으로 하여 url mapping
                }
            }
        })
    }

    // 두 지점 사이의 거리를 계산
    const getDistanceFromLatLonInMeters = (lat1, lng1, lat2, lng2) => {
        const R = 6371e3; // 지구의 반지름 (미터 단위)
        const dLat = (lat2 - lat1) * Math.PI / 180;
        const dLng = (lng2 - lng1) * Math.PI / 180;
        const a =
            Math.sin(dLat / 2) * Math.sin(dLat / 2) +
            Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
            Math.sin(dLng / 2) * Math.sin(dLng / 2);
        const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        const distance = R * c; // 미터 단위 거리
        return distance;
    }
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