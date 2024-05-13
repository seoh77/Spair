<template>
    <div id="list-container">
        <table>
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>작성시각</th>
                    <th>모집상태</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="board in store.boardList" :key="board.id">
                    <!-- 여기에 camelcase로 쓴 이유? 자바에서 데이터 넘겨줄 때 카멜케이스로 줬기 때문-->
                    <td>{{ board.id }}</td>
                    <!-- <td>
                        <RouterLink :to="`/board/${board.id}`">{{ board.title }}</RouterLink>
                    </td> -->
                    <td>{{  board.title }}</td>
                    <td>{{  board.writer }}</td>
                    <td>{{  board.regDate }}</td>
                    <td>{{  board.status }}</td>
                </tr>
            </tbody>
        </table>
        
    </div>
</template>

<script setup>
import { useBoardStore } from '@/stores/board';
const store = useBoardStore();

</script>

<style scoped>
#list-container {
    width: 75%;
    max-width: 1200px;
    min-width: 1000px;
    display:flex;
    justify-content: center;
    align-items: center;
    font-family: 'Tenada';
    margin: 2rem;
    border: 0.1rem solid var(--gray-color);
    box-shadow: 0.1rem 0.5rem 0.5rem var(--gray-color);
}
table {
    width: 90%;
    height: 200px;
    margin: 3rem 0;
    table-layout: fixed;
}
thead, tbody {
    text-align: center;
    font-size: 1.5rem;
}
td {
    font-size: 1.3rem;
    font-family: 'NanumSquareRound';
    border-bottom: 0.1rem dotted var(--primary-color);
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
</style>