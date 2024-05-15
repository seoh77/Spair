<template>
    <BoardSearchFilter @apply-filters="filterBoardList" v-if="$route.path == '/board'"/>
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
                    <td>{{ board.id }}</td>
                    <td>
                        <RouterLink :to="`/board/${board.id}`">{{ board.title }}</RouterLink>
                    </td>
                    <td>{{  board.writer }}</td>
                    <td>{{  board.created_date }}</td>
                    <td>{{  board.status ? '모집중' : '모집완료' }}</td>
                </tr>
            </tbody>
        </table>
        
    </div>
</template>

<script setup>
import BoardSearchFilter from '@/components/board/BoardSearchFilter.vue';
import { useBoardStore } from '@/stores/board';
const store = useBoardStore();

// 임시 메소드. fake 구현. API 연결 후 삭제 및 새롭게 생성 예정
const filterBoardList = (filters) => {
  const { status, gender, exerciseType, minPrice, maxPrice } = filters;
  const filteredList = [];

  store.boardList.forEach(board => {
    if (
      (status === '' || board.status === parseInt(status)) &&
      (gender === '' || board.gender === parseInt(gender)) &&
      (exerciseType === '' || board.exerciseType === exerciseType) &&
      (minPrice === '' || board.price >= parseInt(minPrice)) &&
      (maxPrice === '' || board.price <= parseInt(maxPrice))
    ) {
      filteredList.push(board);
    }
  });
};

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