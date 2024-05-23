<template>
    <div id="map-view">
        <h2>게시글이 등록된 스포츠센터</h2>
        <div>
            <KakaoMap width="100%" height="70vh" :lat="coordinate.lat" :lng="coordinate.lng" :markerList="markerList" @onLoadKakaoMap="onLoadKakaoMap"/>
            <div id="center-board" v-if="selectList">
                <template v-for="board in selectList" :key="board.postId">
                    <div class="item_wrap">
                        <div class="item_content">
                            <h4 class="item_title">
                                <RouterLink :to="{name: 'boardDetail', params: {'postId' : board.postId }}">{{ board.title }}</RouterLink>
                            </h4>
                            <div class="item_writer">{{ board.user.nickname }}</div>
                            <div class="item_date">{{ board.createdDate.substr(0, 10) }}</div>
                        </div>
                        <div class="item_status">
                            <div :class="{ 'trueStatus': board.status === 1 }">{{ board.status ? '모집중' : '모집완료'}}</div>
                        </div>
                    </div>
                </template>
            </div>
        </div>
    </div>
</template>

<script setup>
    import axios from 'axios';
    import { ref, onMounted } from 'vue'
    import { KakaoMap, KakaoMapMarker } from 'vue3-kakao-maps'
    import router from '@/router';

    const map = ref()
    const markerList = ref([])
    const selectList = ref()

    const coordinate = ref({
        lat: 37.501712,
        lng: 127.039603
    })

    onMounted(async() => {
        const localStorageData = JSON.parse(localStorage.getItem("loginUserInfo")) 
        const userId = localStorageData.userId

        axios.get(`http://localhost:8080/api/user/search/${userId}`)
        .then((response) => {
            coordinate.value.lat = response.data.latitude
            coordinate.value.lng = response.data.longitude
        })

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

            // 클릭한 위치의 반경 50m 이내에 있는 스포츠 센터 게시글 출력
            for(let i=0; i<markerList.value.length; i++) {
                const distance = getDistanceFromLatLonInMeters(lat, lng, markerList.value[i].lat, markerList.value[i].lng) 

                if(distance <= 50) {
                    const centerId = markerList.value[i].key

                    axios.get(`http://localhost:8080/api/search/center/${centerId}`)
                    .then((response) => {
                        selectList.value = response.data
                    })
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
        width: 100%;
        margin-top: 30px ;
        display: flex;
        flex-direction: column;
        align-items: center;
        margin-bottom: 50px ;
    }

    #map-view h2 {
        font-family: 'Tenada';
        font-size: 2rem;
    }

    #map-view > div {
        width: 80%;
        min-height: 600px;
        display: flex ;
        position: relative;
        min-width: 1000px;
    }

    #center-board {
        width: 30%;
        height: 70vh;
        position: absolute;
        top: 0 ;
        right: 0;
        z-index: 100;
        background-color: rgba(177, 177, 177, 0.3) ;
        display: flex;
        flex-direction: column ;
        align-items: center ;
        padding: 5px;
        box-sizing: border-box ;
    }

    .item_wrap {
        width: 100%;
        height: 100px ;
        border-bottom: 1px solid var(--gray-color);
        display: flex ;
        justify-content: space-between;
        padding: 10px;
        box-sizing: border-box;
        background-color: #fffbf4 ;
        border-radius: 10px ;
        margin-bottom: 7px ;
        font-family: 'NanumSquareRound';
    }

    .item_content {
        width: 70%;
        display: flex ;
        flex-direction: column;
        justify-content: space-around ;
    }

    .item_title {
        margin: 0 ;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

    .item_status {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 25%;
    }

    .item_status div {
        width: 100%;
        height: 35px ;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .trueStatus {
        background-color: var(--primary-color) ;
        color: white ;
        border-radius: 10px;     
    }

    .item_date {
        font-size: 14px ;
        font-weight: 500 ;
        color: rgb(153, 152, 152);
    }

    a {
        text-decoration: none;
    }

    a:link {
        color: #000000;
    }

    a:visited {
        color: #000000;
    }

    a:hover {
        color: var(--sub-color);
    }

    a:active {
        color: var(--sub-color);
    }
</style>