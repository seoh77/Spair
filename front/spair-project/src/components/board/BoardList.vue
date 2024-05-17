<template>
    <!-- <BoardSearchFilter @apply-filters="filterBoardList" v-if="$route.path == '/board'"/> -->
    <BoardSearchFilter v-if="$route.path == '/board'"/>
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
            <tbody>
                <tr v-for="board in store.boardList" :key="board.id">
                    <td>{{ board.postId }}</td>
                    <td >
                        <RouterLink :to="`/board/${board.postId}`">{{ board.title }}</RouterLink>
                    </td>
                    <td>{{  board.user.nickname }}</td>
                    <td>{{  board.createdDate }}</td>
                    <td :class="{ 'red': board.status === 1 }">{{  board.status ? '모집중' : '모집완료' }}</td>
                </tr>
            </tbody>
        </table>
        
    </div>
</template>

<script setup>
import BoardSearchFilter from '@/components/board/BoardSearchFilter.vue';
import { useBoardStore } from '@/stores/board';
import { onMounted } from 'vue';

const store = useBoardStore();
onMounted(() => {
    store.getBoardList()
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