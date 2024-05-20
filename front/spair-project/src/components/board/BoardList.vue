<!-- 우리 동네 게시판 게시글 리스트-->
<template>
    <BoardSearchFilter @apply-filters="filteredBoard" v-if="$route.path == '/board'"/>
    <div id="list-container">
        <table>
            <thead>
                <tr>
                    <th>번호</th>
                    <th id="title">제목</th>
                    <th>작성자</th>
                    <th>작성시각</th>
                    <th>모집상태</th>
                </tr>
            </thead>
            <tbody v-if="!filteredBoardList.length && !isSearch">
                <tr v-for="(board, index) in boardListInit" :key="board.id">
                    <td>{{ index + 1 }}</td>
                    <td >
                        <RouterLink :to="`/board/${board.postId}`">{{ board.title }}</RouterLink>
                    </td>
                    <td>{{  board.user.nickname }}</td>
                    <td>{{  board.createdDate }}</td>
                    <td :class="{ 'red': board.status === 1 }">{{  board.status ? '모집중' : '모집완료' }}</td>
                </tr>
            </tbody>
            <tbody v-else-if="filteredBoardList.length && isSearch">
                <tr v-for="(board, index) in filteredBoardList" :key="board.id">
                    <td>{{ index + 1 }}</td>
                    <td >
                        <RouterLink :to="`/board/${board.postId}`">{{ board.title }}</RouterLink>
                    </td>
                    <td>{{  board.user.nickname }}</td>
                    <td>{{  board.createdDate }}</td>
                    <td :class="{ 'red': board.status === 1 }">{{  board.status ? '모집중' : '모집완료' }}</td>
                </tr>
            </tbody>
            <tbody v-else>
                <tr>
                    <td colspan="5">
                        게시글이 없습니다.
                    </td>
                </tr>
            </tbody>
        </table>
        
    </div>
</template>

<script setup>
import BoardSearchFilter from '@/components/board/BoardSearchFilter.vue';
import { useBoardStore } from '@/stores/board';
import { onMounted,ref, watch } from 'vue';
import { useRoute } from 'vue-router';
import axios from 'axios'
const store = useBoardStore();
const route = useRoute()

const filteredBoardList = ref([])
const isSearch = ref(false)
const boardListInit = ref([])


// 첫 진입시 전체 리스트 반환
onMounted(() => {
    const currentPath = window.location.pathname;
    
    if (route.query.exerciseType) {
        const exerciseType = route.query.exerciseType
        store.filteredBoard( exerciseType ).then(() => {
            boardListInit.value = store.filteredBoardList
        })
    } 
    else {
        // home화면일 때는 전체(전국) 게시판이 호출
        if (currentPath === '/') {
            store.getBoardListTotal().then(data => {
                boardListInit.value = data;
            })

        // 통합검색 화면일 때는 keyword를 포함하는 게시물 리스트가 호출
        } else if ( currentPath === '/board/search'){
            // watch를 추가하여 검색어 변경을 감시하고, 변경될 때마다 API를 호출
            watch(() => route.query.search, async (newSearchQuery) => {
                await store.getBoardListSearch(newSearchQuery).then( data => {
                    boardListInit.value = data;
                });
            });

            // 초기에도 검색어가 있을 수 있으므로 초기 검색어에 대해 API 호출
            const initialSearchQuery = route.query.search;
            if (initialSearchQuery) {
                store.getBoardListSearch(initialSearchQuery).then(data => {
                    boardListInit.value = data;
                });
            }

        // '/board'일 경우에는 우리동네 게시판이 호출
        } else if ( currentPath === '/board' ) {
            store.getBoardList().then(data => {
                boardListInit.value = data;
            })
        
        // 그 외 경로일때는 스포츠센터 게시판 호출
        } else {
            const pathArr = currentPath.split('/')
            const centerId = pathArr[pathArr.length-1]
            
            axios.get(`http://localhost:8080/api/search/center/${centerId}`)
            .then((response) => {
                boardListInit.value = response.data
            })
        }
    }
})


// 검색필터를 걸었을 때 조건에 맞는 리스트 반환
const filteredBoard = (filter) => {
    isSearch.value = true;
    const userInfoStr = localStorage.getItem('loginUserInfo')
    const userIdInfo = JSON.parse(userInfoStr)
    const userId = userIdInfo.userId
   
    axios.get(`http://localhost:8080/api/search/town?userId=${userId}`, {
        params: filter
    }).then(response => {
        filteredBoardList.value = response.data
    })
}


// 우리동네 필라테스, 헬스장 바로가기 연결시 조건에 맞는 리스트 반환
// watch함수로 쿼리 상태 감지 후 반영
watch(() => route.query.exerciseType, (exerciseType) => {
    if(exerciseType){
        store.filteredBoard({exerciseType}.then(() => {
            boardListInit.value = store.filteredBoardList
        }))
    }
})
</script>

<style scoped>
#list-container {
    width: 75%;
    max-width: 1200px;
    min-width: 1000px;
    display:flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    font-family: 'Tenada';
    margin: 2rem;
    border: 0.1rem solid var(--gray-color);
    box-shadow: 0.1rem 0.5rem 0.5rem var(--gray-color);
}
table {
    width: 90%;
    margin: 3rem 0;
    table-layout: fixed;
}
thead, tbody {
    text-align: center;
    font-size: 1.5rem;
}
td {
    font-size: 1.2rem;
    font-family: 'NanumSquareRound';
    border-bottom: 0.1rem dotted var(--gray-color);
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    padding: 0.7rem;
}
#title {
    width: 50%;
}
.red {
    color: red;
}
/* .gray {
    color: #000000;
    font-size: 1.2rem;
} */
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
    color: var(--primary-color);
}
a:active {
    color: var(--primary-color);
}
</style>